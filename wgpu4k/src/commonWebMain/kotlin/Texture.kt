package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class Texture(internal val handler: WGPUTexture) : GPUTexture {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }
    actual override val width: GPUIntegerCoordinateOut
        get() = handler.width
    actual override val height: GPUIntegerCoordinateOut
        get() = handler.height
    actual override val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = handler.depthOrArrayLayers
    actual override val mipLevelCount: GPUIntegerCoordinateOut
        get() = handler.mipLevelCount
    actual override val sampleCount: GPUSize32Out
        get() = handler.sampleCount
    actual override val dimension: TextureDimension
        get() = GPUTextureDimension.of(handler.dimension) ?: error("unsupported texture dimension ${handler.dimension}")
    actual override val format: GPUTextureFormat
        get() = GPUTextureFormat.of(handler.format) ?: error("unsupported texture format ${handler.format}")
    actual override val usage: GPUFlagsConstant
        get() = handler.usage

    actual override fun createView(descriptor: GPUTextureViewDescriptor?): GPUTextureView {
        return TextureView(
            when (descriptor) {
                null -> handler.createView()
                else -> handler.createView(map(descriptor))
            }
        )
    }

    actual override fun close() {
        handler.destroy()
    }
}
