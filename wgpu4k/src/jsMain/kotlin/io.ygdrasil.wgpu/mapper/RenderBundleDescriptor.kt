package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleDescriptor
import io.ygdrasil.wgpu.internal.js.GPURenderBundleDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: RenderBundleDescriptor): GPURenderBundleDescriptor =
    createJsObject<GPURenderBundleDescriptor>().apply {
        if (input.label != null) label = input.label
    }