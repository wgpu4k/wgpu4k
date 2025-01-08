package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.WGPUSType_ShaderModuleWGSLDescriptor
import io.ygdrasil.wgpu.WGPUShaderModuleDescriptor
import io.ygdrasil.wgpu.WGPUShaderModuleWGSLDescriptor

internal fun MemoryAllocator.map(input: ShaderModuleDescriptor): WGPUShaderModuleDescriptor =
    WGPUShaderModuleDescriptor.allocate(this).also { output ->
        if (input.label != null) output.label = allocateFrom(input.label)
        output.nextInChain = mapCode(input.code).handler

    }

private fun MemoryAllocator.mapCode(input: String) = WGPUShaderModuleDescriptor.allocate(this).apply {
    nextInChain = WGPUShaderModuleWGSLDescriptor.allocate(this@mapCode).apply {
        code = allocateFrom(input)
        chain.sType = WGPUSType_ShaderModuleWGSLDescriptor
    }.handler
}


