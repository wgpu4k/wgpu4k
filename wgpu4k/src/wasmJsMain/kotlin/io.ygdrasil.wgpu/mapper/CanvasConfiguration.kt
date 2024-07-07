package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.CanvasConfiguration
import io.ygdrasil.wgpu.TextureFormat
import io.ygdrasil.wgpu.internal.js.GPUCanvasConfiguration

fun map(input: CanvasConfiguration, textureFormat: TextureFormat): GPUCanvasConfiguration = TODO()
    /*object : GPUCanvasConfiguration {
    override var device: GPUDevice = input.device.handler
    override var format: String = input.format?.actualName ?: textureFormat.actualName
    override var usage: GPUTextureUsageFlags? = input.usage.toFlagInt()
    override var viewFormats: JsArray<JsAny?>?  = null //TODO fix this
    override var colorSpace: JsAny? = null  //TODO map this
    override var alphaMode: String? = input.alphaMode?.name
}*/