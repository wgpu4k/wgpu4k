package io.ygdrasil.webgpu

expect class TextureView : GPUTextureView {

    override var label: String

    override fun close()
}

