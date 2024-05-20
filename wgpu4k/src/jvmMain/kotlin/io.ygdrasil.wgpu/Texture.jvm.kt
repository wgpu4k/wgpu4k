package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.textureViewDescriptorMapper
import java.lang.foreign.MemorySegment


actual class Texture(internal val handler: MemorySegment) : AutoCloseable {

    val handler2: WGPUTexture = WGPUTextureImpl(handler.toPointer())

    actual val width: GPUIntegerCoordinateOut
        get() = wgpuTextureGetWidth(handler2)
    actual val height: GPUIntegerCoordinateOut
        get() = wgpuTextureGetHeight(handler2)
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = wgpuTextureGetDepthOrArrayLayers(handler2)
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = wgpuTextureGetMipLevelCount(handler2)
    actual val sampleCount: GPUSize32Out
        get() = wgpuTextureGetSampleCount(handler2)
    actual val dimension: TextureDimension
        get() = wgpuTextureGetDimension(handler2)
            ?.let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = wgpuTextureGetFormat(handler2)
            ?.let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = wgpuTextureGetUsage(handler2)



    actual fun createView(descriptor: TextureViewDescriptor?): TextureView =
        descriptor?.let { textureViewDescriptorMapper.map<Any, WGPUTextureViewDescriptor>(it) }
            .also { logUnitNative { "wgpuTextureCreateView" to listOf(handler2, it) } }
            .let { wgpuTextureCreateView(handler2, it) }
            ?.let { TextureView(it) }
            ?: error("fail to create texture view")

    actual override fun close() {
        logUnitNative { "wgpuTextureRelease" to listOf(handler2) }
        wgpuTextureRelease(handler2)
    }
}
