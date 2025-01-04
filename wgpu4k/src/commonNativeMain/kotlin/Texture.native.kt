package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUTexture
import io.ygdrasil.wgpu.wgpuTextureCreateView
import io.ygdrasil.wgpu.wgpuTextureGetDepthOrArrayLayers
import io.ygdrasil.wgpu.wgpuTextureGetDimension
import io.ygdrasil.wgpu.wgpuTextureGetFormat
import io.ygdrasil.wgpu.wgpuTextureGetHeight
import io.ygdrasil.wgpu.wgpuTextureGetMipLevelCount
import io.ygdrasil.wgpu.wgpuTextureGetSampleCount
import io.ygdrasil.wgpu.wgpuTextureGetUsage
import io.ygdrasil.wgpu.wgpuTextureGetWidth
import io.ygdrasil.wgpu.wgpuTextureRelease


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
