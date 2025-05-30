package io.ygdrasil.webgpu.examples

import io.ygdrasil.webgpu.HTMLCanvasElement
import io.ygdrasil.webgpu.canvasContextRenderer
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

external fun setInterval(render: () -> Unit, updateInterval: Int)

// ~60 Frame per second
val UPDATE_INTERVAL = (1000.0 / 60.0).toInt()

fun jsApplication(canvas: HTMLCanvasElement) {
    MainScope().launch {

        val canvasContext = canvasContextRenderer(canvas)

        val application = createApplication(
            canvasContext.wgpuContext
        )

        registerKeyToChangeScene(application)
        // Schedule main loop to run repeatedly
        setInterval({
            MainScope().launch {
                application.renderFrame()
            }
        }, UPDATE_INTERVAL)

    }

}

private fun registerKeyToChangeScene(application: Application) {
    window.onkeydown = { event ->
        if (event.keyCode == 33 || event.keyCode == 34) {
            val currentIndex = application.availableScenes.indexOf(application.currentScene)
            val index = if (event.keyCode == 33) {
                currentIndex - 1
            } else {
                currentIndex + 1
            }.let {
                when (it) {
                    application.availableScenes.size -> 0
                    -1 -> application.availableScenes.size - 1
                    else -> it
                }
            }

            MainScope().launch {
                application.changeScene(application.availableScenes[index])
            }
        }
    }

}