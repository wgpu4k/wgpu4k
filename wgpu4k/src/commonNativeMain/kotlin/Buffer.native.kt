@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.webgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.memoryScope
import io.ygdrasil.webgpu.internal.NativeCallbackOperation
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.webgpu.mapper.toArrayBuffer
import io.ygdrasil.wgpu.WGPUBuffer
import io.ygdrasil.wgpu.WGPUBufferMapCallback
import io.ygdrasil.wgpu.WGPUBufferMapCallbackInfo
import io.ygdrasil.wgpu.WGPUCallbackMode_AllowProcessEvents
import io.ygdrasil.wgpu.WGPUMapAsyncStatus_Success
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.allocate
import io.ygdrasil.wgpu.register
import io.ygdrasil.wgpu.wgpuBufferGetMapState
import io.ygdrasil.wgpu.wgpuBufferGetMappedRange
import io.ygdrasil.wgpu.wgpuBufferGetSize
import io.ygdrasil.wgpu.wgpuBufferGetUsage
import io.ygdrasil.wgpu.wgpuBufferMapAsync
import io.ygdrasil.wgpu.wgpuBufferRelease
import io.ygdrasil.wgpu.wgpuBufferSetLabel
import io.ygdrasil.wgpu.wgpuBufferUnmap
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicReference

actual class Buffer(val handler: WGPUBuffer, private val device: Device, label: String) : GPUBuffer {

    private val closed = AtomicBoolean(false)
    private val pendingMap = AtomicReference<NativeCallbackOperation<Unit>?>(null)

    actual override var label: String = label
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuBufferSetLabel(handler, newLabel)
            field = value
        }
    actual override val size: GPUSize64
        get() = wgpuBufferGetSize(handler)
    actual override val usage: Set<GPUBufferUsage>
        get() = wgpuBufferGetUsage(handler)
            .let { usage -> GPUBufferUsage.entries.filter { it.value and usage != 0uL }.toSet() }
    actual override val mapState: GPUBufferMapState
        get() = wgpuBufferGetMapState(handler)
            .let { GPUBufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual override fun unmap() {
        wgpuBufferUnmap(handler)
    }

    actual override fun getMappedRange(offset: GPUSize64, size: GPUSize64?): ArrayBuffer {
        val size = size ?: (this.size - offset)
        return wgpuBufferGetMappedRange(handler, offset, size)
            ?.toArrayBuffer(size) ?: error("Can't get mapped range")
    }

    actual override suspend fun mapAsync(
        mode: GPUMapMode,
        offset: GPUSize64,
        size: GPUSize64?,
    ): Result<Unit> {
        if (closed.load()) return Result.failure(IllegalStateException("Buffer is closed"))
        val mappedSize = size ?: (this.size - offset)
        return suspendCancellableCoroutine { continuation ->
            lateinit var operation: NativeCallbackOperation<Unit>
            val registration = WGPUBufferMapCallback.register(
                policy = CallbackPolicy.ONCE,
                callback = WGPUBufferMapCallback { status, message, _ ->
                    if (status == WGPUMapAsyncStatus_Success) {
                        operation.succeed(Unit)
                    } else {
                        operation.fail(
                            IllegalStateException(
                                "mapAsync failed with status: $status and message: ${message.data?.toKString(message.length)}"
                            )
                        )
                    }
                },
            )
            val operationHolder = AtomicReference<NativeCallbackOperation<Unit>?>(null)
            operation = NativeCallbackOperation(
                continuation = continuation,
                closeRegistration = registration::close,
                onFinished = {
                    operationHolder.load()?.let { pendingMap.compareAndSet(it, null) }
                },
            )
            operationHolder.store(operation)

            if (closed.load()) {
                operation.fail(IllegalStateException("Buffer is closed"))
                return@suspendCancellableCoroutine
            }
            if (!pendingMap.compareAndSet(expectedValue = null, newValue = operation)) {
                operation.fail(IllegalStateException("Buffer map is already pending"))
                return@suspendCancellableCoroutine
            }
            if (operation.isFinished) {
                pendingMap.compareAndSet(operation, null)
                return@suspendCancellableCoroutine
            }

            val ticket = device.eventPump.tryRegister(needsPolling = true) { operation.fail(it) }
            if (ticket == null) {
                operation.fail(IllegalStateException("Device is closed"))
                return@suspendCancellableCoroutine
            }
            operation.attach(ticket)
            val started = ticket.runNativeCall {
                memoryScope { scope ->
                    val callbackInfo = WGPUBufferMapCallbackInfo.allocate(
                        scope,
                        WGPUCallbackMode_AllowProcessEvents,
                        registration,
                    )
                    wgpuBufferMapAsync(handler, mode.value, offset, mappedSize, callbackInfo)
                }
            }
            if (!started) operation.fail(IllegalStateException("Device is closed"))
        }
    }

    actual override fun close() {
        if (!closed.compareAndSet(expectedValue = false, newValue = true)) return
        pendingMap.load()?.fail(IllegalStateException("Buffer is closed"))
        wgpuBufferRelease(handler)
    }
}
