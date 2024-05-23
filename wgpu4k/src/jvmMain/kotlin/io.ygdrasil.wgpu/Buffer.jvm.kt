package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import java.lang.foreign.MemorySegment

actual class Buffer(internal val handler: MemorySegment) : AutoCloseable {

	val handler2: WGPUBuffer = WGPUBufferImpl(handler.toPointer())

	actual val size: GPUSize64
		get() = webgpu_h.wgpuBufferGetSize(handler)

	actual fun getMappedRange(offset: GPUSize64?, size: GPUSize64?): ByteArray {
		webgpu_h.wgpuBufferGetMappedRange(handler, offset ?: 0, size ?: 0)
		TODO()
	}

	actual fun unmap() {
		webgpu_h.wgpuBufferUnmap(handler)
	}

	actual fun map(buffer: FloatArray) {
		logUnitNative {
			"wgpuBufferGetMappedRange" to listOf(
				NativeLong(0),
				(buffer.size * Float.SIZE_BYTES).toNativeLong()
			)
		}
		(wgpuBufferGetMappedRange(handler2, NativeLong(0), (buffer.size * Float.SIZE_BYTES).toNativeLong())
			?: error("fail to get mapped range"))
			.write(0L, buffer, 0, buffer.size)
	}

	actual override fun close() {
		webgpu_h.wgpuBufferRelease(handler)
	}

}


