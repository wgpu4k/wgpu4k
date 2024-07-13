package io.ygdrasil.wgpu

typealias GPUBufferDynamicOffset = ULong

typealias GPUBufferUsageFlags = Int

typealias GPUColorWriteFlags = Int

typealias GPUDepthBias = Int

typealias GPUFlagsConstant = Int

typealias GPUIndex32 = Int

typealias GPUIntegerCoordinate = Int

typealias GPUIntegerCoordinates = Pair<GPUIntegerCoordinate, GPUIntegerCoordinate>

typealias GPUIntegerCoordinateOut = Int

typealias GPUMapModeFlags = Int

typealias GPUPipelineConstantValue = Double

typealias GPUSampleMask = UInt

typealias GPUShaderStageFlags = Int

typealias GPUSignedOffset32 = Int

typealias GPUSize32 = Int

typealias GPUSize32Out = Int

typealias GPUSize64 = Long

typealias GPUSize64Out = Long

typealias GPUStencilValue = Long

typealias GPUTextureUsageFlags = Int

data class Size3D(
	val width: Int,
	val height: Int = 1,
	val depthOrArrayLayers: Int = 1,
) {
    fun toArray() = arrayOf(width, height, depthOrArrayLayers)
}

data class Origin3D(
	val x: GPUIntegerCoordinate = 0,
	val y: GPUIntegerCoordinate = 0,
	val z: GPUIntegerCoordinate = 0,
) {
    fun toArray() = arrayOf(x, y, z)

    companion object {
        val Zero = Origin3D(0, 0, 0)
    }
}