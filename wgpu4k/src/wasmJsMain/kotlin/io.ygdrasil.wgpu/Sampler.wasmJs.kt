package io.ygdrasil.wgpu

actual class Sampler : AutoCloseable {

    actual override fun close() {
        // Nothing to do on JS
    }

}