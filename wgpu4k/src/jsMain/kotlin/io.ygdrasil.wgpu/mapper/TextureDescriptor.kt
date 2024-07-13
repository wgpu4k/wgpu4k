package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.internal.js.GPUTextureDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject
import io.ygdrasil.wgpu.toFlagInt

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