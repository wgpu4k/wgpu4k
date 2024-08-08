package io.ygdrasil.wgpu

actual class ShaderModule(internal val handler: Long) : AutoCloseable {
    actual override fun close() {
    }

}