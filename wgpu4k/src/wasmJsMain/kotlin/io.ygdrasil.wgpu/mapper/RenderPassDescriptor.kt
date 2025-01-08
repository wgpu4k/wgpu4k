package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.internal.js.GPURenderPassColorAttachment
import io.ygdrasil.webgpu.internal.js.GPURenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.internal.js.GPURenderPassDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray
import io.ygdrasil.webgpu.internal.js.toJsNumber

internal fun map(input: RenderPassDescriptor): GPURenderPassDescriptor =
    createJsObject<GPURenderPassDescriptor>().apply {
        colorAttachments = input.colorAttachments.mapJsArray { map(it) }
        if (input.label != null) label = input.label.toJsString()
        if (input.depthStencilAttachment != null) depthStencilAttachment = map(input.depthStencilAttachment)
        // TODO map this occlusionQuerySet
        // TODO map this timestampWrites
        maxDrawCount = input.maxDrawCount.toJsNumber()
    }

private fun map(input: RenderPassDescriptor.DepthStencilAttachment): GPURenderPassDepthStencilAttachment =
    createJsObject<GPURenderPassDepthStencilAttachment>().apply {
        view = input.view.handler
        if (input.depthClearValue != null) depthClearValue = input.depthClearValue
        if (input.depthLoadOp != null) depthLoadOp = input.depthLoadOp.name
        if (input.depthStoreOp != null) depthStoreOp = input.depthStoreOp.name
        depthReadOnly = input.depthReadOnly
        stencilClearValue = input.stencilClearValue.toJsNumber()
        if (input.stencilLoadOp != null) stencilLoadOp = input.stencilLoadOp.name
        if (input.stencilStoreOp != null) stencilStoreOp = input.stencilStoreOp.name
        stencilReadOnly = input.stencilReadOnly
    }

private fun map(input: RenderPassDescriptor.ColorAttachment): GPURenderPassColorAttachment =
    createJsObject<GPURenderPassColorAttachment>().apply {
        view = input.view.handler
        loadOp = input.loadOp.name
        storeOp = input.storeOp.name
        if (input.depthSlice != null) depthSlice = input.depthSlice.toJsNumber()
        if (input.resolveTarget != null) resolveTarget = input.resolveTarget.handler
        clearValue = map(input.clearValue)
    }

