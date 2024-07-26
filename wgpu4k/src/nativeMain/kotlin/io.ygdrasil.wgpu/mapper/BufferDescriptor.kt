@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.internal.toUInt
import io.ygdrasil.wgpu.toFlagUInt
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.WGPUBufferDescriptor

internal fun ArenaBase.map(input: BufferDescriptor) = alloc<WGPUBufferDescriptor> {
    size = input.size.toULong()
    usage = input.usage.toFlagUInt()
    mappedAtCreation = input.mappedAtCreation.toUInt()
}

