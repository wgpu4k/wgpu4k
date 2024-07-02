package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.js.GPUExtent3DDict
import io.ygdrasil.wgpu.internal.js.GPUTextureDescriptor

internal fun map(input: TextureDescriptor): GPUTextureDescriptor = object : GPUTextureDescriptor {
    override var label: String? = input.label ?: undefined
    override var size: GPUExtent3DDict = map(input.size)
    override var mipLevelCount: GPUIntegerCoordinate? = input.mipLevelCount
    override var sampleCount: GPUSize32? = input.sampleCount
    override var dimension: String? = input.dimension.stringValue
    override var format: String = input.format.actualName
    override var usage: GPUTextureUsageFlags = input.usage.toFlagInt()
    override var viewFormats: Array<String> = input.viewFormats.map { it.actualName }.toTypedArray()

}