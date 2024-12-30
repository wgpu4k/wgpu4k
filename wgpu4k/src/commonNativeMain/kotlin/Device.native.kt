package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import webgpu.WGPUCommandEncoderDescriptor
import webgpu.WGPUDevice
import webgpu.WGPULimits
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
import webgpu.wgpuDeviceGetLimits
import webgpu.wgpuDeviceGetQueue
import webgpu.wgpuDeviceHasFeature
import webgpu.wgpuDeviceRelease

actual class Device(internal val handler: WGPUDevice) : AutoCloseable {

    actual val queue: Queue by lazy { Queue(wgpuDeviceGetQueue(handler) ?: error("fail to get device queue")) }

    actual val features: Set<Feature> by lazy {
        Feature.entries
            .mapNotNull { feature ->
                feature.takeIf { wgpuDeviceHasFeature(handler, feature.uValue) }
            }
            .toSet()
    }

    actual val limits: Limits = memoryScope { scope ->
        val supportedLimits = WGPULimits.allocate(scope)
        wgpuDeviceGetLimits(handler, supportedLimits)
        map(supportedLimits)
    }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder = memoryScope { scope ->
        WGPUCommandEncoderDescriptor.allocate(scope)
            .let { wgpuDeviceCreateCommandEncoder(handler, it) }
            ?.let(::CommandEncoder) ?: error("fail to create command encoder")
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateShaderModule(handler, it) }
            ?.let(::ShaderModule) ?: error("fail to create shader module")
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreatePipelineLayout(handler, it) }
            ?.let(::PipelineLayout) ?: error("fail to create pipeline layout")
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateRenderPipeline(handler, it) }
            ?.let(::RenderPipeline) ?: error("fail to create render pipeline")
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateBuffer(handler, it) }
            ?.let(::Buffer) ?: error("fail to create buffer")
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateBindGroup(handler, it) }
            ?.let(::BindGroup) ?: error("fail to create bind group")
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateTexture(handler, it) }
            ?.let(::Texture) ?: error("fail to create texture")
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateSampler(handler, it) }
            ?.let(::Sampler) ?: error("fail to create sampler")
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateComputePipeline(handler, it) }
            ?.let(::ComputePipeline) ?: error("fail to create compute pipeline")
    }

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateBindGroupLayout(handler, it) }
            ?.let(::BindGroupLayout) ?: error("fail to create bind group layout")
    }

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder =
        memoryScope { scope ->
            scope.map(descriptor)
                .let { wgpuDeviceCreateRenderBundleEncoder(handler, it) }
                ?.let(::RenderBundleEncoder) ?: error("fail to create bind group layout")
        }

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateQuerySet(handler, it) }
            ?.let(::QuerySet) ?: error("fail to create bind group layout")
    }

    actual suspend fun poll() {
        //TODO: implement this
        //wgpuDevicePoll(handler, 1, MemorySegment.NULL)
    }

    actual override fun close() {
        wgpuDeviceRelease(handler)
    }

}