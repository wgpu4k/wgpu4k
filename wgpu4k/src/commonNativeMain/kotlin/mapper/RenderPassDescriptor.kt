package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.RenderPassDescriptor
import webgpu.WGPURenderPassColorAttachment
import webgpu.WGPURenderPassDepthStencilAttachment
import webgpu.WGPURenderPassDescriptor

internal fun MemoryAllocator.map(input: RenderPassDescriptor): WGPURenderPassDescriptor =
    WGPURenderPassDescriptor.allocate(this).also { output ->
        println("render pass descriptor $output")
        if (input.label != null) map(input.label, output.label)

        if (input.colorAttachments.isNotEmpty()) {
            output.colorAttachmentCount = input.colorAttachments.size.toULong()
            output.colorAttachments = WGPURenderPassColorAttachment.allocateArray(
                this,
                output.colorAttachmentCount.toUInt()
            ) { index, value ->
                map(input.colorAttachments[index.toInt()], value)
            }
        }

        if (input.depthStencilAttachment != null) output.depthStencilAttachment = map(input.depthStencilAttachment)
        //TODO map this var occlusionQuerySet: GPUQuerySet?
        //TODO map this var timestampWrites: GPURenderPassTimestampWrites?
        //TODO map this var maxDrawCount: GPUSize64
        // check WGPURenderPassDescriptorMaxDrawCount
    }

internal fun MemoryAllocator.map(input: RenderPassDescriptor.ColorAttachment, output: WGPURenderPassColorAttachment) {
    println("color attachment $output")
    output.view = input.view.handler
    output.loadOp = input.loadOp.value
    output.storeOp = input.storeOp.value
    // TODO find how to map this
    //if (input.depthSlice != null) WGPURenderPassColorAttachment.depthSlice = input.depthSlice)
    if (input.resolveTarget != null) output.resolveTarget = input.resolveTarget.handler
    map(input.clearValue, output.clearValue)
}

internal fun MemoryAllocator.map(input: RenderPassDescriptor.DepthStencilAttachment): WGPURenderPassDepthStencilAttachment =
    WGPURenderPassDepthStencilAttachment.allocate(this).also { output ->
        output.view = input.view.handler
        if (input.depthClearValue != null) output.depthClearValue = input.depthClearValue

        if (input.depthLoadOp != null) output.depthLoadOp = input.depthLoadOp.value
        if (input.depthStoreOp != null) output.depthStoreOp = input.depthStoreOp.value
        output.depthReadOnly = input.depthReadOnly
        output.stencilClearValue = input.stencilClearValue
        if (input.stencilLoadOp != null) output.stencilLoadOp = input.stencilLoadOp.value
        if (input.stencilStoreOp != null) output.stencilStoreOp = input.stencilStoreOp.value
        output.stencilReadOnly = input.stencilReadOnly
    }
