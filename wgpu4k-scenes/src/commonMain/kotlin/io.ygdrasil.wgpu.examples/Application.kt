package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.CanvasConfiguration
import io.ygdrasil.wgpu.CompositeAlphaMode
import io.ygdrasil.wgpu.Device
import io.ygdrasil.wgpu.Surface
import io.ygdrasil.wgpu.SurfaceRenderingContext
import io.ygdrasil.wgpu.TextureUsage
import io.ygdrasil.wgpu.WGPUContext
import io.ygdrasil.wgpu.autoClosableContext

suspend fun createApplication(wgpuContext: WGPUContext, resourceBasePath: String = ""): Application {
    wgpuContext.configureRenderingContext()
    val availableScenes = loadScenes(wgpuContext, resourceBasePath)
    val scene = availableScenes.first()
    scene.initialize()

    return Application(wgpuContext, scene, availableScenes)
}

class Application internal constructor(
    val wgpuContext: WGPUContext,
    currentScene: Scene,
    val availableScenes: List<Scene>,
) {

    var currentScene: Scene = currentScene
        private set

    internal val surface: Surface
        get() = wgpuContext.surface
    internal val device: Device
        get() = wgpuContext.device

    private var onError = false

    var frame = 0
        private set

    suspend fun changeScene(nextScene: Scene) {
        println("switch to scene ${nextScene::class.simpleName}")
        with(nextScene) {
            try {
                initialize()
            } catch (e: Throwable) {
                e.printStackTrace()
                onError = true
                throw e
            }
        }
        // TODO make it stable

        //if (this::currentScene.isInitialized) currentScene.autoClosableContext.close()
        currentScene = nextScene
    }

    suspend fun renderFrame() = autoClosableContext {
        if (onError) return@autoClosableContext
        frame += 1
        currentScene.frame = frame
        with(currentScene) {
            try {
                render()
            } catch (e: Throwable) {
                e.printStackTrace()
                onError = true
                throw e
            }
        }
        if (wgpuContext.renderingContext is SurfaceRenderingContext) {
            surface.present()
        }
    }

}

private fun WGPUContext.configureRenderingContext() {
    val format = renderingContext.textureFormat
    val alphaMode = CompositeAlphaMode.inherit?.takeIf { surface.supportedAlphaMode.contains(it) }
        ?: CompositeAlphaMode.opaque

    println("Using format $format and alpha mode $alphaMode")
    println("Supported formats: ${surface.supportedFormats}")
    surface.configure(
        CanvasConfiguration(
            device = device,
            format = format,
            usage = setOf(TextureUsage.renderattachment, TextureUsage.copysrc),
            alphaMode = alphaMode
        )
    )
}