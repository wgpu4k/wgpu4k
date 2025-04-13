package io.ygdrasil.webgpu

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Float64Array
import org.khronos.webgl.Int16Array
import org.khronos.webgl.Int32Array
import org.khronos.webgl.Int8Array
import org.khronos.webgl.Uint16Array
import org.khronos.webgl.Uint32Array
import org.khronos.webgl.Uint8Array

actual fun FloatArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Float32Array>()
        .set(this.unsafeCast<Float32Array>(), 0)
}

actual fun DoubleArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Float64Array>()
        .set(this.unsafeCast<Float64Array>(), 0)
}

actual fun ByteArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Int8Array>()
        .set(this.unsafeCast<Int8Array>(), 0)
}

actual fun UByteArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Uint8Array>()
        .set(this.unsafeCast<Uint8Array>(), 0)
}

actual fun ShortArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Int16Array>()
        .set(this.unsafeCast<Int16Array>(), 0)
}

actual fun UShortArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Uint16Array>()
        .set(this.unsafeCast<Uint16Array>(), 0)
}

actual fun IntArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Int32Array>()
        .set(this.unsafeCast<Int32Array>(), 0)
}

actual fun UIntArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Uint32Array>()
        .set(this.unsafeCast<Uint32Array>(), 0)
}