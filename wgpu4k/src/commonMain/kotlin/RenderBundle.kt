package io.ygdrasil.webgpu

@WGPULowLevel
expect class RenderBundle : GPURenderBundle {
    override var label: String
}