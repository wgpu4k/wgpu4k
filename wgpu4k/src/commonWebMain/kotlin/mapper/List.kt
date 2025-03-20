package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.JsArray
import io.ygdrasil.webgpu.JsObject
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.jsArray
import io.ygdrasil.webgpu.set

fun map(input: List<UInt>): JsArray<JsObject> = jsArray<JsObject>().also { array ->
    input.mapIndexed { index, value ->
        set(array, index, value.asJsNumber())
    }
}