package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class ComputePipeline : GPUComputePipeline {
    override var label: String
    override fun getBindGroupLayout(index: UInt): GPUBindGroupLayout
    override fun close()
}
