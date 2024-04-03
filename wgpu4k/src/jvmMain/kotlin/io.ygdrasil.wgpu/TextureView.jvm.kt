package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUTextureView
import io.ygdrasil.wgpu.internal.jvm.logNative
import io.ygdrasil.wgpu.internal.jvm.wgpuTextureViewRelease

actual class TextureView(internal val handler: WGPUTextureView) : AutoCloseable {
	override fun close() {
		logNative { "wgpuTextureViewRelease" to listOf(handler) }
		wgpuTextureViewRelease(handler)
	}
}