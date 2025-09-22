@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import web.gpu.GPUCanvasContext
import web.gpu.ID
import web.html.HTMLCanvasElement
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.unsafeCast


fun HTMLCanvasElement.getCanvasSurface() = getContext(GPUCanvasContext.ID)!!.unsafeCast<WGPUCanvasContext>()

// TODO remove if not needed
enum class GPUCanvasAlphaMode(val value: String) {
    Opaque("opaque"),
    Premultiplied("premultiplied");
}

enum class GPUCanvasToneMappingMode(val value: String) {
    Standard("standard"),
    Extended("extended");
}