package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUIntegerCoordinate
import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.internal.js.GPUExtent3DDict

fun map(input: Size3D): GPUExtent3DDict = object : GPUExtent3DDict {
    override var width: GPUIntegerCoordinate = input.width
    override var height: GPUIntegerCoordinate? = input.height
    override var depthOrArrayLayers: GPUIntegerCoordinate? = input.depthOrArrayLayers
}