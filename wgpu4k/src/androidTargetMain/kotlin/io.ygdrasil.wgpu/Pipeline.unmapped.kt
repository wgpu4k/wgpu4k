package io.ygdrasil.wgpu

actual class RenderPipeline : AutoCloseable {
    actual fun getBindGroupLayout(index: Int): BindGroupLayout {
        TODO("Not yet implemented")
    }

    actual override fun close() {
    }

}

actual class PipelineLayout