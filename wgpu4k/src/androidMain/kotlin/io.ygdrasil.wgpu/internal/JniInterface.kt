package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.GPUFlagsConstant
import io.ygdrasil.wgpu.GPUIndex32
import io.ygdrasil.wgpu.GPUIntegerCoordinateOut
import io.ygdrasil.wgpu.GPUSignedOffset32
import io.ygdrasil.wgpu.GPUSize32
import io.ygdrasil.wgpu.GPUSize32Out
import io.ygdrasil.wgpu.GPUSize64
import io.ygdrasil.wgpu.RenderBundle
import io.ygdrasil.wgpu.RenderBundleDescriptor
import io.ygdrasil.wgpu.TextureViewDescriptor
import io.ygdrasil.wgpu.WGPUInstanceBackend

class JniInterface  {
    init {
        System.loadLibrary("wgpu4k")
    }

    external fun createWgpuInstance2(): Long

    /*** Instance ***/
    external fun wgpuInstanceRelease(handler: Long)
    external fun wgpuInstanceRequestAdapter(
        handler: Long,
        nothing: Any?,
        nothing1: Any?,
        nothing2: Any?,
    )
    external fun wgpuCreateInstance(backend: WGPUInstanceBackend?): Long

    /*** Texture ***/
    external fun wgpuTextureGetWidth(handler: Long): GPUIntegerCoordinateOut
    external fun wgpuTextureGetHeight(handler: Long): GPUIntegerCoordinateOut
    external fun wgpuTextureGetDepthOrArrayLayers(handler: Long): GPUIntegerCoordinateOut
    external fun wgpuTextureGetMipLevelCount(handler: Long): GPUIntegerCoordinateOut
    external fun wgpuTextureGetSampleCount(handler: Long): GPUSize32Out
    external fun wgpuTextureGetDimension(handler: Long): Int
    external fun wgpuTextureGetFormat(handler: Long): Int
    external fun wgpuTextureGetUsage(handler: Long): GPUFlagsConstant
    external fun wgpuTextureCreateView(
        handler: Long,
        textureViewDescriptor: TextureViewDescriptor?
    ): Long

    external fun wgpuTextureRelease(handler: Long)

    /*** Surface ***/
    external fun wgpuSurfaceGetCurrentTexture(handler: Long): Long
    external fun wgpuSurfacePresent(handler: Long)
    external fun wgpuSurfaceRelease(handler: Long)

    /*** Sampler ***/
    external fun wgpuSamplerRelease(handler: Long)

    /*** ShaderModule ***/
    external fun wgpuShaderModuleRelease(handler: Long)

    /*** TextureView ***/
    external fun wgpuTextureViewRelease(handler: Long)

    /*** RenderPassEncoder ***/
    external fun wgpuRenderPassEncoderRelease(handler: Long)
    external fun wgpuRenderPassEncoderExecuteBundles(
        handler: Long,
        bundlesSize: Long,
        bundles: List<RenderBundle>
    )

    external fun wgpuRenderPassEncoderSetIndexBuffer(
        handler: Long,
        handler1: Long,
        value: Int,
        offset: GPUSize64,
        size: GPUSize64
    )

    external fun wgpuRenderPassEncoderSetVertexBuffer(
        handler: Long,
        slot: Int,
        buffer: Long,
        offset: Long,
        size: GPUSize64
    )

    external fun wgpuRenderPassEncoderSetBindGroup(
        handler: Long,
        index: Int,
        handler1: Long,
        l: Long,
        nothing: Nothing?
    )

    external fun wgpuRenderPassEncoderDraw(
        handler: Long,
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    )

    external fun wgpuRenderPassEncoderSetPipeline(handler: Long, renderPipeline: Long)
    external fun wgpuRenderPassEncoderEnd(handler: Long)

    /*** RenderBundleEncoder ***/
    external fun wgpuRenderBundleEncoderRelease(handler: Long)
    external fun wgpuRenderBundleEncoderDraw(
        handler: Long,
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    )

    external fun wgpuRenderBundleEncoderDrawIndexed(
        handler: Long,
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32
    )

    external fun wgpuRenderBundleEncoderSetIndexBuffer(
        handler: Long,
        handler1: Long,
        value: Int,
        offset: GPUSize64,
        size: GPUSize64
    )

    external fun wgpuRenderBundleEncoderSetVertexBuffer(
        handler: Long,
        slot: GPUIndex32,
        handler1: Long,
        offset: GPUSize64,
        size: GPUSize64
    )

    external fun wgpuRenderBundleEncoderSetPipeline(handler: Long, handler1: Long)
    external fun wgpuRenderBundleEncoderSetBindGroup(
        handler: Long,
        index: GPUIndex32,
        handler1: Long,
        i: Int,
        nothing: Nothing?
    )

    external fun wgpuRenderBundleEncoderFinish(
        handler: Long,
        descriptor: RenderBundleDescriptor
    ): Long


    companion object {

        val instance by lazy { JniInterface() }

    }
}