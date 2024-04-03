package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.bindGroupDescriptorMapper
import io.ygdrasil.wgpu.mapper.renderPipelineDescriptorMapper
import io.ygdrasil.wgpu.mapper.samplerDescriptorMapper
import io.ygdrasil.wgpu.mapper.textureDescriptorMapper

actual class Device(internal val handler: WGPUDeviceImpl) : AutoCloseable {

    actual val queue: Queue by lazy { Queue(wgpuDeviceGetQueue(handler) ?: error("fail to get device queue")) }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder =
            descriptor?.convert()
                .also { logNative { "wgpuDeviceCreateCommandEncoder" to listOf(handler, it) } }
                    .let { wgpuDeviceCreateCommandEncoder(handler, it) }
                    ?.let(::CommandEncoder) ?: error("fail to create command encoder")

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule =
            descriptor.convert()
                .also { logNative { "wgpuDeviceCreateShaderModule" to listOf(handler, it) } }
                    .let { wgpuDeviceCreateShaderModule(handler, it) }
                    ?.let(::ShaderModule) ?: error("fail to create shader module")

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout =
            descriptor.convert()
                .also { logNative { "wgpuDeviceCreatePipelineLayout" to listOf(handler, it) } }
                    .let { wgpuDeviceCreatePipelineLayout(handler, it) }
                    ?.let(::PipelineLayout) ?: error("fail to create pipeline layout")

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline =
            renderPipelineDescriptorMapper.map<Any, WGPURenderPipelineDescriptor>(descriptor)
                    .also { it.write() }
                .also { logNative { "wgpuDeviceCreateRenderPipeline" to listOf(handler, it) } }
                    .let { wgpuDeviceCreateRenderPipeline(handler, it) }
                    ?.let(::RenderPipeline) ?: error("fail to create render pipeline")

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer =
            descriptor.convert()
                .also { logNative { "wgpuDeviceCreateBuffer" to listOf(handler, it) } }
                    .let { wgpuDeviceCreateBuffer(handler, it) }
                    ?.let(::Buffer) ?: error("fail to create buffer")

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup =
            bindGroupDescriptorMapper.map<Any, WGPUBindGroupDescriptor>(descriptor)
                .also { logNative { "wgpuDeviceCreateBindGroup" to listOf(handler, it) } }
                    .let { wgpuDeviceCreateBindGroup(handler, it) }
                    ?.let(::BindGroup) ?: error("fail to create bind group")

    actual fun createTexture(descriptor: TextureDescriptor): Texture =
            textureDescriptorMapper.map<Any, WGPUTextureDescriptor>(descriptor)
                .also { logNative { "wgpuDeviceCreateTexture" to listOf(handler, it) } }
                    .let { wgpuDeviceCreateTexture(handler, it) }
                    ?.let(::Texture) ?: error("fail to create texture")

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler =
            samplerDescriptorMapper.map<Any, WGPUSamplerDescriptor>(descriptor)
                .also { logNative { "wgpuDeviceCreateSampler" to listOf(handler, it) } }
                    .let { wgpuDeviceCreateSampler(handler, it) }
                    ?.let(::Sampler) ?: error("fail to create texture")

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline {
        TODO()
    }

    override fun close() {
        logNative { "wgpuDeviceRelease" to listOf(handler) }
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
