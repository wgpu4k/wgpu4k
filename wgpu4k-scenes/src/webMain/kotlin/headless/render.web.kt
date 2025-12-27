@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.examples.headless

import web.location.location
import web.url.URLSearchParams
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsString
import kotlin.js.toJsString

actual fun getSceneParameter(): SceneParameter {
    val urlParameters = URLSearchParams(location.search)
    val scene = urlParameters.get("scene".toJsString()) ?: error("fail to get scene name")
    val frame = urlParameters.get("frame".toJsString()) ?: error("fail to get frame")
    val screenshotPath = urlParameters.get("screenshotPath".toJsString())
    return sceneParameter(scene, frame, screenshotPath)
}

internal expect fun sceneParameter(scene: JsString, frame: JsString, screenshotPath: JsString?): SceneParameter

actual fun initLog() {
}