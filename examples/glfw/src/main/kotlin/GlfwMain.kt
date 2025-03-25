package io.ygdrasil.webgpu.examples

import ffi.LibraryLoader
import ffi.globalMemory
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.GLFWContext
import io.ygdrasil.webgpu.glfwContextRenderer
import io.ygdrasil.wgpu.WGPULogCallback
import io.ygdrasil.wgpu.WGPULogLevel_Debug
import io.ygdrasil.wgpu.WGPULogLevel_Error
import io.ygdrasil.wgpu.WGPULogLevel_Info
import io.ygdrasil.wgpu.WGPULogLevel_Trace
import io.ygdrasil.wgpu.WGPULogLevel_Warn
import io.ygdrasil.wgpu.wgpuSetLogCallback
import io.ygdrasil.wgpu.wgpuSetLogLevel
import kotlinx.coroutines.runBlocking
import org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN
import org.lwjgl.glfw.GLFW.GLFW_KEY_PAGE_DOWN
import org.lwjgl.glfw.GLFW.GLFW_KEY_PAGE_UP
import org.lwjgl.glfw.GLFW.GLFW_KEY_UP
import org.lwjgl.glfw.GLFW.GLFW_PRESS
import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwSetKeyCallback
import org.lwjgl.glfw.GLFW.glfwShowWindow
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose

private val logger = KotlinLogging.logger {}

val callback = WGPULogCallback.allocate(globalMemory) { level, cMessage, userdata ->
    val message = cMessage?.data?.toKString(cMessage.length) ?: "empty message"
    when (level) {
        WGPULogLevel_Error -> logger.error { message }
        WGPULogLevel_Warn -> logger.warn { message }
        WGPULogLevel_Info -> logger.info { message }
        WGPULogLevel_Debug -> logger.debug { message }
        WGPULogLevel_Trace -> logger.trace { message }
        else -> logger.warn { "Unknown log level $level with message $message" }
    }
}

fun main() = runBlocking {
    LibraryLoader.load()
    wgpuSetLogLevel(WGPULogLevel_Trace)
    wgpuSetLogCallback(callback, globalMemory.bufferOfAddress(callback.handler).handler)

    val glfwContext = glfwContextRenderer(width = 640, height = 480, title = "GLFW+WebGPU")

    logger.info { "Devices features : ${glfwContext.wgpuContext.device.features}" }
    logger.info{ "Devices limits : ${glfwContext.wgpuContext.device.limits}" }

    logger.info { "Adapter features : ${glfwContext.wgpuContext.adapter.features}" }
    logger.info { "Adapter limits : ${glfwContext.wgpuContext.adapter.limits}" }

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
