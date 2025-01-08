package io.ygdrasil.webgpu

expect class BindGroupLayout : AutoCloseable {
    override fun close()
}