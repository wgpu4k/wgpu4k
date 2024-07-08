package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.CanvasConfiguration
import io.ygdrasil.wgpu.GPUTextureUsageFlags
import io.ygdrasil.wgpu.TextureFormat
import io.ygdrasil.wgpu.internal.js.GPUCanvasConfiguration
import io.ygdrasil.wgpu.internal.js.GPUDevice
import io.ygdrasil.wgpu.toFlagInt

internal fun map(input: CanvasConfiguration, textureFormat: TextureFormat): GPUCanvasConfiguration = GPUCanvasConfigurationImp().apply {
    device = input.device.handler
    format = input.format?.actualName ?: textureFormat.actualName
    usage = input.usage.toFlagInt()
    viewFormats = null //TODO fix this
    colorSpace = null  //TODO map this
    alphaMode = input.alphaMode?.name
}

private external class GPUCanvasConfigurationImp : GPUCanvasConfiguration {
    override var device: GPUDevice
    override var format: String
    override var usage: GPUTextureUsageFlags?
    override var viewFormats: JsArray<JsAny?>?
    override var colorSpace: JsAny?
    override var alphaMode: String?
}