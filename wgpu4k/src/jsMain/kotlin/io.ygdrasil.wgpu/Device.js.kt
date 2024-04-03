

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.*

@JsExport
actual class Device(val handler: GPUDevice) : AutoCloseable {

	actual val queue: Queue by lazy { Queue(handler.queue) }

	actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder {
		return CommandEncoder(
			when (descriptor) {
				null -> handler.createCommandEncoder()
				else -> handler.createCommandEncoder(descriptor.convert())
			}
		)
	}

	actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule {
		return ShaderModule(handler.createShaderModule(descriptor.convert()))
	}

	actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout = handler
		.createPipelineLayout(descriptor.convert())
		.let(::PipelineLayout)

	actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = handler
		.createRenderPipeline(descriptor.convert())
		.let(::RenderPipeline)


	actual fun createBuffer(descriptor: BufferDescriptor): Buffer = handler
		.createBuffer(descriptor.convert())
		.let(::Buffer)

	actual fun createTexture(descriptor: TextureDescriptor): Texture = handler
		.createTexture(descriptor.convert())
		.let(::Texture)

	actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup =
		descriptor.convert()
			.let { handler.createBindGroup(it) }
			.let(::BindGroup)

	actual fun createSampler(descriptor: SamplerDescriptor): Sampler =
		descriptor.convert()
			.let { handler.createSampler(it) }
			.let(::Sampler)

	actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline =
		descriptor.convert()
			.let { handler.createComputePipeline(it) }
			.let(::ComputePipeline)


	override fun close() {
		// Nothing on JS
	}
}

private fun ComputePipelineDescriptor.convert(): GPUComputePipelineDescriptor = object : GPUComputePipelineDescriptor {
	override var compute: GPUProgrammableStage = this@convert.compute.convert()
	override var layout: dynamic = this@convert.layout?.convert() ?: "auto"
}

private fun PipelineLayout.convert(): dynamic {
	TODO()
}

private fun ComputePipelineDescriptor.ProgrammableStage.convert(): GPUProgrammableStage =
	object : GPUProgrammableStage {
		override var module: GPUShaderModule = this@convert.module.handler
		override var entryPoint: String? = this@convert.entryPoint ?: undefined
		override var constants: Map<String, GPUPipelineConstantValue>? = undefined//this@convert.constants
}

private fun SamplerDescriptor.convert(): GPUSamplerDescriptor = object : GPUSamplerDescriptor {
	override var label: String? = this@convert.label ?: undefined
	override var addressModeU: String? = this@convert.addressModeU.stringValue
	override var addressModeV: String? = this@convert.addressModeV.stringValue
	override var addressModeW: String? = this@convert.addressModeW.stringValue
	override var magFilter: String? = this@convert.magFilter.name
	override var minFilter: String? = this@convert.minFilter.name
	override var mipmapFilter: String? = this@convert.mipmapFilter.name
	override var lodMinClamp: Number? = this@convert.lodMinClamp
	override var lodMaxClamp: Number? = this@convert.lodMaxClamp
	override var compare: String? = this@convert.compare?.stringValue ?: undefined
	override var maxAnisotropy: Number? = this@convert.maxAnisotropy
}

private fun BindGroupDescriptor.convert(): GPUBindGroupDescriptor = object : GPUBindGroupDescriptor {
	override var label: String? = this@convert.label ?: undefined
	override var layout: GPUBindGroupLayout = this@convert.layout.handler
	override var entries: Array<GPUBindGroupEntry> = this@convert.entries.map { it.convert() }.toTypedArray()
}

private fun BindGroupDescriptor.BindGroupEntry.convert(): GPUBindGroupEntry = object : GPUBindGroupEntry {
	override var binding: GPUIndex32 = this@convert.binding
	override var resource: dynamic = when (val localResource = this@convert.resource) {
			is BindGroupDescriptor.SamplerBinding -> localResource.sampler.handler
			is BindGroupDescriptor.BufferBinding -> object : GPUBufferBinding {
				override var buffer: GPUBuffer = localResource.buffer.handler
				override var offset: GPUSize64? = localResource.offset
				override var size: GPUSize64? = localResource.size
			}

			is BindGroupDescriptor.TextureViewBinding -> localResource.view.handler
			else -> null
		}
}

private fun TextureDescriptor.convert(): GPUTextureDescriptor = object : GPUTextureDescriptor {
	override var label: String? = this@convert.label ?: undefined
	override var size: dynamic = this@convert.size
	override var mipLevelCount: GPUIntegerCoordinate? = this@convert.mipLevelCount
	override var sampleCount: GPUSize32? = this@convert.sampleCount
	override var dimension: String? = this@convert.dimension.stringValue
	override var format: String = this@convert.format.name
	override var usage: GPUTextureUsageFlags = this@convert.usage
	override var viewFormats: Array<String?>? = this@convert.viewFormats ?: undefined
}

private fun BufferDescriptor.convert(): GPUBufferDescriptor = object : GPUBufferDescriptor {
	override var size: GPUSize64 = this@convert.size
	override var usage: GPUBufferUsageFlags = this@convert.usage
	override var mappedAtCreation: Boolean? = this@convert.mappedAtCreation ?: undefined
}

/*** RenderPipelineDescriptor ***/

private fun RenderPipelineDescriptor.convert(): GPURenderPipelineDescriptor = object : GPURenderPipelineDescriptor {
	override var vertex: GPUVertexState = this@convert.vertex.convert()
	override var layout: dynamic = this@convert.layout?.handler ?: "auto"
	override var label: dynamic = this@convert.label ?: undefined
	override var primitive: GPUPrimitiveState? = this@convert.primitive?.convert() ?: undefined
	override var depthStencil: GPUDepthStencilState? = this@convert.depthStencil?.convert() ?: undefined
	override var fragment: GPUFragmentState? = this@convert.fragment?.convert() ?: undefined
	override var multisample: GPUMultisampleState? = this@convert.multisample?.convert() ?: undefined
}

private fun RenderPipelineDescriptor.VertexState.convert(): GPUVertexState =
	object : GPUVertexState {
		override var module: GPUShaderModule = this@convert.module.handler
		override var entryPoint: String? = this@convert.entryPoint ?: undefined

		//TODO check mapping
		//override var constants: Map<String, GPUPipelineConstantValue>? = null
		override var buffers: Array<GPUVertexBufferLayout?>? = this@convert.buffers
			?.map { it?.convert() }?.toTypedArray() ?: undefined
	}

private fun RenderPipelineDescriptor.VertexState.VertexBufferLayout.convert(): GPUVertexBufferLayout =
	object : GPUVertexBufferLayout {
		override var arrayStride: GPUSize64 = this@convert.arrayStride
		override var attributes: Array<GPUVertexAttribute> = this@convert.attributes
			.map { it.convert() }.toTypedArray()
		override var stepMode: String? = this@convert.stepMode?.name ?: undefined
	}

private fun RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute.convert(): GPUVertexAttribute =
	object : GPUVertexAttribute {
		override var format: String = this@convert.format.name
		override var offset: GPUSize64 = this@convert.offset
		override var shaderLocation: GPUIndex32 = this@convert.shaderLocation
	}

private fun RenderPipelineDescriptor.PrimitiveState.convert(): GPUPrimitiveState =
	object : GPUPrimitiveState {
		override var topology: String? = this@convert.topology?.stringValue ?: undefined
		override var stripIndexFormat: String? = this@convert.stripIndexFormat?.name ?: undefined
		override var frontFace: String? = this@convert.frontFace?.name ?: undefined
		override var cullMode: String? = this@convert.cullMode?.name ?: undefined
		override var unclippedDepth: Boolean? = this@convert.unclippedDepth ?: undefined
	}

private fun RenderPipelineDescriptor.DepthStencilState.convert(): GPUDepthStencilState =
	object : GPUDepthStencilState {
		override var format: String = this@convert.format.name
		override var depthWriteEnabled: Boolean? = this@convert.depthWriteEnabled ?: undefined
		override var depthCompare: String? = this@convert.depthCompare?.stringValue ?: undefined
		override var stencilFront: GPUStencilFaceState? = this@convert.stencilFront?.convert() ?: undefined
		override var stencilBack: GPUStencilFaceState? = this@convert.stencilBack?.convert() ?: undefined
		override var stencilReadMask: GPUStencilValue? = this@convert.stencilReadMask ?: undefined
		override var stencilWriteMask: GPUStencilValue? = this@convert.stencilWriteMask ?: undefined
		override var depthBias: GPUDepthBias? = this@convert.depthBias ?: undefined
		override var depthBiasSlopeScale: Float? = this@convert.depthBiasSlopeScale ?: undefined
		override var depthBiasClamp: Float? = this@convert.depthBiasClamp ?: undefined
	}

private fun RenderPipelineDescriptor.DepthStencilState.StencilFaceState.convert(): GPUStencilFaceState =
	object : GPUStencilFaceState {
		override var compare: String? = this@convert.compare?.stringValue ?: undefined
		override var failOp: String? = this@convert.failOp?.stringValue ?: undefined
		override var depthFailOp: String? = this@convert.depthFailOp?.stringValue ?: undefined
		override var passOp: String? = this@convert.passOp?.stringValue ?: undefined
	}

private fun RenderPipelineDescriptor.MultisampleState.convert(): GPUMultisampleState =
	object : GPUMultisampleState {
		override var count: dynamic = this@convert.count
		override var mask: dynamic = this@convert.mask
		override var alphaToCoverageEnabled: dynamic = this@convert.alphaToCoverageEnabled
	}

private fun RenderPipelineDescriptor.FragmentState.convert(): GPUFragmentState =
	object : GPUFragmentState {
		override var targets: Array<GPUColorTargetState?> = this@convert.targets.map { it?.convert() }.toTypedArray()
		override var module: GPUShaderModule = this@convert.module.handler
		override var entryPoint: String? = this@convert.entryPoint
		// TODO not sure how to map this
		//override var constants: Record<String, GPUPipelineConstantValue>? = TODO("Not yet implemented")
	}

private fun RenderPipelineDescriptor.FragmentState.ColorTargetState.convert(): GPUColorTargetState =
	object : GPUColorTargetState {
		override var format: String = this@convert.format.name
		override var blend: GPUBlendState? = this@convert.blend?.convert()
		override var writeMask: GPUColorWriteFlags? = this@convert.writeMask?.value
	}

private fun RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.convert(): GPUBlendState =
	object : GPUBlendState {
		override var color: GPUBlendComponent = this@convert.color.convert()
		override var alpha: GPUBlendComponent = this@convert.alpha.convert()
	}

private fun RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent.convert(): GPUBlendComponent =
	object : GPUBlendComponent {
		override var operation: String? = this@convert.operation.stringValue
		override var srcFactor: String? = this@convert.srcFactor.stringValue
		override var dstFactor: String? = this@convert.dstFactor.stringValue
	}

/*** PipelineLayoutDescriptor ***/

private fun PipelineLayoutDescriptor.convert(): GPUPipelineLayoutDescriptor = object : GPUPipelineLayoutDescriptor {
	override var label: String? = this@convert.label ?: undefined
	override var bindGroupLayouts: Array<GPUBindGroupLayout> = this@convert.bindGroupLayouts
		.map { it.convert() }.toTypedArray()
}

private fun PipelineLayoutDescriptor.BindGroupLayout.convert(): GPUBindGroupLayout =
	object : GPUBindGroupLayout {
		override var label: String = this@convert.label
		override var __brand: String = this@convert.brand
	}

private fun CommandEncoderDescriptor.convert(): GPUCommandEncoderDescriptor = object : GPUCommandEncoderDescriptor {
	override var label: String? = this@convert.label ?: undefined
}

