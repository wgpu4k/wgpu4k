package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUSize32
import io.ygdrasil.wgpu.internal.js.mapJsArray
import io.ygdrasil.wgpu.internal.js.toJsNumber

internal fun map(input: List<GPUSize32>): JsArray<JsNumber> {
    return input.mapJsArray { it.toJsNumber() }
}