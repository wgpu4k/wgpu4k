

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int8Array

actual class Buffer(val handler: GPUBuffer) : AutoCloseable {

	actual val size: GPUSize64
		get() = handler.size
	actual val usage: Set<BufferUsage>
		get() = BufferUsage.entries.filter { it.value and handler.usage != 0 }.toSet()

	actual fun unmap() {
		handler.unmap()
	}

	actual fun mapFrom(buffer: FloatArray, offset: Int) {
		Float32Array(handler.getMappedRange(offset.toLong()))
			.set(buffer.toTypedArray(), 0)
	}

	actual 	fun mapFrom(buffer: ByteArray, offset: Int) {
		Int8Array(handler.getMappedRange(offset.toLong()))
			.set(buffer.toTypedArray(), 0)
	}

	actual override fun close() {
		//Nothing to do on JS
	}
}