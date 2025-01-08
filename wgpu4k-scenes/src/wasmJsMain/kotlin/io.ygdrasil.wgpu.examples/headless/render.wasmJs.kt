package io.ygdrasil.webgpu.examples.headless

import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams

actual fun getSceneParameter(): SceneParameter {
    val urlParameters = URLSearchParams(window.location.search.toJsString())
    val scene = urlParameters.get("scene") ?: error("fail to get scene name")
    val frame = urlParameters.get("frame") ?: error("fail to get frame")
    val screenshotPath = urlParameters.get("screenshotPath")
    return SceneParameter(scene, frame.toInt(), screenshotPath)
}

actual fun initLog() {
}