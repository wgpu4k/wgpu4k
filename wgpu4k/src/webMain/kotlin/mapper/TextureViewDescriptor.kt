@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUTextureViewDescriptor
import io.ygdrasil.webgpu.WGPUTextureViewDescriptor
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: GPUTextureViewDescriptor): WGPUTextureViewDescriptor =
    createJsObject<WGPUTextureViewDescriptor>().apply {
        label = input.label
        input.format?.let { format = it.value }
        input.dimension?.let { dimension = it.value }
        aspect = input.aspect.value
        baseMipLevel = input.baseMipLevel.asJsNumber()
        input.mipLevelCount?.let { mipLevelCount = it.asJsNumber() }
        baseArrayLayer = input.baseArrayLayer.asJsNumber()
        input.arrayLayerCount?.let { arrayLayerCount = it.asJsNumber() }
    }