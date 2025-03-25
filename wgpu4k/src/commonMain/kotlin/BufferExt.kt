package io.ygdrasil.webgpu

@Deprecated("use mapAsync instead")
suspend fun GPUBuffer.map(mode: Set<GPUMapMode>, offset: GPUSize64 = 0u, size: GPUSize64 = this.size)
        = mapAsync(mode, offset, size).getOrThrow()

@Deprecated("use getMappedRange instead")
expect fun GPUBuffer.mapFrom(buffer: ShortArray, offset: GPUSize64 = 0u)

@Deprecated("use getMappedRange instead")
expect fun GPUBuffer.mapFrom(buffer: FloatArray, offset: GPUSize64 = 0u)

@Deprecated("use getMappedRange instead")
expect fun GPUBuffer.mapFrom(buffer: ByteArray, offset: GPUSize64 = 0u)

@Deprecated("use getMappedRange instead")
expect fun GPUBuffer.mapInto(buffer: ByteArray, offset: GPUSize64 = 0u)

@Deprecated("use getMappedRange instead")
expect fun GPUBuffer.mapInto(buffer: IntArray, offset: GPUSize64 = 0u)