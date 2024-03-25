

package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder
import io.ygdrasil.wgpu.getRenderingContext
import io.ygdrasil.wgpu.requestAdapter
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.promise
import org.w3c.dom.HTMLCanvasElement
import kotlin.js.Promise

external fun setInterval(render: () -> Unit, updateInterval: Int)

// ~60 Frame per second
val UPDATE_INTERVAL = (1000.0 / 60.0).toInt()

@JsExport
fun jsApplication(canvas: HTMLCanvasElement, baseImagePaths: String = "./assets/img/"): Promise<Application> {
	return MainScope().promise {

		val assetManager = getAssetManager(baseImagePaths)

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

suspend fun getAssetManager(baseImagePaths: String): AssetManager {
	val Di3d: ImageBitmapHolder = getImage("${baseImagePaths}Di-3d.png")
	val cubemapPosx = getImage("${baseImagePaths}cubemap/posx.jpg")
	val cubemapNegx = getImage("${baseImagePaths}cubemap/negx.jpg")
	val cubemapPosy = getImage("${baseImagePaths}cubemap/posy.jpg")
	val cubemapNegy = getImage("${baseImagePaths}cubemap/negy.jpg")
	val cubemapPosz = getImage("${baseImagePaths}cubemap/posz.jpg")
	val cubemapNegz = getImage("${baseImagePaths}cubemap/negz.jpg")
	val webgpu4kotlin = getImage("${baseImagePaths}webgpu4kotlin.png")

	return object : AssetManager {
		override val Di3d: ImageBitmapHolder = Di3d
		override val cubemapPosx: ImageBitmapHolder = cubemapPosx
		override val cubemapNegx: ImageBitmapHolder = cubemapNegx
		override val cubemapPosy: ImageBitmapHolder = cubemapPosy
		override val cubemapNegy: ImageBitmapHolder = cubemapNegy
		override val cubemapPosz: ImageBitmapHolder = cubemapPosz
		override val cubemapNegz: ImageBitmapHolder = cubemapNegz
		override val webgpu4kotlin: ImageBitmapHolder = webgpu4kotlin
	}
}

private suspend fun getImage(url: String): ImageBitmapHolder {
	return window.fetch(url).await()
		.blob().await()
		.let { window.createImageBitmap(it) }.await()
		.let { ImageBitmapHolder(it) }
}
