@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

expect class Buffer : AutoCloseable {

	val size: GPUSize64

	fun getMappedRange(offset: GPUSize64? = null, size: GPUSize64? = null): ByteArray

	fun unmap()

	fun map(buffer: FloatArray)
}

data class BufferDescriptor(
	var size: GPUSize64,
	var usage: GPUBufferUsageFlags,
	var mappedAtCreation: Boolean? = null
)