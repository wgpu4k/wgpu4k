@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUShaderModuleCompilationHint
import io.ygdrasil.webgpu.GPUShaderModuleDescriptor
import io.ygdrasil.webgpu.WGPUShaderModuleCompilationHint
import io.ygdrasil.webgpu.WGPUShaderModuleDescriptor
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray
import kotlin.js.ExperimentalWasmJsInterop

fun map(input: GPUShaderModuleDescriptor): WGPUShaderModuleDescriptor = createJsObject<WGPUShaderModuleDescriptor>().apply {
    code = input.code
    compilationHints = input.compilationHints.mapJsArray { map(it) }
    label = input.label
}

private fun map(input: GPUShaderModuleCompilationHint) =
    createJsObject<WGPUShaderModuleCompilationHint>().apply {
        entryPoint = input.entryPoint
        layout = TODO("no yet implemented")//input.layout ?: undefined
    }
