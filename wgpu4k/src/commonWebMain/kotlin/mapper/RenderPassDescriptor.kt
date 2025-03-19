package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPURenderPassColorAttachment
import io.ygdrasil.webgpu.GPURenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.GPURenderPassDescriptor
import io.ygdrasil.webgpu.TextureView
import io.ygdrasil.webgpu.WGPURenderPassColorAttachment
import io.ygdrasil.webgpu.WGPURenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.WGPURenderPassDescriptor
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPURenderPassDescriptor): WGPURenderPassDescriptor =
    createJsObject<WGPURenderPassDescriptor>().apply {
        label = input.label
        colorAttachments = input.colorAttachments.map { map(it) }.toTypedArray()
        if (input.depthStencilAttachment != null) depthStencilAttachment = map(input.depthStencilAttachment)
        // TODO map this occlusionQuerySet
        // TODO map this timestampWrites
        maxDrawCount = input.maxDrawCount
    }

private fun map(input: GPURenderPassDepthStencilAttachment): WGPURenderPassDepthStencilAttachment =
    createJsObject<WGPURenderPassDepthStencilAttachment>().apply {
        view = (input.view as TextureView).handler
        if (input.depthClearValue != null) depthClearValue = input.depthClearValue
        if (input.depthLoadOp != null) depthLoadOp = input.depthLoadOp.value
        if (input.depthStoreOp != null) depthStoreOp = input.depthStoreOp.value
        depthReadOnly = input.depthReadOnly
        stencilClearValue = input.stencilClearValue
        if (input.stencilLoadOp != null) stencilLoadOp = input.stencilLoadOp.value
        if (input.stencilStoreOp != null) stencilStoreOp = input.stencilStoreOp.value
        stencilReadOnly = input.stencilReadOnly
    }

private fun map(input: GPURenderPassColorAttachment): WGPURenderPassColorAttachment =
    createJsObject<WGPURenderPassColorAttachment>().apply {
        view = (input.view as TextureView).handler
        loadOp = input.loadOp.value
        storeOp = input.storeOp.value
        if (input.depthSlice != null) depthSlice = input.depthSlice
        if (input.resolveTarget != null) resolveTarget = (input.resolveTarget as TextureView).handler
        clearValue = map(input.clearValue)
    }

