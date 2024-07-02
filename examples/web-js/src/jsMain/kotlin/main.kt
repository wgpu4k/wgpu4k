import io.ygdrasil.wgpu.examples.jsApplication
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLCanvasElement

fun main() {
	window.addEventListener("DOMContentLoaded", {
		jsApplication(
			(document.getElementById("webgpu") as? HTMLCanvasElement) ?: error("fail to get canvas")
		)
	})
}