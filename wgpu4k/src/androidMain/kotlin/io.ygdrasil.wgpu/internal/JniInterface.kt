package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.GPUSize64
import io.ygdrasil.wgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.PowerPreference

internal object JniInterface {
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