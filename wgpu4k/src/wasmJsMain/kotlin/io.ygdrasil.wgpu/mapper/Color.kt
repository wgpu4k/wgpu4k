package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Color
import io.ygdrasil.wgpu.internal.js.GPUColorDict
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: Color): GPUColorDict = createJsObject<GPUColorDict>().apply {
    r = input.red
    g = input.green
    b = input.blue
    a = input.alpha
}