@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: RenderBundleDescriptor): MemorySegment =
    WGPURenderBundleDescriptor.allocate(this).also { output ->
        if (input.label != null) WGPURenderBundleDescriptor.label(output, allocateFrom(input.label))
    }