package io.ygdrasil.webgpu

class WGPUContext(
    val adapter: Adapter,
    val device: Device,
    val renderingContext: RenderingContext,
    val surface: Surface
) : AutoCloseable {


    override fun close() {
        renderingContext.close()
        surface.close()
        adapter.close()
        device.close()
    }

}