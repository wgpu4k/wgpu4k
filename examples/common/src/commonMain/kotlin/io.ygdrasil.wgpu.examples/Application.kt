

package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.scenes.basic.*
import io.ygdrasil.wgpu.examples.scenes.graphics.techniques.ParticlesScene
import io.ygdrasil.wgpu.examples.scenes.graphics.techniques.RotatingMeshCubeScene
import io.ygdrasil.wgpu.examples.scenes.graphics.techniques.WhaleScene

abstract class Application(
	val renderingContext: RenderingContext,
	val device: Device,
	val adapter: Adapter,
	assetManager: AssetManager
) : AutoCloseable, AssetManager by assetManager {

	lateinit var currentScene: Scene
		private set
	private var onError = false

	val dummyTexture by lazy {
		device.createTexture(
			TextureDescriptor(
				size = Size3D(1, 1),
				format = TextureFormat.depth24plus,
				usage = setOf(TextureUsage.renderattachment),
			)
		)
	}

	var frame = 0
		private set

	init {
		changeScene(availableScenes.last())
	}

	abstract class Scene {

		val autoClosableContext = AutoClosableContext()

		abstract fun Application.initialiaze()

		abstract fun Application.render()

		open fun Application.configureRenderingContext() {
			renderingContext.configure(
				CanvasConfiguration(
					device = device
				)
			)
		}

	}

	fun changeScene(nextScene: Scene) {
		println("switch to scene ${nextScene::class.simpleName}")
		with(nextScene) {
			try {
				configureRenderingContext()
				initialiaze()
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

	override fun close() {
		renderingContext.close()
		device.close()
		adapter.close()
	}

	abstract fun run()

}

val availableScenes = listOf(
	HelloTriangleScene(),
	HelloTriangleMSAAScene(),
	HelloTriangleRotatingScene(),
	RotatingCubeScene(),
	TwoCubesScene(),
	CubemapScene(),
	FractalCubeScene(),
	InstancedCubeScene(),
	TexturedCubeScene(),
	// Not working
	ParticlesScene(),
	WhaleScene(),
	RotatingMeshCubeScene()
)