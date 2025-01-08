package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.Size3D
import io.ygdrasil.webgpu.internal.js.GPUExtent3DDict
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: Size3D): GPUExtent3DDict = createJsObject<GPUExtent3DDict>().apply {
    width = input.width
    height = input.height
    depthOrArrayLayers = input.depthOrArrayLayers
}