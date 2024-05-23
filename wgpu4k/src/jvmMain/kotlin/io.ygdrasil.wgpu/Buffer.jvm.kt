package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import io.ygdrasil.wgpu.internal.jvm.*
import java.lang.foreign.MemorySegment

actual class Buffer(internal val handler: MemorySegment) : AutoCloseable {

	val handler2: WGPUBuffer = WGPUBufferImpl(handler.toPointer())

	actual val size: GPUSize64
		get() = wgpuBufferGetSize(handler2)

	actual fun getMappedRange(offset: GPUSize64?, size: GPUSize64?): ByteArray {
		wgpuBufferGetMappedRange(handler2, offset?.toNativeLong(), size?.toNativeLong())
		TODO()
	}

	actual fun unmap() {
		logUnitNative { "wgpuBufferUnmap" to listOf() }
		wgpuBufferUnmap(handler2)
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
		logUnitNative { "wgpuBufferRelease" to listOf(handler2) }
		wgpuBufferRelease(handler2)
	}

}


