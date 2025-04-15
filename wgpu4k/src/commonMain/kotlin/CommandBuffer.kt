package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class CommandBuffer : GPUCommandBuffer {

    override var label: String

    override fun close()
}