package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.internal.js.*

internal fun map(input: RenderPipelineDescriptor): GPURenderPipelineDescriptor =
    createJsObject<GPURenderPipelineDescriptor>().apply {
        vertex = map(input.vertex)
        layout = input.layout?.handler ?: "auto"
        label = input.label ?: undefined
        primitive = map(input.primitive)
        depthStencil = input.depthStencil?.let { map(it) } ?: undefined
        fragment = input.fragment?.let { map(it) } ?: undefined
        multisample = map(input.multisample)
    }

private fun map(input: RenderPipelineDescriptor.VertexState): GPUVertexState = createJsObject<GPUVertexState>().apply {
    module = input.module.handler
    entryPoint = input.entryPoint

    // TODO map this
    constants = undefined
    buffers = input.buffers.map { map(it) }.toTypedArray()
}

private fun map(input: RenderPipelineDescriptor.VertexState.VertexBufferLayout): GPUVertexBufferLayout =
    createJsObject<GPUVertexBufferLayout>().apply {
        arrayStride = input.arrayStride
        attributes = input.attributes.map { map(it) }.toTypedArray()
        stepMode = input.stepMode.name
    }

private fun map(input: RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute): GPUVertexAttribute =
    createJsObject<GPUVertexAttribute>().apply {
        format = input.format.name
        offset = input.offset
        shaderLocation = input.shaderLocation
    }

private fun map(input: RenderPipelineDescriptor.PrimitiveState): GPUPrimitiveState =
    createJsObject<GPUPrimitiveState>().apply {
        topology = input.topology.stringValue
        stripIndexFormat = input.stripIndexFormat?.name ?: undefined
        frontFace = input.frontFace.name
        cullMode = input.cullMode.name
        unclippedDepth = input.unclippedDepth
    }

private fun map(input: RenderPipelineDescriptor.DepthStencilState): GPUDepthStencilState =
    createJsObject<GPUDepthStencilState>().apply {
        format = input.format.actualName
        depthWriteEnabled = input.depthWriteEnabled ?: undefined
        depthCompare = input.depthCompare?.stringValue ?: undefined
        stencilFront = map(input.stencilFront)
        stencilBack = map(input.stencilBack)
        stencilReadMask = input.stencilReadMask
        stencilWriteMask = input.stencilWriteMask
        depthBias = input.depthBias
        depthBiasSlopeScale = input.depthBiasSlopeScale
        depthBiasClamp = input.depthBiasClamp
    }

private fun map(input: RenderPipelineDescriptor.DepthStencilState.StencilFaceState): GPUStencilFaceState =
    createJsObject<GPUStencilFaceState>().apply {
        compare = input.compare.stringValue
        failOp = input.failOp.stringValue
        depthFailOp = input.depthFailOp.stringValue
        passOp = input.passOp.stringValue
    }

private fun map(input: RenderPipelineDescriptor.MultisampleState): GPUMultisampleState =
    createJsObject<GPUMultisampleState>().apply {
        count = input.count
        mask = input.mask
        alphaToCoverageEnabled = input.alphaToCoverageEnabled
    }

private fun map(input: RenderPipelineDescriptor.FragmentState): GPUFragmentState =
    createJsObject<GPUFragmentState>().apply {
        targets = input.targets.map { map(it) }.toTypedArray()
        module = input.module.handler
        entryPoint = input.entryPoint

        // TODO not sure how to map this
        constants = undefined
    }

private fun map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState): GPUColorTargetState =
    createJsObject<GPUColorTargetState>().apply {
        format = input.format.actualName
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
        operation = input.operation.stringValue
        srcFactor = input.srcFactor.stringValue
        dstFactor = input.dstFactor.stringValue
    }
