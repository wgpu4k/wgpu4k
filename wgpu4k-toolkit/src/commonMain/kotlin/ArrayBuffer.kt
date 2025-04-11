package io.ygdrasil.webgpu

inline fun FloatArray.asArraybuffer(action: (ArrayBuffer) -> Unit) = arrayBufferOf(this, action)

expect inline fun arrayBufferOf(input: FloatArray, action: (ArrayBuffer) -> Unit)

expect fun FloatArray.writeInto(target: ArrayBuffer)