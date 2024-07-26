@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Color
import io.ygdrasil.wgpu.RenderPassDescriptor
import io.ygdrasil.wgpu.internal.toUInt
import kotlinx.cinterop.*
import webgpu.WGPUColor
import webgpu.WGPURenderPassColorAttachment
import webgpu.WGPURenderPassDepthStencilAttachment
import webgpu.WGPURenderPassDescriptor

internal fun Arena.map(input: RenderPassDescriptor) =
    alloc<WGPURenderPassDescriptor>().also { output ->
        println("render pass descriptor $output")
        if (input.label != null) output.label = input.label.cstr.getPointer(this)

        if (input.colorAttachments.isNotEmpty()) {
            output.colorAttachmentCount = input.colorAttachments.size.toULong()
            val colorAttachments = allocArray<WGPURenderPassColorAttachment>(input.colorAttachments.size)
            println("color attachments $colorAttachments")

            input.colorAttachments.forEachIndexed { index, colorAttachment ->
                map(colorAttachment, colorAttachments[index])
            }

            output.colorAttachments = colorAttachments

        }

        if (input.depthStencilAttachment != null) output.depthStencilAttachment = map(input.depthStencilAttachment).ptr
        //TODO map this var occlusionQuerySet: GPUQuerySet?
        //TODO map this var timestampWrites: GPURenderPassTimestampWrites?
        //TODO map this var maxDrawCount: GPUSize64
        // check WGPURenderPassDescriptorMaxDrawCount
    }

internal fun Arena.map(input: RenderPassDescriptor.ColorAttachment, output: WGPURenderPassColorAttachment) {
    println("color attachment $output")
    output.view = input.view.handler
    output.loadOp = input.loadOp.uValue
    output.storeOp = input.storeOp.uValue
    // TODO find how to map this
    //if (input.depthSlice != null) WGPURenderPassColorAttachment.depthSlice(output, input.depthSlice)
    if (input.resolveTarget != null) output.resolveTarget = input.resolveTarget.handler
    map(input.clearValue, output.clearValue)
}

internal fun Arena.map(input: Color, output: WGPUColor) {
    output.r = input.red
    output.g = input.green
    output.b = input.blue
    output.a = input.alpha
}


internal fun Arena.map(input: RenderPassDescriptor.RenderPassDepthStencilAttachment) =
    alloc<WGPURenderPassDepthStencilAttachment>().also { output ->
        output.view = input.view.handler
        if (input.depthClearValue != null) output.depthClearValue = input.depthClearValue
        if (input.depthLoadOp != null) output.depthLoadOp = input.depthLoadOp.uValue
        if (input.depthStoreOp != null) output.depthStoreOp = input.depthStoreOp.uValue

        output.depthReadOnly = input.depthReadOnly.toUInt()
        output.stencilClearValue = input.stencilClearValue.toUInt()
        if (input.stencilLoadOp != null) output.stencilLoadOp = input.stencilLoadOp.uValue
        if (input.stencilStoreOp != null) output.stencilStoreOp = input.stencilStoreOp.uValue

        output.stencilReadOnly = input.stencilReadOnly.toUInt()
    }
