package io.ygdrasil.webgpu

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

actual inline fun arrayBufferOf(input: FloatArray, action: (ArrayBuffer) -> Unit) = Arena.ofConfined().use { arena ->
    val byteSizeToCopy = (input.size * Float.SIZE_BYTES).toLong()
    val segment = arena.allocate(byteSizeToCopy)
    MemorySegment.copy(MemorySegment.ofArray(input), 0, segment, 0, byteSizeToCopy)
    segment.asArrayBuffer(byteSizeToCopy)
        .let(action)
}

actual fun FloatArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    val sizeInBytes = (size * Float.SIZE_BYTES).toLong()
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(sizeInBytes), 0,
        sizeInBytes
    )
}

actual fun DoubleArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    val sizeInBytes = (size * Double.SIZE_BYTES).toLong()
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(sizeInBytes), 0,
        sizeInBytes
    )
}

actual fun ByteArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    val sizeInBytes = (size * Byte.SIZE_BYTES).toLong()
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(sizeInBytes), 0,
        sizeInBytes
    )
}

actual fun UByteArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    val sizeInBytes = (size * UByte.SIZE_BYTES).toLong()
    MemorySegment.copy(
        MemorySegment.ofArray(this.toByteArray()), 0,
        target.asMemorySegment(sizeInBytes), 0,
        sizeInBytes
    )
}

actual fun ShortArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    val sizeInBytes = (size * Short.SIZE_BYTES).toLong()
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(sizeInBytes), 0,
        sizeInBytes
    )
}

actual fun UShortArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    val sizeInBytes = (size * UShort.SIZE_BYTES).toLong()
    MemorySegment.copy(
        MemorySegment.ofArray(this.toShortArray()), 0,
        target.asMemorySegment(sizeInBytes), 0,
        sizeInBytes
    )
}

actual fun IntArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    val sizeInBytes = (size * Int.SIZE_BYTES).toLong()
    MemorySegment.copy(
        MemorySegment.ofArray(this), 0,
        target.asMemorySegment(sizeInBytes), 0,
        sizeInBytes
    )
}

actual fun UIntArray.writeInto(target: ArrayBuffer): Unit = Arena.ofConfined().use { arena ->
    val sizeInBytes = (size * UInt.SIZE_BYTES).toLong()
    MemorySegment.copy(
        MemorySegment.ofArray(this.toIntArray()), 0,
        target.asMemorySegment(sizeInBytes), 0,
        sizeInBytes
    )
}

fun MemorySegment.asArrayBuffer(size: Long): ArrayBuffer = ArrayBuffer(address().toULong(), size.toULong())
fun ArrayBuffer.asMemorySegment(sizeInBytes: Long): MemorySegment = MemorySegment.ofAddress(rawPointer.toLong())
    .reinterpret(sizeInBytes)
