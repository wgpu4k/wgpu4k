package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUExtent3D
import io.ygdrasil.webgpu.WGPUExtent3DDict
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUExtent3D): WGPUExtent3DDict = createJsObject<WGPUExtent3DDict>().apply {
    width = input.width
    height = input.height
    depthOrArrayLayers = input.depthOrArrayLayers
}