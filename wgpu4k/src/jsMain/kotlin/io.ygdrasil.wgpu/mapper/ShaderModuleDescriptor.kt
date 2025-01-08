package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.internal.js.GPUShaderModuleCompilationHint
import io.ygdrasil.webgpu.internal.js.GPUShaderModuleDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

fun map(input: ShaderModuleDescriptor): GPUShaderModuleDescriptor = createJsObject<GPUShaderModuleDescriptor>().apply {
    code = input.code
    sourceMap = input.sourceMap ?: undefined
    compilationHints = input
        .compilationHints
        .map { map(it) }
        ?.toTypedArray()
        ?: undefined
    label = input.label ?: undefined
}

private fun map(input: ShaderModuleDescriptor.CompilationHint) =
    createJsObject<GPUShaderModuleCompilationHint>().apply {
        entryPoint = input.entryPoint
        layout = TODO("no yet implemented")//input.layout ?: undefined
    }
