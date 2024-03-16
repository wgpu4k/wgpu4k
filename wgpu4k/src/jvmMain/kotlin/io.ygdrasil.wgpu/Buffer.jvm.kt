package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import io.ygdrasil.wgpu.internal.jvm.*

actual class Buffer(internal val handler: WGPUBuffer) : AutoCloseable {

	actual val size: GPUSize64
		get() = wgpuBufferGetSize(handler)

	actual fun getMappedRange(offset: GPUSize64?, size: GPUSize64?): ByteArray {
		wgpuBufferGetMappedRange(handler, offset?.toNativeLong(), size?.toNativeLong())
		TODO()
	}

	actual fun unmap() {
		wgpuBufferUnmap(handler)
	}

	actual fun map(buffer: FloatArray) {
		(wgpuBufferGetMappedRange(handler, NativeLong(0), (buffer.size * Float.SIZE_BYTES).toNativeLong())
			?: error("fail to get mapped range"))
			.write(0L, buffer, 0, buffer.size)
	}

	override fun close() {
		wgpuBufferRelease(handler)
	}

}


