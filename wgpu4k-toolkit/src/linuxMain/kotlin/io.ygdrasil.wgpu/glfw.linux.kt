@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import cnames.structs.GLFWwindow
import glfw.glfwGetX11Display
import glfw.glfwGetX11Window
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret

actual fun WGPU.getSurface(
    window: CPointer<GLFWwindow>
): Surface {

    val display = glfwGetX11Display(window) ?: error("fail to get X11 display")
    val x11_window = glfwGetX11Window(window).takeIf { it != 0uL } ?: error("fail to get X11 window")

    val surface = getSurfaceFromX11Window(display.reinterpret(), x11_window) ?: error("fail to get surface on MacOs")
    return Surface(surface)
}