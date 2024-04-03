package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.textureViewDescriptorMapper


actual class Texture(internal val handler: WGPUTexture) : AutoCloseable {

    init {
        check(handler != null) { "handler should not be null" }
    }

    actual val width: GPUIntegerCoordinateOut
        get() = wgpuTextureGetWidth(handler)
    actual val height: GPUIntegerCoordinateOut
        get() = wgpuTextureGetHeight(handler)
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = wgpuTextureGetDepthOrArrayLayers(handler)
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = wgpuTextureGetMipLevelCount(handler)
    actual val sampleCount: GPUSize32Out
        get() = wgpuTextureGetSampleCount(handler)
    actual val dimension: TextureDimension
        get() = wgpuTextureGetDimension(handler)
            ?.let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = wgpuTextureGetFormat(handler)
            ?.let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = wgpuTextureGetUsage(handler)



    actual fun createView(descriptor: TextureViewDescriptor?): TextureView =
        descriptor?.let { textureViewDescriptorMapper.map<Any, WGPUTextureViewDescriptor>(it) }
            .also { logNative { "wgpuTextureCreateView" to listOf(handler, it) } }
            .let { wgpuTextureCreateView(handler, it) }
            ?.let { TextureView(it) }
            ?: error("fail to create texture view")

    override fun close() {
        logNative { "wgpuTextureRelease" to listOf(handler) }
        wgpuTextureRelease(handler)
    }
}
