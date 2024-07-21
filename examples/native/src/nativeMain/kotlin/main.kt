@file:OptIn(ExperimentalForeignApi::class)

import glfw.glfwPollEvents
import glfw.glfwShowWindow
import glfw.glfwWindowShouldClose
import io.ygdrasil.wgpu.glfwContextRenderer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val glfwContext = glfwContextRenderer(width = 640, height = 480, title = "GLFW+WebGPU")

    glfwShowWindow(glfwContext.windowHandler)

    while (glfwWindowShouldClose(glfwContext.windowHandler) == 0) {
        glfwPollEvents()

    }

    glfwContext.close()
}