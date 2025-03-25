package io.ygdrasil.webgpu

expect class PipelineLayout : GPUPipelineLayout {
    override var label: String
    override fun close()
}

expect class RenderPipeline : GPURenderPipeline {
    override var label: String
    override fun getBindGroupLayout(index: UInt): GPUBindGroupLayout
    override fun close()
}