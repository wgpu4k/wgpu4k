package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.GPUSize32
import io.ygdrasil.wgpu.GPUSize64
import io.ygdrasil.wgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.PowerPreference
import io.ygdrasil.wgpu.RenderBundle

object JniInterface {
    init {
        System.loadLibrary("wgpu4kv2")
    }

    /*** Instance ***/
    external fun wgpuInstanceRequestAdapter(handler: Long, powerPreference: PowerPreference?, surface: Long): Long
    external fun wgpuInstanceCreateSurface(handler: Long, androidSurface: android.view.Surface): Long

    /*** Adapter ***/
    external fun wgpuAdapterRequestDevice(handler: Long, descriptor: DeviceDescriptor): Long
    external fun wgpuAdapterRelease(handler: Long)

    /*** Surface ***/
    external fun wgpuSurfaceGetCurrentTexture(handler: Long): Long
    external fun wgpuSurfaceGetFormat(handler: Long, adapter: Long): Int
    external fun wgpuSurfaceConfigure(
        handler: Long,
        device: Long,
        usage: Int,
        format: Int,
        alphaMode: Int,
        width: Int,
        height: Int
    )


    /*** RenderPassEncoder ***/
    // TODO jni
    external fun wgpuRenderPassEncoderExecuteBundles(
        handler: Long,
        bundlesSize: Long,
        bundles: List<RenderBundle>
    )
    // TODO jni
    external fun wgpuRenderPassEncoderSetIndexBuffer(
        handler: Long,
        handler1: Long,
        value: Int,
        offset: GPUSize64,
        size: GPUSize64
    )
    // TODO jni
    external fun wgpuRenderPassEncoderSetVertexBuffer(
        handler: Long,
        slot: Int,
        buffer: Long,
        offset: Long,
        size: GPUSize64
    )
    // TODO jni
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

    /*** Queue ***/
    // TODO jni
    external fun wgpuQueueWriteBuffer(
        handler: Long,
        buffer: Long,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: Long
    )
    // TODO jni
    external fun wgpuQueueWriteBuffer(
        handler: Long,
        buffer: Long,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: Long
    )
    // TODO jni
    external fun wgpuQueueWriteTexture(
        handler: Long,
        destination: ImageCopyTextureTagged,
        data: ByteArray,
        toLong: Long,
        width: Int,
        height: Int,
        bytePerPixel: Int
    )

    /*** Buffer ***/
    // TODO jni
    external fun wgpuBufferGetSize(handler: Long): GPUSize64
    // TODO jni
    external fun wgpuBufferGetUsage(handler: Long): Int
    // TODO jni
    external fun wgpuBufferGetMapState(handler: Long): Int
    // TODO jni
    external fun wgpuBufferUnmap(handler: Long)
    // TODO jni
    external fun wgpuBufferMapAsync(
        handler: Long,
        toFlagInt: Int,
        offset: GPUSize64,
        size: GPUSize64
    )
    // TODO jni
    external fun wgpuBufferRelease(handler: Long)
    // TODO jni
    external fun wgpuBufferMapInto(handler: Long, buffer: ByteArray, offset: Int)
    // TODO jni
    external fun mapFrom(buffer: FloatArray, offset: Int)
    // TODO jni
    external fun mapFrom(buffer: ByteArray, offset: Int)


}