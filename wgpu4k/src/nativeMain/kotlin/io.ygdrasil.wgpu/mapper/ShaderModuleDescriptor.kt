@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ShaderModuleDescriptor
import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: ShaderModuleDescriptor) =
    alloc<WGPUShaderModuleDescriptor>().also { output ->
        if (input.label != null) output.label = input.label.cstr.getPointer(this)
        output.nextInChain = mapCode(input.code)
        if (input.compilationHints != null && input.compilationHints.isNotEmpty()) {
            WGPUShaderModuleDescriptor.hintCount(output, input.compilationHints.size.toLong())
            val hints = WGPUShaderModuleCompilationHint.allocateArray(input.compilationHints.size.toLong(), this)
            input.compilationHints.forEachIndexed { index, hint ->
                map(hint, WGPUShaderModuleDescriptor.asSlice(hints, index.toLong()))
            }
            WGPUShaderModuleDescriptor.hints(output, hints)
        }

    }

private fun Arena.map(input: ShaderModuleDescriptor.CompilationHint, output: WGPUShaderModuleCompilationHint) {
    output.entryPoint = input.entryPoint.cstr.getPointer(this)
    // TODO find how to map layout
}

private fun Arena.mapCode(input: String) = alloc<WGPUShaderModuleWGSLDescriptor>().also { output ->
    output.code = input.cstr.getPointer(this)
    output.chain.sType = WGPUSType_ShaderModuleWGSLDescriptor
}