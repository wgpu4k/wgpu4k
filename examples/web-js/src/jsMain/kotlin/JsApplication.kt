

package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.getRenderingContext
import io.ygdrasil.wgpu.requestAdapter
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise
import org.w3c.dom.HTMLCanvasElement
import kotlin.js.Promise

external fun setInterval(render: () -> Unit, updateInterval: Int)

// ~60 Frame per second
val UPDATE_INTERVAL = (1000.0 / 60.0).toInt()

fun jsApplication(canvas: HTMLCanvasElement): Promise<Application> {

	return MainScope().promise {
		val assetManager = genericAssetManager()

		val devicePixelRatio = window.devicePixelRatio
		canvas.width = (canvas.clientWidth * devicePixelRatio).toInt()
		canvas.height = (canvas.clientHeight * devicePixelRatio).toInt()
		val adapter = requestAdapter() ?: error("No appropriate Adapter found.")
		val device = adapter.requestDevice() ?: error("No appropriate Device found.")
		val renderingContext = canvas.getRenderingContext() ?: error("fail to get context")

		object : Application(
			renderingContext,
			device,
			adapter,
			assetManager
		) {
			override fun run() {
				// Schedule main loop to run repeatedly
				setInterval({
					renderFrame()
				}, UPDATE_INTERVAL);
			}
		}.also { application ->
			window.onkeydown = { event ->
				if (event.keyCode == 33 || event.keyCode == 34) {
					val currentIndex = availableScenes.indexOf(application.currentScene)
					val index = if (event.keyCode == 33) {
						currentIndex - 1
					} else {
						currentIndex + 1
					}.let {
						when (it) {
							availableScenes.size -> 0
							-1 -> availableScenes.size - 1
							else -> it
						}
					}


					application.changeScene(availableScenes[index])
				}


			}
		}


	}

}