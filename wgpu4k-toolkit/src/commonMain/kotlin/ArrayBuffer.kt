package io.ygdrasil.webgpu

inline fun FloatArray.asArraybuffer(action: (ArrayBuffer) -> Unit) = arrayBufferOf(this, action)

expect inline fun arrayBufferOf(input: FloatArray, action: (ArrayBuffer) -> Unit)

expect fun FloatArray.writeInto(target: ArrayBuffer)

expect fun DoubleArray.writeInto(target: ArrayBuffer)

expect fun ByteArray.writeInto(target: ArrayBuffer)

expect fun UByteArray.writeInto(target: ArrayBuffer)

expect fun ShortArray.writeInto(target: ArrayBuffer)

expect fun UShortArray.writeInto(target: ArrayBuffer)

expect fun IntArray.writeInto(target: ArrayBuffer)

expect fun UIntArray.writeInto(target: ArrayBuffer)
