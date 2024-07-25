@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: PipelineLayoutDescriptor): MemorySegment =
    WGPUPipelineLayoutDescriptor.allocate(this).also { output ->
        if (input.label != null) WGPUPipelineLayoutDescriptor.label(output, allocateFrom(input.label))
        if (input.bindGroupLayouts.isNotEmpty()) {
            WGPUPipelineLayoutDescriptor.bindGroupLayoutCount(output, input.bindGroupLayouts.size.toLong())
            WGPUPipelineLayoutDescriptor.bindGroupLayouts(
                output,
                input.bindGroupLayouts.map { it.handler }.toPointerArray(this)
            )
        }
    }

