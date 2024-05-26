

package io.ygdrasil.wgpu

expect class Buffer : AutoCloseable {

	val size: GPUSize64

	fun getMappedRange(offset: GPUSize64? = null, size: GPUSize64? = null): ByteArray

	fun unmap()

	fun map(buffer: FloatArray)

	override fun close()
}

data class BufferDescriptor(
	var size: GPUSize64,
	var usage: Set<BufferUsage>,
	var mappedAtCreation: Boolean = false
)