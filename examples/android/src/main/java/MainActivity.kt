package io.ygdrasil.webgpu

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutParams = ViewGroup
            .LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

        val surfaceView = WGPUSurfaceView(context = this)
        surfaceView.layoutParams = layoutParams
        addContentView(surfaceView, surfaceView.layoutParams)

    }
}