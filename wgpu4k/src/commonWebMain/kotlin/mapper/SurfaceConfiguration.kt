package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.Device
import io.ygdrasil.webgpu.SurfaceConfiguration
import io.ygdrasil.webgpu.WGPUCanvasConfiguration
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.asJsString
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray
import io.ygdrasil.webgpu.toFlagInt

fun map(input: SurfaceConfiguration) : WGPUCanvasConfiguration = createJsObject<WGPUCanvasConfiguration>().apply {
    device = (input.device as Device).handler
    format = input.format.value
    usage = input.usage.toFlagInt().asJsNumber()
    viewFormats = input.viewFormats.mapJsArray {
        it.value.asJsString()
    }
    colorSpace = input.colorSpace.value.asJsString()
    alphaMode = input.alphaMode.value.asJsString()
}

