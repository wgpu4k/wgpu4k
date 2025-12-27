package io.ygdrasil.webgpu

import ffi.MemoryAllocator
import ffi.NativeAddress
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.wgpuQueueWriteBuffer
import io.ygdrasil.wgpu.wgpuQueueWriteTexture
import java.lang.foreign.MemorySegment

@Suppress("NOTHING_TO_INLINE")
internal actual inline fun Queue.queueWriteBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: ArrayBuffer,
    dataOffset: GPUSize64,
    size: GPUSize64?
) {
    val size = size ?: (buffer.size - bufferOffset)
    val data = ((data as JvmArrayBuffer).buffer.address().toULong() + dataOffset).toNativeAddress()
    wgpuQueueWriteBuffer(handler, (buffer as Buffer).handler, bufferOffset, data, size)
}

@Suppress("NOTHING_TO_INLINE")
internal actual inline fun Queue.queueWriteTexture(
    scope: MemoryAllocator,
    destination: GPUTexelCopyTextureInfo,
    data: ArrayBuffer,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D
) {
    wgpuQueueWriteTexture(
        handler,
        scope.map(destination),
        (data as JvmArrayBuffer).buffer.address().toNativeAddress(),
        data.size,
        scope.map(dataLayout),
        scope.map(size)
    )
}

@Suppress("NOTHING_TO_INLINE")
private inline fun Long.toNativeAddress(): NativeAddress? = toULong()
    .toNativeAddress()

private fun ULong.toNativeAddress(): NativeAddress? = takeIf { it != 0uL }
    ?.let { MemorySegment.ofAddress(it.toLong())  }
    ?.let { NativeAddress(it) }