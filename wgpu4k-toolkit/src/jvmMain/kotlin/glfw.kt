package io.ygdrasil.webgpu

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.Kernel32
import darwin.CAMetalLayer
import darwin.NSWindow
import ffi.NativeAddress
import io.ygdrasil.webgpu.WGPU.Companion.createInstance
import io.ygdrasil.webgpu.internal.Os
import io.ygdrasil.webgpu.internal.Platform
import org.lwjgl.glfw.GLFW.GLFW_CLIENT_API
import org.lwjgl.glfw.GLFW.GLFW_FALSE
import org.lwjgl.glfw.GLFW.GLFW_NO_API
import org.lwjgl.glfw.GLFW.GLFW_RESIZABLE
import org.lwjgl.glfw.GLFW.GLFW_VISIBLE
import org.lwjgl.glfw.GLFW.glfwCreateWindow
import org.lwjgl.glfw.GLFW.glfwDestroyWindow
import org.lwjgl.glfw.GLFW.glfwInit
import org.lwjgl.glfw.GLFW.glfwWindowHint
import org.lwjgl.glfw.GLFWNativeCocoa.glfwGetCocoaWindow
import org.lwjgl.glfw.GLFWNativeWayland.glfwGetWaylandDisplay
import org.lwjgl.glfw.GLFWNativeWayland.glfwGetWaylandWindow
import org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Display
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Window
import org.lwjgl.system.MemoryUtil.NULL
import org.rococoa.ID
import org.rococoa.Rococoa
import java.lang.foreign.MemorySegment

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

    val windowHandler: Long = glfwCreateWindow(width, height, title, NULL, NULL)

    val wgpu = createInstance() ?: error("fail to wgpu instance")
    val nativeSurface = wgpu.getNativeSurface(windowHandler)
    val surface = Surface(nativeSurface, windowHandler)

    val adapter = wgpu.requestAdapter(nativeSurface)
        ?: error("fail to get adapter")

    val device = adapter.requestDevice()
        .getOrThrow()

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(256u, 256u, GPUTextureFormat.RGBA8Unorm, device)
        false -> {
            nativeSurface.computeSurfaceCapabilities(adapter)
            SurfaceRenderingContext(surface, surface.supportedFormats.first())
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

private fun WGPU.getNativeSurface(window: Long): NativeSurface = when (Platform.os) {
    Os.Linux -> when {
        glfwGetWaylandWindow(window) == 0L -> {
            println("running on X11")
            val display = glfwGetX11Display().toNativeAddress()
            val x11_window = glfwGetX11Window(window).toULong()
            getSurfaceFromX11Window(display, x11_window) ?: error("fail to get surface on Linux")
        }

        else -> {
            println("running on Wayland")
            val display = glfwGetWaylandDisplay().toNativeAddress()
            val wayland_window = glfwGetWaylandWindow(window).toNativeAddress()
            getSurfaceFromWaylandWindow(display, wayland_window)
        }
    }

    Os.Window -> {
        val hwnd = glfwGetWin32Window(window).toNativeAddress()
        val hinstance = Kernel32.INSTANCE.GetModuleHandle(null).pointer.toNativeAddress()
        getSurfaceFromWindows(hinstance, hwnd) ?: error("fail to get surface on Windows")
    }

    Os.MacOs -> {
        val nsWindowPtr = glfwGetCocoaWindow(window)
        val nswindow = Rococoa.wrap(ID.fromLong(nsWindowPtr), NSWindow::class.java)
        nswindow.contentView()?.setWantsLayer(true)
        val layer = CAMetalLayer.layer()
        nswindow.contentView()?.setLayer(layer.id().toLong().toPointer())
        getSurfaceFromMetalLayer(layer.id().toLong().toNativeAddress())
    }
} ?: error("fail to get surface")


private fun Long.toPointer(): Pointer = Pointer(this)

fun Pointer.toNativeAddress() = let { MemorySegment.ofAddress(Pointer.nativeValue(this)) }
    .let { NativeAddress(it) }

fun Long.toNativeAddress() = let { MemorySegment.ofAddress(it) }
    .let { NativeAddress(it) }