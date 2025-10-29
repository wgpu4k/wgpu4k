@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.examples.headless

import web.location.location
import web.url.URLSearchParams
import kotlin.OptIn
import kotlin.error
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.toJsString
import kotlin.toString

actual fun getSceneParameter(): SceneParameter {
    val urlParameters = URLSearchParams(location.search)
    val scene = urlParameters.get("scene".toJsString()) ?: error("fail to get scene name")
    val frame = urlParameters.get("frame".toJsString()) ?: error("fail to get frame")
    val screenshotPath = urlParameters.get("screenshotPath".toJsString())
    return SceneParameter(scene.toString(), frame.toString().toInt(), screenshotPath.toString())
}

actual fun initLog() {
}