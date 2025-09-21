@file:OptIn(ExperimentalWasmJsInterop::class)

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
import io.ygdrasil.webgpu.PipelineLayout
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
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.asJsString
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray
import io.ygdrasil.webgpu.toFlagInt
import kotlin.js.ExperimentalWasmJsInterop

internal fun map(input: GPURenderPipelineDescriptor): WGPURenderPipelineDescriptor =
    createJsObject<WGPURenderPipelineDescriptor>().apply {
        label = input.label
        vertex = map(input.vertex)
        layout = (input.layout as PipelineLayout?)?.handler ?: "auto".asJsString()
        primitive = map(input.primitive)
        input.depthStencil?.let { depthStencil = map(it) }
        input.fragment?.let { fragment = map(it) }
        multisample = map(input.multisample)
    }

private fun map(input: GPUVertexState): WGPUVertexState = createJsObject<WGPUVertexState>().apply {
    module = (input.module as ShaderModule).handler
    input.entryPoint?.let { entryPoint = it }

    // TODO map this
    //constants = undefined
    buffers = input.buffers.mapJsArray { map(it) }
}

private fun map(input: GPUVertexBufferLayout): WGPUVertexBufferLayout =
    createJsObject<WGPUVertexBufferLayout>().apply {
        arrayStride = input.arrayStride.asJsNumber()
        attributes = input.attributes.mapJsArray { map(it) }
        stepMode = input.stepMode.value
    }

private fun map(input: GPUVertexAttribute): WGPUVertexAttribute =
    createJsObject<WGPUVertexAttribute>().apply {
        format = input.format.value
        offset = input.offset.asJsNumber()
        shaderLocation = input.shaderLocation.asJsNumber()
    }

private fun map(input: GPUPrimitiveState): WGPUPrimitiveState =
    createJsObject<WGPUPrimitiveState>().apply {
        topology = input.topology.value
        input.stripIndexFormat?.let { stripIndexFormat = it.value }
        frontFace = input.frontFace.value
        cullMode = input.cullMode.value
        unclippedDepth = input.unclippedDepth
    }

private fun map(input: GPUDepthStencilState): WGPUDepthStencilState =
    createJsObject<WGPUDepthStencilState>().apply {
        format = input.format.value
        input.depthWriteEnabled?.let { depthWriteEnabled = it }
        input.depthCompare?.let { depthCompare = it.value }
        stencilFront = map(input.stencilFront)
        stencilBack = map(input.stencilBack)
        stencilReadMask = input.stencilReadMask.asJsNumber()
        stencilWriteMask = input.stencilWriteMask.asJsNumber()
        depthBias = input.depthBias.asJsNumber()
        depthBiasSlopeScale = input.depthBiasSlopeScale.asJsNumber()
        depthBiasClamp = input.depthBiasClamp.asJsNumber()
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
        count = input.count.asJsNumber()
        mask = input.mask.asJsNumber()
        alphaToCoverageEnabled = input.alphaToCoverageEnabled
    }

private fun map(input: GPUFragmentState): WGPUFragmentState =
    createJsObject<WGPUFragmentState>().apply {
        targets = input.targets.mapJsArray { map(it) }
        module = (input.module as ShaderModule).handler
        input.entryPoint?.let { entryPoint = it }

        // TODO not sure how to map this
        //constants = undefined
    }

private fun map(input: GPUColorTargetState): WGPUColorTargetState =
    createJsObject<WGPUColorTargetState>().apply {
        format = input.format.value
        input.blend?.let { blend = map(it) }
        writeMask = input.writeMask.toFlagInt().asJsNumber()
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
