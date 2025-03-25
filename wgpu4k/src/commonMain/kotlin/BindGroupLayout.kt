package io.ygdrasil.webgpu

expect class BindGroupLayout : GPUBindGroupLayout {

    override var label: String

    override fun close()
}