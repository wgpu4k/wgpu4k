package io.ygdrasil.webgpu.mapper

import ffi.ArrayHolder
import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPURenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.WGPURenderBundleEncoderDescriptor

fun MemoryAllocator.map(input: GPURenderBundleEncoderDescriptor): WGPURenderBundleEncoderDescriptor =
    WGPURenderBundleEncoderDescriptor.allocate(this).also { output ->
        map(input.label, output.label)

        if (input.colorFormats.isNotEmpty()) {
            output.colorFormatCount = input.colorFormats.size.toULong()

            output.colorFormats = allocateBuffer(Int.SIZE_BYTES.toULong() * output.colorFormatCount)
                .also { it.writeUInts(input.colorFormats.map { it.value }.toUIntArray()) }
                .let { ArrayHolder(it.handler) }
        }

        input.depthStencilFormat?.let { output.depthStencilFormat = it.value }
        output.sampleCount = input.sampleCount
        output.depthReadOnly = input.depthReadOnly
        output.stencilReadOnly = input.stencilReadOnly
    }