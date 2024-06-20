import io.ygdrasil.wgpu.WGPUContext
import io.ygdrasil.wgpu.canvasContextRenderer

actual suspend fun getHeadlessContext(): WGPUContext {
    return canvasContextRenderer(deferredRendering = true).wgpuContext
}