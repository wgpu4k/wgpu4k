package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Texture(internal val handler: Long) : AutoCloseable {

    actual val width: GPUIntegerCoordinateOut
        get() = JniInterface.instance.wgpuTextureGetWidth(handler)
    actual val height: GPUIntegerCoordinateOut
        get() = JniInterface.instance.wgpuTextureGetHeight(handler)
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = JniInterface.instance.wgpuTextureGetDepthOrArrayLayers(handler)
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = JniInterface.instance.wgpuTextureGetMipLevelCount(handler)
    actual val sampleCount: GPUSize32Out
        get() = JniInterface.instance.wgpuTextureGetSampleCount(handler)
    actual val dimension: TextureDimension
        get() = JniInterface.instance.wgpuTextureGetDimension(handler)
            .let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = JniInterface.instance.wgpuTextureGetFormat(handler)
            .let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = JniInterface.instance.wgpuTextureGetUsage(handler)

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView {
        return JniInterface.instance.wgpuTextureCreateView(handler, descriptor)
            .let { TextureView(it) }
    }

    actual override fun close() {
        JniInterface.instance.wgpuTextureRelease(handler)
    }
}
