package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUChainedStruct
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUShaderModuleCompilationHint
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUShaderModuleWGSLDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h.WGPUSType_ShaderModuleWGSLDescriptor
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

internal fun Arena.map(input: ShaderModuleDescriptor): MemorySegment = WGPUShaderModuleDescriptor.allocate(this).also { output ->
    if (input.label != null) WGPUShaderModuleDescriptor.label(output, allocateFrom(input.label))
    WGPUShaderModuleDescriptor.nextInChain(output, mapCode(input.code))
    if (input.compilationHints.isNotEmpty()) {
        WGPUShaderModuleDescriptor.hintCount(output, input.compilationHints.size.toLong())
        val hints = WGPUShaderModuleCompilationHint.allocateArray(input.compilationHints.size.toLong(), this)
        input.compilationHints.forEachIndexed { index, hint ->
            map(hint, WGPUShaderModuleDescriptor.asSlice(hints, index.toLong()))
        }
        WGPUShaderModuleDescriptor.hints(output, hints)
    }

}

private fun Arena.map(input: ShaderModuleDescriptor.CompilationHint, output: MemorySegment) {
    WGPUShaderModuleCompilationHint.entryPoint(output, allocateFrom(input.entryPoint))
    // TODO find how to map layout
}

private fun Arena.mapCode(input: String): MemorySegment = WGPUShaderModuleWGSLDescriptor.allocate(this) .also{ output ->
    WGPUShaderModuleWGSLDescriptor.code(output, allocateFrom(input))
    WGPUShaderModuleWGSLDescriptor.chain(output).let { chain ->
        WGPUChainedStruct.sType(chain, WGPUSType_ShaderModuleWGSLDescriptor())
    }
}