package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.WGPU.Companion.loadLibrary
import io.ygdrasil.wgpu.glfwContextRenderer
import io.ygdrasil.wgpu.internal.jvm.panama.WGPULogCallback
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import korlibs.io.async.launch
import korlibs.io.async.launchUnscoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.lwjgl.glfw.GLFW.*
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

val callback = WGPULogCallback.allocate( { level, message, data ->
    println("LOG {$level} ${message.getString(0)}")
}, Arena.global())

fun main() = runBlocking {
    loadLibrary()
    wgpu_h.wgpuSetLogLevel(1)
    wgpu_h.wgpuSetLogCallback(callback, MemorySegment.NULL)

    val glfwContext = glfwContextRenderer(width = 640, height = 480, title = "GLFW+WebGPU")

    val glfwDispatcher = GlfwCoroutineDispatcher() // a custom coroutine dispatcher, in which Compose will run

    glfwSetWindowCloseCallback(glfwContext.windowHandler) {
        glfwDispatcher.stop()
    }

    val application = createApplication(
        glfwContext.wgpuContext
    )

    fun run() {
        glfwDispatcher.dispatch(Dispatchers.Default) {
            Dispatchers.Default.launchUnscoped {
                application.renderFrame()
                run()
            }
        }
    }

    glfwSetWindowSizeCallback(glfwContext.windowHandler) { _, windowWidth, windowHeight ->
        application.configureRenderingContext()
        Dispatchers.Default.launchUnscoped {
            application.renderFrame()
        }
    }

    glfwSetKeyCallback(glfwContext.windowHandler) { _, key, scancode, action, mods ->

        if ((key == GLFW_KEY_PAGE_UP || key == GLFW_KEY_PAGE_DOWN || key == GLFW_KEY_UP || key == GLFW_KEY_DOWN) && action == GLFW_PRESS) {
            val currentIndex = application.availableScenes.indexOf(application.currentScene)
            val index = if (key == GLFW_KEY_PAGE_UP || key == GLFW_KEY_UP) {
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


            launch(Dispatchers.Default) {
                application.changeScene(application.availableScenes[index])
            }
        }
    }


    glfwShowWindow(glfwContext.windowHandler)

    run()
    glfwDispatcher.runLoop()

    glfwContext.close()

}
