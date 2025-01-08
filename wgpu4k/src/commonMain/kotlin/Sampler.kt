package io.ygdrasil.webgpu

/**
 * A GPUSampler encodes transformations and filtering information that can be used in a shader to interpret texture resource data.
 *
 * @see <a href="https://www.w3.org/TR/webgpu/#gpusampler">W3C specifications</a>
 */
expect class Sampler : AutoCloseable {

    override fun close()
}

/**
 * A GPUSamplerDescriptor specifies the options to use to create a GPUSampler.
 *
 * @see <a href="https://www.w3.org/TR/webgpu/#GPUSamplerDescriptor">W3C specifications</a>
 */
data class SamplerDescriptor(
    val addressModeU: AddressMode = AddressMode.ClampToEdge,
    val addressModeV: AddressMode = AddressMode.ClampToEdge,
    val addressModeW: AddressMode = AddressMode.ClampToEdge,
    val magFilter: FilterMode = FilterMode.Nearest,

    val minFilter: FilterMode = FilterMode.Nearest,
    val mipmapFilter: MipmapFilterMode = MipmapFilterMode.Nearest,
    val lodMinClamp: Float = 0f,
    val lodMaxClamp: Float = 32f,
    val compare: CompareFunction? = null,
    val maxAnisotropy: UShort = 1u,
    val label: String? = null,
)