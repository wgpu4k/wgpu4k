@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.AdapterInfo
import io.ygdrasil.webgpu.GPUAdapterInfo
import io.ygdrasil.webgpu.WGPUAdapterInfo
import io.ygdrasil.webgpu.asUInt
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: WGPUAdapterInfo): GPUAdapterInfo = AdapterInfo(
    architecture = input.architecture,
    vendor = input.vendor,
    device = input.device,
    description = input.description,
    subgroupMinSize = input.subgroupMinSize.asUInt(),
    subgroupMaxSize = input.subgroupMaxSize.asUInt(),
    isFallbackAdapter = input.isFallbackAdapter
)