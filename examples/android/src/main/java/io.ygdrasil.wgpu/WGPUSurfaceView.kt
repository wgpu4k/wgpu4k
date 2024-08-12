package io.ygdrasil.wgpu

import android.content.Context
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import io.ygdrasil.wgpu.examples.genericAssetManager
import korlibs.io.android.withAndroidContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.future.future

class WGPUSurfaceView : SurfaceView, SurfaceHolder.Callback2 {
    private var rustBrige = RustBridge()
    private val wgpu = WGPU.createInstance()
    private var wgpuObj: Long = Long.MAX_VALUE
    private var idx: Int = 0

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

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    // 绘制表面被创建后，创建/重新创建 wgpu 对象
    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        MainScope().future {
            glfwContextRenderer(surfaceHolder)
        }

        surfaceHolder.let { h ->
            //wgpuObj = rustBrige.createWgpuCanvas(wgpuIntance, h.surface, this.idx)
            Log.i("myApp", "instances")
            // SurfaceView 默认不会自动开始绘制，setWillNotDraw(false) 用于通知 App 已经准备好开始绘制了。
            setWillNotDraw(false)
        }
    }

    // 绘制表面被销毁后，也销毁 wgpu 对象
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        if (wgpuObj != Long.MAX_VALUE) {
            rustBrige.dropWgpuCanvas(wgpuObj)
            wgpuObj = Long.MAX_VALUE
        }
    }

    override fun surfaceRedrawNeeded(holder: SurfaceHolder) {
    }

    // API Level 26+
//    override fun surfaceRedrawNeededAsync(holder: SurfaceHolder, drawingFinished: Runnable) {
//        super.surfaceRedrawNeededAsync(holder, drawingFinished)
//    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        // 考虑到边界情况，这个条件判断不能省略
        if (wgpuObj == Long.MAX_VALUE) {
           return
        }
        rustBrige.enterFrame(wgpuObj)
        // invalidate() 函数通知通知 App，在下一个 UI 刷新周期重新调用 draw() 函数
        invalidate()
    }

    fun changeExample(index: Int) {
        if (wgpuObj != Long.MAX_VALUE && this.idx != index) {
            rustBrige.changeExample(wgpuObj, index)
            this.idx = index
        }
    }

}