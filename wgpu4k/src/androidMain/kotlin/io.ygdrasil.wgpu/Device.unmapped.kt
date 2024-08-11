package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Device(internal val handler: Long) : AutoCloseable {


    actual val queue: Queue by lazy {
        Queue(JniInterface.instance.wgpuDeviceGetQueue(handler))
    }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder {
        return JniInterface.instance.wgpuDeviceCreateCommandEncoder(handler, descriptor)
            .let(::CommandEncoder)
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule {
        return JniInterface.instance.wgpuDeviceCreateShaderModule(handler, descriptor)
            .let(::ShaderModule)
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout {
        return JniInterface.instance.wgpuDeviceCreatePipelineLayout(handler, descriptor)
            .let(::PipelineLayout)
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline {
        return JniInterface.instance.wgpuDeviceCreateRenderPipeline(handler, descriptor)
            .let(::RenderPipeline)
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer {
        return JniInterface.instance.wgpuDeviceCreateBuffer(handler, descriptor)
            .let(::Buffer)
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup {
        return JniInterface.instance.wgpuDeviceCreateBindGroup(handler, descriptor)
            .let(::BindGroup)
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture {
        return JniInterface.instance.wgpuDeviceCreateTexture(handler, descriptor)
            .let(::Texture)
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler {
        return JniInterface.instance.wgpuDeviceCreateSampler(handler, descriptor)
            .let(::Sampler)
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline {
        return JniInterface.instance.wgpuDeviceCreateComputePipeline(handler, descriptor)
            .let(::ComputePipeline)
    }

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout {
        return JniInterface.instance.wgpuDeviceCreateBindGroupLayout(handler, descriptor)
            .let(::BindGroupLayout)
    }

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder {
        return JniInterface.instance.wgpuDeviceCreateRenderBundleEncoder(handler, descriptor)
            .let(::RenderBundleEncoder)
    }

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet {
        return JniInterface.instance.wgpuDeviceCreateQuerySet(handler, descriptor)
            .let(::QuerySet)
    }

    actual suspend fun poll() {
        JniInterface.instance.wgpuDevicePoll(handler, 1)
    }

    actual override fun close() {
        JniInterface.instance.wgpuDeviceRelease(handler)
    }

}