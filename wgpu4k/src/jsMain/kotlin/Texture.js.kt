package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUTexture
import io.ygdrasil.webgpu.mapper.map

actual class Texture(internal val handler: GPUTexture) : AutoCloseable {

    val alphaMode: CompositeAlphaMode
        get() = CompositeAlphaMode.of(handler.alphaMode) ?: error("unsupported alpha mode ${handler.alphaMode}")
    actual val width: GPUIntegerCoordinateOut
        get() = handler.width
    actual val height: GPUIntegerCoordinateOut
        get() = handler.height
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = handler.depthOrArrayLayers
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = handler.mipLevelCount
    actual val sampleCount: GPUSize32Out
        get() = handler.sampleCount
    actual val dimension: TextureDimension
        get() = TextureDimension.of(handler.dimension) ?: error("unsupported texture dimension ${handler.dimension}")
    actual val format: TextureFormat
        get() = TextureFormat.of(handler.format) ?: error("unsupported texture format ${handler.format}")
    actual val usage: GPUFlagsConstant
        get() = handler.usage

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView {
        return TextureView(
            when (descriptor) {
                null -> handler.createView()
                else -> handler.createView(map(descriptor))
            }
        )
    }

    actual override fun close() {
        // nothing to do
    }
}
