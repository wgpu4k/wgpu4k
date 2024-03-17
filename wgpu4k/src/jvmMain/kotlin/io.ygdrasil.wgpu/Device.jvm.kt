package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.renderPipelineDescriptorMapper

actual class Device(internal val handler: WGPUDeviceImpl) : AutoCloseable {

	actual val queue: Queue by lazy { Queue(wgpuDeviceGetQueue(handler) ?: error("fail to get device queue")) }

	actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder =
		wgpuDeviceCreateCommandEncoder(handler, descriptor?.convert())
			?.let(::CommandEncoder) ?: error("fail to create command encoder")

	actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule =
		wgpuDeviceCreateShaderModule(handler, descriptor.convert())
			?.let(::ShaderModule) ?: error("fail to create shader module")

	actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout =
		wgpuDeviceCreatePipelineLayout(handler, descriptor.convert())
			?.let(::PipelineLayout) ?: error("fail to create pipeline layout")

	actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline =
			descriptor.let<Any, WGPURenderPipelineDescriptor>(renderPipelineDescriptorMapper::map)
			.let { wgpuDeviceCreateRenderPipeline(handler, it) }
			?.let(::RenderPipeline) ?: error("fail to create render pipeline")

	actual fun createBuffer(descriptor: BufferDescriptor): Buffer =
		descriptor.convert()
			.let { wgpuDeviceCreateBuffer(handler, it) }
			?.let(::Buffer) ?: error("fail to create buffer")

	actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup =
		bindGroupDescriptorMapper.map<BindGroupDescriptor, WGPUBindGroupDescriptor>(descriptor)
			.let { wgpuDeviceCreateBindGroup(handler, it) }
			?.let(::BindGroup) ?: error("fail to create bind group")

	actual fun createTexture(descriptor: TextureDescriptor): Texture =
		textureDescriptorMapper.map<TextureDescriptor, WGPUTextureDescriptor>(descriptor)
			.let { wgpuDeviceCreateTexture(handler, it) }
			?.let(::Texture) ?: error("fail to create texture")

	actual fun createSampler(descriptor: SamplerDescriptor): Sampler =
		samplerDescriptorMapper.map<SamplerDescriptor, WGPUSamplerDescriptor>(descriptor)
			.let { wgpuDeviceCreateSampler(handler, it) }
			?.let(::Sampler) ?: error("fail to create texture")

	override fun close() {
		wgpuDeviceRelease(handler)
	}

}

internal val textureDescriptorMapper = mapper<TextureDescriptor, WGPUTextureDescriptor> {
	TextureDescriptor::format mappedTo WGPUTextureDescriptor::format withTransformer EnumerationTransformer()
	TextureDescriptor::dimension mappedTo WGPUTextureDescriptor::dimension withTransformer EnumerationTransformer()
	TextureDescriptor::size mappedTo WGPUTextureDescriptor::size withTransformer GPUExtent3DDictStrictTransformer()
}

private val samplerDescriptorMapper = mapper<SamplerDescriptor, WGPUSamplerDescriptor> { }

private val bindGroupDescriptorMapper = mapper<BindGroupDescriptor, WGPUBindGroupDescriptor> {
	BindGroupDescriptor::layout mappedTo WGPUBindGroupDescriptor::layout withTransformer BindGroupLayoutTransformer()
	BindGroupDescriptor::entries mappedTo WGPUBindGroupDescriptor::entries withTransformer MappingTransformer<Array<BindGroupDescriptor.BindGroupEntry>, Array<WGPUBindGroupEntry.ByReference>> { context ->
		context.originalValue?.toStructureArray { bindGroupEntry ->
			binding = bindGroupEntry.binding
			when (val resource = bindGroupEntry.resource) {
				is BindGroupDescriptor.BufferBinding -> {
					size = resource.size
					offset = resource.offset
					buffer = resource.buffer.handler
				}

				is BindGroupDescriptor.SamplerBinding -> sampler = resource.sampler.handler
				is BindGroupDescriptor.TextureViewBinding -> textureView = resource.view.handler
			}
		}
	}
}


private fun BufferDescriptor.convert(): WGPUBufferDescriptor = WGPUBufferDescriptor().also {
	it.usage = usage
	it.size = size
	it.mappedAtCreation = mappedAtCreation?.toInt()
}

private fun PipelineLayoutDescriptor.convert(): WGPUPipelineLayoutDescriptor = WGPUPipelineLayoutDescriptor().also {
	it.label = label
	// TODO find how to map this
	//it.bindGroupLayoutCount = bindGroupLayouts.size.toLong().let { NativeLong(it) }
	//it.bindGroupLayouts = bindGroupLayouts.map { it.convert() }.toTypedArray()
}

private fun CommandEncoderDescriptor.convert(): WGPUCommandEncoderDescriptor = WGPUCommandEncoderDescriptor().also {
	it.label = label
}
