package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class TextureView : GPUTextureView {

    override var label: String

    override fun close()
}

