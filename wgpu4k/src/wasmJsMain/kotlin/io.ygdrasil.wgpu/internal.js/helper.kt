package io.ygdrasil.wgpu.internal.js

private fun infer(value: Byte): JsNumber = js("value")
private fun infer(value: UInt): JsNumber = js("value")
private fun infer(value: Float): JsNumber = js("value")
private fun infer(value: Double): JsNumber = js("value")
private fun infer(value: Long): JsNumber = js("Number(value)")
private fun infer(value: ULong): JsNumber = js("Number(value)")

private fun inferBig(value: Long): JsBigInt = js("BigInt(value)")

fun Double.toJsNumber(): JsNumber =
    infer(this)

fun Float.toJsNumber(): JsNumber =
    infer(this)

fun UInt.toJsNumber(): JsNumber =
    infer(this)

fun Byte.toJsNumber(): JsNumber =
    infer(this)

fun Long.toJsNumber(): JsNumber =
    infer(this)

fun ULong.toJsNumber(): JsNumber =
    infer(this)

fun Long.toJsBigInt(): JsBigInt =
    inferBig(this)

fun <T : JsAny>createJsObject(): T =
    js("({ })")

fun <A, B : JsAny> List<A>.mapJsArray(converter: (A) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

fun <A, B : JsAny> Array<A>.mapJsArray(converter: (A) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

fun <B : JsAny> FloatArray.mapJsArray(converter: (Float) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

fun <B : JsAny> ByteArray.mapJsArray(converter: (Byte) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

fun <B : JsAny> IntArray.mapJsArray(converter: (Int) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

fun <B : JsAny> UIntArray.mapJsArray(converter: (UInt) -> B): JsArray<B> {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}

