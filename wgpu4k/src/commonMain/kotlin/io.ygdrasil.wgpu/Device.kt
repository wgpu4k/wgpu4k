package io.ygdrasil.wgpu

expect class Device : AutoCloseable {

    val queue: Queue

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
    val entries: Array<Entry>,
    val label: String? = null,
) {
    data class Entry(
        val binding: GPUIndex32,
        val visibility: Set<ShaderStage>,
        val bindingType: BindingType,
    ) {

        sealed interface BindingType

        data class BufferBindingLayout(
            var type: BufferBindingType = BufferBindingType.uniform,
            var hasDynamicOffset: Boolean = false,
            var minBindingSize: GPUSize64 = 0,
        ) : BindingType

        data class SamplerBindingLayout(
            var type: SamplerBindingType = SamplerBindingType.filtering,
        ) : BindingType

        data class TextureBindingLayout(
            var sampleType: TextureSampleType = TextureSampleType.float,
            var viewDimension: TextureViewDimension = TextureViewDimension._2d,
            var multisampled: Boolean = false,
        ) : BindingType

        data class StorageTextureBindingLayout(
            var format: TextureFormat,
            var access: StorageTextureAccess = StorageTextureAccess.writeonly,
            var viewDimension: TextureViewDimension = TextureViewDimension._2d,
        ) : BindingType

    }
}

data class RenderBundleEncoderDescriptor(
    val label: String? = null,
    val colorFormats: Array<TextureFormat>,
    val depthStencilFormat: TextureFormat,
    val sampleCount: GPUSize32 = 1,
    val depthReadOnly: Boolean = false,
    val stencilReadOnly: Boolean = false,
)

// TODO
data class QuerySetDescriptor(val label: String? = null)
data class CommandEncoderDescriptor(val label: String? = null)