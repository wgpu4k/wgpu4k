package io.ygdrasil.webgpu

actual class ComputePipeline(val handler: WGPUComputePipeline) : GPUComputePipeline {
    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun getBindGroupLayout(index: GPUSize32): GPUBindGroupLayout =
        handler.getBindGroupLayout(index.asJsNumber())
            .let { BindGroupLayout(it) }

    actual override fun close() {
        // Nothing to do on js
    }

}