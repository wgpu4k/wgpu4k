

package io.ygdrasil.wgpu

expect class ShaderModule : AutoCloseable {

	override fun close()
}

data class ShaderModuleDescriptor(
	var code: String,
	var label: String? = null,
	var sourceMap: Any? = null,
	var compilationHints: Array<CompilationHint>? = null
) {
	data class CompilationHint(
		var entryPoint: String,
		// TODO
		//var layout: dynamic /* GPUPipelineLayout? | "auto" */
	)
}