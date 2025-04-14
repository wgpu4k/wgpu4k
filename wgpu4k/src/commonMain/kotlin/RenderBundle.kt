package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class RenderBundle : GPURenderBundle {
    override var label: String
}