

package io.ygdrasil.wgpu

expect class Buffer : AutoCloseable {

	val size: GPUSize64
	val usage: Set<BufferUsage>

	fun unmap()

	fun mapFrom(buffer: FloatArray)

	fun mapFrom(buffer: ByteArray)

	override fun close()
}

data class BufferDescriptor(
	val size: GPUSize64,
	val usage: Set<BufferUsage>,
	val mappedAtCreation: Boolean = false,
	val label: String? = null
)