package io.ygdrasil.webgpu

actual class Queue(internal val handler: WGPUQueue) : GPUQueue {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override suspend fun onSubmittedWorkDone(): Result<Unit> {
        TODO("Not yet implemented")
    }

    actual override fun submit(commandBuffers: List<GPUCommandBuffer>) {
        TODO("Not yet implemented")
    }

    actual override fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: GPUSize64,
        data: ArrayBuffer,
        dataOffset: GPUSize64,
        size: GPUSize64?
    ) {
        TODO("Not yet implemented")
    }

    actual override fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: ArrayBuffer,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D
    ) {
        TODO("Not yet implemented")
    }
}