package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.SurfaceConfiguration
import io.ygdrasil.wgpu.internal.js.GPUCanvasConfiguration
import io.ygdrasil.wgpu.internal.js.createJsObject
import io.ygdrasil.wgpu.internal.js.mapJsArray
import io.ygdrasil.wgpu.toFlagInt

internal fun map(input: SurfaceConfiguration): GPUCanvasConfiguration {
    return createJsObject<GPUCanvasConfiguration>().apply {
        device = input.device.handler
        format = input.format.actualName.toJsString()
        usage = input.usage.toFlagInt().toJsNumber()
        viewFormats = input.viewFormats.mapJsArray { it.actualName.toJsString() }
        colorSpace = input.colorSpace.name.toJsString()
        alphaMode = input.alphaMode.stringValue.toJsString()
    }
}

