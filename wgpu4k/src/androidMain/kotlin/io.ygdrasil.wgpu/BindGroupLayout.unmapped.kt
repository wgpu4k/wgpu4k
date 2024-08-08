package io.ygdrasil.wgpu

actual class BindGroupLayout(internal val handler: Long) : AutoCloseable {
    actual override fun close() {
    }
}