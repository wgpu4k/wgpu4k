package io.ygdrasil.wgpu

import android.view.Surface

class RustBridge {
    init {
        System.loadLibrary("wgpu_in_app")
    }

    external fun createWgpuInstance(): Long
    external fun createWgpuCanvas(wgpu: Long, surface: Surface, idx: Int): Long
    external fun enterFrame(rustObj: Long)
    external fun changeExample(rustObj: Long, idx: Int)

    external fun dropWgpuCanvas(rustObj: Long)
}