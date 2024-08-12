package io.ygdrasil.wgpu

import android.content.Context
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.createApplication
import io.ygdrasil.wgpu.examples.genericAssetManager
import korlibs.io.android.withAndroidContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.future.future
import kotlinx.coroutines.launch

class WGPUSurfaceView : SurfaceView, SurfaceHolder.Callback2 {
    private var rustBrige = RustBridge()
    private var wgpuObj: Long = Long.MAX_VALUE
    private var idx: Int = 0
    private var application: Application? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        holder.addCallback(this)
        // The only way to set SurfaceView background color to transparent:
        // https://groups.google.com/g/android-developers/c/jYjvm7ItpXQ?pli=1
        this.setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSPARENT)

        val assetManager = MainScope().future {

            try {
                println("will load asset manager")
                withAndroidContext(context) {
                    genericAssetManager("/")
                        .also { println("test ${it.boxMesh}") }
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        MainScope().launch {
            withAndroidContext(context) {
                val androidContext = androidContextRenderer(surfaceHolder, width, height)
                application = createApplication(androidContext.wgpuContext)
            }
        }
        setWillNotDraw(false)


        //wgpuObj = rustBrige.createWgpuCanvas(wgpuIntance, surfaceHolder.surface, this.idx)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        application = null
        if (wgpuObj != Long.MAX_VALUE) {
            rustBrige.dropWgpuCanvas(wgpuObj)
            wgpuObj = Long.MAX_VALUE
        }
    }

    override fun surfaceRedrawNeeded(holder: SurfaceHolder) {}

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        MainScope().launch {
            application?.renderFrame()
        }

        if (wgpuObj == Long.MAX_VALUE) {
            return
        }
        rustBrige.enterFrame(wgpuObj)

        invalidate()
    }

    fun changeExample(index: Int) {
        if (wgpuObj != Long.MAX_VALUE && this.idx != index) {
            rustBrige.changeExample(wgpuObj, index)
            this.idx = index
        }
    }

}