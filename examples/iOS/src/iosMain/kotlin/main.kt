@file:OptIn(ExperimentalForeignApi::class)

import io.ygdrasil.wgpu.Surface
import io.ygdrasil.wgpu.WGPU
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi

fun initwgpu(metalLayer: COpaquePointer) {
    val sizeProvider = {
        100 to 100
    }
    val instance = WGPU.createInstance() ?: error("Can't create WGPU instance")
    val surface = instance.getSurfaceFromMetalLayer(metalLayer)
        ?.let { Surface(it, sizeProvider) } ?: error("Can't create Surface")
    val adapter = instance.requestAdapter(surface) ?: error("Can't create Adapter")
}

fun nothing() {
    println("nothing")
}