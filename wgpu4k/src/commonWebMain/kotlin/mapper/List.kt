package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.set
import kotlin.js.JsAny
import kotlin.js.JsArray

fun map(input: List<UInt>): JsArray<JsAny> = JsArray<JsAny>().also { array ->
    input.mapIndexed { index, value ->
        set(array, index, value.asJsNumber())
    }
}