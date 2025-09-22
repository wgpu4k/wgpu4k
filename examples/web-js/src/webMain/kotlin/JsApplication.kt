package io.ygdrasil.webgpu.examples

import io.ygdrasil.webgpu.canvasContextRenderer
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import web.events.EventHandler
import web.html.HTMLCanvasElement
import web.keyboard.KeyCode
import web.keyboard.KeyboardEvent
import web.keyboard.PageDown
import web.keyboard.PageUp
import web.window.window

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
    window.onkeydown = EventHandler { event: KeyboardEvent ->
        if (event.code == KeyCode.PageUp || event.code == KeyCode.PageDown) {
            val currentIndex = application.availableScenes.indexOf(application.currentScene)
            val index = if (event.code == KeyCode.PageUp) {
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