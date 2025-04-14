package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class ShaderModule : GPUShaderModule {
    override var label: String
    override suspend fun getCompilationInfo(): Result<GPUCompilationInfo>
    override fun close()
}
