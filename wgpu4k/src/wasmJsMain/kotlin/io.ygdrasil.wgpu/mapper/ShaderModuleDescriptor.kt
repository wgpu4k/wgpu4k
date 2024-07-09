package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.js.GPUShaderModuleCompilationHint
import io.ygdrasil.wgpu.internal.js.GPUShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject
import io.ygdrasil.wgpu.internal.js.toJsArray

fun map(input: ShaderModuleDescriptor): GPUShaderModuleDescriptor = createJsObject<GPUShaderModuleDescriptor>().apply {
    code = input.code.toJsString()
    // TODO map this
    // sourceMap = input.sourceMap
    compilationHints = input
        .compilationHints
        ?.map { map(it) }
        ?.toJsArray()
    label = input.label?.toJsString()
}

private fun map(input: ShaderModuleDescriptor.CompilationHint) =
    createJsObject<GPUShaderModuleCompilationHint>().apply {
        entryPoint = input.entryPoint
        layout = TODO("no yet implemented")//input.layout ?: undefined
    }
