package io.ygdrasil.webgpu

@WGPULowLevel
expect class PipelineLayout : GPUPipelineLayout {
    override var label: String
    override fun close()
}

@WGPULowLevel
expect class RenderPipeline : GPURenderPipeline {
    override var label: String
    override fun getBindGroupLayout(index: UInt): GPUBindGroupLayout
    override fun close()
}