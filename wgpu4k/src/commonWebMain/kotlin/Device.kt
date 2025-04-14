package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class Device(val handler: WGPUDevice) : GPUDevice {
    actual override var label: String
        get() = handler.label
        set(value) {
            handler.label = value
        }

    actual override val queue: GPUQueue by lazy { Queue(handler.queue) }

    actual override val features: Set<GPUFeatureName> by lazy {
        GPUFeatureName.entries
            .filter { handler.features.has(it.value.asJsString().castAs()) }
            .toSet()
    }

    actual override val limits: GPUSupportedLimits by lazy { map(handler.limits) }
    actual override val adapterInfo: GPUAdapterInfo
        get() = map(handler.adapterInfo)

    actual override fun createCommandEncoder(descriptor: GPUCommandEncoderDescriptor?): GPUCommandEncoder {
        return CommandEncoder(
            when (descriptor) {
                null -> handler.createCommandEncoder()
                else -> handler.createCommandEncoder(map(descriptor))
            }
        )
    }

    actual override fun createShaderModule(descriptor: GPUShaderModuleDescriptor): GPUShaderModule {
        return map(descriptor)
            .let { handler.createShaderModule(it) }
            .let(::ShaderModule)
    }

    actual override fun createPipelineLayout(descriptor: GPUPipelineLayoutDescriptor): GPUPipelineLayout = handler
        .createPipelineLayout(map(descriptor))
        .let(::PipelineLayout)

    actual override fun createRenderPipeline(descriptor: GPURenderPipelineDescriptor): GPURenderPipeline =
        map(descriptor)
            .let { handler.createRenderPipeline(it) }
            .let(::RenderPipeline)

    actual override suspend fun createComputePipelineAsync(descriptor: GPUComputePipelineDescriptor): Result<GPUComputePipeline> = runCatching {
        map(descriptor)
            .let { handler.createComputePipelineAsync(it) }
            .wait<WGPUComputePipeline>()
            .let { ComputePipeline(it) }
    }

    actual override suspend fun createRenderPipelineAsync(descriptor: GPURenderPipelineDescriptor): Result<GPURenderPipeline> = runCatching {
        map(descriptor)
            .let { handler.createRenderPipelineAsync(it) }
            .wait<WGPURenderPipeline>()
            .let { RenderPipeline(it) }
    }

    actual override fun createBuffer(descriptor: GPUBufferDescriptor): GPUBuffer = map(descriptor)
        .let { handler.createBuffer(it) }
        .let(::Buffer)

    actual override fun createTexture(descriptor: GPUTextureDescriptor): GPUTexture = map(descriptor)
        .let { handler.createTexture(it) }
        .let(::Texture)

    actual override fun createBindGroup(descriptor: GPUBindGroupDescriptor): GPUBindGroup = map(descriptor)
        .let { handler.createBindGroup(it) }
        .let(::BindGroup)

    actual override fun createSampler(descriptor: GPUSamplerDescriptor?): GPUSampler =
        when (descriptor) {
            null -> handler.createSampler()
            else -> map(descriptor)
                .let { handler.createSampler(it) }
        }.let(::Sampler)

    actual override fun createComputePipeline(descriptor: GPUComputePipelineDescriptor): GPUComputePipeline =
        map(descriptor)
            .let { handler.createComputePipeline(it) }
            .let(::ComputePipeline)

    actual override fun createBindGroupLayout(descriptor: GPUBindGroupLayoutDescriptor): GPUBindGroupLayout =
        map(descriptor)
            .let { handler.createBindGroupLayout(it) }
            .let(::BindGroupLayout)

    actual override fun createRenderBundleEncoder(descriptor: GPURenderBundleEncoderDescriptor): GPURenderBundleEncoder =
        map(descriptor)
            .let { handler.createRenderBundleEncoder(it) }
            .let(::RenderBundleEncoder)

    actual override fun createQuerySet(descriptor: GPUQuerySetDescriptor): GPUQuerySet =
        map(descriptor)
            .let { handler.createQuerySet(it) }
            .let(::QuerySet)

    actual override fun pushErrorScope(filter: GPUErrorFilter) {
        handler.pushErrorScope(filter.value)
    }

    actual override suspend fun popErrorScope(): Result<GPUError?> = runCatching {
        handler.popErrorScope()
            .wait<WGPUError>()
            .toGPUError()
    }

    actual override fun close() {
        handler.destroy()
    }

}

private fun WGPUError.toGPUError(): GPUError = when (this) {
    is WGPUValidationError -> object : GPUValidationError {
        override val message: String
            get() = this@toGPUError.message
    }

    is WGPUOutOfMemoryError -> object : GPUOutOfMemoryError {
        override val message: String
            get() = this@toGPUError.message
    }

    is WGPUInternalError -> object : GPUInternalError {
        override val message: String
            get() = this@toGPUError.message
    }

    else -> object : GPUError {
        override val message: String
            get() = this@toGPUError.message
    }
}






