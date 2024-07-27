@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.mapper.map
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import webgpu.*

actual class Texture(internal val handler: WGPUTexture) : AutoCloseable {

    actual val width: GPUIntegerCoordinateOut
        get() = wgpuTextureGetWidth(handler).toInt()
    actual val height: GPUIntegerCoordinateOut
        get() = wgpuTextureGetHeight(handler).toInt()
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = wgpuTextureGetDepthOrArrayLayers(handler).toInt()
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = wgpuTextureGetMipLevelCount(handler).toInt()
    actual val sampleCount: GPUSize32Out
        get() = wgpuTextureGetSampleCount(handler).toInt()
    actual val dimension: TextureDimension
        get() = wgpuTextureGetDimension(handler)
            .toInt()
            .let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = wgpuTextureGetFormat(handler)
            .toInt()
            .let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = wgpuTextureGetUsage(handler).toInt()

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView = memScoped {
        descriptor?.let { map(it) }
            .let { wgpuTextureCreateView(handler, it?.ptr) }
            ?.let { TextureView(it) }
            ?: error("fail to create texture view")
    }

    actual override fun close() {
        wgpuTextureRelease(handler)
    }
}
