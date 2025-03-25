@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import cnames.structs.GLFWwindow
import glfw.glfwGetWindowSize
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value

actual class Surface(private val handler: NativeSurface, private val windowHandler: CPointer<GLFWwindow>) : AutoCloseable {
    actual val width: UInt
        get() = memScoped {
            val width = alloc<IntVar>()
            val height = alloc<IntVar>()
            glfwGetWindowSize(windowHandler, width.ptr, height.ptr)
            width.value.toUInt()
        }
    actual val height: UInt
        get() = memScoped {
            val width = alloc<IntVar>()
            val height = alloc<IntVar>()
            glfwGetWindowSize(windowHandler, width.ptr, height.ptr)
            height.value.toUInt()
        }

    actual val supportedFormats: Set<GPUTextureFormat>
        get() = handler.supportedFormats
    actual val supportedAlphaMode: Set<CompositeAlphaMode>
        get() = handler.supportedAlphaMode

    actual fun getCurrentTexture(): SurfaceTexture = handler.getCurrentTexture()

    actual fun present() = handler.present()

    actual fun configure(surfaceConfiguration: SurfaceConfiguration) {
        handler.configure(surfaceConfiguration, width, height)
    }

    actual override fun close() = handler.close()

}