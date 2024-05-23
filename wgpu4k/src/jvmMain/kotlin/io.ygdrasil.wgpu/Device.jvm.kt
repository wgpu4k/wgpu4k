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
            .also { logUnitNative { "wgpuDeviceCreateShaderModule" to listOf(handler2, it) } }
            .let { wgpuDeviceCreateShaderModule(handler2, it) }
            ?.let(::ShaderModule) ?: error("fail to create shader module")

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout =
        descriptor.convert()
            .also { logUnitNative { "wgpuDeviceCreatePipelineLayout" to listOf(handler2, it) } }
            .let { wgpuDeviceCreatePipelineLayout(handler2, it) }
            ?.let(::PipelineLayout) ?: error("fail to create pipeline layout")

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline =
        renderPipelineDescriptorMapper.map<Any, WGPURenderPipelineDescriptor>(descriptor)
            .also { it.write() }
            .also {
                logNative {
                    Triple(
                        "wgpuDeviceCreateRenderPipeline",
                        listOf(handler2, it),
                        WGPURenderPipeline::class
                    )
                }
            }
            .let { wgpuDeviceCreateRenderPipeline(handler2, it) }
            .also { registerNative { it } }
            ?.let(::RenderPipeline) ?: error("fail to create render pipeline")

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer =
        descriptor.convert()
            .also { logNative { Triple("wgpuDeviceCreateBuffer", listOf(handler2, it), WGPUBuffer::class) } }
            .let { wgpuDeviceCreateBuffer(handler2, it) }
            .also { registerNative { it } }
            ?.let(::Buffer) ?: error("fail to create buffer")


    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup =
        bindGroupDescriptorMapper.map<Any, WGPUBindGroupDescriptor>(descriptor)
            .also { logUnitNative { "wgpuDeviceCreateBindGroup" to listOf(handler2, it) } }
            .also { it.write() }
            .let { wgpuDeviceCreateBindGroup(handler2, it) }
            ?.let(::BindGroup) ?: error("fail to create bind group")

    actual fun createTexture(descriptor: TextureDescriptor): Texture =
        textureDescriptorMapper.map<Any, WGPUTextureDescriptor>(descriptor)
            .also { logUnitNative { "wgpuDeviceCreateTexture" to listOf(handler2, it) } }
            .let { wgpuDeviceCreateTexture(handler2, it)?.pointer?.toMemory() }
            ?.let(::Texture) ?: error("fail to create texture")

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler =
        samplerDescriptorMapper.map<Any, WGPUSamplerDescriptor>(descriptor)
            .also { logUnitNative { "wgpuDeviceCreateSampler" to listOf(handler2, it) } }
            .let { wgpuDeviceCreateSampler(handler2, it) }
            ?.let(::Sampler) ?: error("fail to create texture")

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline {
        TODO()
    }

    actual override fun close() {
        logUnitNative { "wgpuDeviceRelease" to listOf(handler2) }
        wgpuDeviceRelease(handler2)
    }

}

private fun BufferDescriptor.convert(): WGPUBufferDescriptor = WGPUBufferDescriptor().also {
    it.usage = usage
    it.size = size
    it.mappedAtCreation = mappedAtCreation?.toInt()
}.also { it.write() }

private fun PipelineLayoutDescriptor.convert(): WGPUPipelineLayoutDescriptor = WGPUPipelineLayoutDescriptor().also {
    it.label = label
    // TODO find how to map this
    //it.bindGroupLayoutCount = bindGroupLayouts.size.toLong().let { NativeLong(it) }
    //it.bindGroupLayouts = bindGroupLayouts.map { it.convert() }.toTypedArray()
}.also { it.write() }

private fun CommandEncoderDescriptor.convert() = WGPUCommandEncoderDescriptor().also {
    it.label = label
}.also { it.write() }.pointer.toMemory()
