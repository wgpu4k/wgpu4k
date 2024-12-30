package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.internal.js.GPUTextureDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.toFlagInt

internal fun map(input: TextureDescriptor): GPUTextureDescriptor = createJsObject<GPUTextureDescriptor>().apply {
    if (input.label != null) label = input.label
    size = map(input.size)
    mipLevelCount = input.mipLevelCount
    sampleCount = input.sampleCount
    dimension = input.dimension.stringValue
    format = input.format.actualName
    usage = input.usage.toFlagInt()
    viewFormats = input.viewFormats.map { it.actualName }.toTypedArray()
}