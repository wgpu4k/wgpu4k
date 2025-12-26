package io.ygdrasil.webgpu

import com.sun.jna.Pointer
import ffi.MemoryAllocator
import ffi.NativeAddress
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.wgpuQueueWriteBuffer
import io.ygdrasil.wgpu.wgpuQueueWriteTexture
import java.nio.ByteBuffer

@Suppress("NOTHING_TO_INLINE")
internal actual inline fun Queue.queueWriteBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: ArrayBuffer,
    dataOffset: GPUSize64,
    size: GPUSize64?
) {
    val size = size ?: (buffer.size - bufferOffset)
    val data = ((data as AndroidArrayBuffer).buffer.getAddress() + dataOffset).toNativeAddress()
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
        (data as AndroidArrayBuffer).buffer.getAddress().toNativeAddress(),
        data.size,
        scope.map(dataLayout),
        scope.map(size)
    )
}

private fun ByteBuffer.getAddress() = try {
    val addressMethod = ByteBuffer::class.java.getDeclaredMethod("address")
    addressMethod.isAccessible = true
    (addressMethod.invoke(this) as Long?)!!
} catch (e: Exception) {
    throw RuntimeException("Failed to get ByteBuffer address", e)
}.toULong()


private fun ULong.toNativeAddress(): NativeAddress? = takeIf { it != 0uL }
    ?.let { Pointer(it.toLong()) }