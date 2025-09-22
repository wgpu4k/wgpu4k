package io.ygdrasil.webgpu

import js.typedarrays.Float32Array
import js.typedarrays.Float64Array
import js.typedarrays.Int16Array
import js.typedarrays.Int32Array
import js.typedarrays.Int8Array
import js.typedarrays.Uint16Array
import js.typedarrays.Uint32Array
import js.typedarrays.Uint8Array

actual fun FloatArray.writeInto(target: ArrayBuffer) {
    Float32Array(target)
        .set(this.unsafeCast<Float32Array<ArrayBuffer>>(), 0)
}

actual fun DoubleArray.writeInto(target: ArrayBuffer) {
    Float64Array(target)
        .set(this.unsafeCast<Float64Array<ArrayBuffer>>(), 0)
}

actual fun ByteArray.writeInto(target: ArrayBuffer) {
    Int8Array(target)
        .set(this.unsafeCast<Int8Array<ArrayBuffer>>(), 0)
}

actual fun UByteArray.writeInto(target: ArrayBuffer) {
    Uint8Array(target)
        .set(this.unsafeCast<Uint8Array<ArrayBuffer>>(), 0)
}

actual fun ShortArray.writeInto(target: ArrayBuffer) {
    Int16Array(target)
        .set(this.unsafeCast<Int16Array<ArrayBuffer>>(), 0)
}

actual fun UShortArray.writeInto(target: ArrayBuffer) {
    Uint16Array(target)
        .set(this.unsafeCast<Uint16Array<ArrayBuffer>>(), 0)
}

actual fun IntArray.writeInto(target: ArrayBuffer) {
    Int32Array(target)
        .set(this.unsafeCast<Int32Array<ArrayBuffer>>(), 0)
}

actual fun UIntArray.writeInto(target: ArrayBuffer) {
    Uint32Array(target)
        .set(this.unsafeCast<Uint32Array<ArrayBuffer>>(), 0)
}