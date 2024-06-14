

package io.ygdrasil.wgpu

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
	val addressModeU: AddressMode = AddressMode.clamptoedge,
	val addressModeV: AddressMode = AddressMode.clamptoedge,
	val addressModeW: AddressMode = AddressMode.clamptoedge,
	val magFilter: FilterMode = FilterMode.nearest,

	val minFilter: FilterMode = FilterMode.nearest,
	val mipmapFilter: MipmapFilterMode = MipmapFilterMode.nearest,
	val lodMinClamp: Float = 0f,
	val lodMaxClamp: Float = 32f,
	val compare: CompareFunction? = null,
	val maxAnisotropy: Byte = 1,
	val label: String? = null,
)