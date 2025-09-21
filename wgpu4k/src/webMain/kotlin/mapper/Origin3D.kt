@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUOrigin3D
import io.ygdrasil.webgpu.WGPUOrigin3D
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: GPUOrigin3D): WGPUOrigin3D = createJsObject<WGPUOrigin3D>().apply {
    x = input.x.asJsNumber()
    y = input.y.asJsNumber()
    z = input.z.asJsNumber()
}