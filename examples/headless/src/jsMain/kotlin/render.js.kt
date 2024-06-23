import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams

actual fun initLog() {
}

actual fun getSceneParameter(): SceneParameter {
    val urlParameters = URLSearchParams(window.location.search)
    val scene = urlParameters.get("scene") ?: error("fail to get scene name")
    val frame = urlParameters.get("frame") ?: error("fail to get frame")
    return SceneParameter(scene, frame.toInt())
}