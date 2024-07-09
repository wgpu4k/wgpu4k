package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUTexture

actual class Texture(gpuTexture: GPUTexture) : AutoCloseable {

    actual val width: GPUIntegerCoordinateOut
        get() = TODO("Not yet implemented")
    actual val height: GPUIntegerCoordinateOut
        get() = TODO("Not yet implemented")
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = TODO("Not yet implemented")
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = TODO("Not yet implemented")
    actual val sampleCount: GPUSize32Out
        get() = TODO("Not yet implemented")
    actual val dimension: TextureDimension
        get() = TODO("Not yet implemented")
    actual val format: TextureFormat
        get() = TODO("Not yet implemented")
    actual val usage: GPUFlagsConstant
        get() = TODO("Not yet implemented")

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        TODO("Not yet implemented")
    }
}
