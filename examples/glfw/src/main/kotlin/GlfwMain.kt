package io.ygdrasil.wgpu.examples

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.Kernel32
import darwin.CAMetalLayer
import darwin.NSWindow
import io.ygdrasil.wgpu.RenderingContext
import io.ygdrasil.wgpu.WGPU
import io.ygdrasil.wgpu.WGPU.Companion.createInstance
import io.ygdrasil.wgpu.WGPU.Companion.loadLibrary
import io.ygdrasil.wgpu.internal.jvm.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWNativeCocoa.glfwGetCocoaWindow
import org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Display
import org.lwjgl.glfw.GLFWNativeX11.glfwGetX11Window
import org.lwjgl.system.MemoryUtil.NULL
import org.rococoa.ID
import org.rococoa.Rococoa
import java.lang.foreign.MemorySegment
import javax.imageio.ImageIO
import kotlin.system.exitProcess


val callback = object : WGPULogCallback {
    override fun invoke(level: Int, message: String, param3: Pointer?) {
        println("LOG {$level} $message")
    }
}

suspend fun main() {
    wgpuSetLogLevel(1)
    wgpuSetLogCallback(callback, null)

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

	loadLibrary()

	val wgpu = createInstance() ?: error("fail to wgpu instance")
	val surface = wgpu.getSurface(windowHandle)

	val renderingContext = RenderingContext(WGPUSurface(Pointer(surface.address()))) {
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

        if ((key == GLFW_KEY_PAGE_UP || key == GLFW_KEY_PAGE_DOWN) && action == GLFW_PRESS) {
            val currentIndex = availableScenes.indexOf(application.currentScene)
            val index = if (key == GLFW_KEY_PAGE_UP) {
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


            glfwDispatcher.dispatch(Dispatchers.Main) {
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

private fun Long.toPointer(): Pointer = Pointer(this)

