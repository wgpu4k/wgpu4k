package io.ygdrasil.webgpu.mapper

import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUShaderModuleDescriptor
import io.ygdrasil.wgpu.WGPUSType_ShaderSourceWGSL
import io.ygdrasil.wgpu.WGPUShaderModuleDescriptor
import io.ygdrasil.wgpu.WGPUShaderSourceWGSL

internal fun MemoryAllocator.map(input: GPUShaderModuleDescriptor): WGPUShaderModuleDescriptor =
    WGPUShaderModuleDescriptor.allocate(this).also { output ->
        map(input.label, output.label)
        output.nextInChain = mapCode(input.code).chain

    }

private fun MemoryAllocator.mapCode(input: String) = WGPUShaderSourceWGSL.allocate(this).apply {
        code.length = input.length.toULong()
        code.data = allocateFrom(input)
        chain.sType = WGPUSType_ShaderSourceWGSL
}

