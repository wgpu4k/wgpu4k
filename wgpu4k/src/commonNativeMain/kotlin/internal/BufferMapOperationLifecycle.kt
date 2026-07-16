@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.webgpu.internal

import kotlin.concurrent.atomics.AtomicReference

/**
 * Keeps native map resources alive while start, cancellation, and callback delivery race.
 *
 * [close] is the cleanup hook attached to the coroutine operation. Before the native downcall it
 * closes resources immediately. Once the downcall starts, it requests an abort and defers cleanup
 * until the native callback has returned through [callbackFinished]. This ordering ensures that
 * the callback registration, its userdata, the buffer ticket, and device polling remain valid
 * while `wgpuBufferUnmap` completes a pending map.
 *
 * Cancellation during [startNativeCall] cannot invoke the abort concurrently with the starting
 * downcall. Instead, the starting thread observes [State.abortRequested] and performs [abortInline]
 * immediately after the downcall returns. Cancellation after start uses [abortProtected], which
 * must retain the buffer across the unmap call.
 */
internal class BufferMapOperationLifecycle(
    private val closeResources: () -> Unit,
    private val abortInline: () -> Unit,
    private val abortProtected: () -> Boolean,
    private val reportException: (Throwable) -> Unit = {},
) : AutoCloseable {
    private enum class Phase {
        READY,
        STARTING,
        STARTED,
        ABORTING,
        CALLBACK,
        FINISHED,
    }

    private data class State(
        val phase: Phase = Phase.READY,
        val abortRequested: Boolean = false,
    )

    private val state = AtomicReference(State())

    /**
     * Runs the native map downcall after atomically claiming the start phase.
     *
     * The caller must already protect both the buffer and device native handles. Returning `false`
     * means cleanup won before the downcall could start. A cancellation that arrives during
     * [block] is converted into an inline abort after [block] returns.
     */
    internal fun startNativeCall(block: () -> Unit): Boolean {
        while (true) {
            val current = state.load()
            if (current.phase != Phase.READY) return false
            if (state.compareAndSet(current, current.copy(phase = Phase.STARTING))) break
        }

        try {
            block()
        } catch (failure: Throwable) {
            finishWithoutCallback()
            throw failure
        }
        finishStart()
        return true
    }

    /** Claims delivery of the single native callback. */
    internal fun callbackStarted(): Boolean {
        while (true) {
            val current = state.load()
            when (current.phase) {
                Phase.STARTING,
                Phase.STARTED,
                Phase.ABORTING,
                -> if (state.compareAndSet(current, current.copy(phase = Phase.CALLBACK))) return true

                Phase.READY,
                Phase.CALLBACK,
                Phase.FINISHED,
                -> return false
            }
        }
    }

    /**
     * Ends callback delivery, optionally unmapping a successful result that was not delivered.
     *
     * This method must run while [BufferLifecycle.Ticket.complete] protects the buffer whenever
     * [mapped] is `true`, because [abortInline] may call `wgpuBufferUnmap`.
     */
    internal fun callbackFinished(mapped: Boolean, delivered: Boolean) {
        while (true) {
            val current = state.load()
            if (current.phase == Phase.FINISHED) return
            check(current.phase == Phase.CALLBACK) {
                "Map callback finished from unexpected phase ${current.phase}"
            }
            if (state.compareAndSet(current, current.copy(phase = Phase.FINISHED))) {
                if (mapped && (!delivered || current.abortRequested)) runSafely(abortInline)
                runSafely(closeResources)
                return
            }
        }
    }

    /**
     * Requests cleanup and aborts a native map that has already started.
     *
     * During callback delivery, cleanup is completed by [callbackFinished]. This covers both
     * ordinary completion and cancellation that wins immediately before the result is delivered.
     */
    override fun close() {
        while (true) {
            val current = state.load()
            when (current.phase) {
                Phase.READY -> {
                    if (state.compareAndSet(current, current.copy(phase = Phase.FINISHED))) {
                        runSafely(closeResources)
                        return
                    }
                }

                Phase.STARTING -> {
                    if (current.abortRequested) return
                    if (state.compareAndSet(current, current.copy(abortRequested = true))) return
                }

                Phase.STARTED -> {
                    if (state.compareAndSet(
                            current,
                            current.copy(
                                phase = Phase.ABORTING,
                                abortRequested = true,
                            ),
                        )
                    ) {
                        runSafely { abortProtected() }
                        return
                    }
                }

                Phase.ABORTING,
                Phase.CALLBACK,
                Phase.FINISHED,
                -> return
            }
        }
    }

    /** Publishes successful start or performs a cancellation requested during the downcall. */
    private fun finishStart() {
        while (true) {
            val current = state.load()
            when (current.phase) {
                Phase.STARTING -> {
                    val next = if (current.abortRequested) Phase.ABORTING else Phase.STARTED
                    if (state.compareAndSet(current, current.copy(phase = next))) {
                        if (next == Phase.ABORTING) runSafely(abortInline)
                        return
                    }
                }

                Phase.CALLBACK,
                Phase.FINISHED,
                -> return

                Phase.READY,
                Phase.STARTED,
                Phase.ABORTING,
                -> error("Map start finished from unexpected phase ${current.phase}")
            }
        }
    }

    /** Cleans up a downcall that threw before it could arrange callback delivery. */
    private fun finishWithoutCallback() {
        while (true) {
            val current = state.load()
            if (current.phase == Phase.FINISHED || current.phase == Phase.CALLBACK) return
            if (state.compareAndSet(current, current.copy(phase = Phase.FINISHED))) {
                runSafely(closeResources)
                return
            }
        }
    }

    /** Contains failures so cleanup invoked from a native upcall never unwinds across FFI. */
    private fun runSafely(block: () -> Unit) {
        try {
            block()
        } catch (error: Throwable) {
            try {
                reportException(error)
            } catch (_: Throwable) {
                // The reporter is the terminal sink; recursive reporting would only fail again.
            }
        }
    }
}
