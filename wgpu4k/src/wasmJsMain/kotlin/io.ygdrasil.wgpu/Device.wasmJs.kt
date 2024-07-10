package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUDevice
import io.ygdrasil.wgpu.mapper.map


actual class Device(internal val handler: GPUDevice) : AutoCloseable {
    actual val queue: Queue by lazy { Queue(handler.queue) }

    actual fun createCommandEncoder(descriptor: CommandEncoderDescriptor?): CommandEncoder {
        return CommandEncoder(
            when (descriptor) {
                null -> handler.createCommandEncoder()
                else -> handler.createCommandEncoder(map(descriptor))
            }
        )
    }

    actual fun createShaderModule(descriptor: ShaderModuleDescriptor): ShaderModule {
        return map(descriptor)
            .let { handler.createShaderModule(it) }
            .let(::ShaderModule)
    }

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout {
        TODO("Not yet implemented")
    }

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = map(descriptor)
        .let { handler.createRenderPipeline(it) }
        .let(::RenderPipeline)

    actual fun createBuffer(descriptor: BufferDescriptor): Buffer {
        TODO("Not yet implemented")
    }

    actual fun createTexture(descriptor: TextureDescriptor): Texture = map(descriptor)
        .let { handler.createTexture(it) }
        .let(::Texture)

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