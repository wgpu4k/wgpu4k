@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.internal.callListMethodFrom
import io.ygdrasil.wgpu.internal.callStringMethodFrom
import io.ygdrasil.wgpu.internal.toCString
import kotlinx.cinterop.*
import platform.sles.jobject
import webgpu.WGPUSType_ShaderModuleWGSLDescriptor
import webgpu.WGPUShaderModuleDescriptor
import webgpu.WGPUShaderModuleWGSLDescriptor
import webgpu.WGPUShaderModuleCompilationHint

internal fun ArenaBase.mapShaderModuleDescriptor(input: jobject, env: JNIEnvPointer): WGPUShaderModuleDescriptor =
    alloc<WGPUShaderModuleDescriptor>().also { output ->
        println("mapShaderModuleDescriptor")
        output.label = env.callStringMethodFrom(input, "getLabel")?.toCString(env, this)
        output.nextInChain = mapCode(input, env).ptr.reinterpret()

         env.callListMethodFrom(input, "getCompilationHints")?.also { compilationHints ->
             compilationHints.getSize(env)
                 .takeIf { it > 0 }
                 ?.let { size ->
                     output.hintCount = size.toULong()
                     val hints = allocArray<WGPUShaderModuleCompilationHint>(size.toLong())
                     repeat(size) { index ->
                         mapCompilationHints(
                             compilationHints.getObject(index, env)?.reinterpret() ?: error("fail to get object"),
                             hints[index],
                             env
                         )
                     }
                     output.hints = hints
             }
         } ?: error("getCompilationHints should not be null")
    }
private fun ArenaBase.mapCompilationHints(
    input: jobject,
    output: WGPUShaderModuleCompilationHint
    , env: JNIEnvPointer
) {
    output.entryPoint = env.callStringMethodFrom(input, "getEntryPoint")?.toCString(env, this)
    // TODO find how to map layout
}

private fun ArenaBase.mapCode(
    input: jobject,
    env: JNIEnvPointer
) = alloc<WGPUShaderModuleWGSLDescriptor>().also { output ->
    output.code = env.callStringMethodFrom(input, "getCode")?.toCString(env, this)
    output.chain.sType = WGPUSType_ShaderModuleWGSLDescriptor
}

