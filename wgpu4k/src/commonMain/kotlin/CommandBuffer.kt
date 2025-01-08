package io.ygdrasil.webgpu

expect class CommandBuffer : AutoCloseable {

    override fun close()
}