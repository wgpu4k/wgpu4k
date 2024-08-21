package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Texture(val handler: Long) : AutoCloseable {

    actual val width: GPUIntegerCoordinateOut
        get() = JniInterface.wgpuTextureGetWidth(handler)
    actual val height: GPUIntegerCoordinateOut
        get() = JniInterface.wgpuTextureGetHeight(handler)
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = JniInterface.wgpuTextureGetDepthOrArrayLayers(handler)
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = JniInterface.wgpuTextureGetMipLevelCount(handler)
    actual val sampleCount: GPUSize32Out
        get() = JniInterface.wgpuTextureGetSampleCount(handler)
    actual val dimension: TextureDimension
        get() = JniInterface.wgpuTextureGetDimension(handler)
            .let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = JniInterface.wgpuTextureGetFormat(handler)
            .let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = JniInterface.wgpuTextureGetUsage(handler)

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView {
        return JniInterface.wgpuTextureCreateView(handler, descriptor)
            .let { TextureView(it) }
    }

    actual override fun close() {
        JniInterface.wgpuTextureRelease(handler)
    }
}
