package io.ygdrasil.wgpu.internal.js

fun infer(value: UInt): JsNumber = js("value")

fun UInt.toJsNumber(): JsNumber =
    infer(this)

fun <T : JsAny>createJsObject(): T =
    js("({ })")

fun <T : JsAny> List<T>.toJsArray(): JsArray<T> {
    val output: JsArray<T> = JsArray()
    forEachIndexed { index, value ->
        output[index] = value
    }
    return output
}
