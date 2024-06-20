package io.ygdrasil.wgpu.examples

import GlfwCoroutineDispatcher
import androidx.compose.ui.ComposeScene
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.Kernel32
import darwin.CAMetalLayer
import darwin.NSWindow
import io.ygdrasil.wgpu.RenderingContext
import io.ygdrasil.wgpu.WGPU
import io.ygdrasil.wgpu.WGPU.Companion.createInstance
import io.ygdrasil.wgpu.WGPU.Companion.loadLibrary
import io.ygdrasil.wgpu.internal.jvm.panama.WGPULogCallback
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import korlibs.io.async.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jetbrains.skia.*
import org.jetbrains.skia.FramebufferFormat.Companion.GR_GL_RGBA8
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWNativeCocoa.glfwGetCocoaWindow
import org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Display
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Window
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_BINDING
import org.lwjgl.system.MemoryUtil.NULL
import org.rococoa.ID
import org.rococoa.Rococoa
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import kotlin.system.exitProcess

val callback = WGPULogCallback.allocate( { level, message, data ->
    println("LOG {$level} ${message.getString(0)}")
}, Arena.global())

suspend fun main() {
    loadLibrary()
    wgpu_h.wgpuSetLogLevel(1)
    wgpu_h.wgpuSetLogCallback(callback, MemorySegment.NULL)

    var width = 640
    var height = 480

    glfwInit()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
    // Disable context creation, else vulkan backend crashes because swap already exists
    glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
    val windowHandle: Long = glfwCreateWindow(width, height, "GLFW+WebGPU", NULL, NULL)

    val glfwDispatcher = GlfwCoroutineDispatcher() // a custom coroutine dispatcher, in which Compose will run

    glfwSetWindowCloseCallback(windowHandle) {
        glfwDispatcher.stop()
    }

    val wgpu = createInstance() ?: error("fail to wgpu instance")
    val surface = wgpu.getSurface(windowHandle)

    val renderingContext = RenderingContext(surface) {
        val width = intArrayOf(1)
        val height = intArrayOf(1)
        glfwGetWindowSize(windowHandle, width, height)
        width[0] to height[0]
    }

    val adapter = wgpu.requestAdapter(renderingContext)
        ?: error("fail to get adapter")

    val device = adapter.requestDevice()
        ?: error("fail to get device")

    renderingContext.computeSurfaceCapabilities(adapter)

    val assetManager = runBlocking { genericAssetManager() }

    val application = object : Application(
        renderingContext,
        device,
        adapter,
        assetManager
    ) {

        override fun run() {
            glfwDispatcher.dispatch(Dispatchers.Main) {
                renderFrame()
                run()
            }
        }

    }

    application.load()
    lateinit var composeScene: ComposeScene

    fun render() {

        with(application.currentScene) {
            application.configureRenderingContext()
        }
        application.renderFrame()
    }


    glfwSetWindowSizeCallback(windowHandle) { _, windowWidth, windowHeight ->
        width = windowWidth
        height = windowHeight

        render()
    }

    glfwSetKeyCallback(windowHandle) { _, key, scancode, action, mods ->

        if ((key == GLFW_KEY_PAGE_UP || key == GLFW_KEY_PAGE_DOWN || key == GLFW_KEY_UP || key == GLFW_KEY_DOWN) && action == GLFW_PRESS) {
            val currentIndex = availableScenes.indexOf(application.currentScene)
            val index = if (key == GLFW_KEY_PAGE_UP || key == GLFW_KEY_UP) {
                currentIndex - 1
            } else {
                currentIndex + 1
            }.let {
                when (it) {
                    availableScenes.size -> 0
                    -1 -> availableScenes.size - 1
                    else -> it
                }
            }


            launch(glfwDispatcher) {
                application.changeScene(availableScenes[index])
            }
        }
    }


    glfwShowWindow(windowHandle)

    application.run()
    glfwDispatcher.runLoop()

    application.close()
    wgpu.close()
    glfwDestroyWindow(windowHandle)
    exitProcess(0)
}

fun WGPU.getSurface(window: Long): MemorySegment = when (Platform.os) {
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

private fun createSurface(width: Int, height: Int, context: DirectContext): Surface {
    val fbId = GL11.glGetInteger(GL_FRAMEBUFFER_BINDING)
    val renderTarget = BackendRenderTarget.makeGL(width, height, 0, 8, fbId, GR_GL_RGBA8)
    return Surface.makeFromBackendRenderTarget(
        context, renderTarget, SurfaceOrigin.BOTTOM_LEFT, SurfaceColorFormat.RGBA_8888, ColorSpace.sRGB
    )!!
}

private fun Long.toPointer(): Pointer = Pointer(this)

fun Pointer.toMemory() = MemorySegment.ofAddress(Pointer.nativeValue(this))