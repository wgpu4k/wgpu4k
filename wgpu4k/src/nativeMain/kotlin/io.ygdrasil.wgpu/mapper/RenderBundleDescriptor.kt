@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleDescriptor
import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import webgpu.WGPURenderBundleDescriptor

internal fun Arena.map(input: RenderBundleDescriptor) =
    alloc<WGPURenderBundleDescriptor>().also { output ->
        if (input.label != null) output.label = input.label.cstr.getPointer(this)
    }