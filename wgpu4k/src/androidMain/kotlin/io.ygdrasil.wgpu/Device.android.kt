package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.jna.WGPUCommandEncoderDescriptor
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.mapper.map

actual class Device(val handler: Long) : AutoCloseable {

    actual val queue: Queue by lazy {
        Queue(JnaInterface.wgpuDeviceGetQueue(handler))
    }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder = scoped { arena ->
        WGPUCommandEncoderDescriptor.allocate(arena).pointer.toAddress()
            .let { JnaInterface.wgpuDeviceCreateCommandEncoder(handler, it) }
            .let(::CommandEncoder)
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreateShaderModule(handler, it) }
            .let(::ShaderModule)
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreatePipelineLayout(handler, it) }
            .let(::PipelineLayout)
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreateRenderPipeline(handler, it) }
            .let(::RenderPipeline)
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreateBuffer(handler, it) }
            .let(::Buffer)
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreateBindGroup(handler, it) }
            .let(::BindGroup)
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreateTexture(handler, it) }
            .let(::Texture)
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreateSampler(handler, it) }
            .let(::Sampler)
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreateComputePipeline(handler, it) }
            .let(::ComputePipeline)
    }

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreateBindGroupLayout(handler, it) }
            .let(::BindGroupLayout)
    }

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder =
        scoped { arena ->
            arena.map(descriptor)
                .let { JnaInterface.wgpuDeviceCreateRenderBundleEncoder(handler, it) }
            .let(::RenderBundleEncoder)
    }

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuDeviceCreateQuerySet(handler, it) }
            .let(::QuerySet)
    }

    actual suspend fun poll() {
        JnaInterface.wgpuDevicePoll(handler, 1, 0L)
    }

    actual override fun close() {
        JnaInterface.wgpuDeviceRelease(handler)
    }

}