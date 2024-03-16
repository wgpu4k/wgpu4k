package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.internal.jvm.*

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
		descriptor.convert()
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

private val textureDescriptorMapper = mapper<TextureDescriptor, WGPUTextureDescriptor> {
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

private fun RenderPipelineDescriptor.VertexState.VertexBufferLayout.convert(): WGPUVertexBufferLayout.ByReference =
	WGPUVertexBufferLayout.ByReference().also {
		it.arrayStride = arrayStride
		it.attributeCount = attributes.size.toNativeLong()
		it.attributes = WGPUVertexAttribute.ByReference()
			.toArray(attributes.size)
			.let { it as Array<WGPUVertexAttribute.ByReference> }
			.also {
				it.forEachIndexed { index, structure -> structure.updateFrom(attributes[index]) }
			}

		it.stepMode = stepMode?.value
	}

private fun WGPUVertexAttribute.ByReference.updateFrom(vertexAttribute: RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute) {
	format = vertexAttribute.format.value
	offset = vertexAttribute.offset
	shaderLocation = vertexAttribute.shaderLocation
}

private fun RenderPipelineDescriptor.convert(): WGPURenderPipelineDescriptor = WGPURenderPipelineDescriptor().also {
	it.vertex = WGPUVertexState().also { wGPUVertexState ->
		wGPUVertexState.module = vertex.module.handler
		wGPUVertexState.entryPoint = vertex.entryPoint ?: "main"
		wGPUVertexState.bufferCount = (vertex.buffers?.size ?: 0).toNativeLong()
		wGPUVertexState.buffers = if (wGPUVertexState.bufferCount.toLong() == 0L) {
			arrayOf(WGPUVertexBufferLayout.ByReference())
		} else {
			vertex.buffers?.map { it.convert() }?.toTypedArray()
		}
	}
	it.layout = layout?.handler
	it.label = label
	it.primitive = WGPUPrimitiveState().also { wgpuPrimitiveState ->
		wgpuPrimitiveState.topology = primitive?.topology?.value
		wgpuPrimitiveState.stripIndexFormat = primitive?.stripIndexFormat?.value
		wgpuPrimitiveState.frontFace = primitive?.frontFace?.value
		wgpuPrimitiveState.cullMode = primitive?.cullMode?.value
		// TODO find how to map this
		//wgpuPrimitiveState.unclippedDepth = primitive.unclippedDepth
	}


//	it.depthStencil = this@convert.depthStencil?.convert()
	it.fragment = fragment?.convert()

	it.multisample = WGPUMultisampleState().also { wgpuMultisampleState ->
		wgpuMultisampleState.count = multisample?.count
		wgpuMultisampleState.mask = multisample?.mask
		wgpuMultisampleState.alphaToCoverageEnabled = multisample?.alphaToCoverageEnabled?.let {
			if (it) 1 else 0
		}
	}
}

private fun RenderPipelineDescriptor.FragmentState.convert(): WGPUFragmentState.ByReference =
	WGPUFragmentState.ByReference().also {
		it.module = module.handler
		it.entryPoint = entryPoint ?: "main"
		it.targetCount = targets.filterNotNull().size.toLong().let { NativeLong(it) }
		it.targets = targets.filterNotNull().map { it.convert() }.toTypedArray()
	}

private fun RenderPipelineDescriptor.FragmentState.ColorTargetState.convert(): WGPUColorTargetState.ByReference =
	WGPUColorTargetState.ByReference().also {
		it.format = format.value
		it.blend = blend?.convert()
		it.writeMask = writeMask?.value
	}

private fun RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.convert(): Pointer? {
	TODO("Not yet implemented")
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
