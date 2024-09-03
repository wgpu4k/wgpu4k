import io.ygdrasil.wgpu.CanvasConfiguration
import io.ygdrasil.wgpu.TextureFormat
import io.ygdrasil.wgpu.WGPUContext
import io.ygdrasil.wgpu.canvasContextRenderer

actual suspend fun getHeadlessContext(): WGPUContext {
    val canvas = canvasContextRenderer(deferredRendering = false, width = 256, height = 256)
    canvas.wgpuContext.surface.configure(
        CanvasConfiguration(
            canvas.wgpuContext.device,
            canvas.wgpuContext.surface.preferredCanvasFormat ?: TextureFormat.rgba8unorm,
        )
    )
    return canvas.wgpuContext
}