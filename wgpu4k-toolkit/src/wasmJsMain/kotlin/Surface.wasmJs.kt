@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import kotlinx.browser.document

internal actual fun createcCanvas(name: String, isHidden: Boolean): HTMLCanvasElement =
    (document.createElement("canvas") as org.w3c.dom.HTMLCanvasElement).also {
        document.body?.appendChild(it)
        it.hidden = isHidden
    }.unsafeCast()