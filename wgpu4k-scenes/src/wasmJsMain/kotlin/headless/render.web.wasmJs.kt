@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.examples.headless

actual fun sceneParameter(
    scene: JsString,
    frame: JsString,
    screenshotPath: JsString?
): SceneParameter = SceneParameter(scene.toString(), frame.toString().toInt(), screenshotPath.toString())