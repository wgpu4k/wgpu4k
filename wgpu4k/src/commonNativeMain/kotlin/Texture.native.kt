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


actual class Texture(internal val handler: WGPUTexture) : GPUTexture {

    actual override val width: GPUIntegerCoordinateOut
        get() = wgpuTextureGetWidth(handler)
    actual override val height: GPUIntegerCoordinateOut
        get() = wgpuTextureGetHeight(handler)
    actual override val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = wgpuTextureGetDepthOrArrayLayers(handler)
    actual override val mipLevelCount: GPUIntegerCoordinateOut
        get() = wgpuTextureGetMipLevelCount(handler)
    actual override val sampleCount: GPUSize32Out
        get() = wgpuTextureGetSampleCount(handler)
    actual override val dimension: GPUTextureDimension
        get() = wgpuTextureGetDimension(handler)
            .let { GPUTextureDimension.of(it) ?: error("Unknown texture dimension $it") }
    actual override val format: GPUTextureFormat
        get() = wgpuTextureGetFormat(handler)
            .let { GPUTextureFormat.of(it) ?: error("Unknown texture format $it")}
    actual override val usage: GPUFlagsConstant
        get() = wgpuTextureGetUsage(handler)

    actual override fun createView(descriptor: GPUTextureViewDescriptor?): GPUTextureView = memoryScope { scope ->
        descriptor?.let { scope.map(descriptor) }
            .let { wgpuTextureCreateView(handler, it) }
            ?.let { TextureView(it) }
            ?: error("fail to create texture view")
    }

    actual override fun close() {
        wgpuTextureRelease(handler)
    }
}
