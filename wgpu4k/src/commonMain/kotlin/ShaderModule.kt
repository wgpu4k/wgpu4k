package io.ygdrasil.webgpu

expect class ShaderModule : GPUShaderModule {
    override var label: String
    override suspend fun getCompilationInfo(): GPUCompilationInfo
    override fun close()
}
