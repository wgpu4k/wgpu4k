package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPURenderBundleEncoderDescriptor
import io.ygdrasil.webgpu.WGPURenderBundleEncoderDescriptor
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.asJsString
import io.ygdrasil.webgpu.castAs
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray

internal fun map(input: GPURenderBundleEncoderDescriptor): WGPURenderBundleEncoderDescriptor =
    createJsObject<WGPURenderBundleEncoderDescriptor>().apply {
        label = input.label
        depthReadOnly = input.depthReadOnly
        stencilReadOnly = input.stencilReadOnly
        colorFormats = input.colorFormats.mapJsArray { it.value.asJsString().castAs() }
        input.depthStencilFormat?.let { depthStencilFormat = it.value }
        sampleCount = input.sampleCount.asJsNumber()
    }