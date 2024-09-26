package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.internal.js.mapJsArray

internal fun map(input: List<Int>): JsArray<JsNumber> {
    return input.mapJsArray { it.toJsNumber() }
}