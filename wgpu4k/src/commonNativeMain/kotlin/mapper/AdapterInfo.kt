package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.AdapterInfo
import io.ygdrasil.webgpu.GPUAdapterInfo
import io.ygdrasil.wgpu.WGPUAdapterInfo

internal fun map(input: WGPUAdapterInfo): GPUAdapterInfo = AdapterInfo(
    architecture = input.architecture.data?.toKString(input.architecture.length) ?: error("Failed to get architecture"),
    vendor = input.vendor.data?.toKString(input.vendor.length) ?: error("Failed to get vendor"),
    device = input.device.data?.toKString(input.device.length) ?: error("Failed to get device"),
    description = input.description.data?.toKString(input.description.length) ?: error("Failed to get description"),
    subgroupMinSize = 0u,
    subgroupMaxSize = 0u,
    isFallbackAdapter = false
)