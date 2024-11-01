package io.ygdrasil.wgpu.mapper

import ffi.ArrayHolder
import ffi.MemoryAllocator
import io.ygdrasil.wgpu.RenderBundleEncoderDescriptor
import webgpu.WGPURenderBundleEncoderDescriptor

fun MemoryAllocator.map(input: RenderBundleEncoderDescriptor): WGPURenderBundleEncoderDescriptor =
    WGPURenderBundleEncoderDescriptor.allocate(this).also { output ->
        if (input.label != null) map(input.label, output.label)

        if (input.colorFormats.isNotEmpty()) {
            output.colorFormatCount = input.colorFormats.size.toULong()

            output.colorFormats = allocateBuffer(Int.SIZE_BYTES * output.colorFormatCount)
                .also { it.writeInts(input.colorFormats.map { it.value }.toIntArray()) }
                .let { ArrayHolder(it.handler) }
        }

        output.depthStencilFormat = input.depthStencilFormat.uValue
        output.sampleCount = input.sampleCount
        output.depthReadOnly = input.depthReadOnly
        output.stencilReadOnly = input.stencilReadOnly
}