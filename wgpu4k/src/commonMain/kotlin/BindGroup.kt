package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class BindGroup : GPUBindGroup {

    override var label: String

    override fun close()
}

