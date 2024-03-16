package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUTexture
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureViewDescriptor
import io.ygdrasil.wgpu.internal.jvm.wgpuTextureCreateView
import io.ygdrasil.wgpu.internal.jvm.wgpuTextureRelease


actual class Texture(internal val handler: WGPUTexture) : AutoCloseable {
	actual fun createView(descriptor: TextureViewDescriptor?): TextureView {
		return TextureView(
			wgpuTextureCreateView(handler, descriptor?.convert())
				?: error("fail to create texture view")
		)
	}

	override fun close() {
		wgpuTextureRelease(handler)
	}
}

private fun TextureViewDescriptor?.convert(): WGPUTextureViewDescriptor? = WGPUTextureViewDescriptor().also {
	// TODO
}