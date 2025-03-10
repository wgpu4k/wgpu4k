package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUCommandEncoderDescriptor
import io.ygdrasil.wgpu.WGPUDevice
import io.ygdrasil.wgpu.WGPULimits
import io.ygdrasil.wgpu.wgpuDeviceCreateBindGroup
import io.ygdrasil.wgpu.wgpuDeviceCreateBindGroupLayout
import io.ygdrasil.wgpu.wgpuDeviceCreateBuffer
import io.ygdrasil.wgpu.wgpuDeviceCreateCommandEncoder
import io.ygdrasil.wgpu.wgpuDeviceCreateComputePipeline
import io.ygdrasil.wgpu.wgpuDeviceCreatePipelineLayout
import io.ygdrasil.wgpu.wgpuDeviceCreateQuerySet
import io.ygdrasil.wgpu.wgpuDeviceCreateRenderBundleEncoder
import io.ygdrasil.wgpu.wgpuDeviceCreateRenderPipeline
import io.ygdrasil.wgpu.wgpuDeviceCreateSampler
import io.ygdrasil.wgpu.wgpuDeviceCreateShaderModule
import io.ygdrasil.wgpu.wgpuDeviceCreateTexture
import io.ygdrasil.wgpu.wgpuDeviceGetLimits
import io.ygdrasil.wgpu.wgpuDeviceGetQueue
import io.ygdrasil.wgpu.wgpuDeviceHasFeature
import io.ygdrasil.wgpu.wgpuDevicePoll
import io.ygdrasil.wgpu.wgpuDeviceRelease

actual class Device(internal val handler: WGPUDevice) : GPUDevice {

    actual val queue: Queue by lazy { Queue(wgpuDeviceGetQueue(handler) ?: error("fail to get device queue")) }

    actual val features: Set<GPUFeatureName> by lazy {
        FeatureName.entries
            .mapNotNull { feature ->
                feature.takeIf { wgpuDeviceHasFeature(handler, feature.value) }
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

    actual fun createShaderModule(descriptor: GPUShaderModuleDescriptor): ShaderModule = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateShaderModule(handler, it) }
            ?.let(::ShaderModule) ?: error("fail to create shader module")
    }

    actual fun createPipelineLayout(descriptor: GPUPipelineLayoutDescriptor): PipelineLayout = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreatePipelineLayout(handler, it) }
            ?.let(::PipelineLayout) ?: error("fail to create pipeline layout")
    }

    actual fun createRenderPipeline(descriptor: GPURenderPipelineDescriptor): RenderPipeline = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateRenderPipeline(handler, it) }
            ?.let(::RenderPipeline) ?: error("fail to create render pipeline")
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateBuffer(handler, it) }
            ?.let(::Buffer) ?: error("fail to create buffer")
    }

    actual fun createBindGroup(descriptor: GPUBindGroupDescriptor): BindGroup = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateBindGroup(handler, it) }
            ?.let(::BindGroup) ?: error("fail to create bind group")
    }

    actual fun createTexture(descriptor: GPUTextureDescriptor): Texture = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateTexture(handler, it) }
            ?.let(::Texture) ?: error("fail to create texture")
    }

    actual fun createSampler(descriptor: GPUSamplerDescriptor): Sampler = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateSampler(handler, it) }
            ?.let(::Sampler) ?: error("fail to create sampler")
    }

    actual fun createComputePipeline(descriptor: GPUComputePipelineDescriptor): ComputePipeline = memoryScope { scope ->
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
        wgpuDevicePoll(handler, true, null)
    }

    actual override fun close() {
        wgpuDeviceRelease(handler)
    }

}