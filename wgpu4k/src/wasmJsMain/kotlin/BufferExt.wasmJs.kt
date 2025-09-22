@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import js.core.JsPrimitives.toByte
import js.typedarrays.Float32Array
import js.typedarrays.Int16Array
import js.typedarrays.Int32Array
import js.typedarrays.Int8Array

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapFrom(buffer: ShortArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    Int16Array(
        handler.getMappedRange(
            offset.asJsNumber(),
            (buffer.size.toLong() * Short.SIZE_BYTES).asJsNumber()
        )
    ).set(map(buffer).unsafeCast<kotlin.js.JsArray<kotlin.js.JsNumber>>(), 0)
}

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapFrom(buffer: FloatArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    Float32Array(
        handler.getMappedRange(
            offset.asJsNumber(),
            (buffer.size.toLong() * Float.SIZE_BYTES).asJsNumber()
        )
    ).set(map(buffer).unsafeCast<kotlin.js.JsArray<kotlin.js.JsNumber>>(), 0)
}

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapFrom(buffer: ByteArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    Int8Array(handler.getMappedRange(offset.asJsNumber(), buffer.size.asJsNumber()))
        .set(map(buffer).unsafeCast<kotlin.js.JsArray<kotlin.js.JsNumber>>(), 0)
}

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapInto(buffer: ByteArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    Int8Array(handler.getMappedRange(offset.asJsNumber(), buffer.size.asJsNumber()))
        .also { remoteBuffer ->
            buffer.indices.forEach { index -> buffer[index] = remoteBuffer.get(index).toByte() }
        }
}

@Deprecated(message = "use getMappedRange instead")
actual fun GPUBuffer.mapInto(buffer: IntArray, offset: GPUSize64) {
    val handler = (this as Buffer).handler
    Int32Array(
        handler.getMappedRange(
            offset.toLong().asJsNumber(),
            (buffer.size * Int.SIZE_BYTES).asJsNumber()
        )
    )
        .also { remoteBuffer ->
            buffer.indices.forEach { index -> buffer[index] = remoteBuffer.get(index).toInt() }
        }
}

private fun map(buffer: ShortArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}

private fun map(buffer: FloatArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}

private fun map(buffer: ByteArray) = jsArray<JsNumber>().also {
    buffer.forEachIndexed { index, value ->
        it[index] = value.asJsNumber()
    }
}