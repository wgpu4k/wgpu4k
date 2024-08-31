package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Color
import io.ygdrasil.wgpu.RenderPassDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUColor
import io.ygdrasil.wgpu.internal.jna.WGPURenderPassColorAttachment
import io.ygdrasil.wgpu.internal.jna.WGPURenderPassDepthStencilAttachment
import io.ygdrasil.wgpu.internal.jna.WGPURenderPassDescriptor
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.toInt
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: RenderPassDescriptor) =
    WGPURenderPassDescriptor.allocate(this).also { renderPassDescriptor ->
        println("render pass descriptor $renderPassDescriptor")
        if (input.label != null) WGPURenderPassDescriptor.label(renderPassDescriptor, allocateFrom(input.label))

        if (input.colorAttachments.isNotEmpty()) {
            WGPURenderPassDescriptor.colorAttachmentCount(renderPassDescriptor, input.colorAttachments.size.toLong())
            val colorAttachments =
                    WGPURenderPassColorAttachment.allocateArray(input.colorAttachments.size.toLong(), this)
            println("color attachments $colorAttachments")

            input.colorAttachments.forEachIndexed { index, colorAttachment ->
                map(colorAttachment, WGPURenderPassColorAttachment.asSlice(colorAttachments, index.toLong()))
            }

            WGPURenderPassDescriptor.colorAttachments(renderPassDescriptor, colorAttachments)

        }

        if (input.depthStencilAttachment != null) WGPURenderPassDescriptor.depthStencilAttachment(
            renderPassDescriptor,
            map(input.depthStencilAttachment)
        )
        //TODO map this var occlusionQuerySet: GPUQuerySet?
        //TODO map this var timestampWrites: GPURenderPassTimestampWrites?
        //TODO map this var maxDrawCount: GPUSize64
        // check WGPURenderPassDescriptorMaxDrawCount
    }.pointer.toAddress()

internal fun SegmentAllocator.map(input: RenderPassDescriptor.ColorAttachment, output: MemorySegment) {
    println("color attachment $output")
    WGPURenderPassColorAttachment.view(output, input.view.mhandler)
    WGPURenderPassColorAttachment.loadOp(output, input.loadOp.value)
    WGPURenderPassColorAttachment.storeOp(output, input.storeOp.value)
    // TODO find how to map this
    //if (input.depthSlice != null) WGPURenderPassColorAttachment.depthSlice(output, input.depthSlice)
    if (input.resolveTarget != null) WGPURenderPassColorAttachment.resolveTarget(output, input.resolveTarget.mhandler)
    map(input.clearValue, WGPURenderPassColorAttachment.clearValue(output))
}

internal fun SegmentAllocator.map(input: Color, output: MemorySegment) {
        WGPUColor.r(output, input.red)
        WGPUColor.g(output, input.green)
        WGPUColor.b(output, input.blue)
        WGPUColor.a(output, input.alpha)
    }


internal fun SegmentAllocator.map(input: RenderPassDescriptor.DepthStencilAttachment): MemorySegment =
    WGPURenderPassDepthStencilAttachment.allocate(this).also { depthStencilAttachment ->
        WGPURenderPassDepthStencilAttachment.view(depthStencilAttachment, input.view.mhandler)
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
