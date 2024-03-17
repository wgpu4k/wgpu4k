package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUTexture
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureViewDescriptor
import io.ygdrasil.wgpu.internal.jvm.wgpuTextureCreateView
import io.ygdrasil.wgpu.internal.jvm.wgpuTextureRelease
import io.ygdrasil.wgpu.mapper.textureViewDescriptorMapper


actual class Texture(internal val handler: WGPUTexture) : AutoCloseable {
    actual fun createView(descriptor: TextureViewDescriptor?): TextureView =
        textureViewDescriptorMapper.map<Any, WGPUTextureViewDescriptor>(descriptor ?: TextureViewDescriptor())
            .let { wgpuTextureCreateView(handler, it) }
            ?.let { TextureView(it) }
            ?: error("fail to create texture view")

    override fun close() {
        wgpuTextureRelease(handler)
    }
}
