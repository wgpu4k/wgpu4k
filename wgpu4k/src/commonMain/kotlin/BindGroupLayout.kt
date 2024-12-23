package io.ygdrasil.wgpu

expect class BindGroupLayout : AutoCloseable {
    override fun close()
}