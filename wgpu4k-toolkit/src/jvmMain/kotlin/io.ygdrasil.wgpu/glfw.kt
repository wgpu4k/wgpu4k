package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.Kernel32
import darwin.CAMetalLayer
import darwin.NSWindow
import io.ygdrasil.wgpu.WGPU.Companion.createInstance
import io.ygdrasil.wgpu.internal.Os
import io.ygdrasil.wgpu.internal.Platform
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWNativeCocoa.glfwGetCocoaWindow
import org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Display
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Window
import org.lwjgl.system.MemoryUtil.NULL
import org.rococoa.ID
import org.rococoa.Rococoa
import java.lang.foreign.MemorySegment

suspend fun glfwContextRenderer(width: Int = 1, height: Int = 1, title: String = "", deferredRendering: Boolean = false): GLFWContext {

    glfwInit()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
    // Disable context creation, WGPU will manage that
    glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
    val windowHandler: Long = glfwCreateWindow(width, height, title, NULL, NULL)

    val wgpu = createInstance() ?: error("fail to wgpu instance")
    val surface = wgpu.getSurface(windowHandler){
        val width = intArrayOf(1)
        val height = intArrayOf(1)
        glfwGetWindowSize(windowHandler, width, height)
        width[0] to height[0]
    }

    val adapter = wgpu.requestAdapter(surface)
        ?: error("fail to get adapter")

    val device = adapter.requestDevice()
        ?: error("fail to get device")

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(256, 256, TextureFormat.rgba8unorm, device)
        false -> {
            surface.computeSurfaceCapabilities(adapter)
            SurfaceRenderingContext(surface)
        }
    }

    return GLFWContext(
        windowHandler,
        WGPUContext(surface, adapter, device, renderingContext)
    )
}

class GLFWContext(
    val windowHandler: Long,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
        glfwDestroyWindow(windowHandler)
    }
}

fun WGPU.getSurface(window: Long, sizeProvider: () -> Pair<Int, Int>): Surface = when (Platform.os) {
    Os.Linux -> {
        val display = glfwGetX11Display().let { MemorySegment.ofAddress(it) }
        val x11_window = glfwGetX11Window(window)
        getSurfaceFromX11Window(display, x11_window) ?: error("fail to get surface on Linux")
    }
    Os.Window -> {
        val hwnd = glfwGetWin32Window(window).let { MemorySegment.ofAddress(it) }
        val hinstance = Kernel32.INSTANCE.GetModuleHandle(null).pointer.toMemory()
        getSurfaceFromWindows(hinstance, hwnd) ?: error("fail to get surface on Windows")
    }
    Os.MacOs -> {
        val nsWindowPtr = glfwGetCocoaWindow(window)
        val nswindow = Rococoa.wrap(ID.fromLong(nsWindowPtr), NSWindow::class.java)
        nswindow.contentView()?.setWantsLayer(true)
        val layer = CAMetalLayer.layer()
        nswindow.contentView()?.setLayer(layer.id().toLong().toPointer())
        getSurfaceFromMetalLayer(MemorySegment.ofAddress(layer.id().toLong()))
    }
}.also { if( it == MemorySegment.NULL) error("fail to get surface") }
    .let { Surface(it, sizeProvider) }


private fun Long.toPointer(): Pointer = Pointer(this)

private fun Pointer.toMemory() = MemorySegment.ofAddress(Pointer.nativeValue(this))