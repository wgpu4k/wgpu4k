package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.*

suspend fun createApplication(wgpuContext: WGPUContext): Application {
    wgpuContext.configureRenderingContext()
    val availableScenes = loadScenes(wgpuContext)
    val scene = availableScenes.first()
    scene.initialize()

    return Application(wgpuContext, scene, availableScenes)
}

class Application internal constructor(
    private val wgpuContext: WGPUContext,
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

    fun configureRenderingContext() {
        wgpuContext.configureRenderingContext()
    }

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
    surface.configure(
        CanvasConfiguration(
            device = device,
            usage = setOf(TextureUsage.renderattachment, TextureUsage.copysrc)
        )
    )
}