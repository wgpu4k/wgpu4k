package io.ygdrasil.webgpu

expect class Device : GPUDevice {

    override var label: String
    override val features: GPUSupportedFeatures
    override val limits: GPUSupportedLimits
    override val adapterInfo: GPUAdapterInfo
    override val queue: GPUQueue

    override fun createBuffer(descriptor: GPUBufferDescriptor): GPUBuffer
    override fun createTexture(descriptor: GPUTextureDescriptor): GPUTexture
    override fun createSampler(descriptor: GPUSamplerDescriptor?): GPUSampler
    override fun createBindGroupLayout(descriptor: GPUBindGroupLayoutDescriptor): GPUBindGroupLayout
    override fun createPipelineLayout(descriptor: GPUPipelineLayoutDescriptor): GPUPipelineLayout
    override fun createBindGroup(descriptor: GPUBindGroupDescriptor): GPUBindGroup
    override fun createShaderModule(descriptor: GPUShaderModuleDescriptor): GPUShaderModule
    override fun createComputePipeline(descriptor: GPUComputePipelineDescriptor): GPUComputePipeline
    override fun createRenderPipeline(descriptor: GPURenderPipelineDescriptor): GPURenderPipeline
    override suspend fun createComputePipelineAsync(descriptor: GPUComputePipelineDescriptor): Result<GPUComputePipeline>
    override suspend fun createRenderPipelineAsync(descriptor: GPURenderPipelineDescriptor): Result<GPURenderPipeline>
    override fun createCommandEncoder(descriptor: GPUCommandEncoderDescriptor?): GPUCommandEncoder
    override fun createRenderBundleEncoder(descriptor: GPURenderBundleEncoderDescriptor): GPURenderBundleEncoder
    override fun createQuerySet(descriptor: GPUQuerySetDescriptor): GPUQuerySet
    override fun pushErrorScope(filter: GPUErrorFilter)
    override suspend fun popErrorScope(): Result<GPUError?>

    override fun close()
}

expect suspend fun GPUDevice.poll(): Result<Unit>