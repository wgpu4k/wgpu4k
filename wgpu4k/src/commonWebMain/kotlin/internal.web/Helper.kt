package io.ygdrasil.webgpu.internal.web

expect fun <T: JsObject> createJsObject(): T
expect internal fun <A, B : JsObject> Set<A>.mapJsArray(converter: (A) -> B): JsObject