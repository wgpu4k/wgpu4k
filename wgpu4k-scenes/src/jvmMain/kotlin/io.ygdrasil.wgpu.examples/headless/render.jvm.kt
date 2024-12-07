package io.ygdrasil.wgpu.examples.headless

import io.ygdrasil.wgpu.LogLevel
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment


val callback = WGPULogCallback.allocate({ level, message, data ->
    println("LOG {$level} ${message.getString(0)}")
}, Arena.global())

actual fun initLog() {
    wgpuSetLogLevel(LogLevel.trace.value)
    wgpu_h.wgpuSetLogCallback(callback, MemorySegment.NULL)
}

actual fun getSceneParameter(): SceneParameter {
    val scene = System.getProperty("scene")
    val frame = System.getProperty("frame")
    val screenshotPath = System.getProperty("screenshotPath")
    return SceneParameter(scene, frame.toInt(), screenshotPath)
}