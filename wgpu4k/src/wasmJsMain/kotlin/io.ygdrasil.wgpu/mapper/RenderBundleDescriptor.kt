package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.RenderBundleDescriptor
import io.ygdrasil.webgpu.internal.js.GPURenderBundleDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: RenderBundleDescriptor): GPURenderBundleDescriptor =
    createJsObject<GPURenderBundleDescriptor>().apply {
        if (input.label != null) label = input.label.toJsString()
    }