

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUBuffer
import org.khronos.webgl.Float32Array

actual class Buffer(internal val handler: GPUBuffer) : AutoCloseable {

	actual val size: GPUSize64
		get() = handler.size

	actual fun getMappedRange(offset: GPUSize64?, size: GPUSize64?): ByteArray = when {
		size == null && offset != null -> handler.getMappedRange(offset)
		size == null && offset == null -> handler.getMappedRange()
		size != null && offset != null -> handler.getMappedRange(offset, size)
		else -> error("size cannot be set without offset")
	}
		.unsafeCast<ByteArray>()

	actual fun unmap() {
		handler.unmap()
	}

	actual fun map(buffer: FloatArray) {
		Float32Array(handler.getMappedRange())
			.set(buffer.toTypedArray(), 0)
	}

	override fun close() {
		//Nothing to do on JS
	}
}