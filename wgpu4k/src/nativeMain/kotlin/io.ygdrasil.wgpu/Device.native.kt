@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.mapper.map
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import webgpu.*

actual class Device(internal val handler: WGPUDevice) : AutoCloseable {

    actual val queue: Queue by lazy { Queue(wgpuDeviceGetQueue(handler) ?: error("fail to get device queue")) }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder = memScoped {
        alloc<WGPUCommandEncoderDescriptor>()
            .let { wgpuDeviceCreateCommandEncoder(handler, it.ptr) }
            ?.let(::CommandEncoder) ?: error("fail to create command encoder")
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateShaderModule(handler, it.ptr) }
            ?.let(::ShaderModule) ?: error("fail to create shader module")
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreatePipelineLayout(handler, it.ptr) }
            ?.let(::PipelineLayout) ?: error("fail to create pipeline layout")
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateRenderPipeline(handler, it.ptr) }
            ?.let(::RenderPipeline) ?: error("fail to create render pipeline")
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateBuffer(handler, it.ptr) }
            ?.let(::Buffer) ?: error("fail to create buffer")
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateBindGroup(handler, it.ptr) }
            ?.let(::BindGroup) ?: error("fail to create bind group")
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateTexture(handler, it.ptr) }
            ?.let(::Texture) ?: error("fail to create texture")
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateSampler(handler, it.ptr) }
            ?.let(::Sampler) ?: error("fail to create sampler")
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateComputePipeline(handler, it.ptr) }
            ?.let(::ComputePipeline) ?: error("fail to create compute pipeline")
    }

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateBindGroupLayout(handler, it.ptr) }
            ?.let(::BindGroupLayout) ?: error("fail to create bind group layout")
    }

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateRenderBundleEncoder(handler, it.ptr) }
            ?.let(::RenderBundleEncoder) ?: error("fail to create bind group layout")
    }

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet = memScoped {
        map(descriptor)
            .let { wgpuDeviceCreateQuerySet(handler, it.ptr) }
            ?.let(::QuerySet) ?: error("fail to create bind group layout")
    }

    actual suspend fun poll() {
        wgpuDevicePoll(handler, 1u, null)
    }

    actual override fun close() {
        wgpuDeviceRelease(handler)
    }

}