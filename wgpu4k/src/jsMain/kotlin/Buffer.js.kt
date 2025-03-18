package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int16Array
import org.khronos.webgl.Int8Array

actual class Buffer(internal val handler: GPUBuffer) : AutoCloseable {

    actual fun mapFrom(buffer: ShortArray, offset: GPUSize64) {
        Int16Array(handler.getMappedRange(offset, buffer.size.toULong() * Short.SIZE_BYTES.toULong()))
            .set(buffer.toTypedArray(), 0)
    }

    actual fun mapFrom(buffer: FloatArray, offset: GPUSize64) {
        logger.debug { "mapping float array with size ${buffer.size} and offset $offset" }
        Float32Array(handler.getMappedRange(offset, buffer.size.toULong() * Float.SIZE_BYTES.toULong()))
            .set(buffer.toTypedArray(), 0)
    }

    actual fun mapFrom(buffer: ByteArray, offset: GPUSize64) {
        Int8Array(handler.getMappedRange(offset, buffer.size.toULong()))
            .set(buffer.toTypedArray(), 0)
    }

    actual fun mapInto(buffer: ByteArray, offset: GPUSize64) {
        Int8Array(handler.getMappedRange(offset, buffer.size.toULong()))
            .unsafeCast<ByteArray>()
            .copyInto(buffer)
    }

    actual fun mapInto(buffer: IntArray, offset: GPUSize64) {
        Int8Array(handler.getMappedRange(offset, (buffer.size * Int.SIZE_BYTES).toULong()))
            .unsafeCast<IntArray>()
            .copyInto(buffer)
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        handler.mapAsync(mode.toFlagInt(), offset, size)
    }


}