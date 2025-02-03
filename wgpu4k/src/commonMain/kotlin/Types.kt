package io.ygdrasil.webgpu

typealias GPUBufferDynamicOffset = ULong

typealias GPUBufferUsageFlags = Int

typealias GPUColorWriteFlags = ULong

typealias GPUDepthBias = Int

typealias GPUFlagsConstant = ULong

typealias GPUIndex32 = UInt

typealias GPUIntegerCoordinate = UInt

typealias GPUIntegerCoordinates = Pair<GPUIntegerCoordinate, GPUIntegerCoordinate>

typealias GPUIntegerCoordinateOut = UInt

typealias GPUMapModeFlags = Int

typealias GPUPipelineConstantValue = Double

typealias GPUSampleMask = UInt

typealias GPUShaderStageFlags = Int

typealias GPUSignedOffset32 = Int

typealias GPUSize32 = UInt

typealias GPUSize32Out = UInt

typealias GPUSize64 = ULong

typealias GPUStencilValue = UInt

typealias GPUTextureUsageFlags = Int

data class Size3D(
	val width: UInt,
	val height: UInt = 1u,
	val depthOrArrayLayers: UInt = 1u,
) {
    fun toArray() = arrayOf(width, height, depthOrArrayLayers)
}

data class Origin3D(
	val x: GPUIntegerCoordinate = 0u,
	val y: GPUIntegerCoordinate = 0u,
	val z: GPUIntegerCoordinate = 0u,
) {
    fun toArray() = arrayOf(x, y, z)

    companion object {
        val Zero = Origin3D(0u, 0u, 0u)
    }
}