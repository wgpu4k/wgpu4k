package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleDescriptor
import io.ygdrasil.wgpu.internal.js.GPURenderBundleDescriptor

internal fun map(input: RenderBundleDescriptor): GPURenderBundleDescriptor = object : GPURenderBundleDescriptor{
    override var label: String? = input.label ?: undefined
}