@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.AdapterInfo
import io.ygdrasil.webgpu.GPUAdapterInfo
import io.ygdrasil.webgpu.WGPUAdapterInfo
import kotlin.OptIn
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.toInt
import kotlin.toUInt

internal fun map(input: WGPUAdapterInfo): GPUAdapterInfo = AdapterInfo(
    architecture = input.architecture,
    vendor = input.vendor,
    device = input.device,
    description = input.description,
    subgroupMinSize = input.subgroupMinSize.toInt().toUInt(),
    subgroupMaxSize = input.subgroupMaxSize.toInt().toUInt(),
    isFallbackAdapter = input.isFallbackAdapter
)