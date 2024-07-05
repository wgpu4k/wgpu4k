package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUSize32
import io.ygdrasil.wgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.internal.js.GPURenderBundleEncoderDescriptor

internal fun map(input: RenderBundleEncoderDescriptor): GPURenderBundleEncoderDescriptor = object : GPURenderBundleEncoderDescriptor{
    override var depthReadOnly: Boolean? = input.depthReadOnly
    override var stencilReadOnly: Boolean? = input.stencilReadOnly
    override var colorFormats: Array<String> = input.colorFormats.map { it.actualName }.toTypedArray()
    override var depthStencilFormat: String? = input.depthStencilFormat.actualName
    override var sampleCount: GPUSize32? = input.sampleCount
    override var label: String? = input.label ?: undefined
}