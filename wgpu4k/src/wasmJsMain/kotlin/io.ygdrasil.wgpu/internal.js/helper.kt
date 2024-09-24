package io.ygdrasil.wgpu.internal.js

import org.khronos.webgl.Int8Array
import org.khronos.webgl.set

private fun infer(value: Short): JsNumber = js("value")
private fun infer(value: Byte): JsNumber = js("value")
private fun infer(value: UInt): JsNumber = js("value")
private fun infer(value: Float): JsNumber = js("value")
private fun infer(value: Double): JsNumber = js("value")
private fun infer(value: Long): JsNumber = js("Number(value)")
private fun infer(value: ULong): JsNumber = js("Number(value)")

private fun inferBig(value: Long): JsBigInt = js("BigInt(value)")

internal fun Double.toJsNumber(): JsNumber =
    infer(this)

internal fun Float.toJsNumber(): JsNumber =
    infer(this)

internal fun Short.toJsNumber(): JsNumber =
    infer(this)

internal fun UInt.toJsNumber(): JsNumber =
    infer(this)

internal fun Byte.toJsNumber(): JsNumber =
    infer(this)

internal fun Long.toJsNumber(): JsNumber =
    infer(this)

internal fun ULong.toJsNumber(): JsNumber =
    infer(this)

internal fun Long.toJsBigInt(): JsBigInt =
    inferBig(this)

internal fun <T : JsAny> createJsObject(): T =
    js("({ })")

internal fun <A, B : JsAny> List<A>.mapJsArray(converter: (A) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun <A, B : JsAny> Set<A>.mapJsArray(converter: (A) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun <A, B : JsAny> Array<A>.mapJsArray(converter: (A) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun <B : JsAny> ShortArray.mapJsArray(converter: (Short) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun <B : JsAny> FloatArray.mapJsArray(converter: (Float) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun <B : JsAny> DoubleArray.mapJsArray(converter: (Double) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun <B : JsAny> ByteArray.mapJsArray(converter: (Byte) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun <B : JsAny> IntArray.mapJsArray(converter: (Int) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun <B : JsAny> LongArray.mapJsArray(converter: (Long) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun <B : JsAny> UIntArray.mapJsArray(converter: (UInt) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

internal fun ByteArray.toInt8Array(): Int8Array {
    val out = Int8Array(this.size)
    for (n in 0 until out.length) out[n] = this[n]
    return out
}