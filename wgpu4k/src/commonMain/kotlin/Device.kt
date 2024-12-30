package io.ygdrasil.webgpu

expect class Device : AutoCloseable {

    val features: Set<FeatureName>

    val queue: Queue

    val limits: Limits

    fun createCommandEncoder(descriptor: CommandEncoderDescriptor? = null): CommandEncoder

    fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule

    fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout

    fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline

    fun createBuffer(descriptor: BufferDescriptor): Buffer

    fun createTexture(descriptor: TextureDescriptor): Texture

    fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup

    fun createSampler(descriptor: SamplerDescriptor = SamplerDescriptor()): Sampler

    fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline

    fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout

    fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder

    fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet

    suspend fun poll()

    override fun close()
}

/**
 *
 * @see https://webgpu.rocks/reference/dictionary/gpubindgrouplayoutdescriptor/#idl-gpubindgrouplayoutdescriptor
 *
 */
data class BindGroupLayoutDescriptor(
    val entries: List<Entry>,
    val label: String? = null,
) {
    data class Entry(
        val binding: GPUIndex32,
        val visibility: Set<ShaderStage>,
        val bindingType: BindingType,
    ) {

        sealed interface BindingType

        data class BufferBindingLayout(
            val type: BufferBindingType = BufferBindingType.Uniform,
            val hasDynamicOffset: Boolean = false,
            val minBindingSize: GPUSize64 = 0u,
        ) : BindingType

        data class SamplerBindingLayout(
            val type: SamplerBindingType = SamplerBindingType.Filtering,
        ) : BindingType

        data class TextureBindingLayout(
            val sampleType: TextureSampleType = TextureSampleType.Float,
            val viewDimension: TextureViewDimension = TextureViewDimension.TwoD,
            val multisampled: Boolean = false,
        ) : BindingType

        data class StorageTextureBindingLayout(
            val format: TextureFormat,
            val access: StorageTextureAccess = StorageTextureAccess.WriteOnly,
            val viewDimension: TextureViewDimension = TextureViewDimension.TwoD,
        ) : BindingType

    }
}

data class RenderBundleEncoderDescriptor(
    val label: String? = null,
    val colorFormats: List<TextureFormat>,
    val depthStencilFormat: TextureFormat,
    val sampleCount: GPUSize32 = 1u,
    val depthReadOnly: Boolean = false,
    val stencilReadOnly: Boolean = false,
)

data class QuerySetDescriptor(
    val label: String? = null,
    val type: QueryType,
    val count: GPUSize32,
)

data class CommandEncoderDescriptor(val label: String? = null)