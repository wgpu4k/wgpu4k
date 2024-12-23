package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.RenderPipelineDescriptor
import webgpu.WGPUBlendComponent
import webgpu.WGPUBlendState
import webgpu.WGPUColorTargetState
import webgpu.WGPUDepthStencilState
import webgpu.WGPUFragmentState
import webgpu.WGPUMultisampleState
import webgpu.WGPUPrimitiveState
import webgpu.WGPURenderPipelineDescriptor
import webgpu.WGPUStencilFaceState
import webgpu.WGPUVertexAttribute
import webgpu.WGPUVertexBufferLayout
import webgpu.WGPUVertexState
import webgpu.toUInt

internal fun MemoryAllocator.map(input: RenderPipelineDescriptor) =
    WGPURenderPipelineDescriptor.allocate(this).also { output ->
        map(input.vertex, output.vertex)
        if (input.label != null) map(input.label, output.label)
        if (input.layout != null) output.layout = input.layout.handler
        map(input.primitive, output.primitive)
        if (input.depthStencil != null) output.depthStencil = map(input.depthStencil)
        if (input.fragment != null) output.fragment = map(input.fragment)
        map(input.multisample, output.multisample)
    }

fun MemoryAllocator.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState, output: WGPUColorTargetState) {
    println("colorTargetState $output")
    output.format = input.format.uValue
    output.writeMask = input.writeMask.value.toULong()
    output.blend = map(input.blend)
}

fun MemoryAllocator.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState): WGPUBlendState =
    WGPUBlendState
        .allocate(this).also { output ->
            println("blend state $output")
            map(input.color, output.color)
            map(input.alpha, output.alpha)

        }

fun map(
    input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent,
    output: WGPUBlendComponent
) {
    println("blend component $output")
    output.operation = input.operation.uValue
    output.srcFactor = input.srcFactor.uValue
    output.dstFactor = input.dstFactor.uValue
}

private fun MemoryAllocator.map(input: RenderPipelineDescriptor.FragmentState): WGPUFragmentState =
    WGPUFragmentState.allocate(this)
        .also { fragmentState ->
            println("fragment $fragmentState")
            fragmentState.module = input.module.handler
            map(input.entryPoint, fragmentState.entryPoint)
            if (input.targets.isNotEmpty()) {
                fragmentState.targetCount = input.targets.size.toULong()
                val colorTargets =
                    WGPUColorTargetState.allocateArray(this, input.targets.size.toUInt(), { index, value ->
                        val colorTargetState = input.targets[index.toInt()]
                        map(colorTargetState, value)

                    })
                fragmentState.targets = colorTargets
            }
        }

private fun MemoryAllocator.map(input: RenderPipelineDescriptor.DepthStencilState): WGPUDepthStencilState =
    WGPUDepthStencilState.allocate(this)
        .also { output ->
            output.format = input.format.uValue
            if (input.depthWriteEnabled != null) output.depthWriteEnabled = input.depthWriteEnabled.toUInt()
            if (input.depthCompare != null) output.depthCompare = input.depthCompare.uValue
            map(input.stencilFront, output.stencilFront)
            map(input.stencilBack, output.stencilBack)
            output.stencilReadMask = input.stencilReadMask
            output.stencilWriteMask = input.stencilWriteMask
            output.depthBias = input.depthBias
            output.depthBiasSlopeScale = input.depthBiasSlopeScale
            output.depthBiasClamp = input.depthBiasClamp
        }

fun map(input: RenderPipelineDescriptor.DepthStencilState.StencilFaceState, output: WGPUStencilFaceState) {
    output.compare = input.compare.uValue
    output.failOp = input.failOp.uValue
    output.depthFailOp = input.depthFailOp.uValue
    output.passOp = input.passOp.uValue
}

private fun map(input: RenderPipelineDescriptor.MultisampleState, output: WGPUMultisampleState) {
    output.count = input.count
    output.mask = input.mask
    output.alphaToCoverageEnabled = input.alphaToCoverageEnabled
}

private fun map(input: RenderPipelineDescriptor.PrimitiveState, output: WGPUPrimitiveState) {
    output.topology = input.topology.uValue
    if (input.stripIndexFormat != null) output.stripIndexFormat = input.stripIndexFormat.uValue
    output.frontFace = input.frontFace.uValue
    output.cullMode = input.cullMode.uValue
    //TODO check how to map unclippedDepth https://docs.rs/wgpu/latest/wgpu/struct.PrimitiveState.html
}

private fun MemoryAllocator.map(input: RenderPipelineDescriptor.VertexState, output: WGPUVertexState) {
    println("vertex $output")
    output.module = input.module.handler
    map(input.entryPoint, output.entryPoint)
    // TODO learn how to map this
    output.constantCount = 0uL
    if (input.buffers.isNotEmpty()) {
        output.buffers = WGPUVertexBufferLayout.allocateArray(this, input.buffers.size.toUInt(), { index, value ->
            val vertexBufferLayout = input.buffers[index.toInt()]
            map(vertexBufferLayout, value)

        })
        output.bufferCount = input.buffers.size.toULong()
    }
}

private fun map(
    input: RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute,
    output: WGPUVertexAttribute
) {
    println("attribute $output")
    output.format = input.format.uValue
    output.offset = input.offset
    output.shaderLocation = input.shaderLocation
}

private fun MemoryAllocator.map(
    input: RenderPipelineDescriptor.VertexState.VertexBufferLayout,
    output: WGPUVertexBufferLayout
) {
    println("buffer $output")
    output.arrayStride = input.arrayStride
    if (input.attributes.isNotEmpty()) {
        output.attributes = WGPUVertexAttribute.allocateArray(this, input.attributes.size.toUInt(), { index, value ->
            val vertexAttribute = input.attributes[index.toInt()]
            map(vertexAttribute, value)
        })
        output.attributeCount = input.attributes.size.toULong()
    }
    output.stepMode = input.stepMode.uValue
}

