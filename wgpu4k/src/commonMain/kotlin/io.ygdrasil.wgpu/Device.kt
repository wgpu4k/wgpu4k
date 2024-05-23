package io.ygdrasil.wgpu

expect class Device: AutoCloseable {

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
}

// TODO
data class CommandEncoderDescriptor(var label: String? = null)