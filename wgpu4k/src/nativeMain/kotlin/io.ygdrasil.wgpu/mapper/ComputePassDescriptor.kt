@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ComputePassDescriptor
import kotlinx.cinterop.*
import webgpu.native.WGPUComputePassDescriptor
import webgpu.native.WGPUComputePassTimestampWrites

internal fun ArenaBase.map(input: ComputePassDescriptor) = alloc<WGPUComputePassDescriptor>().also { output ->
    if (input.label != null) output.label = input.label.cstr.getPointer(this)
    if (input.timestampWrites != null) output.timestampWrites = map(input.timestampWrites).ptr
}

private fun ArenaBase.map(input: ComputePassDescriptor.ComputePassTimestampWrites): WGPUComputePassTimestampWrites =
    alloc<WGPUComputePassTimestampWrites>().also { output ->
        output.querySet = input.querySet.handler
        if (input.beginningOfPassWriteIndex != null) output.beginningOfPassWriteIndex =
            input.beginningOfPassWriteIndex.toUInt()
        if (input.endOfPassWriteIndex != null) output.endOfPassWriteIndex = input.endOfPassWriteIndex.toUInt()
    }