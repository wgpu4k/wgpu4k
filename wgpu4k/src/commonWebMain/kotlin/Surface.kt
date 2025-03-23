package io.ygdrasil.webgpu

fun HTMLCanvasElement.getCanvasSurface() = getContext("webgpu").castAs<WGPUCanvasContext>()

// TODO remove if not needed
enum class GPUCanvasAlphaMode(val value: String) {
    Opaque("opaque"),
    Premultiplied("premultiplied");
}

enum class GPUCanvasToneMappingMode(val value: String) {
    Standard("standard"),
    Extended("extended");
}