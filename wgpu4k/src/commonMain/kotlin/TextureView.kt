package io.ygdrasil.webgpu

@WGPULowLevel
expect class TextureView : GPUTextureView {

    override var label: String

    override fun close()
}

