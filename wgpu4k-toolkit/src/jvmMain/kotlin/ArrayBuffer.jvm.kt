package io.ygdrasil.webgpu

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

actual inline fun arrayBufferOf(input: FloatArray, action: (ArrayBuffer) -> Unit) {
    MemorySegment.ofArray(input)
        .asArrayBuffer()
        .let(action)
}

actual fun FloatArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(), 0,
        (size * Float.SIZE_BYTES).toLong()
    )
}

actual fun DoubleArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(), 0,
        (size * Double.SIZE_BYTES).toLong()
    )
}

actual fun ByteArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(), 0,
        (size * Byte.SIZE_BYTES).toLong()
    )
}

actual fun UByteArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    MemorySegment.copy(
        MemorySegment.ofArray(this.toByteArray()), 0,
        target.asMemorySegment(), 0,
        (size * UByte.SIZE_BYTES).toLong()
    )
}

actual fun ShortArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(), 0,
        (size * Short.SIZE_BYTES).toLong()
    )
}

actual fun UShortArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    MemorySegment.copy(
        MemorySegment.ofArray(this.toShortArray()), 0,
        target.asMemorySegment(), 0,
        (size * UShort.SIZE_BYTES).toLong()
    )
}

actual fun IntArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(), 0,
        (size * Int.SIZE_BYTES).toLong()
    )
}

actual fun UIntArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    MemorySegment.copy(
        MemorySegment.ofArray(this.toIntArray()), 0,
        target.asMemorySegment(), 0,
        (size * UInt.SIZE_BYTES).toLong()
    )
}

fun MemorySegment.asArrayBuffer(): ArrayBuffer = ArrayBuffer(address().toULong())
fun ArrayBuffer.asMemorySegment(): MemorySegment = MemorySegment.ofAddress(rawPointer.toLong())
