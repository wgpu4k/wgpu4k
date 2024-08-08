package io.ygdrasil.wgpu

actual class ComputePipeline(internal val handler: Long) : AutoCloseable {
    actual fun getBindGroupLayout(index: Int): BindGroupLayout {
        TODO("Not yet implemented")
    }

    actual override fun close() {
    }

}