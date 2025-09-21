@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUTexelCopyTextureInfo
import io.ygdrasil.webgpu.Texture
import io.ygdrasil.webgpu.WGPUTexelCopyTextureInfo
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: GPUTexelCopyTextureInfo): WGPUTexelCopyTextureInfo = createJsObject<WGPUTexelCopyTextureInfo>().apply {
    texture = (input.texture as Texture).handler
    mipLevel = input.mipLevel.asJsNumber()
    origin = map(input.origin)
    aspect = input.aspect.value
}