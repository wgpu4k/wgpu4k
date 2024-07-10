package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.*
import io.ygdrasil.wgpu.mapper.map
import kotlinx.coroutines.await

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

    actual fun createPipelineLayout(descriptor: PipelineLayoutDescriptor): PipelineLayout = handler
        .createPipelineLayout(descriptor.convert())
        .let(::PipelineLayout)

    actual fun createRenderPipeline(descriptor: RenderPipelineDescriptor): RenderPipeline = map(descriptor)
        .let { handler.createRenderPipeline(it) }
        .let(::RenderPipeline)


    actual fun createBuffer(descriptor: BufferDescriptor): Buffer = handler
        .createBuffer(descriptor.convert())
        .let(::Buffer)

    actual fun createTexture(descriptor: TextureDescriptor): Texture = map(descriptor)
        .let { handler.createTexture(it) }
        .let(::Texture)

    actual fun createBindGroup(descriptor: BindGroupDescriptor): BindGroup =
        descriptor.convert()
            .let { handler.createBindGroup(it) }
            .let(::BindGroup)

    actual fun createSampler(descriptor: SamplerDescriptor): Sampler =
        descriptor.convert()
            .let { handler.createSampler(it) }
            .let(::Sampler)

    actual fun createComputePipeline(descriptor: ComputePipelineDescriptor): ComputePipeline =
        descriptor.convert()
            .let { handler.createComputePipeline(it) }
            .let(::ComputePipeline)

    actual fun createBindGroupLayout(descriptor: BindGroupLayoutDescriptor): BindGroupLayout =
        map(descriptor)
            .let { handler.createBindGroupLayout(it) }
            .let(::BindGroupLayout)

    actual fun createRenderBundleEncoder(descriptor: RenderBundleEncoderDescriptor): RenderBundleEncoder =
        map(descriptor)
            .let { handler.createRenderBundleEncoder(it) }
            .let(::RenderBundleEncoder)

    actual fun createQuerySet(descriptor: QuerySetDescriptor): QuerySet =
        descriptor.convert()
            .let { handler.createQuerySet(it) }
            .let(::QuerySet)

    actual suspend fun poll() {
        queue.handler.onSubmittedWorkDone().await()
    }

    actual override fun close() {
        // Nothing on JS
    }
}

private fun QuerySetDescriptor.convert(): GPUQuerySetDescriptor {
    TODO("Not yet implemented")
}

private fun ComputePipelineDescriptor.convert(): GPUComputePipelineDescriptor = object : GPUComputePipelineDescriptor {
    override var compute: GPUProgrammableStage = this@convert.compute.convert()
    override var layout: dynamic = this@convert.layout?.convert() ?: "auto"
    override var label: String? = this@convert.label ?: undefined
}

private fun PipelineLayout.convert(): dynamic {
    TODO()
}

private fun ComputePipelineDescriptor.ProgrammableStage.convert(): GPUProgrammableStage =
    object : GPUProgrammableStage {
        override var module: GPUShaderModule = this@convert.module.handler
        override var entryPoint: String? = this@convert.entryPoint ?: undefined
        override var constants: Map<String, GPUPipelineConstantValue>? = undefined//this@convert.constants
    }

private fun SamplerDescriptor.convert(): GPUSamplerDescriptor = object : GPUSamplerDescriptor {
    override var label: String? = this@convert.label ?: undefined
    override var addressModeU: String? = this@convert.addressModeU.stringValue
    override var addressModeV: String? = this@convert.addressModeV.stringValue
    override var addressModeW: String? = this@convert.addressModeW.stringValue
    override var magFilter: String? = this@convert.magFilter.name
    override var minFilter: String? = this@convert.minFilter.name
    override var mipmapFilter: String? = this@convert.mipmapFilter.name
    override var lodMinClamp: Number? = this@convert.lodMinClamp
    override var lodMaxClamp: Number? = this@convert.lodMaxClamp
    override var compare: String? = this@convert.compare?.stringValue ?: undefined
    override var maxAnisotropy: Number? = this@convert.maxAnisotropy
}

private fun BindGroupDescriptor.convert(): GPUBindGroupDescriptor = object : GPUBindGroupDescriptor {
    override var label: String? = this@convert.label ?: undefined
    override var layout: GPUBindGroupLayout = this@convert.layout.handler
    override var entries: Array<GPUBindGroupEntry> = this@convert.entries.map { it.convert() }.toTypedArray()
}

private fun BindGroupDescriptor.BindGroupEntry.convert(): GPUBindGroupEntry = object : GPUBindGroupEntry {
    override var binding: GPUIndex32 = this@convert.binding
    override var resource: dynamic = when (val localResource = this@convert.resource) {
        is BindGroupDescriptor.SamplerBinding -> localResource.sampler.handler
        is BindGroupDescriptor.BufferBinding -> object : GPUBufferBinding {
            override var buffer: GPUBuffer = localResource.buffer.handler
            override var offset: GPUSize64? = localResource.offset
            override var size: GPUSize64? = localResource.size
        }

        is BindGroupDescriptor.TextureViewBinding -> localResource.view.handler
        else -> null
    }
}

private fun BufferDescriptor.convert(): GPUBufferDescriptor = object : GPUBufferDescriptor {
    override var size: GPUSize64 = this@convert.size
    override var usage: GPUBufferUsageFlags = this@convert.usage.toFlagInt()
    override var mappedAtCreation: Boolean? = this@convert.mappedAtCreation
    override var label: String? = this@convert.label ?: undefined
}


/*** PipelineLayoutDescriptor ***/

private fun PipelineLayoutDescriptor.convert(): GPUPipelineLayoutDescriptor = object : GPUPipelineLayoutDescriptor {
    override var label: String? = this@convert.label ?: undefined
    override var bindGroupLayouts: Array<GPUBindGroupLayout> = this@convert.bindGroupLayouts
        .map { it.handler }.toTypedArray()
}



