package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUCommandBufferDescriptor
import io.ygdrasil.webgpu.WGPUCommandBufferDescriptor
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUCommandBufferDescriptor): WGPUCommandBufferDescriptor =
    createJsObject<WGPUCommandBufferDescriptor>()
        .also { it.label = input.label }