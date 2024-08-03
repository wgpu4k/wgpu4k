package io.ygdrasil.wgpu

import cnames.structs.GLFWwindow
import glfw.glfwGetWin32Window
import kotlinx.cinterop.CPointer
import platform.windows.GetModuleHandle

actual fun WGPU.getSurface(window: CPointer<GLFWwindow>, sizeProvider: () -> Pair<Int, Int>): Surface {
    val hwnd = glfwGetWin32Window(window)
    val hinstance = GetModuleHandle?(null)?.reinterpret<COpaquePointer>() ?: error("fail to get hinstance")
    val surface = getSurfaceFromWindows(hinstance, hwnd) ?: error("fail to get surface on Windows")
    return Surface(surface, sizeProvider)
}