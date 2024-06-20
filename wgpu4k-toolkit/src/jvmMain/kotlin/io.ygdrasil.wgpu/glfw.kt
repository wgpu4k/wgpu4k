package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.Kernel32
import darwin.CAMetalLayer
import darwin.NSWindow
import io.ygdrasil.wgpu.internal.Os
import io.ygdrasil.wgpu.internal.Platform
import org.lwjgl.glfw.GLFWNativeCocoa.glfwGetCocoaWindow
import org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Display
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Window
import org.rococoa.ID
import org.rococoa.Rococoa
import java.lang.foreign.MemorySegment

suspend fun glfwContextRenderer() {

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