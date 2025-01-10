package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.internal.js.GPURenderPassColorAttachment
import io.ygdrasil.webgpu.internal.js.GPURenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.internal.js.GPURenderPassDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: RenderPassDescriptor): GPURenderPassDescriptor =
    createJsObject<GPURenderPassDescriptor>().apply {
        colorAttachments = input.colorAttachments.map { map(it) }.toTypedArray()
        if (input.label != null) label = input.label
        if (input.depthStencilAttachment != null) depthStencilAttachment = map(input.depthStencilAttachment)
        // TODO map this occlusionQuerySet
        // TODO map this timestampWrites
        maxDrawCount = input.maxDrawCount
    }

private fun map(input: RenderPassDescriptor.DepthStencilAttachment): GPURenderPassDepthStencilAttachment =
    createJsObject<GPURenderPassDepthStencilAttachment>().apply {
        view = input.view.handler
        if (input.depthClearValue != null) depthClearValue = input.depthClearValue
        if (input.depthLoadOp != null) depthLoadOp = input.depthLoadOp.value
        if (input.depthStoreOp != null) depthStoreOp = input.depthStoreOp.value
        depthReadOnly = input.depthReadOnly
        stencilClearValue = input.stencilClearValue
        if (input.stencilLoadOp != null) stencilLoadOp = input.stencilLoadOp.value
        if (input.stencilStoreOp != null) stencilStoreOp = input.stencilStoreOp.value
        stencilReadOnly = input.stencilReadOnly
    }

private fun map(input: RenderPassDescriptor.ColorAttachment): GPURenderPassColorAttachment =
    createJsObject<GPURenderPassColorAttachment>().apply {
        view = input.view.handler
        loadOp = input.loadOp.value
        storeOp = input.storeOp.value
        if (input.depthSlice != null) depthSlice = input.depthSlice
        if (input.resolveTarget != null) resolveTarget = input.resolveTarget.handler
        clearValue = map(input.clearValue)
    }

