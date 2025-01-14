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
import io.ygdrasil.wgpu.mapper.map

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
        stepMode = input.stepMode.value
    }

private fun map(input: RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute): GPUVertexAttribute =
    createJsObject<GPUVertexAttribute>().apply {
        format = input.format.value
        offset = input.offset
        shaderLocation = input.shaderLocation
    }

private fun map(input: RenderPipelineDescriptor.PrimitiveState): GPUPrimitiveState =
    createJsObject<GPUPrimitiveState>().apply {
        topology = input.topology.value
        stripIndexFormat = input.stripIndexFormat?.value ?: undefined
        frontFace = input.frontFace.value
        cullMode = input.cullMode.value
        unclippedDepth = input.unclippedDepth
    }

private fun map(input: RenderPipelineDescriptor.DepthStencilState): GPUDepthStencilState =
    createJsObject<GPUDepthStencilState>().apply {
        format = input.format.value
        depthWriteEnabled = input.depthWriteEnabled ?: undefined
        depthCompare = input.depthCompare?.value ?: undefined
        stencilFront = map(input.stencilFront)
        stencilBack = map(input.stencilBack)
        stencilReadMask = map(input.stencilReadMask)
        stencilWriteMask = map(input.stencilWriteMask)
        depthBias = input.depthBias
        depthBiasSlopeScale = input.depthBiasSlopeScale
        depthBiasClamp = input.depthBiasClamp
    }

private fun map(input: RenderPipelineDescriptor.DepthStencilState.StencilFaceState): GPUStencilFaceState =
    createJsObject<GPUStencilFaceState>().apply {
        compare = input.compare.value
        failOp = input.failOp.value
        depthFailOp = input.depthFailOp.value
        passOp = input.passOp.value
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
        format = input.format.value
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
        operation = input.operation.value
        srcFactor = input.srcFactor.value
        dstFactor = input.dstFactor.value
    }
