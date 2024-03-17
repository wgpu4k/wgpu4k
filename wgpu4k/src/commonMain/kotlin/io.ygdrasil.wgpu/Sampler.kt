@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

/**
 * A GPUSampler encodes transformations and filtering information that can be used in a shader to interpret texture resource data.
 *
 * @see <a href="https://www.w3.org/TR/webgpu/#gpusampler">W3C specifications</a>
 */
expect class Sampler : AutoCloseable {
}

/**
 * @see <a href="https://www.w3.org/TR/webgpu/#GPUSamplerDescriptor">W3C specifications</a>
 */
class SamplerDescriptor(
	var addressModeU: AddressMode = AddressMode.clamptoedge,
	var addressModeV: AddressMode = AddressMode.clamptoedge,
	var addressModeW: AddressMode = AddressMode.clamptoedge,
	var magFilter: FilterMode = FilterMode.nearest,

	var minFilter: FilterMode = FilterMode.nearest,
	var mipmapFilter: MipmapFilterMode = MipmapFilterMode.nearest,
	var lodMinClamp: Float = 0f,
	var lodMaxClamp: Float = 32f,
	var compare: CompareFunction? = null,
	var maxAnisotropy: Byte = 1,
	var label: String? = null,
)