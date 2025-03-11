package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPURenderPassColorAttachment
import io.ygdrasil.webgpu.GPURenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.GPURenderPassDescriptor
import io.ygdrasil.webgpu.TextureView
import io.ygdrasil.wgpu.WGPURenderPassColorAttachment
import io.ygdrasil.wgpu.WGPURenderPassDepthStencilAttachment
import io.ygdrasil.wgpu.WGPURenderPassDescriptor

internal fun MemoryAllocator.map(input: GPURenderPassDescriptor): WGPURenderPassDescriptor =
    WGPURenderPassDescriptor.allocate(this).also { output ->
        println("render pass descriptor $output")
        map(input.label, output.label)

        if (input.colorAttachments.isNotEmpty()) {
            output.colorAttachmentCount = input.colorAttachments.size.toULong()
            output.colorAttachments = WGPURenderPassColorAttachment.allocateArray(
                this,
                output.colorAttachmentCount.toUInt()
            ) { index, value ->
                map(input.colorAttachments[index.toInt()], value)
            }
        }

        input.depthStencilAttachment?.let { output.depthStencilAttachment = map(it) }
        //TODO map this var occlusionQuerySet: GPUQuerySet?
        //TODO map this var timestampWrites: GPURenderPassTimestampWrites?
        //TODO map this var maxDrawCount: GPUSize64
        // check WGPURenderPassDescriptorMaxDrawCount
    }

internal fun MemoryAllocator.map(input: GPURenderPassColorAttachment, output: WGPURenderPassColorAttachment) {
    println("color attachment $output")
    output.view = (input.view as TextureView).handler
    output.loadOp = input.loadOp.value
    output.storeOp = input.storeOp.value
    // TODO find how to map this
    //if (input.depthSlice != null) WGPURenderPassColorAttachment.depthSlice = input.depthSlice)
    if (input.resolveTarget != null) output.resolveTarget = (input.resolveTarget as TextureView).handler
    input.clearValue?.let { map(it, output.clearValue) }
}

internal fun MemoryAllocator.map(input: GPURenderPassDepthStencilAttachment): WGPURenderPassDepthStencilAttachment =
    WGPURenderPassDepthStencilAttachment.allocate(this).also { output ->
        output.view = (input.view as TextureView).handler
        input.depthClearValue?.let { output.depthClearValue = it }

        input.depthLoadOp?.let { output.depthLoadOp = it.value }
        input.depthStoreOp?.let { output.depthStoreOp = it.value }
        output.depthReadOnly = input.depthReadOnly
        output.stencilClearValue = input.stencilClearValue
        input.stencilLoadOp?.let { output.stencilLoadOp = it.value }
        input.stencilStoreOp?.let { output.stencilStoreOp = it.value }
        output.stencilReadOnly = input.stencilReadOnly
    }
