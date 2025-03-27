package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class Texture(val handler: WGPUTexture) : GPUTexture {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }
    actual override val width: GPUIntegerCoordinateOut
        get() = handler.width.asUInt()
    actual override val height: GPUIntegerCoordinateOut
        get() = handler.height.asUInt()
    actual override val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = handler.depthOrArrayLayers.asUInt()
    actual override val mipLevelCount: GPUIntegerCoordinateOut
        get() = handler.mipLevelCount.asUInt()
    actual override val sampleCount: GPUSize32Out
        get() = handler.sampleCount.asUInt()
    actual override val dimension: GPUTextureDimension
        get() = GPUTextureDimension.of(handler.dimension) ?: error("unsupported texture dimension ${handler.dimension}")
    actual override val format: GPUTextureFormat
        get() = GPUTextureFormat.of(handler.format) ?: error("unsupported texture format ${handler.format}")
    actual override val usage: Set<GPUTextureUsage>
        get() = GPUTextureUsage.entries.filter { (it.value or handler.usage.asULong()) != 0uL }.toSet()

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
