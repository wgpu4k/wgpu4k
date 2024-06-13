

package io.ygdrasil.wgpu

expect class Buffer : AutoCloseable {

	val size: GPUSize64
	val usage: Set<BufferUsage>

	fun unmap()

	fun mapFrom(buffer: FloatArray, offset: Int = 0)

	fun mapFrom(buffer: ByteArray, offset: Int = 0)

	override fun close()
}

data class BufferDescriptor(
	val size: GPUSize64,
	val usage: Set<BufferUsage>,
	val mappedAtCreation: Boolean = false,
	val label: String? = null
)