package io.ygdrasil.webgpu

import io.ygdrasil.kffi.ArrayHolder
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.memoryScope
import io.ygdrasil.webgpu.internal.NativeCallbackOperation
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUCallbackMode_AllowProcessEvents
import io.ygdrasil.wgpu.WGPUCommandBuffer
import io.ygdrasil.wgpu.WGPUQueue
import io.ygdrasil.wgpu.WGPUQueueWorkDoneCallback
import io.ygdrasil.wgpu.WGPUQueueWorkDoneCallbackInfo
import io.ygdrasil.wgpu.WGPUQueueWorkDoneStatus_Success
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.allocate
import io.ygdrasil.wgpu.register
import io.ygdrasil.wgpu.wgpuQueueOnSubmittedWorkDone
import io.ygdrasil.wgpu.wgpuQueueSetLabel
import io.ygdrasil.wgpu.wgpuQueueSubmit
import kotlinx.coroutines.suspendCancellableCoroutine

actual class Queue internal constructor(
    val handler: WGPUQueue,
    private val device: Device,
    label: String,
) : GPUQueue {

    actual override var label: String = label
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuQueueSetLabel(handler, newLabel)
            field = value
        }

    actual override fun submit(commandBuffers: List<GPUCommandBuffer>)= memoryScope { scope ->
        if (commandBuffers.isNotEmpty()) {

            val commands = scope.bufferOfAddresses(commandBuffers.map { (it as CommandBuffer).handler.handler })
                .handler
                .let { ArrayHolder<WGPUCommandBuffer>(it) }

            wgpuQueueSubmit(
                handler,
                commandBuffers.size.toULong(),
                commands.handler
            )
        } else {

            wgpuQueueSubmit(
                handler,
                0uL,
                null
            )
        }
    }

    actual override suspend fun onSubmittedWorkDone(): Result<Unit> {
        return suspendCancellableCoroutine { continuation ->
            lateinit var operation: NativeCallbackOperation<Unit>
            val registration = WGPUQueueWorkDoneCallback.register(
                policy = CallbackPolicy.ONCE,
                callback = WGPUQueueWorkDoneCallback { status, message, _ ->
                    if (status == WGPUQueueWorkDoneStatus_Success) {
                        operation.succeed(Unit)
                    } else {
                        operation.fail(
                            IllegalStateException(
                                "onSubmittedWorkDone failed with status: $status and message: ${message.data?.toKString(message.length)}"
                            )
                        )
                    }
                },
            )
            operation = NativeCallbackOperation(continuation, registration::close)
            val ticket = device.eventPump.tryRegister(needsPolling = true) { operation.fail(it) }
            if (ticket == null) {
                operation.fail(IllegalStateException("Device is closed"))
                return@suspendCancellableCoroutine
            }
            operation.attach(ticket)
            val started = ticket.runNativeCall {
                memoryScope { scope ->
                    val callbackInfo = WGPUQueueWorkDoneCallbackInfo.allocate(
                        scope,
                        WGPUCallbackMode_AllowProcessEvents,
                        registration,
                    )
                    wgpuQueueOnSubmittedWorkDone(handler, callbackInfo)
                }
            }
            if (!started) operation.fail(IllegalStateException("Device is closed"))
        }
    }

    actual override fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: GPUSize64,
        data: ArrayBuffer,
        dataOffset: GPUSize64,
        size: GPUSize64?
    ) {
        queueWriteBuffer(buffer, bufferOffset, data, dataOffset, size)
    }

    actual override fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: ArrayBuffer,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D
    ) = memoryScope { scope ->
        queueWriteTexture(scope, destination, data, dataLayout, size)
    }

}

internal expect inline fun Queue.queueWriteTexture(
    scope: MemoryAllocator,
    destination: GPUTexelCopyTextureInfo,
    data: ArrayBuffer,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D
)

internal expect inline fun Queue.queueWriteBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: ArrayBuffer,
    dataOffset: GPUSize64,
    size: GPUSize64?
)
