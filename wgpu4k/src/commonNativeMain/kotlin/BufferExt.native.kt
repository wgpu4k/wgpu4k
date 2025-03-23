package io.ygdrasil.webgpu

import ffi.MemoryBuffer
import ffi.NativeAddress
import io.ygdrasil.wgpu.wgpuBufferGetMappedRange

private fun NativeAddress?.asBuffer(size: ULong): MemoryBuffer =
    MemoryBuffer((this ?: error("buffer should not be null")), size)

actual fun GPUBuffer.mapFrom(buffer: ShortArray, offset: GPUSize64) {
    val bufferSize = (buffer.size * Short.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .writeShorts(buffer)
}

actual fun GPUBuffer.mapFrom(buffer: FloatArray, offset: GPUSize64) {
    val bufferSize = (buffer.size * Float.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .writeFloats(buffer)
}

actual fun GPUBuffer.mapFrom(buffer: ByteArray, offset: GPUSize64) {
    val bufferSize = (buffer.size * Byte.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .writeBytes(buffer)
}

actual fun GPUBuffer.mapInto(buffer: ByteArray, offset: ULong) {
    val bufferSize = (buffer.size * Byte.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .readBytes(buffer)
}

actual fun GPUBuffer.mapInto(buffer: IntArray, offset: ULong) {
    val bufferSize = (buffer.size * Int.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .readInts(buffer)
}