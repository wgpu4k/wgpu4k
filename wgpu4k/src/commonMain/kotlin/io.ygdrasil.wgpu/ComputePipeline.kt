

package io.ygdrasil.wgpu

expect class ComputePipeline : AutoCloseable {
    fun getBindGroupLayout(index: Int): BindGroupLayout

    override fun close()
}

data class ComputePipelineDescriptor(
    var compute: ProgrammableStage,
    var layout: PipelineLayout? = null
) {
    data class ProgrammableStage(
        var module: ShaderModule,
        var entryPoint: String? = null,
        var constants: Map<String, GPUPipelineConstantValue> = mapOf(),
    )
}