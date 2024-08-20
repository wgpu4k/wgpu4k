package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class Texture(internal val handler: Long) : AutoCloseable {

    actual val width: GPUIntegerCoordinateOut
        get() = JniInterfaceV2.wgpuTextureGetWidth(handler)
    actual val height: GPUIntegerCoordinateOut
        get() = JniInterfaceV2.wgpuTextureGetHeight(handler)
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = JniInterfaceV2.wgpuTextureGetDepthOrArrayLayers(handler)
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = JniInterfaceV2.wgpuTextureGetMipLevelCount(handler)
    actual val sampleCount: GPUSize32Out
        get() = JniInterfaceV2.wgpuTextureGetSampleCount(handler)
    actual val dimension: TextureDimension
        get() = JniInterfaceV2.wgpuTextureGetDimension(handler)
            .let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = JniInterfaceV2.wgpuTextureGetFormat(handler)
            .let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = JniInterfaceV2.wgpuTextureGetUsage(handler)

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView {
        return JniInterfaceV2.wgpuTextureCreateView(handler, descriptor)
            .let { TextureView(it) }
    }

    actual override fun close() {
        JniInterfaceV2.wgpuTextureRelease(handler)
    }
}
