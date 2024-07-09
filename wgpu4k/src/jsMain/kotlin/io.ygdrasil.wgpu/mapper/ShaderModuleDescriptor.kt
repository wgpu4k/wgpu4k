package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.js.GPUShaderModuleCompilationHint
import io.ygdrasil.wgpu.internal.js.GPUShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject

fun map(input: ShaderModuleDescriptor): GPUShaderModuleDescriptor = createJsObject<GPUShaderModuleDescriptor>().apply {
    code = input.code
    sourceMap = input.sourceMap ?: undefined
    compilationHints = input
        .compilationHints
        ?.map { map(it) }
        ?.toTypedArray()
        ?: undefined
    label = input.label ?: undefined
}

private fun map(input: ShaderModuleDescriptor.CompilationHint) =
    createJsObject<GPUShaderModuleCompilationHint>().apply {
        entryPoint = input.entryPoint
        layout = TODO("no yet implemented")//input.layout ?: undefined
    }
