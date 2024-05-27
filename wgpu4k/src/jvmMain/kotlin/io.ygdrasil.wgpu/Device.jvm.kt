package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUCommandEncoderDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUPipelineLayoutDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.MemorySegment

actual class Device(internal val handler: MemorySegment) : AutoCloseable {
    
    actual val queue: Queue by lazy { Queue(wgpu_h.wgpuDeviceGetQueue(handler) ?: error("fail to get device queue")) }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder =confined { arena ->
        WGPUCommandEncoderDescriptor.allocate(arena)
            .let { wgpu_h.wgpuDeviceCreateCommandEncoder(handler, it) }
            ?.let(::CommandEncoder) ?: error("fail to create command encoder")
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule = confined { arena ->
        arena.map(descriptor)
            .let { wgpu_h.wgpuDeviceCreateShaderModule(handler, it) }
            ?.let(::ShaderModule) ?: error("fail to create shader module")
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout = confined { arena ->
        WGPUPipelineLayoutDescriptor.allocate(arena)
            .let { wgpu_h.wgpuDeviceCreatePipelineLayout(handler, it) }
            ?.let(::PipelineLayout) ?: error("fail to create pipeline layout")
}

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = confined { arena ->
        arena.map(descriptor)
            .let {  wgpu_h.wgpuDeviceCreateRenderPipeline(handler, it) }
            ?.let(::RenderPipeline) ?: error("fail to create render pipeline")
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer = confined { arena ->
        arena.map(descriptor)
            .let { wgpu_h.wgpuDeviceCreateBuffer(handler, it) }
            ?.let(::Buffer) ?: error("fail to create buffer")
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup = confined { arena ->
        arena.map(descriptor)
            .let { wgpu_h.wgpuDeviceCreateBindGroup(handler, it) }
            ?.let(::BindGroup) ?: error("fail to create bind group")
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture = confined { arena ->
        arena.map(descriptor)
            .let { wgpu_h.wgpuDeviceCreateTexture(handler, it) }
            ?.let(::Texture) ?: error("fail to create texture")
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler = confined { arena ->
        arena.map(descriptor)
            .let { wgpu_h.wgpuDeviceCreateSampler(handler, it) }
            ?.let(::Sampler) ?: error("fail to create texture")
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline {
        TODO()
    }

    actual override fun close() {
        wgpu_h.wgpuDeviceRelease(handler)
    }

}