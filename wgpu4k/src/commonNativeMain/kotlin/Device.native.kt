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
import io.ygdrasil.wgpu.wgpuDeviceRelease

actual class Device(val handler: WGPUDevice) : GPUDevice {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override val queue: GPUQueue by lazy { Queue(wgpuDeviceGetQueue(handler) ?: error("fail to get device queue")) }

    actual override val features: Set<GPUFeatureName> by lazy {
        GPUFeatureName.entries
            .mapNotNull { feature ->
                feature.takeIf { wgpuDeviceHasFeature(handler, feature.value) }
            }
            .toSet()
    }

    actual override val limits: GPUSupportedLimits = memoryScope { scope ->
        val supportedLimits = WGPULimits.allocate(scope)
        wgpuDeviceGetLimits(handler, supportedLimits)
        map(supportedLimits)
    }

    actual override val adapterInfo: GPUAdapterInfo
        get() = TODO("Not yet implemented")

    actual override fun createCommandEncoder(descriptor: GPUCommandEncoderDescriptor?): GPUCommandEncoder = memoryScope { scope ->
        WGPUCommandEncoderDescriptor.allocate(scope)
            .let { wgpuDeviceCreateCommandEncoder(handler, it) }
            ?.let(::CommandEncoder) ?: error("fail to create command encoder")
    }

    actual override fun createShaderModule(descriptor: GPUShaderModuleDescriptor): GPUShaderModule = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateShaderModule(handler, it) }
            ?.let(::ShaderModule) ?: error("fail to create shader module")
    }

    actual override fun createPipelineLayout(descriptor: GPUPipelineLayoutDescriptor): GPUPipelineLayout = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreatePipelineLayout(handler, it) }
            ?.let(::PipelineLayout) ?: error("fail to create pipeline layout")
    }

    actual override fun createRenderPipeline(descriptor: GPURenderPipelineDescriptor): GPURenderPipeline = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateRenderPipeline(handler, it) }
            ?.let(::RenderPipeline) ?: error("fail to create render pipeline")
    }

    actual override suspend fun createComputePipelineAsync(descriptor: GPUComputePipelineDescriptor): Result<GPUComputePipeline> {
        TODO("Not yet implemented")
    }

    actual override suspend fun createRenderPipelineAsync(descriptor: GPURenderPipelineDescriptor): Result<GPURenderPipeline> {
        TODO("Not yet implemented")
    }

    actual override fun createBuffer(descriptor: GPUBufferDescriptor): GPUBuffer = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateBuffer(handler, it) }
            ?.let { Buffer(it, this)} ?: error("fail to create buffer")
    }

    actual override fun createBindGroup(descriptor: GPUBindGroupDescriptor): GPUBindGroup = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateBindGroup(handler, it) }
            ?.let(::BindGroup) ?: error("fail to create bind group")
    }

    actual override fun createTexture(descriptor: GPUTextureDescriptor): GPUTexture = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateTexture(handler, it) }
            ?.let(::Texture) ?: error("fail to create texture")
    }

    actual override fun createSampler(descriptor: GPUSamplerDescriptor?): GPUSampler = memoryScope { scope ->
        descriptor?.let { scope.map(descriptor) }
            .let { wgpuDeviceCreateSampler(handler, it) }
            ?.let(::Sampler) ?: error("fail to create sampler")
    }

    actual override fun createComputePipeline(descriptor: GPUComputePipelineDescriptor): GPUComputePipeline = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateComputePipeline(handler, it) }
            ?.let(::ComputePipeline) ?: error("fail to create compute pipeline")
    }

    actual override fun createBindGroupLayout(descriptor: GPUBindGroupLayoutDescriptor): GPUBindGroupLayout = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateBindGroupLayout(handler, it) }
            ?.let(::BindGroupLayout) ?: error("fail to create bind group layout")
    }

    actual override fun createRenderBundleEncoder(descriptor: GPURenderBundleEncoderDescriptor): GPURenderBundleEncoder =
        memoryScope { scope ->
            scope.map(descriptor)
                .let { wgpuDeviceCreateRenderBundleEncoder(handler, it) }
                ?.let(::RenderBundleEncoder) ?: error("fail to create bind group layout")
        }

    actual override fun createQuerySet(descriptor: GPUQuerySetDescriptor): GPUQuerySet = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuDeviceCreateQuerySet(handler, it) }
            ?.let(::QuerySet) ?: error("fail to create bind group layout")
    }

    actual override fun pushErrorScope(filter: GPUErrorFilter) {
        TODO("Not yet implemented")
    }

    actual override suspend fun popErrorScope(): Result<GPUError?> {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        wgpuDeviceRelease(handler)
    }
}