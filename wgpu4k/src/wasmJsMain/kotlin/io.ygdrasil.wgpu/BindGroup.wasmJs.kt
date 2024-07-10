package io.ygdrasil.wgpu

actual class BindGroup : AutoCloseable {
    actual override fun close() {
        // Nothing to do on JS
    }

}