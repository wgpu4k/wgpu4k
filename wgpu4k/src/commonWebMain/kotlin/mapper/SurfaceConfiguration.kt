package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.SurfaceConfiguration
import io.ygdrasil.webgpu.internal.web.GPUCanvasConfiguration
import io.ygdrasil.webgpu.internal.web.JsObject
import io.ygdrasil.webgpu.internal.web.JsString
import io.ygdrasil.webgpu.internal.web.createJsObject
import io.ygdrasil.webgpu.internal.web.mapJsArray
import io.ygdrasil.webgpu.toFlagInt

fun map(input: SurfaceConfiguration) : GPUCanvasConfiguration = createJsObject<GPUCanvasConfiguration>().apply {
    device = map(input.device)
    format = map(input.format.value)
    usage = map(input.usage.toFlagInt())
    viewFormats = input.viewFormats.mapJsArray {
        val output: JsString = map(it.value)
        output as JsObject
    }
    colorSpace = map(input.colorSpace.value)
    alphaMode = map(input.alphaMode.value)
}

