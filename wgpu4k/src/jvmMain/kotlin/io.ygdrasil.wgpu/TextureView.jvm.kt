package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import java.lang.foreign.MemorySegment

actual class TextureView(internal val handler: MemorySegment) : AutoCloseable {
	actual override fun close() {
		wgpu_h.wgpuTextureViewRelease(handler)
	}
}