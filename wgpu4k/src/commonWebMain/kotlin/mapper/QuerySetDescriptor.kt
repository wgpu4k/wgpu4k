package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUQuerySetDescriptor
import io.ygdrasil.webgpu.WGPUQuerySetDescriptor
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUQuerySetDescriptor): WGPUQuerySetDescriptor = createJsObject<WGPUQuerySetDescriptor>().apply {
    label = input.label
    type = input.type.value
    count = input.count.asJsNumber()
}