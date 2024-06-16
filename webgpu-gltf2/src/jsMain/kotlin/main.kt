import glb.renderContext
import korlibs.io.async.launch
import kotlinx.browser.window
import kotlinx.coroutines.MainScope


val file = "./DamagedHelmet.glb"
fun main() {
    window.addEventListener("DOMContentLoaded", {
        MainScope().launch {
            renderContext()
        }
    })
}
