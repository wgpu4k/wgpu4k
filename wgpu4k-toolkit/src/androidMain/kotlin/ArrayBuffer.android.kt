package io.ygdrasil.webgpu

import com.sun.jna.Memory
import com.sun.jna.Pointer

actual inline fun arrayBufferOf(input: FloatArray, action: (ArrayBuffer) -> Unit) {
    val byteSizeToCopy = (input.size * Float.SIZE_BYTES).toLong()
    val segment = Memory(byteSizeToCopy)
    segment.write(0, input, 0, input.size)
    segment.asArrayBuffer()
        .let(action)
}

actual fun FloatArray.writeInto(target: ArrayBuffer) {
    val segment = Pointer(target.rawPointer.toLong())
    segment.write(0, this, 0, size)
}

actual fun DoubleArray.writeInto(target: ArrayBuffer) {
    val segment = Pointer(target.rawPointer.toLong())
    segment.write(0, this, 0, size)
}

actual fun ByteArray.writeInto(target: ArrayBuffer) {
    val segment = Pointer(target.rawPointer.toLong())
    segment.write(0, this, 0, size)
}

actual fun UByteArray.writeInto(target: ArrayBuffer) {
    val segment = Pointer(target.rawPointer.toLong())
    segment.write(0, this.toByteArray(), 0, size)
}

actual fun ShortArray.writeInto(target: ArrayBuffer) {
    val segment = Pointer(target.rawPointer.toLong())
    segment.write(0, this, 0, size)
}

actual fun UShortArray.writeInto(target: ArrayBuffer) {
    val segment = Pointer(target.rawPointer.toLong())
    segment.write(0, this.toShortArray(), 0, size)
}

actual fun IntArray.writeInto(target: ArrayBuffer) {
    val segment = Pointer(target.rawPointer.toLong())
    segment.write(0, this, 0, size)
}

actual fun UIntArray.writeInto(target: ArrayBuffer) {
    val segment = Pointer(target.rawPointer.toLong())
    segment.write(0, this.toIntArray(), 0, size)
}

private fun Pointer.asArrayBuffer(): ArrayBuffer = ArrayBuffer(Pointer.nativeValue(this).toULong())