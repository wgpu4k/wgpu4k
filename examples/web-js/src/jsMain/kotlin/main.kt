@file:Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")

import io.ygdrasil.webgpu.examples.jsApplication
import kotlinx.browser.document
import kotlinx.browser.window
import web.html.HTMLCanvasElement

fun main() {
	window.addEventListener("DOMContentLoaded", {
		jsApplication(
			(document.getElementById("webgpu") as? HTMLCanvasElement) ?: error("fail to get canvas")
		)
	})
}