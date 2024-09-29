@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ShaderModuleDescriptor
import kotlinx.cinterop.*
import webgpu.native.WGPUSType_ShaderModuleWGSLDescriptor
import webgpu.native.WGPUShaderModuleCompilationHint
import webgpu.native.WGPUShaderModuleDescriptor
import webgpu.native.WGPUShaderModuleWGSLDescriptor

internal fun ArenaBase.map(input: ShaderModuleDescriptor) =
    alloc<WGPUShaderModuleDescriptor>().also { output ->
        if (input.label != null) output.label = input.label.cstr.getPointer(this)
        output.nextInChain = mapCode(input.code).ptr.reinterpret()
        if (input.compilationHints.isNotEmpty()) {
            output.hintCount = input.compilationHints.size.toULong()
            val hints = allocArray<WGPUShaderModuleCompilationHint>(input.compilationHints.size.toLong())
            input.compilationHints.forEachIndexed { index, hint ->
                map(hint, hints[index])
            }
            output.hints = hints
        }
    }

private fun ArenaBase.map(input: ShaderModuleDescriptor.CompilationHint, output: WGPUShaderModuleCompilationHint) {
    output.entryPoint = input.entryPoint.cstr.getPointer(this)
    // TODO find how to map layout
}

private fun ArenaBase.mapCode(input: String) = alloc<WGPUShaderModuleWGSLDescriptor>().also { output ->
    output.code = input.cstr.getPointer(this)
    output.chain.sType = WGPUSType_ShaderModuleWGSLDescriptor
}