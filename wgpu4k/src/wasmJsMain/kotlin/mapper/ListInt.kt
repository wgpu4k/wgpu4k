package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUSize32
import io.ygdrasil.webgpu.internal.js.mapJsArray
import io.ygdrasil.webgpu.internal.js.toJsNumber

internal fun map(input: List<GPUSize32>): JsArray<JsNumber> {
    return input.mapJsArray { it.toJsNumber() }
}