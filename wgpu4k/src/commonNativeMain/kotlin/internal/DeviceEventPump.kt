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

internal class DeviceEventPump(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val poll: () -> Unit,
    retain: () -> Unit,
    private val release: () -> Unit,
    private val pollDelayMillis: Long = 1L,
    reportException: ((Throwable) -> Unit)? = null,
) : AutoCloseable {
    private data class Pending(
        val needsPolling: Boolean,
        val onTerminated: (Throwable) -> Unit,
    )

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

    init {
        retain()
        scopeJob.invokeOnCompletion { releaseIfQuiescent() }
        scope.launch { pollLoop() }
    }

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

    internal fun launch(block: suspend () -> Unit): Job? {
        if (state.load().closed) return null
        val job = scope.launch { block() }
        if (state.load().closed) job.cancel()
        return job
    }

    override fun close() {
        terminate(IllegalStateException("Device is closed"))
    }

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

    private fun runSafely(block: () -> Unit) {
        try {
            block()
        } catch (error: Throwable) {
            reportSafely(error)
        }
    }

    private fun reportSafely(error: Throwable) {
        try {
            reportException(error)
        } catch (_: Throwable) {
            // A reporter is a terminal sink: retrying it would recurse or report twice.
        }
    }

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

    private fun beginNativeCall(id: Long): Boolean {
        while (true) {
            val current = state.load()
            if (current.closed || id !in current.pending) return false
            if (state.compareAndSet(current, current.copy(activeNativeCalls = current.activeNativeCalls + 1))) {
                return true
            }
        }
    }

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

    private fun releaseIfQuiescent() {
        val current = state.load()
        if (current.closed && current.activeNativeCalls == 0 && scopeJob.isCompleted) {
            if (released.compareAndSet(expectedValue = false, newValue = true)) runSafely(release)
        }
    }

    internal class Ticket internal constructor(
        private val owner: DeviceEventPump,
        private val id: Long,
    ) : AutoCloseable {
        private val closed = AtomicBoolean(false)

        internal fun runNativeCall(block: () -> Unit): Boolean {
            if (!owner.beginNativeCall(id)) return false
            return try {
                block()
                true
            } finally {
                owner.endNativeCall()
            }
        }

        override fun close() {
            if (closed.compareAndSet(expectedValue = false, newValue = true)) owner.remove(id)
        }
    }
}

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
