package io.ygdrasil.webgpu.examples.headless


/*val callback = WGPULogCallback.allocate({ level, message, data ->
    println("LOG {$level} ${message.getString(0)}")
}, Arena.global())
*/

actual fun initLog() {
    //TODO
}

actual fun getSceneParameter(): SceneParameter {
    val scene = System.getProperty("scene")
    val frame = System.getProperty("frame")
    val screenshotPath = System.getProperty("screenshotPath")
    return SceneParameter(scene, frame.toInt(), screenshotPath)
}