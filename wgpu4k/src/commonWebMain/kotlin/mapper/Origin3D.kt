package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUOrigin3D
import io.ygdrasil.webgpu.WGPUOrigin3D
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUOrigin3D): WGPUOrigin3D = createJsObject<WGPUOrigin3D>().apply {
    x = input.x.asJsNumber()
    y = input.y.asJsNumber()
    z = input.z.asJsNumber()
}