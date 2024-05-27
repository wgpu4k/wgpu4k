package io.ygdrasil.wgpu

import kotlin.js.JsExport

typealias GPUBufferDynamicOffset = Number

typealias GPUBufferUsageFlags = Int

typealias GPUColorWriteFlags = Number

typealias GPUDepthBias = Int

typealias GPUFlagsConstant = Number

typealias GPUIndex32 = Int

typealias GPUIntegerCoordinate = Int

typealias GPUIntegerCoordinates = Pair<GPUIntegerCoordinate, GPUIntegerCoordinate>

typealias GPUIntegerCoordinateOut = Int

typealias GPUMapModeFlags = Number

typealias GPUPipelineConstantValue = Number

typealias GPUSampleMask = UInt

typealias GPUShaderStageFlags = Number

typealias GPUSignedOffset32 = Number

typealias GPUSize32 = Int

typealias GPUSize32Out = Int

typealias GPUSize64 = Long

typealias GPUSize64Out = Long

typealias GPUStencilValue = Long

typealias GPUTextureUsageFlags = Int

@JsExport
data class Size3D(
	var width: Int,
	var height: Int = 1,
	var depthOrArrayLayers: Int = 1
) {
	fun toArray() = arrayOf(width, height, depthOrArrayLayers)
}

data class Origin3D(
	var x: GPUIntegerCoordinate = 0,
	var y: GPUIntegerCoordinate = 0,
	var z: GPUIntegerCoordinate = 0
) {
	fun toArray() = arrayOf(x, y, z)
}