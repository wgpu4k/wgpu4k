package io.ygdrasil.webgpu.mapper

import ffi.ArrayHolder
import ffi.MemoryAllocator
import io.ygdrasil.webgpu.RenderBundleEncoderDescriptor
import webgpu.WGPURenderBundleEncoderDescriptor

fun MemoryAllocator.map(input: RenderBundleEncoderDescriptor): WGPURenderBundleEncoderDescriptor =
    WGPURenderBundleEncoderDescriptor.allocate(this).also { output ->
        if (input.label != null) map(input.label, output.label)

        if (input.colorFormats.isNotEmpty()) {
            output.colorFormatCount = input.colorFormats.size.toULong()

            output.colorFormats = allocateBuffer(Int.SIZE_BYTES.toULong() * output.colorFormatCount)
                .also { it.writeUInts(input.colorFormats.map { it.value }.toUIntArray()) }
                .let { ArrayHolder(it.handler) }
        }

        output.depthStencilFormat = input.depthStencilFormat.value
        output.sampleCount = input.sampleCount
        output.depthReadOnly = input.depthReadOnly
        output.stencilReadOnly = input.stencilReadOnly
    }