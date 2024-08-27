@file:OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.createApplication
import kotlinx.cinterop.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import platform.CoreGraphics.CGSize
import platform.MetalKit.MTKView
import platform.MetalKit.MTKViewDelegateProtocol
import platform.darwin.NSObject

suspend fun configureApplication(view: MTKView) {
    try {
        val size = view.drawableSize()
        val sizeProvider = {
            size.useContents { width.toInt() } to size.useContents { height.toInt() }
        }
        val layer = view.layer
        val layerPointer: COpaquePointer = interpretCPointer<COpaque>(layer.objcPtr())!!.reinterpret()
        val instance = WGPU.createInstance() ?: error("Can't create WGPU instance")
        val surface = instance.getSurfaceFromMetalLayer(layerPointer)
            ?.let { Surface(it, sizeProvider) } ?: error("Can't create Surface")
        val adapter = instance.requestAdapter(surface) ?: error("Can't create Adapter")
        val device = adapter.requestDevice() ?: error("fail to get device")

        surface.computeSurfaceCapabilities(adapter)
        val renderingContext = SurfaceRenderingContext(surface)


        val context = WGPUContext(surface, adapter, device, renderingContext)
        val application = createApplication(context)
        view.delegate = View(application)
    }catch (e: Throwable) {
        e.printStackTrace()
        throw e
    }

}

class View(
    val application: Application,
) : NSObject(), MTKViewDelegateProtocol {


    override fun mtkView(view: MTKView, drawableSizeWillChange: CValue<CGSize>) { }

    override fun drawInMTKView(view: MTKView) {
        MainScope().launch {
            try {
                application.renderFrame()
            }catch (e: Throwable) {
                e.printStackTrace()
                throw e
            }
        }
    }

}