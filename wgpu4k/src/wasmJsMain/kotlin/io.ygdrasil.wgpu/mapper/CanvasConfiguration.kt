package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.SurfaceConfiguration
import io.ygdrasil.webgpu.internal.js.GPUCanvasConfiguration
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray
import io.ygdrasil.webgpu.toFlagInt

internal fun map(input: SurfaceConfiguration): GPUCanvasConfiguration {
    return createJsObject<GPUCanvasConfiguration>().apply {
        device = input.device.handler
        format = input.format.value.toJsString()
        usage = input.usage.toFlagInt().toJsNumber()
        viewFormats = input.viewFormats.mapJsArray { it.value.toJsString() }
        colorSpace = input.colorSpace.value.toJsString()
        alphaMode = input.alphaMode.value.toJsString()
    }
}

