package io.ygdrasil.webgpu

import ffi.ArrayHolder
import ffi.MemoryAllocator
import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUCommandBuffer
import io.ygdrasil.wgpu.WGPUQueue
import io.ygdrasil.wgpu.WGPUQueueWorkDoneCallback
import io.ygdrasil.wgpu.WGPUQueueWorkDoneCallbackInfo
import io.ygdrasil.wgpu.WGPUQueueWorkDoneStatus_Success
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuQueueOnSubmittedWorkDone
import io.ygdrasil.wgpu.wgpuQueueSetLabel
import io.ygdrasil.wgpu.wgpuQueueSubmit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class Queue(val handler: WGPUQueue, label: String) : GPUQueue {

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
                commands
            )
        } else {

            wgpuQueueSubmit(
                handler,
                0uL,
                null
            )
        }
    }

    actual override suspend fun onSubmittedWorkDone(): Result<Unit> = suspendCoroutine { continuation ->
        memoryScope { scope ->

            val callback = WGPUQueueWorkDoneCallback.allocate(scope) { status, userdata1, userdata2 ->
                continuation.resume(when(status) {
                    WGPUQueueWorkDoneStatus_Success -> Result.success(Unit)
                    else -> Result.failure(IllegalStateException("request onSubmittedWorkDone fail with status: $status}"))
                })
            }

            val callbackInfo = WGPUQueueWorkDoneCallbackInfo.allocate(scope).apply {
                this.callback = callback
                this.userdata2 = scope.bufferOfAddress(callback.handler).handler
            }

            wgpuQueueOnSubmittedWorkDone(handler, callbackInfo)
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