package io.ygdrasil.webgpu

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.internal.js.GPUBuffer
import io.ygdrasil.webgpu.internal.js.mapJsArray
import io.ygdrasil.webgpu.internal.js.toJsNumber
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int16Array
import org.khronos.webgl.Int32Array
import org.khronos.webgl.Int8Array
import org.khronos.webgl.get

actual class Buffer(internal val handler: GPUBuffer) : AutoCloseable {

    private val logger = KotlinLogging.logger {}

    actual val size: GPUSize64
        get() = handler
            .size
            .toLong().toULong()
    actual val usage: Set<BufferUsage>
        get() = BufferUsage.entries.filter { it.value and handler.usage != 0uL }.toSet()
    actual val mapState: BufferMapState
        get() = BufferMapState.of(handler.mapState) ?: error("fail to get MapState")

    actual fun unmap() {
        handler.unmap()
    }

    actual fun mapFrom(buffer: ShortArray, offset: GPUSize64) {
        Int16Array(
            handler.getMappedRange(
                offset.toJsNumber(),
                (buffer.size.toLong() * Short.SIZE_BYTES).toJsNumber()
            )
        )
            .set(buffer.mapJsArray { it.toJsNumber() }, 0)
    }

    actual fun mapFrom(buffer: FloatArray, offset: GPUSize64) {
        logger.debug { "mapping float array with size ${buffer.size} and offset $offset" }
        Float32Array(
            handler.getMappedRange(
                offset.toJsNumber(),
                (buffer.size.toLong() * Float.SIZE_BYTES).toJsNumber()
            )
        )
            .set(buffer.mapJsArray { it.toJsNumber() }, 0)
    }

    actual fun mapFrom(buffer: ByteArray, offset: GPUSize64) {
        Int8Array(handler.getMappedRange(offset.toJsNumber(), buffer.size.toJsNumber()))
            .set(buffer.mapJsArray { it.toJsNumber() }, 0)
    }

    actual fun mapInto(buffer: ByteArray, offset: GPUSize64) {
        Int8Array(handler.getMappedRange(offset.toLong().toJsNumber(), buffer.size.toLong().toJsNumber()))
            .also { remoteBuffer ->
                buffer.indices.forEach { index -> buffer[index] = remoteBuffer.get(index) }
            }
    }

    actual fun mapInto(buffer: IntArray, offset: GPUSize64) {
        Int32Array(handler.getMappedRange(offset.toLong().toJsNumber(), (buffer.size * Int.SIZE_BYTES).toLong().toJsNumber()))
            .also { remoteBuffer ->
                buffer.indices.forEach { index -> buffer[index] = remoteBuffer.get(index) }
            }
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        handler.mapAsync(mode.toFlagInt(), offset.toJsNumber(), size.toJsNumber())
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}