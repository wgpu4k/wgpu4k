package io.ygdrasil.wgpu

actual class CommandBuffer(internal val handler: Long) : AutoCloseable {
    actual override fun close() {
    }

}