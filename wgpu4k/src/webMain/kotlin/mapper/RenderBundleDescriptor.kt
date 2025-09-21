package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPURenderBundleDescriptor
import io.ygdrasil.webgpu.WGPURenderBundleDescriptor
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPURenderBundleDescriptor): WGPURenderBundleDescriptor =
    createJsObject<WGPURenderBundleDescriptor>().apply {
        label = input.label
    }