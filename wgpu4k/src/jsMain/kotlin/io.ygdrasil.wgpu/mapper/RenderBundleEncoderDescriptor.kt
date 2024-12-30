package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.webgpu.internal.js.GPURenderBundleEncoderDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: RenderBundleEncoderDescriptor): GPURenderBundleEncoderDescriptor =
    createJsObject<GPURenderBundleEncoderDescriptor>().apply {
        depthReadOnly = input.depthReadOnly
        stencilReadOnly = input.stencilReadOnly
        colorFormats = input.colorFormats.map { it.actualName }.toTypedArray()
        depthStencilFormat = input.depthStencilFormat.actualName
        sampleCount = input.sampleCount
        if (input.label != null) label = input.label
    }