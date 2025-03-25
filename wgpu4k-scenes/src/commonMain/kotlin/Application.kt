package io.ygdrasil.webgpu.examples

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.CompositeAlphaMode
import io.ygdrasil.webgpu.GPUDevice
import io.ygdrasil.webgpu.GPUTextureUsage
import io.ygdrasil.webgpu.Surface
import io.ygdrasil.webgpu.SurfaceConfiguration
import io.ygdrasil.webgpu.SurfaceRenderingContext
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.autoClosableContext

private val logger = KotlinLogging.logger {}

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
    internal val device: GPUDevice
        get() = wgpuContext.device

    private var onError = false

    var frame = 0
        private set

    suspend fun changeScene(nextScene: Scene) {
        logger.info { "switch to scene ${nextScene::class.simpleName}" }
        with(nextScene) {
            try {
                initialize()
            } catch (e: Throwable) {
                logger.error(e) { "failed to initialize scene ${nextScene::class.simpleName}" }
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
                logger.error(e) { "failed to render frame $frame" }
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
    val alphaMode = CompositeAlphaMode.Inherit?.takeIf { surface.supportedAlphaMode.contains(it) }
        ?: CompositeAlphaMode.Opaque

    logger.info { "Using format $format and alpha mode $alphaMode" }
    logger.info { "Supported formats: ${surface.supportedFormats}" }
    surface.configure(
        SurfaceConfiguration(
            device = device,
            format = format,
            usage = setOf(GPUTextureUsage.RenderAttachment, GPUTextureUsage.CopySrc),
            alphaMode = alphaMode
        )
    )
}