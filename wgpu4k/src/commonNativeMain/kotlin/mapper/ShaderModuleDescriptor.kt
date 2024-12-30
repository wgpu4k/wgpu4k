package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import webgpu.WGPUSType_ShaderSourceWGSL
import webgpu.WGPUShaderModuleDescriptor
import webgpu.WGPUShaderSourceWGSL

internal fun MemoryAllocator.map(input: ShaderModuleDescriptor): WGPUShaderModuleDescriptor =
    WGPUShaderModuleDescriptor.allocate(this).also { output ->
        if (input.label != null) map(input.label, output.label)
        output.nextInChain = mapCode(input.code).handler

    }

private fun MemoryAllocator.mapCode(input: String) = WGPUShaderModuleDescriptor.allocate(this).apply {
    nextInChain = WGPUShaderSourceWGSL.allocate(this@mapCode).apply {
        code.length = input.length.toULong()
        code.data = allocateFrom(input)
        chain.sType = WGPUSType_ShaderSourceWGSL
    }.handler
}


