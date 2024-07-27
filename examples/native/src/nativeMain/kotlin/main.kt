@file:OptIn(ExperimentalForeignApi::class)

import cnames.structs.GLFWwindow
import glfw.*
import io.ygdrasil.wgpu.GLFWContext
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.createApplication
import io.ygdrasil.wgpu.glfwContextRenderer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.runBlocking


lateinit var application: Application

fun main(args: Array<String>) = runBlocking {

    val resourceBasePath = "${args[0]}/"
    val glfwContext = glfwContextRenderer(width = 640, height = 480, title = "GLFW+WebGPU")

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