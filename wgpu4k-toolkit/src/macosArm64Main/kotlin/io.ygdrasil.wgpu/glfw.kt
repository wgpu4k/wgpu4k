package io.ygdrasil.wgpu

import cnames.structs.GLFWwindow
import glfw.glfwGetCocoaWindow
import kotlinx.cinterop.*
import platform.AppKit.NSWindow
import platform.QuartzCore.CAMetalLayer

actual fun WGPU.getSurface(window: CPointer<GLFWwindow>, sizeProvider: () -> Pair<Int, Int>): Surface {

    val nsWindow = interpretObjCPointer<NSWindow>(glfwGetCocoaWindow(window).rawValue)
    nsWindow.contentView()?.setWantsLayer(true)
    val layer = CAMetalLayer.layer()
    nsWindow.contentView()?.setLayer(layer)
    val layerPointer: COpaquePointer = interpretCPointer<COpaque>(layer.objcPtr())!!.reinterpret()
    val surface = getSurfaceFromMetalLayer(layerPointer) ?: error("fail to get surface on MacOs")
    return Surface(surface, sizeProvider)
}