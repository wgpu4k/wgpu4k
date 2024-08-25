@file:OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.createApplication
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGSize
import platform.MetalKit.MTKView
import platform.MetalKit.MTKViewDelegateProtocol
import platform.darwin.NSObject

suspend fun initwgpu(metalLayer: COpaquePointer): Application {
    try {
        val sizeProvider = {
            100 to 100
        }
        val instance = WGPU.createInstance() ?: error("Can't create WGPU instance")
        val surface = instance.getSurfaceFromMetalLayer(metalLayer)
            ?.let { Surface(it, sizeProvider) } ?: error("Can't create Surface")
        val adapter = instance.requestAdapter(surface) ?: error("Can't create Adapter")
        val device = adapter.requestDevice() ?: error("fail to get device")

        surface.computeSurfaceCapabilities(adapter)
        val renderingContext = SurfaceRenderingContext(surface)


        val context = WGPUContext(surface, adapter, device, renderingContext)

        return createApplication(context)
    }catch (e: Throwable) {
        e.printStackTrace()
        throw e
    }

}

fun nothing() {
    println("nothing")
}

class View : NSObject(), MTKViewDelegateProtocol {


    override fun mtkView(view: MTKView, drawableSizeWillChange: CValue<CGSize>) {
        /*val application = createApplication(
            glfwContext.wgpuContext
        )*/
        TODO("Not yet implemented")
    }


    override fun drawInMTKView(view: MTKView) {
        TODO("Not yet implemented")
    }

}