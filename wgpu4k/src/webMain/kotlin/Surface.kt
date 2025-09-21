@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.unsafeCast

fun HTMLCanvasElement.getCanvasSurface() = getContext("webgpu").unsafeCast<WGPUCanvasContext>()

// TODO remove if not needed
enum class GPUCanvasAlphaMode(val value: String) {
    Opaque("opaque"),
    Premultiplied("premultiplied");
}

enum class GPUCanvasToneMappingMode(val value: String) {
    Standard("standard"),
    Extended("extended");
}