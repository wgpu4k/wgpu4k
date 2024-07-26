@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.internal.toUInt
import kotlinx.cinterop.*
import webgpu.WGPURenderBundleEncoderDescriptor
import webgpu.WGPUTextureFormatVar

fun Arena.map(input: RenderBundleEncoderDescriptor) =
    alloc<WGPURenderBundleEncoderDescriptor>().also { output ->
        if (input.label != null) output.label = input.label.cstr.getPointer(this)

        if (input.colorFormats.isNotEmpty()) {
            output.colorFormatCount = input.colorFormats.size.toULong()

            val colorFormats = allocArray<WGPUTextureFormatVar>(input.colorFormats.size)
            println("color formats $colorFormats")

            input.colorFormats.forEachIndexed { index, colorAttachment ->
                colorFormats[index] = colorAttachment.value.toUInt()
            }

            output.colorFormats = colorFormats
        }

        output.depthStencilFormat = input.depthStencilFormat.value.toUInt()
        output.sampleCount = input.sampleCount.toUInt()
        output.depthReadOnly = input.depthReadOnly.toUInt()
        output.stencilReadOnly = input.stencilReadOnly.toUInt()
    }