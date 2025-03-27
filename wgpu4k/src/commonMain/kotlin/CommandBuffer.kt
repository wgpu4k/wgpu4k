package io.ygdrasil.webgpu

@WGPULowLevel
expect class CommandBuffer : GPUCommandBuffer {

    override var label: String

    override fun close()
}