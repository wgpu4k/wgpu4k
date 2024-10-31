package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUShaderModuleCompilationHint
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUShaderModuleWGSLDescriptor
import webgpu.WGPUShaderModuleDescriptor
import java.lang.foreign.MemorySegment

internal fun MemoryAllocator.map(input: ShaderModuleDescriptor): WGPUShaderModuleDescriptor = WGPUShaderModuleDescriptor.allocate(this).also { output ->
    if (input.label != null) map(input.label, output.label)
    output.nextInChain = mapCode(input.code)
    if (input.compilationHints.isNotEmpty()) {
        output.hintCount = input.compilationHints.size.toLong()
        val hints = WGPUShaderModuleCompilationHint.allocateArray(input.compilationHints.size.toLong(), this)
        input.compilationHints.forEachIndexed { index, hint ->
            map(hint, output.asSlice(hints, index.toLong()))
        }
        output.hints = hints
    }

}

private fun MemoryAllocator.map(input: ShaderModuleDescriptor.CompilationHint, output: MemorySegment) {
    WGPUShaderModuleCompilationHint.entryPoint(output, allocateFrom(input.entryPoint))
    // TODO find how to map layout
}

private fun MemoryAllocator.mapCode(input: String): MemorySegment = WGPUShaderModuleWGSLDescriptor.allocate(this) .also{ output ->
    WGPUShaderModuleWGSLDescriptor.code(output, allocateFrom(input))
    WGPUShaderModuleWGSLDescriptor.chain(output).let { chain ->
        chain.sType = WGPUSType_ShaderModuleWGSLDescriptor
    }
}