package io.ygdrasil.webgpu

@WGPULowLevel
expect class BindGroupLayout : GPUBindGroupLayout {

    override var label: String

    override fun close()
}