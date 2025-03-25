package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPURenderPassColorAttachment
import io.ygdrasil.webgpu.GPURenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.GPURenderPassDescriptor
import io.ygdrasil.webgpu.TextureView
import io.ygdrasil.webgpu.WGPURenderPassColorAttachment
import io.ygdrasil.webgpu.WGPURenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.WGPURenderPassDescriptor
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray

internal fun map(input: GPURenderPassDescriptor): WGPURenderPassDescriptor =
    createJsObject<WGPURenderPassDescriptor>().apply {
        label = input.label
        colorAttachments = input.colorAttachments.mapJsArray { map(it) }
        input.depthStencilAttachment?.let { depthStencilAttachment = map(it) }
        // TODO map this occlusionQuerySet
        // TODO map this timestampWrites
        maxDrawCount = input.maxDrawCount.asJsNumber()
    }

private fun map(input: GPURenderPassDepthStencilAttachment): WGPURenderPassDepthStencilAttachment =
    createJsObject<WGPURenderPassDepthStencilAttachment>().apply {
        view = (input.view as TextureView).handler
        input.depthClearValue?.let { depthClearValue = it.asJsNumber() }
        input.depthLoadOp?.let { depthLoadOp = it.value }
        input.depthStoreOp?.let { depthStoreOp = it.value }
        depthReadOnly = input.depthReadOnly
        stencilClearValue = input.stencilClearValue.asJsNumber()
        input.stencilLoadOp?.let { stencilLoadOp = it.value }
        input.stencilStoreOp?.let { stencilStoreOp = it.value }
        stencilReadOnly = input.stencilReadOnly
    }

private fun map(input: GPURenderPassColorAttachment): WGPURenderPassColorAttachment =
    createJsObject<WGPURenderPassColorAttachment>().apply {
        view = (input.view as TextureView).handler
        loadOp = input.loadOp.value
        storeOp = input.storeOp.value
        input.depthSlice?.let { depthSlice = it.asJsNumber() }
        if (input.resolveTarget != null) resolveTarget = (input.resolveTarget as TextureView).handler
        input.clearValue?.let { clearValue = map(it) }
    }

