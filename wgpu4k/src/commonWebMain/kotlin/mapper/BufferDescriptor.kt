@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUBufferDescriptor
import io.ygdrasil.webgpu.WGPUBufferDescriptor
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.toFlagInt
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: GPUBufferDescriptor): WGPUBufferDescriptor = createJsObject<WGPUBufferDescriptor>().apply {
    label = input.label
    size = input.size.asJsNumber()
    usage = input.usage.toFlagInt().asJsNumber()
    mappedAtCreation = input.mappedAtCreation
}
