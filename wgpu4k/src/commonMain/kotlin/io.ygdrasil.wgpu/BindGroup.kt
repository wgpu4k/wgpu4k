package io.ygdrasil.wgpu

expect class BindGroup : AutoCloseable {

    override fun close()
}

data class BindGroupDescriptor(
    val layout: BindGroupLayout,
    val entries: List<BindGroupEntry>,
    val label: String? = null,
) {

    data class BindGroupEntry(
        val binding: GPUIndex32,
        //TODO support GPUExternalTexture
        val resource: BindGroupResource,
    )

    sealed interface BindGroupResource
    data class BufferBinding(
        val buffer: Buffer,
        val offset: GPUSize64 = 0,
        val size: GPUSize64 = buffer.size,
    ) : BindGroupResource

    data class SamplerBinding(
        val sampler: Sampler,
    ) : BindGroupResource

    data class TextureViewBinding(
        val view: TextureView,
    ) : BindGroupResource

}

