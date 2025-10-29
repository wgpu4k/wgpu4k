package io.ygdrasil.webgpu

class WGPUContext(
    val surface: Surface,
    val adapter: GPUAdapter,
    val device: GPUDevice,
    val renderingContext: RenderingContext,
) : AutoCloseable {


    override fun close() {
        // Texture rendering context crash here, TODO: find why
        // renderingContext.close()
        surface.close()
        adapter.close()
        device.close()
    }

}