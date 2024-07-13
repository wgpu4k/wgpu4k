package io.ygdrasil.wgpu

expect class ComputePipeline : AutoCloseable {
    fun getBindGroupLayout(index: Int): BindGroupLayout

    override fun close()
}

data class ComputePipelineDescriptor(
    val compute: ProgrammableStage,
    val layout: PipelineLayout? = null,
    val label: String? = null,
) {
    data class ProgrammableStage(
        val module: ShaderModule,
        val entryPoint: String? = null,
        val constants: Map<String, GPUPipelineConstantValue> = mapOf(),
    )
}