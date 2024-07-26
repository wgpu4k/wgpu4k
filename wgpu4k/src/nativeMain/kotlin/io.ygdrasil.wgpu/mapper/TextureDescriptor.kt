@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.toFlagUInt
import kotlinx.cinterop.*
import webgpu.WGPUTextureDescriptor
import webgpu.WGPUTextureFormatVar

internal fun Arena.map(input: TextureDescriptor) = alloc<WGPUTextureDescriptor>().also { output ->
    if (input.label != null) output.label = input.label.cstr.getPointer(this)
    map(input.size, output.size)
    output.format = input.format.value.toUInt()
    output.usage = input.usage.toFlagUInt()
    output.mipLevelCount = input.mipLevelCount.toUInt()
    output.sampleCount = input.sampleCount.toUInt()
    output.dimension = input.dimension.value.toUInt()
    if (input.viewFormats.isNotEmpty()) {
        output.viewFormatCount = input.viewFormats.size.toULong()
        val viewFormats = allocArray<WGPUTextureFormatVar>(input.viewFormats.size)
        input.viewFormats.forEachIndexed { index, format ->
            viewFormats[index] = format.value.toUInt()
        }
        output.viewFormats = viewFormats
    }
}

