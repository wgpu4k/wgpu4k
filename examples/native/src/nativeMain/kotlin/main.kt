@file:OptIn(ExperimentalForeignApi::class)

import cnames.structs.GLFWwindow
import glfw.GLFW_KEY_DOWN
import glfw.GLFW_KEY_PAGE_DOWN
import glfw.GLFW_KEY_PAGE_UP
import glfw.GLFW_KEY_UP
import glfw.GLFW_PRESS
import glfw.GLFWkeyfun
import glfw.glfwPollEvents
import glfw.glfwSetKeyCallback
import glfw.glfwShowWindow
import glfw.glfwWindowShouldClose
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.GLFWContext
import io.ygdrasil.webgpu.examples.Application
import io.ygdrasil.webgpu.examples.createApplication
import io.ygdrasil.webgpu.glfwContextRenderer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.runBlocking

private val logger = KotlinLogging.logger {}

lateinit var application: Application

fun main(args: Array<String>) = runBlocking {

    val resourceBasePath = "${args[0]}/"
    logger.info { "resource path that will be used is $resourceBasePath" }
    val glfwContext = glfwContextRenderer(width = 640, height = 480, title = "GLFW+WebGPU")

    logger.info { "Devices features : ${glfwContext.wgpuContext.device.features}" }
    logger.info { "Devices limits : ${glfwContext.wgpuContext.device.limits}" }

    logger.info { "Adapter features : ${glfwContext.wgpuContext.adapter.features}" }
    logger.info { "Adapter limits : ${glfwContext.wgpuContext.adapter.limits}" }

    application = createApplication(
        glfwContext.wgpuContext,
        resourceBasePath
    )

    addKeyBinding(glfwContext)

    glfwShowWindow(glfwContext.windowHandler)

    while (glfwWindowShouldClose(glfwContext.windowHandler) != 1) {
        glfwPollEvents()
        application.renderFrame()
    }

    glfwContext.close()
}


fun addKeyBinding(glfwContext: GLFWContext) {

    val handleRequest: GLFWkeyfun =
        staticCFunction<CPointer<GLFWwindow>?, Int, Int, Int, Int, Unit> { _, key, scancode, action, mods ->

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
        }.reinterpret()

    glfwSetKeyCallback(glfwContext.windowHandler, handleRequest)
}