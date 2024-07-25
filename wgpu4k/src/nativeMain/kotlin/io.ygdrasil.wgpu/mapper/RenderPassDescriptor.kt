@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Color
import io.ygdrasil.wgpu.RenderPassDescriptor
import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: RenderPassDescriptor) =
    alloc<WGPURenderPassDescriptor>().also { output ->
        println("render pass descriptor $output")
        if (input.label != null) output.label = input.label.cstr.getPointer(this)

        if (input.colorAttachments.isNotEmpty()) {
            WGPURenderPassDescriptor.colorAttachmentCount(output, input.colorAttachments.size.toLong())
            val colorAttachments =
                WGPURenderPassColorAttachment.allocateArray(input.colorAttachments.size.toLong(), this)
            println("color attachments $colorAttachments")

            input.colorAttachments.forEachIndexed { index, colorAttachment ->
                map(colorAttachment, WGPURenderPassColorAttachment.asSlice(colorAttachments, index.toLong()))
            }

            WGPURenderPassDescriptor.colorAttachments(output, colorAttachments)

        }

        if (input.depthStencilAttachment != null) WGPURenderPassDescriptor.depthStencilAttachment(
            output,
            map(input.depthStencilAttachment)
        )
        //TODO map this var occlusionQuerySet: GPUQuerySet?
        //TODO map this var timestampWrites: GPURenderPassTimestampWrites?
        //TODO map this var maxDrawCount: GPUSize64
        // check WGPURenderPassDescriptorMaxDrawCount
    }

internal fun Arena.map(input: RenderPassDescriptor.ColorAttachment, output: WGPURenderPassColorAttachment) {
    println("color attachment $output")
    WGPURenderPassColorAttachment.view(output, input.view.handler)
    WGPURenderPassColorAttachment.loadOp(output, input.loadOp.value)
    WGPURenderPassColorAttachment.storeOp(output, input.storeOp.value)
    // TODO find how to map this
    //if (input.depthSlice != null) WGPURenderPassColorAttachment.depthSlice(output, input.depthSlice)
    if (input.resolveTarget != null) WGPURenderPassColorAttachment.resolveTarget(output, input.resolveTarget.handler)
    map(input.clearValue, WGPURenderPassColorAttachment.clearValue(output))
}

internal fun Arena.map(input: Color, output: WGPUColor) {
    output.r = input.red
    output.g = input.green
    output.b = input.blue
    output.a = input.alpha
}


internal fun Arena.map(input: RenderPassDescriptor.RenderPassDepthStencilAttachment) =
    alloc<WGPURenderPassDepthStencilAttachment>().also { depthStencilAttachment ->
        WGPURenderPassDepthStencilAttachment.view(depthStencilAttachment, input.view.handler)
        if (input.depthClearValue != null) WGPURenderPassDepthStencilAttachment.depthClearValue(
            depthStencilAttachment,
            input.depthClearValue
        )
        if (input.depthLoadOp != null) WGPURenderPassDepthStencilAttachment.depthLoadOp(
            depthStencilAttachment,
            input.depthLoadOp.value
        )
        if (input.depthStoreOp != null) WGPURenderPassDepthStencilAttachment.depthStoreOp(
            depthStencilAttachment,
            input.depthStoreOp.value
        )
        WGPURenderPassDepthStencilAttachment.depthReadOnly(depthStencilAttachment, input.depthReadOnly.toInt())
        WGPURenderPassDepthStencilAttachment.stencilClearValue(depthStencilAttachment, input.stencilClearValue.toInt())
        if (input.stencilLoadOp != null) WGPURenderPassDepthStencilAttachment.stencilLoadOp(
            depthStencilAttachment,
            input.stencilLoadOp.value
        )
        if (input.stencilStoreOp != null) WGPURenderPassDepthStencilAttachment.stencilStoreOp(
            depthStencilAttachment,
            input.stencilStoreOp.value
        )
        WGPURenderPassDepthStencilAttachment.stencilReadOnly(depthStencilAttachment, input.stencilReadOnly.toInt())
    }
