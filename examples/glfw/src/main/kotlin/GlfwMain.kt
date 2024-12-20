package io.ygdrasil.wgpu.examples

import ffi.LibraryLoader
import ffi.NativeAddress
import ffi.globalMemory
import io.ygdrasil.wgpu.GLFWContext
import io.ygdrasil.wgpu.glfwContextRenderer
import kotlinx.coroutines.runBlocking
import org.lwjgl.glfw.GLFW.*
import webgpu.WGPULogCallback
import webgpu.WGPULogLevel
import webgpu.WGPUStringView
import webgpu.wgpuSetLogCallback
import webgpu.wgpuSetLogLevel

val callback = WGPULogCallback.allocate(globalMemory, object : WGPULogCallback {
    override fun invoke(level: WGPULogLevel, message: WGPUStringView?, userdata: NativeAddress?) {
        println("LOG {$level} ${message?.data?.toKString(message.length)}")
    }
})

fun main() = runBlocking {
    LibraryLoader.load()
    wgpuSetLogLevel(1u)
    wgpuSetLogCallback(callback, globalMemory.bufferOfAddress(callback.handler).handler)

    val glfwContext = glfwContextRenderer(width = 640, height = 480, title = "GLFW+WebGPU")

    println("Devices features : ${glfwContext.wgpuContext.device.features}")
    println("Devices limits : ${glfwContext.wgpuContext.device.limits}")

    println("Adapter features : ${glfwContext.wgpuContext.adapter.features}")
    println("Adapter limits : ${glfwContext.wgpuContext.adapter.limits}")

    val application = createApplication(
        glfwContext.wgpuContext
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
