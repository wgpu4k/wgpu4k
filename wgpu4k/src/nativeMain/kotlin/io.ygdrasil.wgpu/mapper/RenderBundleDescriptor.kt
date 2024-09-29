@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleDescriptor
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import webgpu.native.WGPURenderBundleDescriptor

internal fun ArenaBase.map(input: RenderBundleDescriptor) =
    alloc<WGPURenderBundleDescriptor>().also { output ->
        if (input.label != null) output.label = input.label.cstr.getPointer(this)
    }