package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map
import kotlin.Boolean
import kotlin.OptIn
import kotlin.String
import kotlin.error
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.toInt
import kotlin.toUInt

@OptIn(ExperimentalWasmJsInterop::class)
actual class Texture(val handler: WGPUTexture, val canBeDestroy: Boolean = true) : GPUTexture {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }
    actual override val width: GPUIntegerCoordinateOut
        get() = handler.width.toInt().toUInt()
    actual override val height: GPUIntegerCoordinateOut
        get() = handler.height.toInt().toUInt()
    actual override val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = handler.depthOrArrayLayers.toInt().toUInt()
    actual override val mipLevelCount: GPUIntegerCoordinateOut
        get() = handler.mipLevelCount.toInt().toUInt()
    actual override val sampleCount: GPUSize32Out
        get() = handler.sampleCount.toInt().toUInt()
    actual override val dimension: GPUTextureDimension
        get() = GPUTextureDimension.of(handler.dimension) ?: error("unsupported texture dimension ${handler.dimension}")
    actual override val format: GPUTextureFormat
        get() = GPUTextureFormat.of(handler.format) ?: error("unsupported texture format ${handler.format}")
    actual override val usage: Set<GPUTextureUsage>
        get() = GPUTextureUsage.entries.filter { (it.value or handler.usage.toULong()) != 0uL }.toSet()

    actual override fun createView(descriptor: GPUTextureViewDescriptor?): GPUTextureView {
        return TextureView(
            when (descriptor) {
                null -> handler.createView()
                else -> handler.createView(map(descriptor))
            }
        )
    }

    actual override fun close() {
        // On firefox, canvas textures throw an exception when calling destroy
        if (canBeDestroy) handler.destroy()
    }
}
