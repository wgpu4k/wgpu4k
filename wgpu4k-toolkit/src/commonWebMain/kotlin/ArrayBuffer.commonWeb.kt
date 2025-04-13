package io.ygdrasil.webgpu

actual inline fun arrayBufferOf(input: FloatArray, action: (ArrayBuffer) -> Unit) {
   input.asArrayBuffer()
       .let(action)
}