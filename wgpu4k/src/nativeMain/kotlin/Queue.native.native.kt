@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import ffi.MemoryAllocator
import ffi.Pointer
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.wgpuQueueWriteBuffer
import io.ygdrasil.wgpu.wgpuQueueWriteTexture
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.NativePtr
import kotlinx.cinterop.interpretCPointer

@Suppress("NOTHING_TO_INLINE")
internal actual inline fun Queue.queueWriteBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: ArrayBuffer,
    dataOffset: GPUSize64,
    size: GPUSize64?
) {
    val size = size ?: (buffer.size - bufferOffset)
    when (data) {
        is NativeArrayBuffer -> data.useOpaquePinned(0) { pinned ->
            val data = pinned.rawValue.withOffset(dataOffset)
                .let { Pointer(it) }
            wgpuQueueWriteBuffer(handler, (buffer as Buffer).handler, bufferOffset, data, size)
        }

        is OpaquePointerArrayBuffer -> {
            val data = data.pointer.rawValue.withOffset(dataOffset)
                .let { Pointer(it) }
            wgpuQueueWriteBuffer(handler, (buffer as Buffer).handler, bufferOffset, data, size)
        }
    }

}

@Suppress("NOTHING_TO_INLINE")
internal actual inline fun Queue.queueWriteTexture(
    scope: MemoryAllocator,
    destination: GPUTexelCopyTextureInfo,
    data: ArrayBuffer,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D
) = when (data) {
    is NativeArrayBuffer -> data.useOpaquePinned(0) { pinned ->
        wgpuQueueWriteTexture(
            handler,
            scope.map(destination),
            Pointer(pinned),
            data.size,
            scope.map(dataLayout),
            scope.map(size)
        )
    }

    is OpaquePointerArrayBuffer -> {
        wgpuQueueWriteTexture(
            handler,
            scope.map(destination),
            Pointer(data.pointer),
            data.size,
            scope.map(dataLayout),
            scope.map(size)
        )
    }
}

private fun NativePtr.withOffset(offset: ULong) = interpretCPointer<CPointed>(this + offset.toLong())!!