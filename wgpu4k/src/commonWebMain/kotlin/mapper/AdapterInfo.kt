package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.AdapterInfo
import io.ygdrasil.webgpu.GPUAdapterInfo
import io.ygdrasil.webgpu.WGPUAdapterInfo
import io.ygdrasil.webgpu.asUInt

internal fun map(input: WGPUAdapterInfo): GPUAdapterInfo = AdapterInfo(
    architecture = input.architecture,
    vendor = input.vendor,
    device = input.device,
    description = input.description,
    subgroupMinSize = input.subgroupMinSize.asUInt(),
    subgroupMaxSize = input.subgroupMaxSize.asUInt(),
    isFallbackAdapter = input.isFallbackAdapter
)