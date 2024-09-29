@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.*
import webgpu.native.*

actual class Buffer(internal val handler: WGPUBuffer) : AutoCloseable {


    actual val size: GPUSize64
        get() = wgpuBufferGetSize(handler).toLong()
    actual val usage: Set<BufferUsage>
        get() = wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage.toInt() != 0 }.toSet() }
    actual val mapState: BufferMapState
        get() = wgpuBufferGetMapState(handler).toInt()
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
        wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: ShortArray, offset: Int) {
        (wgpuBufferGetMappedRange(handler, offset.toULong(), (buffer.size * Short.SIZE_BYTES).toULong())
            ?: error("Can't get map from: $buffer"))
            .reinterpret<ShortVar>()
            .also { buffer.forEachIndexed { index, value -> it[index] = value } }
    }

    actual fun mapFrom(buffer: FloatArray, offset: Int) {
        (wgpuBufferGetMappedRange(handler, offset.toULong(), (buffer.size * Float.SIZE_BYTES).toULong())
            ?: error("Can't get map from: $buffer"))
            .reinterpret<FloatVar>()
            .also { buffer.forEachIndexed { index, value -> it[index] = value } }
    }

    actual fun mapFrom(buffer: ByteArray, offset: Int) {
        (wgpuBufferGetMappedRange(handler, offset.toULong(), (buffer.size * Float.SIZE_BYTES).toULong())
            ?: error("Can't get map from: $buffer"))
            .reinterpret<ByteVar>()
            .also { buffer.forEachIndexed { index, value -> it[index] = value } }
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        wgpuBufferMapAsync(handler, mode.toFlagUInt(), offset.toULong(), size.toULong(), null, null)
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        (wgpuBufferGetMappedRange(handler, offset.toULong(), buffer.size.toULong())
            ?: error("Can't get map from: $buffer"))
            .reinterpret<ByteVar>()
            .also { buffer.indices.forEach { index -> buffer[index] = it[index] } }
    }

    actual fun mapInto(buffer: IntArray, offset: Int) {
        (wgpuBufferGetMappedRange(handler, offset.toULong(), (buffer.size * Int.SIZE_BYTES).toULong())
            ?: error("Can't get map from: $buffer"))
            .reinterpret<IntVar>()
            .also { buffer.indices.forEach { index -> buffer[index] = it[index] } }
    }

    actual override fun close() {
        wgpuBufferRelease(handler)
    }

}