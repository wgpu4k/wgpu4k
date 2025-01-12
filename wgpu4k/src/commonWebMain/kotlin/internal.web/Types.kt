package io.ygdrasil.webgpu.internal.web

expect class JsNumber
expect class JsString
expect interface JsObject

external interface GPUObjectDescriptorBase {
    var label: String?
}

external object navigator {
    val gpu: GPU?
}

external interface GPU {
    fun getPreferredCanvasFormat(): String
}

external interface HtmlElementWithSize {
    var width: JsNumber
    var height: JsNumber
}

external interface GPUCanvasContext {
    var canvas: HtmlElementWithSize /* HTMLCanvasElement | OffscreenCanvas */
    fun configure(configuration: GPUCanvasConfiguration)
    fun unconfigure()
    fun getCurrentTexture(): JsObject
}

external interface GPUCanvasConfiguration: JsObject {
    // GPUDevice
    var device: JsObject
    var format: JsString
    // GPUTextureUsageFlags
    var usage: JsNumber?
    // Array<String>
    var viewFormats: JsObject
    var colorSpace: JsString?
    var alphaMode: JsString?
}