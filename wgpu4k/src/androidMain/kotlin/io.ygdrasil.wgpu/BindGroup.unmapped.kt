package io.ygdrasil.wgpu

actual class BindGroup(internal val handler: Long) : AutoCloseable {
    actual override fun close() {
    }

}