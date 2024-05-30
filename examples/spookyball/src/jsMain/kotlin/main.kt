import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLCanvasElement

fun main() {
	window.addEventListener("DOMContentLoaded", {
		val canvas = (document.querySelector("canvas") as? HTMLCanvasElement) ?: error("fail to get canvas")
		/*jsApplication(canvas)
			.then { it.run() }*/

	})
}