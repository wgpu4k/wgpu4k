package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import java.lang.foreign.MemorySegment

actual class BindGroup(internal val handler: MemorySegment) : AutoCloseable {

	actual override fun close() {
		webgpu_h.wgpuBindGroupRelease(handler)
	}
}