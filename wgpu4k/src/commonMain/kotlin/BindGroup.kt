package io.ygdrasil.webgpu

@WGPULowLevel
expect class BindGroup : GPUBindGroup {

    override var label: String

    override fun close()
}

