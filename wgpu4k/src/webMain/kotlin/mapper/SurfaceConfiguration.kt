@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.Device
import io.ygdrasil.webgpu.SurfaceConfiguration
import io.ygdrasil.webgpu.WGPUCanvasConfiguration
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray
import io.ygdrasil.webgpu.toFlagInt
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.toJsString

fun map(input: SurfaceConfiguration) : WGPUCanvasConfiguration = createJsObject<WGPUCanvasConfiguration>().apply {
    device = (input.device as Device).handler
    format = input.format.value
    usage = input.usage.toFlagInt().asJsNumber()
    viewFormats = input.viewFormats.mapJsArray {
        it.value.toJsString()
    }
    colorSpace = input.colorSpace.value.toJsString()
    alphaMode = input.alphaMode.value.toJsString()
}

