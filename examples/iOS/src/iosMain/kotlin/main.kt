@file:OptIn(ExperimentalForeignApi::class)

import io.ygdrasil.webgpu.examples.Application
import io.ygdrasil.webgpu.examples.createApplication
import io.ygdrasil.webgpu.iosContextRenderer
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import platform.CoreGraphics.CGSize
import platform.MetalKit.MTKView
import platform.MetalKit.MTKViewDelegateProtocol
import platform.darwin.NSObject

suspend fun configureApplication(view: MTKView) {
    try {
        val size = view.drawableSize()
        val context = iosContextRenderer(
            view,
            size.useContents { width.toInt() }, size.useContents { height.toInt() }
        )
        val application = createApplication(context.wgpuContext)
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