package io.ygdrasil.webgpu

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