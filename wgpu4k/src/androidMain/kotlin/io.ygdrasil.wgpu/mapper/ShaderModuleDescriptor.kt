package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUChainedStruct
import io.ygdrasil.wgpu.internal.jna.WGPUShaderModuleCompilationHint
import io.ygdrasil.wgpu.internal.jna.WGPUShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUShaderModuleWGSLDescriptor
import io.ygdrasil.wgpu.internal.jna.wgpu_h.WGPUSType_ShaderModuleWGSLDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: ShaderModuleDescriptor): MemorySegment = WGPUShaderModuleDescriptor.allocate(this).also { output ->
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

private fun SegmentAllocator.map(input: ShaderModuleDescriptor.CompilationHint, output: MemorySegment) {
    WGPUShaderModuleCompilationHint.entryPoint(output, allocateFrom(input.entryPoint))
    // TODO find how to map layout
}

private fun SegmentAllocator.mapCode(input: String): MemorySegment = WGPUShaderModuleWGSLDescriptor.allocate(this) .also{ output ->
    WGPUShaderModuleWGSLDescriptor.code(output, allocateFrom(input))
    WGPUShaderModuleWGSLDescriptor.chain(output).let { chain ->
        WGPUChainedStruct.sType(chain, WGPUSType_ShaderModuleWGSLDescriptor())
    }
}