package io.ygdrasil.webgpu

expect class Queue : GPUQueue {

    override var label: String
    
    override fun submit(commandBuffers: List<GPUCommandBuffer>)
    override suspend fun onSubmittedWorkDone() : Result<Unit>
    override fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: GPUSize64,
        data: ArrayBuffer,
        dataOffset: GPUSize64,
        size: GPUSize64?
    )

    override fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: ArrayBuffer,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D
    )

}

