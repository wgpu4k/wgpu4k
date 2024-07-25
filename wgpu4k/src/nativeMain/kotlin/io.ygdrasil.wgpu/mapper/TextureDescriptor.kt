@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureDescriptor
import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: TextureDescriptor) = alloc<WGPUTextureDescriptor>().also { output ->
    if (input.label != null) WGPUTextureDescriptor.label(output, allocateFrom(input.label))
    map(input.size, WGPUTextureDescriptor.size(output))
    WGPUTextureDescriptor.format(output, input.format.value)
    WGPUTextureDescriptor.usage(output, input.usage.toFlagInt())
    WGPUTextureDescriptor.mipLevelCount(output, input.mipLevelCount)
    WGPUTextureDescriptor.sampleCount(output, input.sampleCount)
    WGPUTextureDescriptor.dimension(output, input.dimension.value)
    if (input.viewFormats.isNotEmpty()) {
        WGPUTextureDescriptor.viewFormatCount(output, input.viewFormats.size.toLong())
        val viewFormats = allocateFrom(ValueLayout.JAVA_INT, *(input.viewFormats.map { it.value }.toIntArray()))
        WGPUTextureDescriptor.viewFormats(output, viewFormats)
    }
}

