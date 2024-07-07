package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUDevice

actual class Device(internal val handler: GPUDevice) : AutoCloseable {
    actual val queue: Queue
        get() = TODO("Not yet implemented")

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder {
        TODO("Not yet implemented")
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule {
        TODO("Not yet implemented")
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout {
        TODO("Not yet implemented")
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline {
        TODO("Not yet implemented")
    }

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer {
        TODO("Not yet implemented")
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture {
        TODO("Not yet implemented")
    }

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup {
        TODO("Not yet implemented")
    }

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler {
        TODO("Not yet implemented")
    }

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline {
        TODO("Not yet implemented")
    }

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout {
        TODO("Not yet implemented")
    }

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder {
        TODO("Not yet implemented")
    }

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet {
        TODO("Not yet implemented")
    }

    actual suspend fun poll() {
    }

    actual override fun close() {
    }

}