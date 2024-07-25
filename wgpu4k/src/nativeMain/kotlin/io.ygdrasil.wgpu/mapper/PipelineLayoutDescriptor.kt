@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.PipelineLayoutDescriptor
import io.ygdrasil.wgpu.internal.toPointerArray
import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import webgpu.WGPUPipelineLayoutDescriptor

internal fun Arena.map(input: PipelineLayoutDescriptor) =
    alloc<WGPUPipelineLayoutDescriptor>().also { output ->
        if (input.label != null) output.label = input.label.cstr.getPointer(this)
        if (input.bindGroupLayouts.isNotEmpty()) {
            output.bindGroupLayoutCount = input.bindGroupLayouts.size.toULong()
            output.bindGroupLayouts = input.bindGroupLayouts.map { it.handler }.toPointerArray(this)
        }
    }



