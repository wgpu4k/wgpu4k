@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.scenes.basic.*
import kotlin.js.JsExport

@JsExport
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
				size = GPUExtent3DDictStrict(1, 1),
				format = TextureFormat.depth24plus,
				usage = TextureUsage.renderattachment.value,
			)
		)
	}


	var frame = 0
		private set

	init {
		changeScene(availableScenes.first())
	}

	abstract class Scene {

		val autoClosableContext = AutoClosableContext()

		abstract fun Application.initialiaze()

		abstract fun Application.render()

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

	fun configureRenderingContext() {
		renderingContext.configure(
			CanvasConfiguration(
				device = device
			)
		)
	}
}

val availableScenes = listOf(
	CubemapScene(),

	HelloTriangleScene(),
	HelloTriangleMSAAScene(),
	HelloTriangleRotatingScene(),
	RotatingCubeScene(),
	TwoCubesScene(),
	CubemapScene(),
	FractalCubeScene(),
	InstancedCubeScene(),
	TexturedCubeScene(),
)