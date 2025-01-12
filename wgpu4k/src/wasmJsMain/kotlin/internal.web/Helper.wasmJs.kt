package io.ygdrasil.webgpu.internal.web

actual fun <T : JsObject> createJsObject(): T = js("({ })")

internal actual fun <A, B : JsObject> Set<A>.mapJsArray(converter: (A) -> B): JsObject {
    val output: JsArray<B> = JsArray()
    forEachIndexed { index, value ->
        output[index] = converter(value)
    }
    return output
}