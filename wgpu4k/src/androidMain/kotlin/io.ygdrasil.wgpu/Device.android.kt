package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class Device(internal val handler: Long) : AutoCloseable {


    actual val queue: Queue by lazy {
        Queue(JniInterfaceV2.wgpuDeviceGetQueue(handler))
    }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder {
        return JniInterfaceV2.wgpuDeviceCreateCommandEncoder(handler, descriptor)
            .let(::CommandEncoder)
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule {
        return JniInterfaceV2.wgpuDeviceCreateShaderModule(handler, descriptor)
            .let(::ShaderModule)
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout {
        return JniInterfaceV2.wgpuDeviceCreatePipelineLayout(handler, descriptor)
            .let(::PipelineLayout)
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline {
        return JniInterfaceV2.wgpuDeviceCreateRenderPipeline(handler, descriptor)
            .let(::RenderPipeline)
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer {
        return JniInterfaceV2.wgpuDeviceCreateBuffer(handler, descriptor)
            .let(::Buffer)
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup {
        return JniInterfaceV2.wgpuDeviceCreateBindGroup(handler, descriptor)
            .let(::BindGroup)
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture {
        return JniInterfaceV2.wgpuDeviceCreateTexture(handler, descriptor)
            .let(::Texture)
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler {
        return JniInterfaceV2.wgpuDeviceCreateSampler(handler, descriptor)
            .let(::Sampler)
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline {
        return JniInterfaceV2.wgpuDeviceCreateComputePipeline(handler, descriptor)
            .let(::ComputePipeline)
    }

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout {
        return JniInterfaceV2.wgpuDeviceCreateBindGroupLayout(handler, descriptor)
            .let(::BindGroupLayout)
    }

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder {
        return JniInterfaceV2.wgpuDeviceCreateRenderBundleEncoder(handler, descriptor)
            .let(::RenderBundleEncoder)
    }

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet {
        return JniInterfaceV2.wgpuDeviceCreateQuerySet(handler, descriptor)
            .let(::QuerySet)
    }

    actual suspend fun poll() {
        JniInterfaceV2.wgpuDevicePoll(handler, 1)
    }

    actual override fun close() {
        JniInterfaceV2.wgpuDeviceRelease(handler)
    }

}