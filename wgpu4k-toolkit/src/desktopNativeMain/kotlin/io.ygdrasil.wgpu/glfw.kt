@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import cnames.structs.GLFWwindow
import glfw.GLFW_CLIENT_API
import glfw.GLFW_FALSE
import glfw.GLFW_NO_API
import glfw.GLFW_RESIZABLE
import glfw.GLFW_VISIBLE
import glfw.glfwCreateWindow
import glfw.glfwDestroyWindow
import glfw.glfwInit
import glfw.glfwWindowHint
import io.ygdrasil.webgpu.WGPU.Companion.createInstance
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CValuesRef
import kotlinx.cinterop.ExperimentalForeignApi

suspend fun glfwContextRenderer(
    width: Int = 1,
    height: Int = 1,
    title: String = "",
    deferredRendering: Boolean = false
): GLFWContext {

    glfwInit()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
    // Disable context creation, WGPU will manage that
    glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
    val windowHandler = glfwCreateWindow(width, height, title, null, null)
        ?: error("fail to create windows")

    val wgpu = createInstance() ?: error("fail to wgpu instance")
    val surface = wgpu.getSurface(windowHandler)
    surface._width = width.toUInt()
    surface._height = height.toUInt()

    val adapter = wgpu.requestAdapter(surface)
        ?: error("fail to get adapter")

    val device = adapter.requestDevice()
        ?: error("fail to get device")

    surface.computeSurfaceCapabilities(adapter)

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(256u, 256u, TextureFormat.rgba8unorm, device)
        false -> SurfaceRenderingContext(surface)
    }

    return GLFWContext(
        windowHandler,
        WGPUContext(surface, adapter, device, renderingContext)
    )
}

class GLFWContext(
    val windowHandler: CValuesRef<GLFWwindow>,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
        glfwDestroyWindow(windowHandler)
    }
}


expect fun WGPU.getSurface(window: CPointer<GLFWwindow>): Surface