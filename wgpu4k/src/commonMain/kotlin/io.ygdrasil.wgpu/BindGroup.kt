@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

expect class BindGroup : AutoCloseable {
}

data class BindGroupDescriptor(
	var layout: BindGroupLayout,
	var entries: Array<BindGroupEntry>,
	var label: String? = null
) {

	data class BindGroupEntry(
		var binding: GPUIndex32,
		//TODO support GPUExternalTexture
		var resource: BindGroupResource
	)

	sealed interface BindGroupResource
	data class BufferBinding(
		var buffer: Buffer,
		var offset: GPUSize64? = null,
		var size: GPUSize64? = buffer.size
	) : BindGroupResource

	data class SamplerBinding(
		var sampler: Sampler
	) : BindGroupResource

	data class TextureViewBinding(
		var view: TextureView
	) : BindGroupResource

}

