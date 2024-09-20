package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUDevice
import io.ygdrasil.wgpu.mapper.map
import kotlinx.coroutines.await

actual class Device(internal val handler: GPUDevice) : AutoCloseable {

    actual val queue: Queue by lazy { Queue(handler.queue) }

    actual val features: Set<FeatureName> by lazy {
        handler.features
            .map { FeatureName.of(it) ?: error("Unsupported feature $it") }
            .toSet()
    }

    val limits: SupportedLimits by lazy {
        SupportedLimits(
            maxTextureDimension1D = handler.limits.maxTextureDimension1D,
            maxTextureDimension2D = handler.limits.maxTextureDimension2D,
            maxTextureDimension3D = handler.limits.maxTextureDimension3D,
            maxTextureArrayLayers = handler.limits.maxTextureArrayLayers,
            maxBindGroups = handler.limits.maxBindGroups,
            maxBindGroupsPlusVertexBuffers = handler.limits.maxBindGroupsPlusVertexBuffers,
            maxBindingsPerBindGroup = handler.limits.maxBindingsPerBindGroup,
            maxDynamicUniformBuffersPerPipelineLayout = handler.limits.maxDynamicUniformBuffersPerPipelineLayout,
            maxDynamicStorageBuffersPerPipelineLayout = handler.limits.maxDynamicStorageBuffersPerPipelineLayout,
            maxSampledTexturesPerShaderStage = handler.limits.maxSampledTexturesPerShaderStage,
            maxSamplersPerShaderStage = handler.limits.maxSamplersPerShaderStage,
            maxStorageBuffersPerShaderStage = handler.limits.maxStorageBuffersPerShaderStage,
            maxStorageTexturesPerShaderStage = handler.limits.maxStorageTexturesPerShaderStage,
            maxUniformBuffersPerShaderStage = handler.limits.maxUniformBuffersPerShaderStage,
            maxUniformBufferBindingSize = handler.limits.maxUniformBufferBindingSize,
            maxStorageBufferBindingSize = handler.limits.maxStorageBufferBindingSize,
            minUniformBufferOffsetAlignment = handler.limits.minUniformBufferOffsetAlignment,
            minStorageBufferOffsetAlignment = handler.limits.minStorageBufferOffsetAlignment,
            maxVertexBuffers = handler.limits.maxVertexBuffers,
            maxBufferSize = handler.limits.maxBufferSize,
            maxVertexAttributes = handler.limits.maxVertexAttributes,
            maxVertexBufferArrayStride = handler.limits.maxVertexBufferArrayStride,
            maxInterStageShaderComponents = handler.limits.maxInterStageShaderComponents,
            maxInterStageShaderVariables = handler.limits.maxInterStageShaderVariables,
            maxColorAttachments = handler.limits.maxColorAttachments,
            maxColorAttachmentBytesPerSample = handler.limits.maxColorAttachmentBytesPerSample,
            maxComputeWorkgroupStorageSize = handler.limits.maxComputeWorkgroupStorageSize,
            maxComputeInvocationsPerWorkgroup = handler.limits.maxComputeInvocationsPerWorkgroup,
            maxComputeWorkgroupSizeX = handler.limits.maxComputeWorkgroupSizeX,
            maxComputeWorkgroupSizeY = handler.limits.maxComputeWorkgroupSizeY,
            maxComputeWorkgroupSizeZ = handler.limits.maxComputeWorkgroupSizeZ,
            maxComputeWorkgroupsPerDimension = handler.limits.maxComputeWorkgroupsPerDimension,
        )
    }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder {
        return CommandEncoder(
            when (descriptor) {
                null -> handler.createCommandEncoder()
                else -> handler.createCommandEncoder(map(descriptor))
            }
        )
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule {
        return map(descriptor)
            .let { handler.createShaderModule(it) }
            .let(::ShaderModule)
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout = handler
        .createPipelineLayout(map(descriptor))
        .let(::PipelineLayout)

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = map(descriptor)
        .let { handler.createRenderPipeline(it) }
        .let(::RenderPipeline)


    actual fun createBuffer(descriptor: BufferDescriptor): Buffer = map(descriptor)
        .let { handler.createBuffer(it) }
        .let(::Buffer)

    actual fun createTexture(descriptor: TextureDescriptor): Texture = map(descriptor)
        .let { handler.createTexture(it) }
        .let(::Texture)

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup = map(descriptor)
        .let { handler.createBindGroup(it) }
        .let(::BindGroup)

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler =
        map(descriptor)
            .let { handler.createSampler(it) }
            .let(::Sampler)

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline =
        map(descriptor)
            .let { handler.createComputePipeline(it) }
            .let(::ComputePipeline)

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout =
        map(descriptor)
            .let { handler.createBindGroupLayout(it) }
            .let(::BindGroupLayout)

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder =
        map(descriptor)
            .let { handler.createRenderBundleEncoder(it) }
            .let(::RenderBundleEncoder)

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet =
        map(descriptor)
            .let { handler.createQuerySet(it) }
            .let(::QuerySet)

    actual suspend fun poll() {
        queue.handler.onSubmittedWorkDone().await()
    }

    actual override fun close() {
        // Nothing on JS
    }

}






