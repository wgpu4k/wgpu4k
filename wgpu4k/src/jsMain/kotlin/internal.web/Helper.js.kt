package io.ygdrasil.webgpu.internal.web

actual fun <T : JsObject> createJsObject(): T = js("({ })")
internal actual fun <A, B : JsObject> Set<A>.mapJsArray(converter: (A) -> B): JsObject {
    return asSequence()
        .map { converter(it) }
        .toList()
        .toTypedArray()
        .unsafeCast<JsObject>()
}