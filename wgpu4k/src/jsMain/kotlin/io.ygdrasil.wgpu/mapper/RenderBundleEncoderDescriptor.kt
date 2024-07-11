package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.internal.js.GPURenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: RenderBundleEncoderDescriptor): GPURenderBundleEncoderDescriptor = createJsObject<GPURenderBundleEncoderDescriptor>().apply{
    depthReadOnly = input.depthReadOnly
    stencilReadOnly = input.stencilReadOnly
    colorFormats = input.colorFormats.map { it.actualName }.toTypedArray()
    depthStencilFormat = input.depthStencilFormat.actualName
    sampleCount = input.sampleCount
    if (input.label != null) label = input.label
}