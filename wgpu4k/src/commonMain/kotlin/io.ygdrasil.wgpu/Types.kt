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

typealias GPUSampleMask = Int

typealias GPUShaderStageFlags = Number

typealias GPUSignedOffset32 = Number

typealias GPUSize32 = Int

typealias GPUSize32Out = Int

typealias GPUSize64 = Long

typealias GPUSize64Out = Long

typealias GPUStencilValue = Int

typealias GPUTextureUsageFlags = Int

@JsExport
data class GPUExtent3DDictStrict(
	var width: Int,
	var height: Int? = null,
	var depthOrArrayLayers: Int? = null
)