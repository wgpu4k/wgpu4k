package io.ygdrasil.wgpu

import glfw.*
import io.ygdrasil.wgpu.WGPU.Companion.createInstance
import kotlinx.cinterop.*

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
    val surface = wgpu.getSurface(windowHandler) {
        memScoped {
            val width = alloc<IntVar>()
            val height = alloc<IntVar>()
            glfwGetWindowSize(windowHandler, width.ptr, height.ptr)
            width.value.toInt() to height.value.toInt()
        }
    }


    val adapter = wgpu.requestAdapter(surface)
        ?: error("fail to get adapter")

    val device = adapter.requestDevice()
        ?: error("fail to get device")

    surface.computeSurfaceCapabilities(adapter)

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(256, 256, TextureFormat.rgba8unormsrgb, device)
        false -> SurfaceRenderingContext(surface)
    }

    return GLFWContext(
        windowHandler,
        WGPUContext(surface, adapter, device, renderingContext)
    )
}

class GLFWContext(
    val windowHandler: CValuesRef<cnames.structs.GLFWwindow>,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
        glfwDestroyWindow(windowHandler)
    }
}
