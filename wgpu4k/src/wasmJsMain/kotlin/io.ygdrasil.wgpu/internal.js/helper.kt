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

fun <T : JsAny> List<T>.toJsArray(): JsArray<T> {
    val output: JsArray<T> = JsArray()
    forEachIndexed { index, value ->
        output[index] = value
    }
    return output
}
