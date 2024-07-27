package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.GLFWContext
import io.ygdrasil.wgpu.WGPU.Companion.loadLibrary
import io.ygdrasil.wgpu.glfwContextRenderer
import io.ygdrasil.wgpu.internal.jvm.panama.WGPULogCallback
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import kotlinx.coroutines.runBlocking
import org.lwjgl.glfw.GLFW.*
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

val callback = WGPULogCallback.allocate({ level, message, data ->
    println("LOG {$level} ${message.getString(0)}")
}, Arena.global())

fun main() = runBlocking {
    loadLibrary()
    wgpu_h.wgpuSetLogLevel(1)
    wgpu_h.wgpuSetLogCallback(callback, MemorySegment.NULL)

    val glfwContext = glfwContextRenderer(width = 640, height = 480, title = "GLFW+WebGPU")

    val application = createApplication(
        glfwContext.wgpuContext,
        resourceBasePath
    )

    addKeyBinding(glfwContext, application)

    glfwShowWindow(glfwContext.windowHandler)

    while (!glfwWindowShouldClose(glfwContext.windowHandler)) {
        glfwPollEvents()
        application.renderFrame()
    }

    glfwContext.close()
}

fun addKeyBinding(glfwContext: GLFWContext, application: Application) {
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

            runBlocking {
                application.changeScene(application.availableScenes[index])
            }
        }
    }
}
