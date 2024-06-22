

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUBuffer
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.asDeferred
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int8Array

actual class Buffer(internal val handler: GPUBuffer) : AutoCloseable {

	actual val size: GPUSize64
		get() = handler.size
	actual val usage: Set<BufferUsage>
		get() = BufferUsage.entries.filter { it.value and handler.usage != 0 }.toSet()

	actual fun unmap() {
		handler.unmap()
	}

	actual fun mapFrom(buffer: FloatArray, offset: Int) {
		Float32Array(handler.getMappedRange(offset.toLong(), buffer.size.toLong() * Float.SIZE_BYTES))
			.set(buffer.toTypedArray(), 0)
	}

	actual 	fun mapFrom(buffer: ByteArray, offset: Int) {
		Int8Array(handler.getMappedRange(offset.toLong(), buffer.size.toLong()))
			.set(buffer.toTypedArray(), 0)
	}

	actual fun mapInto(buffer: ByteArray, offset: Int) {
		Int8Array(handler.getMappedRange(offset.toLong(), buffer.size.toLong()))
			.also { it.buffer.unsafeCast<ByteArray>().copyInto(buffer) }
	}

	actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64): Deferred<BufferMapAsyncStatus> {
		return handler.mapAsync(mode.toFlagInt(), offset, size)
			.then {
				println("mapAsync return ? $it")
				BufferMapAsyncStatus.success
			}
			.asDeferred()
	}

	actual override fun close() {
		//Nothing to do on JS
	}


}