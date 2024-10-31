package io.ygdrasil.wgpu

import ffi.memoryScope
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.mapper.map
import webgpu.WGPUCommandEncoderDescriptor
import webgpu.WGPUDevice
import webgpu.WGPUSupportedLimits
import webgpu.wgpuAdapterGetLimits
import webgpu.wgpuDeviceCreateBindGroup
import webgpu.wgpuDeviceCreateBindGroupLayout
import webgpu.wgpuDeviceCreateBuffer
import webgpu.wgpuDeviceCreateCommandEncoder
import webgpu.wgpuDeviceCreateComputePipeline
import webgpu.wgpuDeviceCreatePipelineLayout
import webgpu.wgpuDeviceCreateQuerySet
import webgpu.wgpuDeviceCreateRenderBundleEncoder
import webgpu.wgpuDeviceCreateRenderPipeline
import webgpu.wgpuDeviceCreateSampler
import webgpu.wgpuDeviceCreateShaderModule
import webgpu.wgpuDeviceCreateTexture
import webgpu.wgpuDeviceGetQueue
import webgpu.wgpuDeviceHasFeature
import webgpu.wgpuDeviceRelease
import java.lang.foreign.MemorySegment

actual class Device(internal val handler: WGPUDevice) : AutoCloseable {

    actual val queue: Queue by lazy { Queue(wgpuDeviceGetQueue(handler) ?: error("fail to get device queue")) }

    actual val features: Set<Feature> by lazy {
        Feature.entries
            .mapNotNull { feature ->
                feature.takeIf { wgpuDeviceHasFeature(handler, feature.value) == 1 }
            }
            .toSet()
    }

    actual val limits: Limits = memoryScope { scope ->
        val supportedLimits = WGPUSupportedLimits.allocate(scope)
        wgpuAdapterGetLimits(handler, supportedLimits)
        map(supportedLimits.limits)
    }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder = memoryScope { scope ->
        WGPUCommandEncoderDescriptor.allocate(scope)
            .let { wgpuDeviceCreateCommandEncoder(handler, it) }
            ?.let(::CommandEncoder) ?: error("fail to create command encoder")
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreateShaderModule(handler, it) }
            ?.let(::ShaderModule) ?: error("fail to create shader module")
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreatePipelineLayout(handler, it) }
            ?.let(::PipelineLayout) ?: error("fail to create pipeline layout")
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreateRenderPipeline(handler, it) }
            ?.let(::RenderPipeline) ?: error("fail to create render pipeline")
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreateBuffer(handler, it) }
            ?.let(::Buffer) ?: error("fail to create buffer")
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreateBindGroup(handler, it) }
            ?.let(::BindGroup) ?: error("fail to create bind group")
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreateTexture(handler, it) }
            ?.let(::Texture) ?: error("fail to create texture")
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreateSampler(handler, it) }
            ?.let(::Sampler) ?: error("fail to create sampler")
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreateComputePipeline(handler, it) }
            ?.let(::ComputePipeline) ?: error("fail to create compute pipeline")
    }

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreateBindGroupLayout(handler, it) }
            ?.let(::BindGroupLayout) ?: error("fail to create bind group layout")
    }

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder =
        confined { arena ->
            arena.map(descriptor)
                .let { wgpuDeviceCreateRenderBundleEncoder(handler, it) }
                ?.let(::RenderBundleEncoder) ?: error("fail to create bind group layout")
        }

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet = confined { arena ->
        arena.map(descriptor)
            .let { wgpuDeviceCreateQuerySet(handler, it) }
            ?.let(::QuerySet) ?: error("fail to create bind group layout")
    }

    actual suspend fun poll() {
        wgpu_h.wgpuDevicePoll(handler, 1, MemorySegment.NULL)
    }

    actual override fun close() {
        wgpuDeviceRelease(handler)
    }

}