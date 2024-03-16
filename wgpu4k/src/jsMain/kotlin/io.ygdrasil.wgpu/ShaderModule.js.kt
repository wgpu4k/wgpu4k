@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUShaderModule
import io.ygdrasil.wgpu.internal.js.GPUShaderModuleCompilationHint
import io.ygdrasil.wgpu.internal.js.GPUShaderModuleDescriptor

@JsExport
actual class ShaderModule(internal val handler: GPUShaderModule) : AutoCloseable {
	override fun close() {
		// Nothing to do on JS
	}
}

fun ShaderModuleDescriptor.convert(): GPUShaderModuleDescriptor = object : GPUShaderModuleDescriptor {
	override var code: String = this@convert.code
	override var sourceMap: Any? = this@convert.sourceMap ?: undefined
	override var compilationHints: Array<GPUShaderModuleCompilationHint>? = this@convert
		.compilationHints
		?.map { it.convert() }
		?.toTypedArray()
		?: undefined
	override var label: String? = this@convert.label ?: undefined

}

private fun ShaderModuleDescriptor.CompilationHint.convert() = object : GPUShaderModuleCompilationHint {
	override var entryPoint: String = this@convert.entryPoint
	override var layout: dynamic = TODO("no yet implemented")//this@convert.layout ?: undefined
}
