@file:OptIn(
    kotlinx.coroutines.InternalCoroutinesApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package io.ygdrasil.webgpu.internal

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicReference
import kotlin.coroutines.coroutineContext

/**
 * Owns the per-device event-pumping coroutine and coordinates native device lifetime.
 *
 * WebGPU callbacks configured with an event-driven callback mode require the application to pump
 * native events. This class centralizes that work for one device instead of polling from the render
 * thread or starting one polling loop per operation. Registered operations receive a [Ticket] and
 * declare whether they actually require [poll]:
 *
 * - tickets with `needsPolling = true` keep the polling loop active;
 * - tickets with `needsPolling = false` are still tracked for termination and native lifetime, but
 *   do not cause polling by themselves; and
 * - when no ticket needs polling, the loop suspends on a conflated wake-up channel instead of
 *   spinning.
 *
 * The pump retains one native device reference during construction. That reference is released
 * exactly once after all three shutdown conditions hold: the pump is closed, no protected native
 * call is active, and the pump's coroutine scope has completed. This prevents device destruction
 * while a downcall is crossing the native boundary or while the polling coroutine may still use
 * the device.
 *
 * Registration, removal, native-call admission, and termination are serialized through an
 * immutable [State] in an [AtomicReference]. The resulting guarantees are:
 *
 * - no operation is admitted after termination;
 * - every operation pending when termination wins receives the same terminal cause;
 * - a [Ticket] can protect a native call only while its registration is current;
 * - polling failures terminate pending operations instead of leaving them suspended; and
 * - failures from termination callbacks, [release], or the exception reporter cannot prevent the
 *   remaining cleanup steps.
 *
 * [close] and ticket closure are idempotent. Termination callbacks execute synchronously on the
 * thread that terminates the pump; each callback is isolated from the others by [runSafely].
 *
 * @param dispatcher dispatcher used by the polling loop and jobs started with [launch]. It should
 * not be the render thread when [poll] may block or perform non-trivial work.
 * @param poll processes pending native device events once.
 * @param retain retains the native device before background work starts.
 * @param release releases the retained native device reference after shutdown becomes quiescent.
 * @param pollDelayMillis delay between consecutive polls while at least one operation needs them.
 * @param reportException terminal sink for cleanup and callback failures. When omitted, failures
 * are delegated to the coroutine context's exception handling.
 */
internal class DeviceEventPump(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val poll: () -> Unit,
    retain: () -> Unit,
    private val release: () -> Unit,
    private val pollDelayMillis: Long = 1L,
    reportException: ((Throwable) -> Unit)? = null,
) : AutoCloseable {
    /**
     * Per-operation data retained until ticket closure, successful completion, or pump termination.
     *
     * @property needsPolling whether this operation requires periodic calls to [poll].
     * @property onTerminated callback used to fail the operation if the device closes or polling
     * fails before normal completion.
     */
    private data class Pending(
        val needsPolling: Boolean,
        val onTerminated: (Throwable) -> Unit,
    )

    /**
     * Atomically published pump state.
     *
     * @property closed whether termination has claimed the pump. A closed state has no [pending]
     * operations and rejects new registrations/native calls.
     * @property nextId monotonically increasing identity assigned to the next ticket.
     * @property pending operations currently owned by their tickets, indexed by ticket identity.
     * @property activeNativeCalls calls admitted by [beginNativeCall] that have not reached
     * [endNativeCall]. These calls postpone native device release.
     */
    private data class State(
        val closed: Boolean = false,
        val nextId: Long = 1L,
        val pending: Map<Long, Pending> = emptyMap(),
        val activeNativeCalls: Int = 0,
    )

    private val state = AtomicReference(State())
    private val released = AtomicBoolean(false)
    private val wakeUp = Channel<Unit>(Channel.CONFLATED)
    private val scopeJob = SupervisorJob()
    private val scope = CoroutineScope(scopeJob + dispatcher)
    private val reportException: (Throwable) -> Unit = reportException ?: {
        handleCoroutineException(scope.coroutineContext, it)
    }

    /** Retains the device, installs the final release hook, and starts the single polling loop. */
    init {
        retain()
        scopeJob.invokeOnCompletion { releaseIfQuiescent() }
        scope.launch { pollLoop() }
    }

    /**
     * Registers an operation that must be failed if this device pump terminates.
     *
     * A registration with `needsPolling = false` participates in termination and native lifetime
     * protection but leaves the polling loop idle unless another registration needs polling. A
     * successful registration signals [wakeUp], allowing the suspended loop to re-evaluate demand.
     *
     * @param needsPolling whether this operation requires periodic native event processing.
     * @param onTerminated fails the operation when device closure or a polling failure wins.
     * @return a ticket for the registered operation, or `null` when the pump is already closed.
     */
    internal fun tryRegister(
        needsPolling: Boolean,
        onTerminated: (Throwable) -> Unit,
    ): Ticket? {
        while (true) {
            val current = state.load()
            if (current.closed) return null
            val id = current.nextId
            val updated = current.copy(
                nextId = id + 1,
                pending = current.pending + (id to Pending(needsPolling, onTerminated)),
            )
            if (state.compareAndSet(current, updated)) {
                wakeUp.trySend(Unit)
                return Ticket(this, id)
            }
        }
    }

    /**
     * Launches [block] in the device-owned coroutine scope.
     *
     * Closure is checked both before and after launch. The second check closes the race in which
     * termination happens between the first check and job creation; in that case the new job is
     * cancelled immediately.
     *
     * @return the launched [Job], or `null` when the pump was already closed before launch.
     */
    internal fun launch(block: suspend () -> Unit): Job? {
        if (state.load().closed) return null
        val job = scope.launch { block() }
        if (state.load().closed) job.cancel()
        return job
    }

    /**
     * Terminates the pump with a standard device-closed failure.
     *
     * The first close performs termination; subsequent calls are no-ops.
     */
    override fun close() {
        terminate(IllegalStateException("Device is closed"))
    }

    /**
     * Polls native events only while at least one current operation requires polling.
     *
     * [wakeUp] is conflated because state, not channel element count, is the source of truth. One
     * signal is sufficient to make the loop reload [state]. A thrown polling failure is wrapped to
     * identify the event pump as the failing subsystem and terminates all pending operations.
     */
    private suspend fun pollLoop() {
        while (coroutineContext.isActive) {
            val current = state.load()
            if (current.closed) return
            if (current.pending.values.none(Pending::needsPolling)) {
                if (wakeUp.receiveCatching().getOrNull() == null) return
                continue
            }
            try {
                poll()
            } catch (failure: Throwable) {
                terminate(IllegalStateException("Device event pump failed", failure))
                return
            }
            delay(pollDelayMillis)
        }
    }

    /**
     * Atomically closes the pump and delivers [cause] to all operations it claims.
     *
     * Clearing [State.pending] in the same transition that sets [State.closed] makes termination
     * the single winner against ticket removal and native-call admission. Callbacks run after the
     * state transition and are isolated so one throwing callback cannot block the others. Closing
     * the wake-up channel and cancelling the scope then allow [releaseIfQuiescent] to release the
     * retained device once active native calls have also returned.
     */
    private fun terminate(cause: Throwable) {
        var callbacks: List<(Throwable) -> Unit> = emptyList()
        while (true) {
            val current = state.load()
            if (current.closed) return
            callbacks = current.pending.values.map(Pending::onTerminated)
            if (state.compareAndSet(current, current.copy(closed = true, pending = emptyMap()))) break
        }
        callbacks.forEach { callback -> runSafely { callback(cause) } }
        wakeUp.close()
        scope.cancel()
        releaseIfQuiescent()
    }

    /** Executes [block] without allowing its failure to interrupt terminal cleanup. */
    private fun runSafely(block: () -> Unit) {
        try {
            block()
        } catch (error: Throwable) {
            reportSafely(error)
        }
    }

    /**
     * Reports a cleanup failure to the configured terminal sink.
     *
     * Reporter failures are deliberately swallowed: reporting them recursively would either loop
     * or deliver the same terminal failure more than once.
     */
    private fun reportSafely(error: Throwable) {
        try {
            reportException(error)
        } catch (_: Throwable) {
            // A reporter is a terminal sink: retrying it would recurse or report twice.
        }
    }

    /** Removes [id] if it is still pending and wakes the loop to re-evaluate polling demand. */
    private fun remove(id: Long) {
        while (true) {
            val current = state.load()
            if (id !in current.pending) return
            if (state.compareAndSet(current, current.copy(pending = current.pending - id))) {
                wakeUp.trySend(Unit)
                return
            }
        }
    }

    /**
     * Admits a native call for [id] and increments the device-release barrier.
     *
     * Admission fails if termination has started or the ticket no longer owns a registration.
     */
    private fun beginNativeCall(id: Long): Boolean {
        while (true) {
            val current = state.load()
            if (current.closed || id !in current.pending) return false
            if (state.compareAndSet(current, current.copy(activeNativeCalls = current.activeNativeCalls + 1))) {
                return true
            }
        }
    }

    /** Completes one admitted native call and re-evaluates deferred device release. */
    private fun endNativeCall() {
        while (true) {
            val current = state.load()
            check(current.activeNativeCalls > 0)
            if (state.compareAndSet(current, current.copy(activeNativeCalls = current.activeNativeCalls - 1))) {
                releaseIfQuiescent()
                return
            }
        }
    }

    /**
     * Releases the retained device after termination, native-call quiescence, and scope completion.
     *
     * The independent [released] flag arbitrates calls arriving from termination, the scope
     * completion hook, and the last native call. [release] is protected by [runSafely], so a native
     * release failure is reported once and cannot escape a cleanup path.
     */
    private fun releaseIfQuiescent() {
        val current = state.load()
        if (current.closed && current.activeNativeCalls == 0 && scopeJob.isCompleted) {
            if (released.compareAndSet(expectedValue = false, newValue = true)) runSafely(release)
        }
    }

    /**
     * Capability representing one operation registered with this device pump.
     *
     * Ticket identity prevents stale cleanup from affecting another operation. Closing a ticket
     * only unregisters that operation; it does not close the pump. Calls remain safe after ticket
     * closure or pump termination and simply fail admission.
     */
    internal class Ticket internal constructor(
        private val owner: DeviceEventPump,
        private val id: Long,
    ) : AutoCloseable {
        private val closed = AtomicBoolean(false)

        /**
         * Runs [block] while the retained device is protected from release.
         *
         * @return `true` when the ticket was current and [block] was entered, or `false` after
         * ticket removal/pump termination. Exceptions from [block] propagate, while the active-call
         * counter is always decremented in `finally`.
         */
        internal fun runNativeCall(block: () -> Unit): Boolean {
            if (!owner.beginNativeCall(id)) return false
            return try {
                block()
                true
            } finally {
                owner.endNativeCall()
            }
        }

        /** Idempotently unregisters this operation and updates polling demand. */
        override fun close() {
            if (closed.compareAndSet(expectedValue = false, newValue = true)) owner.remove(id)
        }
    }
}

/**
 * Runs synchronous resource creation in the device-owned scope and exposes its outcome as [Result].
 *
 * The task registers with `needsPolling = false`: it needs device lifetime and termination
 * tracking, but resource creation itself does not require event pumping. [Ticket.runNativeCall]
 * prevents the retained device reference from being released while [create] is executing.
 *
 * [NativeCallbackOperation] arbitrates normal completion, caller cancellation, and device
 * termination. If [create] produces a value after another terminal path has already won, [dispose]
 * is invoked exactly once rather than leaking the late resource. Exceptions from [create] are
 * returned as failed results.
 *
 * @param create synchronous resource construction performed on the pump dispatcher.
 * @param dispose releases a successfully created value that cannot be delivered to the caller.
 * @return a successful result containing the created resource, or a failed result for creation,
 * launch rejection, or device termination. Caller cancellation remains coroutine cancellation;
 * any value produced after it is disposed instead of being returned.
 */
internal suspend fun <T> DeviceEventPump.runResourceTask(
    create: () -> T,
    dispose: (T) -> Unit,
): Result<T> = suspendCancellableCoroutine { continuation ->
    val operation = NativeCallbackOperation(continuation, closeRegistration = {})
    val ticket = tryRegister(needsPolling = false) { operation.fail(it) }
    if (ticket == null) {
        operation.fail(IllegalStateException("Device is closed"))
        return@suspendCancellableCoroutine
    }
    operation.attach(ticket)
    if (operation.isFinished) return@suspendCancellableCoroutine
    if (launch {
            var created: Result<T>? = null
            val started = ticket.runNativeCall { created = runCatching(create) }
            if (!started) {
                operation.fail(IllegalStateException("Device is closed"))
            } else {
                created!!.fold(
                    onSuccess = { operation.succeed(it, dispose) },
                    onFailure = operation::fail,
                )
            }
        } == null
    ) {
        operation.fail(IllegalStateException("Device is closed"))
    }
}
