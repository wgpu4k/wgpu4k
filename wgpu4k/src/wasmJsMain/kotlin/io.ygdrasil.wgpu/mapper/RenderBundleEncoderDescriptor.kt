package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.webgpu.internal.js.GPURenderBundleEncoderDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray

internal fun map(input: RenderBundleEncoderDescriptor): GPURenderBundleEncoderDescriptor =
    createJsObject<GPURenderBundleEncoderDescriptor>().apply {
        depthReadOnly = input.depthReadOnly
        stencilReadOnly = input.stencilReadOnly
        colorFormats = input.colorFormats.mapJsArray { it.value.toJsString() }
        depthStencilFormat = input.depthStencilFormat.value
        sampleCount = input.sampleCount
        if (input.label != null) label = input.label.toJsString()
    }