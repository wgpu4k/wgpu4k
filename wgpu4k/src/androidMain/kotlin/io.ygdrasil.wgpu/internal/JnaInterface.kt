package io.ygdrasil.wgpu.internal

import com.sun.jna.Native

object JnaInterface {

    init {
        Native.register(JnaInterface::class.java, "wgpu4kv2")
    }

    /*** Instance ***/
    external fun wgpuCreateInstance(descriptor: Long): Long
    external fun wgpuInstanceRelease(handler: Long)

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
}