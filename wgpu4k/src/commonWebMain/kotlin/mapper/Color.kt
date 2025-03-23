package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUColor
import io.ygdrasil.webgpu.WGPUColor
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUColor): WGPUColor = createJsObject<WGPUColor>().apply {
    r = input.r.asJsNumber()
    g = input.g.asJsNumber()
    b = input.b.asJsNumber()
    a = input.a.asJsNumber()
}