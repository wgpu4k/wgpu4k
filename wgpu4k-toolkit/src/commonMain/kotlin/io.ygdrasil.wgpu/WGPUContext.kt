package io.ygdrasil.wgpu

class WGPUContext(
    val surface: Surface,
    val adapter: Adapter,
    val device: Device,
    val renderingContext: RenderingContext,
) : AutoCloseable {


    override fun close() {
        renderingContext.close()
        surface.close()
        device.close()
        adapter.close()
    }

}