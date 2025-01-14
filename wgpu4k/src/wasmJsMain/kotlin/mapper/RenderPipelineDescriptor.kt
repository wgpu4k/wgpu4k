package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.internal.js.GPUBlendComponent
import io.ygdrasil.webgpu.internal.js.GPUBlendState
import io.ygdrasil.webgpu.internal.js.GPUColorTargetState
import io.ygdrasil.webgpu.internal.js.GPUDepthStencilState
import io.ygdrasil.webgpu.internal.js.GPUFragmentState
import io.ygdrasil.webgpu.internal.js.GPUMultisampleState
import io.ygdrasil.webgpu.internal.js.GPUPrimitiveState
import io.ygdrasil.webgpu.internal.js.GPURenderPipelineDescriptor
import io.ygdrasil.webgpu.internal.js.GPUStencilFaceState
import io.ygdrasil.webgpu.internal.js.GPUVertexAttribute
import io.ygdrasil.webgpu.internal.js.GPUVertexBufferLayout
import io.ygdrasil.webgpu.internal.js.GPUVertexState
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray
import io.ygdrasil.webgpu.internal.js.toJsNumber

internal fun map(input: RenderPipelineDescriptor): GPURenderPipelineDescriptor =
    createJsObject<GPURenderPipelineDescriptor>().apply {
        vertex = map(input.vertex)
        layout = input.layout?.handler ?: "auto".toJsString()
        label = input.label?.toJsString()
        primitive = map(input.primitive)
        if (input.depthStencil != null) depthStencil = map(input.depthStencil)
        if (input.fragment != null) fragment = map(input.fragment)
        multisample = map(input.multisample)
    }

private fun map(input: RenderPipelineDescriptor.VertexState): GPUVertexState = createJsObject<GPUVertexState>().apply {
    module = input.module.handler
    entryPoint = input.entryPoint.toJsString()

    // TODO map this
    //constants = null
    buffers = input.buffers.mapJsArray { map(it) }
}

private fun map(input: RenderPipelineDescriptor.VertexState.VertexBufferLayout): GPUVertexBufferLayout =
    createJsObject<GPUVertexBufferLayout>().apply {
        arrayStride = input.arrayStride.toJsNumber()
        attributes = input.attributes.mapJsArray { map(it) }
        stepMode = input.stepMode.value
    }

private fun map(input: RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute): GPUVertexAttribute =
    createJsObject<GPUVertexAttribute>().apply {
        format = input.format.value
        offset = input.offset.toJsNumber()
        shaderLocation = input.shaderLocation.toJsNumber()
    }

private fun map(input: RenderPipelineDescriptor.PrimitiveState): GPUPrimitiveState =
    createJsObject<GPUPrimitiveState>().apply {
        topology = input.topology.value
        if (input.stripIndexFormat?.value != null) stripIndexFormat = input.stripIndexFormat.value
        frontFace = input.frontFace.value
        cullMode = input.cullMode.value
        unclippedDepth = input.unclippedDepth
    }

private fun map(input: RenderPipelineDescriptor.DepthStencilState): GPUDepthStencilState =
    createJsObject<GPUDepthStencilState>().apply {
        format = input.format.value.toJsString()
        if (input.depthWriteEnabled != null) depthWriteEnabled = input.depthWriteEnabled
        if (input.depthCompare?.value != null) depthCompare = input.depthCompare.value.toJsString()
        stencilFront = map(input.stencilFront)
        stencilBack = map(input.stencilBack)
        stencilReadMask = input.stencilReadMask.toJsNumber()
        stencilWriteMask = input.stencilWriteMask.toJsNumber()
        depthBias = input.depthBias.toJsNumber()
        depthBiasSlopeScale = input.depthBiasSlopeScale
        depthBiasClamp = input.depthBiasClamp
    }

private fun map(input: RenderPipelineDescriptor.DepthStencilState.StencilFaceState): GPUStencilFaceState =
    createJsObject<GPUStencilFaceState>().apply {
        compare = input.compare.value.toJsString()
        failOp = input.failOp.value.toJsString()
        depthFailOp = input.depthFailOp.value.toJsString()
        passOp = input.passOp.value.toJsString()
    }

private fun map(input: RenderPipelineDescriptor.MultisampleState): GPUMultisampleState =
    createJsObject<GPUMultisampleState>().apply {
        count = input.count.toJsNumber()
        mask = input.mask.toJsNumber()
        alphaToCoverageEnabled = input.alphaToCoverageEnabled
    }

private fun map(input: RenderPipelineDescriptor.FragmentState): GPUFragmentState =
    createJsObject<GPUFragmentState>().apply {
        targets = input.targets.mapJsArray { map(it) }
        module = input.module.handler
        entryPoint = input.entryPoint.toJsString()

        // TODO not sure how to map this
        //constants
    }

private fun map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState): GPUColorTargetState =
    createJsObject<GPUColorTargetState>().apply {
        format = input.format.value.toJsString()
        blend = map(input.blend)
        writeMask = input.writeMask.value
    }

private fun map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState): GPUBlendState =
    createJsObject<GPUBlendState>().apply {
        color = map(input.color)
        alpha = map(input.alpha)
    }

private fun map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent): GPUBlendComponent =
    createJsObject<GPUBlendComponent>().apply {
        operation = input.operation.value.toJsString()
        srcFactor = input.srcFactor.value.toJsString()
        dstFactor = input.dstFactor.value.toJsString()
    }
