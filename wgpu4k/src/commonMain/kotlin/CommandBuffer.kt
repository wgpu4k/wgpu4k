package io.ygdrasil.webgpu

expect class CommandBuffer : GPUCommandBuffer {

    override fun close()
}