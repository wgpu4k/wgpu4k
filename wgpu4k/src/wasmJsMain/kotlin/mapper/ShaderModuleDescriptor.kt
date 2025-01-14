package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.internal.js.GPUShaderModuleCompilationHint
import io.ygdrasil.webgpu.internal.js.GPUShaderModuleDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray

fun map(input: ShaderModuleDescriptor): GPUShaderModuleDescriptor = createJsObject<GPUShaderModuleDescriptor>().apply {
    code = input.code.toJsString()
    // TODO map this
    // sourceMap = input.sourceMap
    compilationHints = input.compilationHints.mapJsArray { map(it) }
    label = input.label?.toJsString()
}

private fun map(input: ShaderModuleDescriptor.CompilationHint) =
    createJsObject<GPUShaderModuleCompilationHint>().apply {
        entryPoint = input.entryPoint
        layout = TODO("no yet implemented")//input.layout ?: undefined
    }
