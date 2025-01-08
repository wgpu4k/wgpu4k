@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import cnames.structs.GLFWwindow
import glfw.glfwGetWin32Window
import kotlinx.cinterop.*
import platform.windows.GetModuleHandle

actual fun WGPU.getSurface(window: CPointer<GLFWwindow>, sizeProvider: () -> Pair<Int, Int>): Surface {
    val hwnd = glfwGetWin32Window(window)  ?: error("fail to get hwnd")
    val hinstance: COpaquePointer = GetModuleHandle?.invoke(null)
        ?.let { interpretCPointer<COpaque>(it.rawValue) }
        ?.reinterpret() ?: error("fail to get hinstance")
    val surface = getSurfaceFromWindows(hinstance, hwnd) ?: error("fail to get surface on Windows")
    return Surface(surface, sizeProvider)
}