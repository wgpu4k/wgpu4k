package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.internal.js.GPURenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject
import io.ygdrasil.wgpu.internal.js.mapJsArray

internal fun map(input: RenderBundleEncoderDescriptor): GPURenderBundleEncoderDescriptor = createJsObject<GPURenderBundleEncoderDescriptor>().apply{
    depthReadOnly = input.depthReadOnly
    stencilReadOnly = input.stencilReadOnly
    colorFormats = input.colorFormats.mapJsArray { it.actualName.toJsString() }
    depthStencilFormat = input.depthStencilFormat.actualName
    sampleCount = input.sampleCount
    if (input.label != null) label = input.label.toJsString()
}