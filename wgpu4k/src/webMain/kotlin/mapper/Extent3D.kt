@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUExtent3D
import io.ygdrasil.webgpu.WGPUExtent3D
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: GPUExtent3D): WGPUExtent3D = createJsObject<WGPUExtent3D>().apply {
    width = input.width.asJsNumber()
    height = input.height.asJsNumber()
    depthOrArrayLayers = input.depthOrArrayLayers.asJsNumber()
}