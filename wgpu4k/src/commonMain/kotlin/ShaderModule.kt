package io.ygdrasil.wgpu

expect class ShaderModule : AutoCloseable {

    override fun close()
}

data class ShaderModuleDescriptor(
	val code: String,
	val label: String? = null,
	val sourceMap: Any? = null,
	val compilationHints: List<CompilationHint> =  listOf(),
) {
    data class CompilationHint(
        val entryPoint: String,
        // TODO
        //val layout: dynamic /* GPUPipelineLayout? | "auto" */
    )
}