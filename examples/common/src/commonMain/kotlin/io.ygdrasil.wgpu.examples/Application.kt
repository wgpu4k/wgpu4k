

package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.scenes.basic.*

suspend fun createApplication(wgpuContext: WGPUContext): Application {
	val assetManager = genericAssetManager()
	wgpuContext.configureRenderingContext()

	val availableScenes = listOf(
		HelloTriangleScene(wgpuContext, assetManager),
		HelloTriangleMSAAScene(wgpuContext, assetManager),
		HelloTriangleRotatingScene(wgpuContext, assetManager),
		RotatingCubeScene(wgpuContext, assetManager),
		TwoCubesScene(wgpuContext, assetManager),
		CubemapScene(wgpuContext, assetManager),
		FractalCubeScene(wgpuContext, assetManager),
		InstancedCubeScene(wgpuContext, assetManager),
		TexturedCubeScene(wgpuContext, assetManager),
		// TODO: Not working test on wgpu new releases ParticlesScene(),
		// TODO: fix it SkinnedMeshScene(),
	)

	val scene = availableScenes.first()


	return Application(wgpuContext, scene, availableScenes)
}

class Application internal constructor(
	private val wgpuContext: WGPUContext,
	currentScene: Scene,
	val availableScenes: List<Scene>
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

	suspend fun load() {
		changeScene(availableScenes.first())
	}

	fun configureRenderingContext() {
		wgpuContext.configureRenderingContext()
	}

	abstract class Scene(
		private val context: WGPUContext,
		assetManager: AssetManager
	) : AutoCloseable, AssetManager by assetManager {

		internal var frame = 0

		val dummyTexture by lazy {
			device.createTexture(
				TextureDescriptor(
					size = Size3D(1, 1),
					format = TextureFormat.depth24plus,
					usage = setOf(TextureUsage.renderattachment),
				)
			).also { with(autoClosableContext) { it.bind() } }
		}

		internal val device: Device
			get() = context.device

		internal val renderingContext: RenderingContext
			get() = context.renderingContext

		protected val autoClosableContext = AutoClosableContext()

		abstract suspend fun initialize()

		abstract fun render()

		override fun close() {
			autoClosableContext.close()
		}
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

	fun renderFrame() {
		if (onError) return
		frame += 1
		with(currentScene) {
			try {
				render()
			} catch (e: Throwable) {
				e.printStackTrace()
				onError = true
				throw e
			}
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