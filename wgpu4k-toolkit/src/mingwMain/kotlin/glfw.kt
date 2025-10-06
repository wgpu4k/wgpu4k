@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import cnames.structs.GLFWwindow
import ffi.NativeAddress
import glfw.glfwGetWin32Window
import kotlinx.cinterop.COpaque
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.interpretCPointer
import kotlinx.cinterop.invoke
import kotlinx.cinterop.reinterpret
import platform.windows.GetModuleHandle

actual fun WGPU.getNativeSurface(window: CPointer<GLFWwindow>): NativeSurface {
    val hwnd = glfwGetWin32Window(window)  ?: error("fail to get hwnd")
    val hinstance: COpaquePointer = GetModuleHandle?.invoke(null)
        ?.let { interpretCPointer<COpaque>(it.rawValue) }
        ?.reinterpret() ?: error("fail to get hinstance")
    return getSurfaceFromWindows(
        hinstance.let(::NativeAddress),
        hwnd.let(::NativeAddress)
    ) ?: error("fail to get surface on Windows")
}