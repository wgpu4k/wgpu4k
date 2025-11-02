package io.ygdrasil.webgpu

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.examples.Application
import io.ygdrasil.webgpu.examples.createApplication
import korlibs.io.android.withAndroidContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WGPUSurfaceView : SurfaceView, SurfaceHolder.Callback2 {
    private var application: Application? = null
    private val logger = KotlinLogging.logger {}
    private val gestureDetector: GestureDetector

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        holder.addCallback(this)
        // The only way to set SurfaceView background color to transparent:
        // https://groups.google.com/g/android-developers/c/jYjvm7ItpXQ?pli=1
        //this.setZOrderOnTop(true)
        //holder.setFormat(PixelFormat.TRANSPARENT)

        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                logger.info { "Double tap detected - switching to next scene" }
                runBlocking {
                    changeScene()
                }
                return true
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        MainScope().launch {
            if (application != null) return@launch
            try {
                withAndroidContext(context) {
                    val androidContext = androidContextRenderer(surfaceHolder, width, height)
                    application = createApplication(androidContext.wgpuContext)
                    logger.info { "Created application $application" }
                    setWillNotDraw(false)
                }
            } catch (e: Exception) {
                logger.error(e) { "Failed to create application" }
            }
        }

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        logger.info { "surfaceDestroyed" }
        application?.apply {
            currentScene.close()
            wgpuContext.close()
        }
        application = null
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        MainScope().launch {
            try {
                application?.renderFrame()
                invalidate()
            } catch (e: Exception) {
                logger.error(e) { "Failed to render frame" }
            }
        }
    }

    suspend fun changeScene() {
        logger.info { "changeScene" }
        val application = application ?: return
        val currentIndex = application.availableScenes.indexOf(application.currentScene)

        val nextIndex = (currentIndex + 1).let {
            when (it) {
                application.availableScenes.size -> 0
                -1 -> application.availableScenes.size - 1
                else -> it
            }
        }
        application.changeScene(application.availableScenes[nextIndex])
    }

    override fun surfaceRedrawNeeded(holder: SurfaceHolder) {}

}