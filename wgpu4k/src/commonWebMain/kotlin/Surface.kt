package io.ygdrasil.webgpu

fun HTMLCanvasElement.getCanvasSurface() = getContext("webgpu").castAs<GPUCanvasContext>()


external interface GPUCanvasContext : JsObject{
    val canvas: HTMLCanvasElement /* HTMLCanvasElement or OffscreenCanvas */

    fun configure(configuration: GPUCanvasConfiguration)
    fun unconfigure()

    fun getConfiguration(): GPUCanvasConfiguration?
    fun getCurrentTexture(): GPUTexture
}

interface GPUCanvasConfiguration {
    val device: GPUDevice
    val format: GPUTextureFormat
    val usage: GPUTextureUsageFlags
    val viewFormats: JsArray<JsObject> /* GPUTextureFormat */
    val colorSpace: PredefinedColorSpace
    val toneMapping: GPUCanvasToneMapping
    val alphaMode: GPUCanvasAlphaMode
}

enum class GPUCanvasAlphaMode(val jsName: String) {
    Opaque("opaque"),
    Premultiplied("premultiplied");
}

enum class GPUCanvasToneMappingMode(val jsName: String) {
    Standard("standard"),
    Extended("extended");
}

interface GPUCanvasToneMapping {
    val mode: String /* GPUCanvasToneMappingMode */
};
