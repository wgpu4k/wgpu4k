@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.webgpu.internal

import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicReference

/**
 * Arbitrates the lifetime of a native buffer against an asynchronous map operation.
 *
 * A buffer can have at most one pending map operation. [registerMap] reserves that slot and
 * returns a [Ticket] tied to the exact reservation. The identity carried by the ticket prevents a
 * stale callback or cancellation handler from completing or removing a newer map operation.
 *
 * The lifecycle is represented by one immutable [State] stored in an [AtomicReference]. Every
 * transition uses compare-and-set, so registration, callback delivery, cancellation, a native
 * downcall, and [close] may safely race on different threads. The important invariants are:
 *
 * - a closed lifecycle never accepts another map operation;
 * - only the current map ticket may start a native call or complete the operation;
 * - closing atomically detaches the pending map before notifying it;
 * - the native buffer is not released while a protected native call is active; and
 * - [release] is invoked exactly once, after the lifecycle is closed and all native calls return.
 *
 * A typical map operation follows this sequence:
 *
 * 1. Reserve the map slot with [registerMap].
 * 2. Use [Ticket.runNativeCall] to cross the native boundary.
 * 3. Let [BufferMapOperationLifecycle] request `wgpuBufferUnmap` when cancellation wins while
 *    retaining the ticket until the native callback arrives.
 * 4. Let the native callback claim the reservation with [Ticket.complete], then close the ticket
 *    together with the upcall registration and device-polling ticket.
 * 5. Close this lifecycle when the public buffer is closed. If a map is still pending, its
 *    termination callback is invoked with `IllegalStateException("Buffer is closed")`.
 *
 * [close] is idempotent. Closing a [Ticket] is also idempotent, but only abandons that map
 * reservation; it does not close or release the buffer.
 *
 * @param release releases the retained native buffer reference. It is called at most once.
 */
internal class BufferLifecycle(
    private val release: () -> Unit,
) : AutoCloseable {
    /**
     * Identity and termination action for one admitted map operation.
     *
     * Referential identity, rather than structural equality, is used when a ticket removes or
     * completes a map. This prevents work belonging to an older operation from affecting a newer
     * operation that reused the single pending-map slot.
     */
    internal class PendingMap(
        val onTerminated: (Throwable) -> Unit,
    )

    /**
     * Atomically published lifecycle state.
     *
     * @property closed whether buffer closure has won the lifecycle race. A closed state always
     * has no [pendingMap].
     * @property pendingMap the single admitted map operation, or `null` when none is pending.
     * @property activeNativeCalls number of calls admitted by [beginNativeCall] that have not yet
     * reached [endNativeCall].
     * @property released whether invocation of [release] has already been atomically claimed.
     */
    private data class State(
        val closed: Boolean = false,
        val pendingMap: PendingMap? = null,
        val activeNativeCalls: Int = 0,
        val released: Boolean = false,
    )

    private val state = AtomicReference(State())

    /**
     * Attempts to reserve the buffer's single pending-map slot.
     *
     * Registration fails without changing state when the buffer is already closed or another map
     * is pending. On success, the returned [Ticket] is the capability required to start the native
     * call and later complete or abandon this exact reservation.
     *
     * [onTerminated] is invoked if [close] wins while this reservation is still pending. It is
     * invoked after the closed state has been published, so reentrant lifecycle calls observe the
     * buffer as closed.
     *
     * @param onTerminated fails the suspended map operation when buffer closure claims it.
     * @return a successful [Result] containing the reservation ticket, or a failed result whose
     * message distinguishes a closed buffer from an already pending map.
     */
    internal fun registerMap(onTerminated: (Throwable) -> Unit): Result<Ticket> {
        val pending = PendingMap(onTerminated)
        while (true) {
            val current = state.load()
            if (current.closed) {
                return Result.failure(IllegalStateException("Buffer is closed"))
            }
            if (current.pendingMap != null) {
                return Result.failure(IllegalStateException("Buffer map is already pending"))
            }
            if (state.compareAndSet(current, current.copy(pendingMap = pending))) {
                return Result.success(Ticket(this, pending))
            }
        }
    }

    /**
     * Closes this lifecycle, terminates any pending map, and schedules native release.
     *
     * The transition to `closed` and removal of the pending map happen in one compare-and-set. As a
     * result, either [close] claims the map and invokes its termination callback, or
     * [Ticket.complete] claims it and runs its completion block; both cannot win.
     *
     * The termination callback runs outside the atomic loop. Native release is attempted in a
     * `finally` block, but [releaseIfQuiescent] defers it until every call admitted through
     * [Ticket.runNativeCall] has returned. Subsequent calls to [close] do nothing.
     */
    override fun close() {
        var termination: ((Throwable) -> Unit)? = null
        while (true) {
            val current = state.load()
            if (current.closed) return
            termination = current.pendingMap?.onTerminated
            if (state.compareAndSet(
                    current,
                    current.copy(closed = true, pendingMap = null),
                )
            ) {
                break
            }
        }
        try {
            termination?.invoke(IllegalStateException("Buffer is closed"))
        } finally {
            releaseIfQuiescent()
        }
    }

    /** Removes [pending] only if it still owns the pending-map slot. */
    private fun remove(pending: PendingMap) {
        while (true) {
            val current = state.load()
            if (current.pendingMap !== pending) return
            if (state.compareAndSet(current, current.copy(pendingMap = null))) return
        }
    }

    /**
     * Atomically gives [pending] ownership of map completion.
     *
     * The pending slot is cleared and a native-lifetime barrier is acquired before [block] runs.
     * This keeps the callback free to perform a final native operation, such as aborting a mapping
     * whose result can no longer be delivered, without racing buffer release. Returning `false`
     * means closure, ticket cancellation, or another completion already won, and [block] is not
     * invoked.
     */
    private fun complete(pending: PendingMap, block: () -> Unit): Boolean {
        while (true) {
            val current = state.load()
            if (current.closed || current.pendingMap !== pending) return false
            if (state.compareAndSet(
                    current,
                    current.copy(
                        pendingMap = null,
                        activeNativeCalls = current.activeNativeCalls + 1,
                    ),
                )
            ) {
                return try {
                    block()
                    true
                } finally {
                    endNativeCall()
                }
            }
        }
    }

    /**
     * Admits one native call for [pending] and increments the release barrier.
     *
     * Admission is rejected after closure, after release, or when the ticket no longer owns the
     * pending-map slot. Once admitted, [releaseIfQuiescent] cannot release the native buffer until
     * the matching [endNativeCall] decrements [State.activeNativeCalls].
     */
    private fun beginNativeCall(pending: PendingMap): Boolean {
        while (true) {
            val current = state.load()
            if (current.closed || current.released || current.pendingMap !== pending) return false
            if (state.compareAndSet(
                    current,
                    current.copy(activeNativeCalls = current.activeNativeCalls + 1),
                )
            ) {
                return true
            }
        }
    }

    /** Completes one admitted native call and re-evaluates deferred native release. */
    private fun endNativeCall() {
        while (true) {
            val current = state.load()
            check(current.activeNativeCalls > 0)
            if (state.compareAndSet(
                    current,
                    current.copy(activeNativeCalls = current.activeNativeCalls - 1),
                )
            ) {
                releaseIfQuiescent()
                return
            }
        }
    }

    /**
     * Releases the native reference once the lifecycle is closed and no downcall is active.
     *
     * Setting [State.released] and claiming [release] are one atomic transition. Closure prevents
     * new native-call admissions, so the quiescent state remains stable after that transition.
     */
    private fun releaseIfQuiescent() {
        while (true) {
            val current = state.load()
            if (!current.closed || current.activeNativeCalls != 0 || current.released) return
            if (state.compareAndSet(current, current.copy(released = true))) {
                release()
                return
            }
        }
    }

    /**
     * Capability representing one pending map reservation.
     *
     * A ticket remains safe to call after cancellation, completion, or buffer closure: operations
     * that no longer own the reservation simply return `false` or become no-ops. The ticket holds
     * no native reference itself; its owner keeps the native buffer alive while an admitted call is
     * executing.
     */
    internal class Ticket internal constructor(
        private val owner: BufferLifecycle,
        private val pending: PendingMap,
    ) : AutoCloseable {
        private val closed = AtomicBoolean(false)

        /**
         * Runs [block] while native buffer release is blocked.
         *
         * @return `true` when this ticket was current and [block] was entered, or `false` when the
         * buffer was closed/released or the reservation had already ended. Exceptions from [block]
         * propagate, while the native-call counter is always decremented in `finally`.
         */
        internal fun runNativeCall(block: () -> Unit): Boolean {
            if (!owner.beginNativeCall(pending)) return false
            return try {
                block()
                true
            } finally {
                owner.endNativeCall()
            }
        }

        /**
         * Attempts to claim and complete this map reservation.
         *
         * @return `true` if this ticket atomically removed the pending map and invoked [block], or
         * `false` if close, cancellation, or another completion had already claimed it.
         */
        internal fun complete(block: () -> Unit): Boolean = owner.complete(pending, block)

        /**
         * Idempotently abandons this reservation if it is still pending.
         *
         * This is the final cleanup path after native completion or abort. Closing the ticket does
         * not itself cancel a native mapping; [BufferMapOperationLifecycle] performs that abort
         * first. It neither invokes the termination callback nor closes the owning buffer
         * lifecycle.
         */
        override fun close() {
            if (closed.compareAndSet(expectedValue = false, newValue = true)) {
                owner.remove(pending)
            }
        }
    }
}
