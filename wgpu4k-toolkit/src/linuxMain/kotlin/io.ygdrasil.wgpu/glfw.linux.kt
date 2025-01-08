@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import cnames.structs.GLFWwindow
import ffi.NativeAddress
import glfw.glfwGetX11Display
import glfw.glfwGetX11Window
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret

actual fun WGPU.getSurface(
    window: CPointer<GLFWwindow>
): Surface {

    val display: COpaquePointer = glfwGetX11Display(window)?.reinterpret() ?: error("fail to get X11 display")
    val x11_window = glfwGetX11Window(window).takeIf { it != 0uL } ?: error("fail to get X11 window")

    return getSurfaceFromX11Window(display.let { NativeAddress(it) }, x11_window) ?: error("fail to get surface on MacOs")
}