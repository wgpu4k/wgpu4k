package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.QuerySetDescriptor
import io.ygdrasil.wgpu.internal.js.GPUQuerySetDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: QuerySetDescriptor): GPUQuerySetDescriptor = createJsObject<GPUQuerySetDescriptor>().apply {
    if (input.label != null) label = input.label
    type = input.type.name
    count = input.count
}