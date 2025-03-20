package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUBlendComponent
import io.ygdrasil.webgpu.GPUBlendState
import io.ygdrasil.webgpu.GPUColorTargetState
import io.ygdrasil.webgpu.GPUDepthStencilState
import io.ygdrasil.webgpu.GPUFragmentState
import io.ygdrasil.webgpu.GPUMultisampleState
import io.ygdrasil.webgpu.GPUPrimitiveState
import io.ygdrasil.webgpu.GPURenderPipelineDescriptor
import io.ygdrasil.webgpu.GPUStencilFaceState
import io.ygdrasil.webgpu.GPUVertexAttribute
import io.ygdrasil.webgpu.GPUVertexBufferLayout
import io.ygdrasil.webgpu.GPUVertexState
import io.ygdrasil.webgpu.ShaderModule
import io.ygdrasil.webgpu.WGPUBlendComponent
import io.ygdrasil.webgpu.WGPUBlendState
import io.ygdrasil.webgpu.WGPUColorTargetState
import io.ygdrasil.webgpu.WGPUDepthStencilState
import io.ygdrasil.webgpu.WGPUFragmentState
import io.ygdrasil.webgpu.WGPUMultisampleState
import io.ygdrasil.webgpu.WGPUPrimitiveState
import io.ygdrasil.webgpu.WGPURenderPipelineDescriptor
import io.ygdrasil.webgpu.WGPUStencilFaceState
import io.ygdrasil.webgpu.WGPUVertexAttribute
import io.ygdrasil.webgpu.WGPUVertexBufferLayout
import io.ygdrasil.webgpu.WGPUVertexState
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPURenderPipelineDescriptor): WGPURenderPipelineDescriptor =
    createJsObject<WGPURenderPipelineDescriptor>().apply {
        vertex = map(input.vertex)
        layout = input.layout?.handler ?: "auto"
        label = input.label ?: undefined
        primitive = map(input.primitive)
        depthStencil = input.depthStencil?.let { map(it) } ?: undefined
        fragment = input.fragment?.let { map(it) } ?: undefined
        multisample = map(input.multisample)
    }

private fun map(input: GPUVertexState): WGPUVertexState = createJsObject<WGPUVertexState>().apply {
    module = (input.module as ShaderModule).handler
    entryPoint = input.entryPoint

    // TODO map this
    constants = undefined
    buffers = input.buffers.map { map(it) }.toTypedArray()
}

private fun map(input: GPUVertexBufferLayout): WGPUVertexBufferLayout =
    createJsObject<WGPUVertexBufferLayout>().apply {
        arrayStride = input.arrayStride
        attributes = input.attributes.map { map(it) }.toTypedArray()
        stepMode = input.stepMode.value
    }

private fun map(input: GPUVertexAttribute): WGPUVertexAttribute =
    createJsObject<WGPUVertexAttribute>().apply {
        format = input.format.value
        offset = input.offset
        shaderLocation = input.shaderLocation
    }

private fun map(input: GPUPrimitiveState): WGPUPrimitiveState =
    createJsObject<WGPUPrimitiveState>().apply {
        topology = input.topology.value
        stripIndexFormat = input.stripIndexFormat?.value ?: undefined
        frontFace = input.frontFace.value
        cullMode = input.cullMode.value
        unclippedDepth = input.unclippedDepth
    }

private fun map(input: GPUDepthStencilState): WGPUDepthStencilState =
    createJsObject<WGPUDepthStencilState>().apply {
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

private fun map(input: GPUStencilFaceState): WGPUStencilFaceState =
    createJsObject<WGPUStencilFaceState>().apply {
        compare = input.compare.value
        failOp = input.failOp.value
        depthFailOp = input.depthFailOp.value
        passOp = input.passOp.value
    }

private fun map(input: GPUMultisampleState): WGPUMultisampleState =
    createJsObject<WGPUMultisampleState>().apply {
        count = input.count
        mask = input.mask
        alphaToCoverageEnabled = input.alphaToCoverageEnabled
    }

private fun map(input: GPUFragmentState): WGPUFragmentState =
    createJsObject<WGPUFragmentState>().apply {
        targets = input.targets.map { map(it) }.toTypedArray()
        module = input.module.handler
        entryPoint = input.entryPoint

        // TODO not sure how to map this
        constants = undefined
    }

private fun map(input: GPUColorTargetState): WGPUColorTargetState =
    createJsObject<WGPUColorTargetState>().apply {
        format = input.format.value
        blend = map(input.blend)
        writeMask = input.writeMask.value
    }

private fun map(input: GPUBlendState): WGPUBlendState =
    createJsObject<WGPUBlendState>().apply {
        color = map(input.color)
        alpha = map(input.alpha)
    }

private fun map(input: GPUBlendComponent): WGPUBlendComponent =
    createJsObject<WGPUBlendComponent>().apply {
        operation = input.operation.value
        srcFactor = input.srcFactor.value
        dstFactor = input.dstFactor.value
    }
