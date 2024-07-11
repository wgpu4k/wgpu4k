package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.js.GPUShaderModuleCompilationHint
import io.ygdrasil.wgpu.internal.js.GPUShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject
import io.ygdrasil.wgpu.internal.js.mapJsArray

fun map(input: ShaderModuleDescriptor): GPUShaderModuleDescriptor = createJsObject<GPUShaderModuleDescriptor>().apply {
    code = input.code.toJsString()
    // TODO map this
    // sourceMap = input.sourceMap
    if (input.compilationHints != null) compilationHints = input.compilationHints.mapJsArray { map(it) }
    label = input.label?.toJsString()
}

private fun map(input: ShaderModuleDescriptor.CompilationHint) =
    createJsObject<GPUShaderModuleCompilationHint>().apply {
        entryPoint = input.entryPoint
        layout = TODO("no yet implemented")//input.layout ?: undefined
    }
