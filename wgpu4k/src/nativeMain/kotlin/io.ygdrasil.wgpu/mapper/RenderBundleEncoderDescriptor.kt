@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import kotlinx.cinterop.*
import webgpu.*

fun Arena.map(input: RenderBundleEncoderDescriptor): MemorySegment =
    WGPURenderBundleEncoderDescriptor.allocate(this).also { renderBundleEncoderDescriptor ->
        if (input.label != null) WGPURenderBundleEncoderDescriptor.label(
            renderBundleEncoderDescriptor,
            allocateFrom(input.label)
        )

        if (input.colorFormats.isNotEmpty()) {
            WGPURenderBundleEncoderDescriptor.colorFormatCount(
                renderBundleEncoderDescriptor,
                input.colorFormats.size.toLong()
            )
            val colorFormats = allocate(ValueLayout.JAVA_INT, input.colorFormats.size.toLong())
            println("color formats $colorFormats")

            input.colorFormats.forEachIndexed { index, colorAttachment ->
                colorFormats.setAtIndex(ValueLayout.JAVA_INT, index.toLong(), colorAttachment.value)
            }

            WGPURenderBundleEncoderDescriptor.colorFormats(renderBundleEncoderDescriptor, colorFormats)
        }

        WGPURenderBundleEncoderDescriptor.depthStencilFormat(
            renderBundleEncoderDescriptor,
            input.depthStencilFormat.value
        )
        WGPURenderBundleEncoderDescriptor.sampleCount(renderBundleEncoderDescriptor, input.sampleCount)
        WGPURenderBundleEncoderDescriptor.depthReadOnly(renderBundleEncoderDescriptor, input.depthReadOnly.toInt())
        WGPURenderBundleEncoderDescriptor.stencilReadOnly(renderBundleEncoderDescriptor, input.stencilReadOnly.toInt())
    }