package io.ygdrasil.wgpu

import ffi.memoryScope
import io.ygdrasil.wgpu.mapper.map
import webgpu.WGPUTexture
import webgpu.wgpuTextureCreateView
import webgpu.wgpuTextureGetDepthOrArrayLayers
import webgpu.wgpuTextureGetDimension
import webgpu.wgpuTextureGetFormat
import webgpu.wgpuTextureGetHeight
import webgpu.wgpuTextureGetMipLevelCount
import webgpu.wgpuTextureGetSampleCount
import webgpu.wgpuTextureGetUsage
import webgpu.wgpuTextureGetWidth
import webgpu.wgpuTextureRelease


actual class Texture(internal val handler: WGPUTexture) : AutoCloseable {

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
            .let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = wgpuTextureGetFormat(handler)
            .let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = wgpuTextureGetUsage(handler)

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView = memoryScope { scope ->
        descriptor?.let { scope.map(descriptor) }
            .let { wgpuTextureCreateView(handler, it) }
            ?.let { TextureView(it) }
            ?: error("fail to create texture view")
    }

    actual override fun close() {
        wgpuTextureRelease(handler)
    }
}
