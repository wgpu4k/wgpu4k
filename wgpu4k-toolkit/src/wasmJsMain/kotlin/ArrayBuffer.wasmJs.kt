@file:OptIn(ExperimentalWasmJsInterop::class)

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
        .set(map(this).unsafeCast<Float32Array<js.buffer.ArrayBuffer>>(), 0)
}

actual fun DoubleArray.writeInto(target: ArrayBuffer) {
    Float64Array(target)
        .set(map(this).unsafeCast<Float64Array<js.buffer.ArrayBuffer>>(), 0)
}

actual fun ByteArray.writeInto(target: ArrayBuffer) {
    Int8Array(target)
        .set(map(this).unsafeCast<Int8Array<js.buffer.ArrayBuffer>>(), 0)
}

actual fun UByteArray.writeInto(target: ArrayBuffer) {
    Uint8Array(target)
        .set(map(this).unsafeCast<Uint8Array<js.buffer.ArrayBuffer>>(), 0)
}

actual fun ShortArray.writeInto(target: ArrayBuffer) {
    Int16Array(target)
        .set(map(this).unsafeCast<Int16Array<js.buffer.ArrayBuffer>>(), 0)
}

actual fun UShortArray.writeInto(target: ArrayBuffer) {
    Uint16Array(target)
        .set(map(this).unsafeCast<Uint16Array<js.buffer.ArrayBuffer>>(), 0)
}

actual fun IntArray.writeInto(target: ArrayBuffer) {
    Int32Array(target)
        .set(map(this).unsafeCast<Int32Array<js.buffer.ArrayBuffer>>(), 0)
}

actual fun UIntArray.writeInto(target: ArrayBuffer) {
    Uint32Array(target)
        .set(map(this).unsafeCast<Uint32Array<js.buffer.ArrayBuffer>>(), 0)
}

private fun map(buffer: FloatArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}

private fun map(buffer: DoubleArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}

private fun map(buffer: ByteArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}

private fun map(buffer: UByteArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}

private fun map(buffer: ShortArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}

private fun map(buffer: UShortArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}

private fun map(buffer: IntArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}

private fun map(buffer: UIntArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}