package io.ygdrasil.webgpu.mapper

import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.webgpu.GPURenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.WGPURenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.toUInt

fun MemoryAllocator.map(input: GPURenderBundleEncoderDescriptor): WGPURenderBundleEncoderDescriptor =
    WGPURenderBundleEncoderDescriptor.allocate(this).also { output ->
        map(input.label, output.label)

        if (input.colorFormats.isNotEmpty()) {
            output.colorFormatCount = input.colorFormats.size.toULong()

            output.colorFormats = allocateBuffer(Int.SIZE_BYTES.toULong() * output.colorFormatCount)
                .also { it.writeUInts(input.colorFormats.map { it.value }.toUIntArray()) }
                .handler
        }

        input.depthStencilFormat?.let { output.depthStencilFormat = it.value }
        output.sampleCount = input.sampleCount
        output.depthReadOnly = input.depthReadOnly.toUInt()
        output.stencilReadOnly = input.stencilReadOnly.toUInt()
    }
