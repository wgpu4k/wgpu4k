@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.examples.headless

internal actual fun sceneParameter(
    scene: JsString,
    frame: JsString,
    screenshotPath: JsString?
): SceneParameter = SceneParameter(scene, frame.toInt(), screenshotPath.toString())