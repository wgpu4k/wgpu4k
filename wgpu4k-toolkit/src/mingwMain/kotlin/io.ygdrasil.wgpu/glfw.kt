package io.ygdrasil.wgpu

import cnames.structs.GLFWwindow
import glfw.glfwGetCocoaWindow
import glfw.glfwGetWin32Window
import kotlinx.cinterop.*
import platform.AppKit.NSWindow
import platform.QuartzCore.CAMetalLayer

actual fun WGPU.getSurface(window: CPointer<GLFWwindow>, sizeProvider: () -> Pair<Int, Int>): Surface {

    val hwnd = glfwGetWin32Window(window)
    val hinstance = Kernel32.INSTANCE.GetModuleHandle(null).pointer.toMemory()
    getSurfaceFromWindows(hinstance, hwnd) ?: error("fail to get surface on Windows")

    val nsWindow = interpretObjCPointer<NSWindow>(glfwGetCocoaWindow(window).rawValue)
    nsWindow.contentView()?.setWantsLayer(true)
    val layer = CAMetalLayer.layer()
    nsWindow.contentView()?.setLayer(layer)
    val layerPointer: COpaquePointer = interpretCPointer<COpaque>(layer.objcPtr())!!.reinterpret()
    val surface = getSurfaceFromMetalLayer(layerPointer) ?: error("fail to get surface on MacOs")
    return Surface(surface, sizeProvider)
}