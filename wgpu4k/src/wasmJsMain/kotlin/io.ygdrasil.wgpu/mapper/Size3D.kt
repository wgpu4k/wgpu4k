package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.internal.js.GPUExtent3DDict
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: Size3D): GPUExtent3DDict = createJsObject<GPUExtent3DDict>().apply {
    width = input.width
    height = input.height
    depthOrArrayLayers = input.depthOrArrayLayers
}