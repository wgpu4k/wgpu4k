package io.ygdrasil.wgpu

actual class BindGroupLayout : AutoCloseable {
    actual override fun close() {
        // Nothing to do on JS
    }
}