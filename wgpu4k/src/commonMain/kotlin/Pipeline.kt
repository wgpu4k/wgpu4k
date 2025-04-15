package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class PipelineLayout : GPUPipelineLayout {
    override var label: String
    override fun close()
}

@WGPULowLevelApi
expect class RenderPipeline : GPURenderPipeline {
    override var label: String
    override fun getBindGroupLayout(index: UInt): GPUBindGroupLayout
    override fun close()
}