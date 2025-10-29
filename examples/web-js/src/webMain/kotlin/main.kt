@file:Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")

import io.ygdrasil.webgpu.examples.jsApplication
import web.dom.DocumentReadyState
import web.dom.ElementId
import web.dom.document
import web.dom.loading
import web.events.Event
import web.events.EventType
import web.events.addEventListener
import web.html.HTMLCanvasElement
import web.window.window

fun main() {
    if (document.readyState != DocumentReadyState.loading) {
        run()
    } else {
        window.addEventListener(EventType("DOMContentLoaded"), { _: Event ->
            run()
        })
    }
}

fun run() {
    jsApplication(
        (document.getElementById(ElementId("webgpu")) as? HTMLCanvasElement) ?: error("fail to get canvas")
    )
}