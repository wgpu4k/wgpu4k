package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import io.ygdrasil.wgpu.mapper.bindGroupDescriptorMapper
import io.ygdrasil.wgpu.mapper.renderPipelineDescriptorMapper
import io.ygdrasil.wgpu.mapper.samplerDescriptorMapper
import io.ygdrasil.wgpu.mapper.textureDescriptorMapper
import java.lang.foreign.MemorySegment

actual class Device(internal val handler: MemorySegment) : AutoCloseable {

    val handler2: WGPUDeviceImpl = WGPUDeviceImpl(handler.toPointer())

    actual val queue: Queue by lazy { Queue(wgpuDeviceGetQueue(handler2) ?: error("fail to get device queue")) }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder =
        descriptor?.convert()
            .let { webgpu_h.wgpuDeviceCreateCommandEncoder(handler, it ?: MemorySegment.NULL) }
            ?.let(::CommandEncoder) ?: error("fail to create command encoder")

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule =
        descriptor.convert()
            .let { webgpu_h.wgpuDeviceCreateShaderModule(handler, it) }
            ?.let(::ShaderModule) ?: error("fail to create shader module")

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout =
        descriptor.convert()
            .let { webgpu_h.wgpuDeviceCreatePipelineLayout(handler, it) }
            ?.let(::PipelineLayout) ?: error("fail to create pipeline layout")

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline =
        renderPipelineDescriptorMapper.map<Any, WGPURenderPipelineDescriptor>(descriptor)
            .also { it.write() }.pointer.toMemory()
            .let {  webgpu_h.wgpuDeviceCreateRenderPipeline(handler, it) }
            ?.let(::RenderPipeline) ?: error("fail to create render pipeline")

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer =
        descriptor.convert()
            .let { webgpu_h.wgpuDeviceCreateBuffer(handler, it) }
            ?.let(::Buffer) ?: error("fail to create buffer")


    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup =
        bindGroupDescriptorMapper.map<Any, WGPUBindGroupDescriptor>(descriptor)
            .also { it.write() }.pointer.toMemory()
            .let { webgpu_h.wgpuDeviceCreateBindGroup(handler, it) }
            ?.let(::BindGroup) ?: error("fail to create bind group")

    actual fun createTexture(descriptor: TextureDescriptor): Texture =
        textureDescriptorMapper.map<Any, WGPUTextureDescriptor>(descriptor)
            .also { it.write() }.pointer.toMemory()
            .let { webgpu_h.wgpuDeviceCreateTexture(handler, it) }
            ?.let(::Texture) ?: error("fail to create texture")

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler =
        samplerDescriptorMapper.map<Any, WGPUSamplerDescriptor>(descriptor)
            .also { it.write() }.pointer.toMemory()
            .let { webgpu_h.wgpuDeviceCreateSampler(handler, it) }
            ?.let(::Sampler) ?: error("fail to create texture")

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline {
        TODO()
    }

    actual override fun close() {
        webgpu_h.wgpuDeviceRelease(handler)
    }

}

private fun BufferDescriptor.convert() = WGPUBufferDescriptor().also {
    it.usage = usage
    it.size = size
    it.mappedAtCreation = mappedAtCreation?.toInt()
}.also { it.write() }.pointer.toMemory()

private fun PipelineLayoutDescriptor.convert() = WGPUPipelineLayoutDescriptor().also {
    it.label = label
    // TODO find how to map this
    //it.bindGroupLayoutCount = bindGroupLayouts.size.toLong().let { NativeLong(it) }
    //it.bindGroupLayouts = bindGroupLayouts.map { it.convert() }.toTypedArray()
}.also { it.write() }.pointer.toMemory()

private fun CommandEncoderDescriptor.convert() = WGPUCommandEncoderDescriptor().also {
    it.label = label
}.also { it.write() }.pointer.toMemory()
