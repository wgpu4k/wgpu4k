@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import cnames.structs.GLFWwindow
import ffi.NativeAddress
import glfw.glfwGetCocoaWindow
import kotlinx.cinterop.COpaque
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.interpretCPointer
import kotlinx.cinterop.interpretObjCPointer
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.rawValue
import kotlinx.cinterop.reinterpret
import platform.AppKit.NSWindow
import platform.QuartzCore.CAMetalLayer

actual fun WGPU.getSurface(window: CPointer<GLFWwindow>): Surface {
    val nsWindow = interpretObjCPointer<NSWindow>(glfwGetCocoaWindow(window).rawValue)
    nsWindow.contentView()?.setWantsLayer(true)
    val layer = CAMetalLayer.layer()
    nsWindow.contentView()?.setLayer(layer)
    val layerPointer: COpaquePointer = interpretCPointer<COpaque>(layer.objcPtr())!!.reinterpret()

    return getSurfaceFromMetalLayer(layerPointer.let(::NativeAddress)) ?: error("fail to get surface on MacOs")
}