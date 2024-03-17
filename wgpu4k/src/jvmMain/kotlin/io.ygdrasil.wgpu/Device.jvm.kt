package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.bindGroupDescriptorMapper
import io.ygdrasil.wgpu.mapper.renderPipelineDescriptorMapper
import io.ygdrasil.wgpu.mapper.samplerDescriptorMapper
import io.ygdrasil.wgpu.mapper.textureDescriptorMapper

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
        renderPipelineDescriptorMapper.map<Any, WGPURenderPipelineDescriptor>(descriptor)
            .let { wgpuDeviceCreateRenderPipeline(handler, it) }
            ?.let(::RenderPipeline) ?: error("fail to create render pipeline")

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer =
        descriptor.convert()
            .let { wgpuDeviceCreateBuffer(handler, it) }
            ?.let(::Buffer) ?: error("fail to create buffer")

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup =
        bindGroupDescriptorMapper.map<Any, WGPUBindGroupDescriptor>(descriptor)
            .let { wgpuDeviceCreateBindGroup(handler, it) }
            ?.let(::BindGroup) ?: error("fail to create bind group")

    actual fun createTexture(descriptor: TextureDescriptor): Texture =
        textureDescriptorMapper.map<Any, WGPUTextureDescriptor>(descriptor)
            .let { wgpuDeviceCreateTexture(handler, it) }
            ?.let(::Texture) ?: error("fail to create texture")

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler =
        samplerDescriptorMapper.map<Any, WGPUSamplerDescriptor>(descriptor)
            .let { wgpuDeviceCreateSampler(handler, it) }
            ?.let(::Sampler) ?: error("fail to create texture")

    override fun close() {
        wgpuDeviceRelease(handler)
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
