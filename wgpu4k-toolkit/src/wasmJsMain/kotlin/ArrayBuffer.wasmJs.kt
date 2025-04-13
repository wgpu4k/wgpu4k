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
        .set(map(this).unsafeCast<Float32Array>(), 0)
}

actual fun DoubleArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Float64Array>()
        .set(map(this).unsafeCast<Float64Array>(), 0)
}

actual fun ByteArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Int8Array>()
        .set(map(this).unsafeCast<Int8Array>(), 0)
}

actual fun UByteArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Uint8Array>()
        .set(map(this).unsafeCast<Uint8Array>(), 0)
}

actual fun ShortArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Int16Array>()
        .set(map(this).unsafeCast<Int16Array>(), 0)
}

actual fun UShortArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Uint16Array>()
        .set(map(this).unsafeCast<Uint16Array>(), 0)
}

actual fun IntArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Int32Array>()
        .set(map(this).unsafeCast<Int32Array>(), 0)
}

actual fun UIntArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Uint32Array>()
        .set(map(this).unsafeCast<Uint32Array>(), 0)
}

private fun map(buffer: FloatArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        set(it, index, value.asJsNumber())
    }
}

private fun map(buffer: DoubleArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        set(it, index, value.asJsNumber())
    }
}

private fun map(buffer: ByteArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        set(it, index, value.asJsNumber())
    }
}

private fun map(buffer: UByteArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        set(it, index, value.asJsNumber())
    }
}

private fun map(buffer: ShortArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        set(it, index, value.asJsNumber())
    }
}

private fun map(buffer: UShortArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        set(it, index, value.asJsNumber())
    }
}

private fun map(buffer: IntArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        set(it, index, value.asJsNumber())
    }
}

private fun map(buffer: UIntArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        set(it, index, value.asJsNumber())
    }
}