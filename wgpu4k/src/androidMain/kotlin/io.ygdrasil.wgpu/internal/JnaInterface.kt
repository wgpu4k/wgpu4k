package io.ygdrasil.wgpu.internal

import com.sun.jna.Native

object JnaInterface {

    init {
        Native.register(JnaInterface::class.java, "wgpu4kv2")
    }

    /*** Instance ***/
    external fun wgpuCreateInstance(descriptor: Long): Long
    external fun wgpuInstanceRelease(handler: Long)

    /*** Surface ***/
    external fun wgpuSurfaceRelease(handler: Long)

    /*** Queue ***/
    external fun wgpuQueueSubmit(
        handler: Long,
        commandsBufferSize: Long,
        commandsBuffer: Long
    )

    /*** TextureView ***/
    external fun wgpuTextureViewRelease(handler: Long)

    /*** Texture ***/
    external fun wgpuTextureCreateView(
        handler: Long,
        textureViewDescriptor: Long
    ): Long

    external fun wgpuTextureRelease(handler: Long)

    /*** CommandEncoder ***/
    external fun wgpuCommandEncoderRelease(handler: Long)

    /*** RenderPassEncoder ***/
    external fun wgpuRenderPassEncoderEnd(handler: Long)
    external fun wgpuRenderPassEncoderRelease(handler: Long)

    /*** CommandBuffer ***/
    external fun wgpuCommandBufferRelease(handler: Long)

    /*** Sampler ***/
    external fun wgpuSamplerRelease(handler: Long)

    /*** ShaderModule ***/
    external fun wgpuShaderModuleRelease(handler: Long)

    /*** BindGroup ***/
    external fun wgpuBindGroupRelease(handler: Long)

    /*** BindGroupLayout ***/
    external fun wgpuBindGroupLayoutRelease(handler: Long)

    /*** Surface ***/
    external fun wgpuSurfacePresent(handler: Long)

    /*** Device ***/
    external fun wgpuDeviceGetQueue(handler: Long): Long
    external fun wgpuDeviceCreateCommandEncoder(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateShaderModule(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreatePipelineLayout(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateRenderPipeline(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateBuffer(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateBindGroup(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateTexture(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateSampler(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateComputePipeline(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateBindGroupLayout(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateRenderBundleEncoder(handler: Long, descriptor: Long): Long
    external fun wgpuDeviceCreateQuerySet(handler: Long, descriptor: Long): Long
    external fun wgpuDevicePoll(handler: Long, bool: Int, ptr: Long)
    external fun wgpuDeviceRelease(handler: Long)
}