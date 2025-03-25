package io.ygdrasil.webgpu

expect class CommandBuffer : GPUCommandBuffer {

    override var label: String

    override fun close()
}