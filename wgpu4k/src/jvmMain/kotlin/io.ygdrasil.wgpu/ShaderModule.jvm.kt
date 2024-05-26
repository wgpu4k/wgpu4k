package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import java.lang.foreign.MemorySegment

actual class ShaderModule(internal val handler: MemorySegment) : AutoCloseable {
	actual override fun close() {
		webgpu_h.wgpuShaderModuleRelease(handler)
	}
}

internal fun ShaderModuleDescriptor.convert() = WGPUShaderModuleDescriptor().also {
	it.label = label
	it.nextInChain = WGPUShaderModuleWGSLDescriptor.ByReference().also {
		it.code = code
		it.chain.apply {
			sType = WGPUSType.WGPUSType_ShaderModuleWGSLDescriptor.value
		}
	}
	it.hintCount = compilationHints?.let { NativeLong(it.size.toLong()) } ?: NativeLong(0)
	it.hints =
		compilationHints?.map { it.convert() }?.toTypedArray() ?: arrayOf(WGPUShaderModuleCompilationHint.ByReference())

}.toMemory()

private fun ShaderModuleDescriptor.CompilationHint.convert() = WGPUShaderModuleCompilationHint.ByReference().also {
	TODO("no yet implemented")
}
