package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.QuerySetDescriptor
import io.ygdrasil.webgpu.internal.js.GPUQuerySetDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: QuerySetDescriptor): GPUQuerySetDescriptor = createJsObject<GPUQuerySetDescriptor>().apply {
    if (input.label != null) label = input.label
    type = input.type.name
    count = input.count
}