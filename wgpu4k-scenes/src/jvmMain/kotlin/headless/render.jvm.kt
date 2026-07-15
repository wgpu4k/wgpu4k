package io.ygdrasil.webgpu.examples.headless

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.wgpu.WGPULogCallback
import io.ygdrasil.wgpu.WGPULogLevel_Debug
import io.ygdrasil.wgpu.WGPULogLevel_Error
import io.ygdrasil.wgpu.WGPULogLevel_Info
import io.ygdrasil.wgpu.WGPULogLevel_Trace
import io.ygdrasil.wgpu.WGPULogLevel_Warn
import io.ygdrasil.wgpu.wgpuSetLogCallback
import io.ygdrasil.wgpu.wgpuSetLogLevel

private val logger = KotlinLogging.logger {}

private val logCallback = WGPULogCallback { level, cMessage ->
    val message = cMessage.data?.toKString(cMessage.length) ?: "empty message"
    when (level) {
        WGPULogLevel_Error -> logger.error { message }
        WGPULogLevel_Warn -> logger.warn { message }
        WGPULogLevel_Info -> logger.info { message }
        WGPULogLevel_Debug -> logger.debug { message }
        WGPULogLevel_Trace -> logger.trace { message }
        else -> logger.warn { "Unknown log level $level with message $message" }
    }
}

private var callbackRegistration: CallbackRegistration<WGPULogCallback>? = null

actual fun initLog() {
    wgpuSetLogLevel(WGPULogLevel_Trace)
    callbackRegistration?.close()
    callbackRegistration = wgpuSetLogCallback(
        policy = CallbackPolicy.REPEATING,
        callback = logCallback,
    )
}

actual fun getSceneParameter(): SceneParameter {
    val scene = System.getProperty("scene")
    val frame = System.getProperty("frame")
    val screenshotPath = System.getProperty("screenshotPath")
    return SceneParameter(scene, frame.toInt(), screenshotPath)
}