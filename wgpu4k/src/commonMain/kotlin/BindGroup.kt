package io.ygdrasil.webgpu

expect class BindGroup : GPUBindGroup {

    override var label: String

    override fun close()
}

