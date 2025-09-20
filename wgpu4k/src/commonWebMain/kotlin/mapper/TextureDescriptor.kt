@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUTextureDescriptor
import io.ygdrasil.webgpu.WGPUTextureDescriptor
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.asJsString
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray
import io.ygdrasil.webgpu.toFlagInt
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: GPUTextureDescriptor): WGPUTextureDescriptor = createJsObject<WGPUTextureDescriptor>().apply {
    label = input.label
    size = map(input.size)
    mipLevelCount = input.mipLevelCount.asJsNumber()
    sampleCount = input.sampleCount.asJsNumber()
    dimension = input.dimension.value
    format = input.format.value
    usage = input.usage.toFlagInt().asJsNumber()
    viewFormats = input.viewFormats.mapJsArray { it.value.asJsString() }
}