package io.ygdrasil.wgpu

actual class Sampler(internal val handler: Long) : AutoCloseable {
    actual override fun close() {
    }

}