import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.DocumentReadyState
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.LOADING

fun main() {
    if (document.readyState != DocumentReadyState.Companion.LOADING) {
        run()
    } else {
        window.addEventListener("DOMContentLoaded") {
            run()
        }
    }
}

fun run() {
    jsApplication(
        (document.getElementById("webgpu") as? HTMLCanvasElement) ?: error("fail to get canvas")
    )
}