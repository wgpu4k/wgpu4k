package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class BindGroupLayout : GPUBindGroupLayout {

    override var label: String

    override fun close()
}