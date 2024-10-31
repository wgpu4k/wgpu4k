package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jna.WGPUCommandEncoderDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUSupportedLimits
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.mapper.map
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual class Device(val handler: Long) : AutoCloseable {

    actual val queue: Queue by lazy {
        Queue(NativeWgpu4k.wgpuDeviceGetQueue(handler))
    }

    actual val limits: Limits = scoped { arena ->
        val supportedLimits = WGPUSupportedLimits.allocate(arena)
        NativeWgpu4k.wgpuAdapterGetLimits(handler, supportedLimits.pointer.toAddress())
        map(WGPUSupportedLimits.limits(supportedLimits))
    }

    actual val features: Set<Feature> by lazy {
        Feature.entries
            .mapNotNull { feature ->
                feature.takeIf { NativeWgpu4k.wgpuDeviceHasFeature(handler, feature.value) == 1 }
            }
            .toSet()
    }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder = scoped { arena ->
        WGPUCommandEncoderDescriptor.allocate(arena).pointer.toAddress()
            .let { NativeWgpu4k.wgpuDeviceCreateCommandEncoder(handler, it) }
            .let(::CommandEncoder)
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreateShaderModule(handler, it) }
            .let(::ShaderModule)
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreatePipelineLayout(handler, it) }
            .let(::PipelineLayout)
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreateRenderPipeline(handler, it) }
            .let(::RenderPipeline)
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreateBuffer(handler, it) }
            .let(::Buffer)
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreateBindGroup(handler, it) }
            .let(::BindGroup)
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreateTexture(handler, it) }
            .let(::Texture)
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreateSampler(handler, it) }
            .let(::Sampler)
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreateComputePipeline(handler, it) }
            .let(::ComputePipeline)
    }

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreateBindGroupLayout(handler, it) }
            .let(::BindGroupLayout)
    }

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder =
        scoped { arena ->
            arena.map(descriptor)
                .let { NativeWgpu4k.wgpuDeviceCreateRenderBundleEncoder(handler, it) }
            .let(::RenderBundleEncoder)
    }

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuDeviceCreateQuerySet(handler, it) }
            .let(::QuerySet)
    }

    actual suspend fun poll() {
        NativeWgpu4k.wgpuDevicePoll(handler, 1, 0L)
    }

    actual override fun close() {
        NativeWgpu4k.wgpuDeviceRelease(handler)
    }

}