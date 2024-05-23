package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import io.ygdrasil.wgpu.mapper.textureViewDescriptorMapper
import java.lang.foreign.MemorySegment


actual class Texture(internal val handler: MemorySegment) : AutoCloseable {

    actual val width: GPUIntegerCoordinateOut
        get() = webgpu_h.wgpuTextureGetWidth(handler)
    actual val height: GPUIntegerCoordinateOut
        get() = webgpu_h.wgpuTextureGetHeight(handler)
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = webgpu_h.wgpuTextureGetDepthOrArrayLayers(handler)
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = webgpu_h.wgpuTextureGetMipLevelCount(handler)
    actual val sampleCount: GPUSize32Out
        get() = webgpu_h.wgpuTextureGetSampleCount(handler)
    actual val dimension: TextureDimension
        get() = webgpu_h.wgpuTextureGetDimension(handler)
            ?.let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = webgpu_h.wgpuTextureGetFormat(handler)
            ?.let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = webgpu_h.wgpuTextureGetUsage(handler)



    actual fun createView(descriptor: TextureViewDescriptor?): TextureView =
        descriptor?.let { textureViewDescriptorMapper.map<Any, WGPUTextureViewDescriptor>(it) }
            .also { it?.write() }?.pointer?.toMemory()
            .let { webgpu_h.wgpuTextureCreateView(handler, it ?: MemorySegment.NULL) }
            ?.let { TextureView(it) }
            ?: error("fail to create texture view")

    actual override fun close() {
        webgpu_h.wgpuTextureRelease(handler)
    }
}
