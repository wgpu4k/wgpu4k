package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Device(val handler: Long) : AutoCloseable {


    actual val queue: Queue by lazy {
        Queue(JniInterface.wgpuDeviceGetQueue(handler))
    }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder {
        return JniInterface.wgpuDeviceCreateCommandEncoder(handler, descriptor)
            .let(::CommandEncoder)
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule {
        return JniInterface.wgpuDeviceCreateShaderModule(handler, descriptor)
            .let(::ShaderModule)
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout {
        return JniInterface.wgpuDeviceCreatePipelineLayout(handler, descriptor)
            .let(::PipelineLayout)
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline {
        return JniInterface.wgpuDeviceCreateRenderPipeline(handler, descriptor)
            .let(::RenderPipeline)
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer {
        return JniInterface.wgpuDeviceCreateBuffer(handler, descriptor)
            .let(::Buffer)
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup {
        return JniInterface.wgpuDeviceCreateBindGroup(handler, descriptor)
            .let(::BindGroup)
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture {
        return JniInterface.wgpuDeviceCreateTexture(handler, descriptor)
            .let(::Texture)
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler {
        return JniInterface.wgpuDeviceCreateSampler(handler, descriptor)
            .let(::Sampler)
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline {
        return JniInterface.wgpuDeviceCreateComputePipeline(handler, descriptor)
            .let(::ComputePipeline)
    }

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout {
        return JniInterface.wgpuDeviceCreateBindGroupLayout(handler, descriptor)
            .let(::BindGroupLayout)
    }

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder {
        return JniInterface.wgpuDeviceCreateRenderBundleEncoder(handler, descriptor)
            .let(::RenderBundleEncoder)
    }

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet {
        return JniInterface.wgpuDeviceCreateQuerySet(handler, descriptor)
            .let(::QuerySet)
    }

    actual suspend fun poll() {
        JniInterface.wgpuDevicePoll(handler, 1)
    }

    actual override fun close() {
        JniInterface.wgpuDeviceRelease(handler)
    }

}