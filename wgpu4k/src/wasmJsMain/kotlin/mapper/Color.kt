package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.internal.js.GPUColorDict
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: Color): GPUColorDict = createJsObject<GPUColorDict>().apply {
    r = input.red
    g = input.green
    b = input.blue
    a = input.alpha
}