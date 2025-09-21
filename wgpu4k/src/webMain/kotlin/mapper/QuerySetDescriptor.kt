@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUQuerySetDescriptor
import io.ygdrasil.webgpu.WGPUQuerySetDescriptor
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: GPUQuerySetDescriptor): WGPUQuerySetDescriptor = createJsObject<WGPUQuerySetDescriptor>().apply {
    label = input.label
    type = input.type.value
    count = input.count.asJsNumber()
}