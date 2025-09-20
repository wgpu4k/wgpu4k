@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUTexelCopyBufferLayout
import io.ygdrasil.webgpu.WGPUTexelCopyBufferLayout
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: GPUTexelCopyBufferLayout): WGPUTexelCopyBufferLayout = createJsObject<WGPUTexelCopyBufferLayout>().apply {
    offset = input.offset.asJsNumber()
    input.bytesPerRow?.let { bytesPerRow = it.asJsNumber() }
    input.rowsPerImage?.let { rowsPerImage = it.asJsNumber() }
}