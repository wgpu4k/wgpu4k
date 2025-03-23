package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class Queue(internal val handler: WGPUQueue) : GPUQueue {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override suspend fun onSubmittedWorkDone(): Result<Unit> = runCatching {
        handler.onSubmittedWorkDone()
            .wait<Unit>()
    }

    actual override fun submit(commandBuffers: List<GPUCommandBuffer>) {
        handler.submit(commandBuffers.mapJsArray { (it as CommandBuffer).handler })
    }

    actual override fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: GPUSize64,
        data: ArrayBuffer,
        dataOffset: GPUSize64,
        size: GPUSize64?
    ) = when (size) {
        null -> handler.writeBuffer(
            (buffer as Buffer).handler,
            bufferOffset.asJsNumber(),
            data,
            dataOffset.asJsNumber()
        )
        else -> handler.writeBuffer(
            (buffer as Buffer).handler,
            bufferOffset.asJsNumber(),
            data,
            dataOffset.asJsNumber(),
            size.asJsNumber()
        )
    }

    actual override fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: ArrayBuffer,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D
    ) {
        handler.writeTexture(
            map(destination),
            data,
            map(dataLayout),
            map(size)
        )
    }
}