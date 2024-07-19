@file:OptIn(ExperimentalForeignApi::class)

import cnames.structs.GLFWwindow
import glfw.*
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.WGPU.Companion.createInstance
import kotlinx.cinterop.*
import kotlinx.coroutines.runBlocking
import platform.AppKit.NSWindow
import platform.QuartzCore.CAMetalLayer
import webgpu.WGPULogCallback
import webgpu.WGPUSurface
import webgpu.wgpuSetLogCallback
import webgpu.wgpuSetLogLevel
import kotlin.system.exitProcess

val callback: WGPULogCallback = staticCFunction<webgpu.WGPULogLevel, CPointer<ByteVar>?, COpaquePointer?, Unit> { level, message, _ ->
    println("LOG {$level} ${message?.toKStringFromUtf8()}")
}.reinterpret()

val glfwDispatcher = GlfwCoroutineDispatcher() // a custom coroutine dispatcher, in which Compose will run

fun main() {
    runBlocking {
        run()
    }
}

suspend fun run() {
    wgpuSetLogLevel(1u)
    wgpuSetLogCallback(callback, null)

    var width = 640
    var height = 480

    glfwInit()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
    // Disable context creation, else vulkan backend crashes because swap already exists
    glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
    val windowHandle = glfwCreateWindow(width, height, "GLFW+WebGPU", null, null) ?: error("Failed to create GLFW window")

    val closeCallback = staticCFunction<Unit> {
        glfwDispatcher.stop()

    }

    glfwSetWindowCloseCallback(windowHandle, closeCallback.reinterpret())

    val wgpu = createInstance() ?: error("fail to wgpu instance")
    val surface = wgpu.getSurface(windowHandle)

    val renderingContext = RenderingContext(surface) {
        memScoped {
            val width = alloc<IntVar>()
            val height = alloc<IntVar>()
            glfwGetWindowSize(windowHandle, width.ptr, height.ptr)
            width.value.toInt() to height.value.toInt()
        }
    }

    val adapter = wgpu.requestAdapter(renderingContext)
        ?: error("fail to get adapter")

    val device = adapter.requestDevice()
        ?: error("fail to get device")

    renderingContext.computeSurfaceCapabilities(adapter)

    renderingContext.configure(
        CanvasConfiguration(
            device = device
        )
    )

    val renderPipeline = device.createRenderPipeline(
        RenderPipelineDescriptor(
            vertex = RenderPipelineDescriptor.VertexState(
                module = device.createShaderModule(
                    ShaderModuleDescriptor(
                        code = triangleVertexShader
                    )
                )
            ),
            fragment = RenderPipelineDescriptor.FragmentState(
                module = device.createShaderModule(
                    ShaderModuleDescriptor(
                        code = redFragmentShader
                    )
                ),
                targets = arrayOf(
                    RenderPipelineDescriptor.FragmentState.ColorTargetState(
                        format = renderingContext.textureFormat
                    )
                )
            )
        )
    )

    // Clear the canvas with a render pass
    val encoder = device.createCommandEncoder()

    val texture = renderingContext.getCurrentTexture()

    val renderPassEncoder = encoder.beginRenderPass(
        RenderPassDescriptor(
            colorAttachments = arrayOf(
                RenderPassDescriptor.ColorAttachment(
                    view =  texture.createView(),
                    loadOp = LoadOp.clear,
                    clearValue = arrayOf(0, 0, 0, 1.0),
                    storeOp = StoreOp.store
                )
            )
        )
    )

    renderPassEncoder.setPipeline(renderPipeline)
    renderPassEncoder.draw(3)
    renderPassEncoder.end()

    val commandBuffer = encoder.finish()

    device.queue.submit(arrayOf(commandBuffer))

    renderingContext.present()
    /*val assetManager = runBlocking { genericAssetManager() }

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
    }*/


    glfwShowWindow(windowHandle)

    //application.run()
    glfwDispatcher.runLoop()

    //application.close()
    wgpu.close()
    glfwDestroyWindow(windowHandle)
    exitProcess(0)
}



fun WGPU.getSurface(window: CPointer<GLFWwindow>): WGPUSurface  {

    val nsWindow = interpretObjCPointer<NSWindow>(glfwGetCocoaWindow(window).rawValue)
    nsWindow.contentView()?.setWantsLayer(true)
    val layer = CAMetalLayer.layer()
    nsWindow.contentView()?.setLayer(layer)
    val layerPointer: COpaquePointer = interpretCPointer<COpaque>(layer.objcPtr())!!.reinterpret()
    return getSurfaceFromMetalLayer(layerPointer) ?: error("fail to get surface on MacOs")
}



