package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleDescriptor
import io.ygdrasil.wgpu.internal.js.GPURenderBundleDescriptor

fun map(input: RenderBundleDescriptor): GPURenderBundleDescriptor = object : GPURenderBundleDescriptor{
    override var label: String? = input.label ?: undefined
}