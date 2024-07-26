@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.internal.toUInt
import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: RenderPipelineDescriptor) = alloc<WGPURenderPipelineDescriptor>().also { output ->
    if (input.label != null) output.label = input.label.cstr.getPointer(this)
    map(input.vertex, output.vertex)
    if (input.layout != null) output.layout = input.layout.handler
    map(input.primitive, output.primitive)
    if (input.depthStencil != null) output.depthStencil = map(input.depthStencil).ptr
    if (input.fragment != null) output.fragment = map(input.fragment).ptr
    map(input.multisample, output.multisample)
}

fun Arena.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState, output: WGPUColorTargetState) {
    println("colorTargetState $output")
    output.format = input.format.uValue
    output.writeMask = input.writeMask.uValue
    output.blend = map(input.blend).ptr
}

fun Arena.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState) =
    alloc<WGPUBlendState>().also { output ->
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

private fun Arena.map(input: RenderPipelineDescriptor.FragmentState): WGPUFragmentState =
    alloc<WGPUFragmentState>().also { output ->
        println("fragment $output")
        output.module = input.module.handler
        output.entryPoint = input.entryPoint.cstr.getPointer(this)
        if (input.targets.isNotEmpty()) {
            output.targetCount = input.targets.size.toULong()
            val colorTargets = allocArray<WGPUColorTargetState>(input.targets.size)
            println("colorTargets $colorTargets")
            input.targets.forEachIndexed { index, colorTargetState ->
                map(colorTargetState, colorTargets[index])
            }
            output.targets = colorTargets
        }
    }

private fun Arena.map(input: RenderPipelineDescriptor.DepthStencilState): WGPUDepthStencilState =
    alloc<WGPUDepthStencilState>()
        .also { output ->
            output.format = input.format.uValue
            if (input.depthWriteEnabled != null) output.depthWriteEnabled = input.depthWriteEnabled.toUInt()
            if (input.depthCompare != null) output.depthCompare = input.depthCompare.uValue
            map(input.stencilFront, output.stencilFront)
            map(input.stencilBack, output.stencilBack)
            output.stencilReadMask = input.stencilReadMask.toUInt()
            output.stencilWriteMask = input.stencilWriteMask.toUInt()
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
    output.count = input.count.toUInt()
    output.mask = input.mask
    output.alphaToCoverageEnabled = input.alphaToCoverageEnabled.toUInt()
}

private fun map(input: RenderPipelineDescriptor.PrimitiveState, output: WGPUPrimitiveState) {
    output.topology = input.topology.uValue
    if (input.stripIndexFormat != null) output.stripIndexFormat = input.stripIndexFormat.uValue
    output.frontFace = input.frontFace.uValue
    output.cullMode = input.cullMode.uValue
    //TODO check how to map unclippedDepth https://docs.rs/wgpu/latest/wgpu/struct.PrimitiveState.html
}

private fun Arena.map(input: RenderPipelineDescriptor.VertexState, output: WGPUVertexState) {
    println("vertex $output")
    output.module = input.module.handler
    output.entryPoint = input.entryPoint.cstr.getPointer(this)
    // TODO learn how to map this
    output.constants = null
    output.constantCount = 0uL
    if (input.buffers.isNotEmpty()) {
        val buffers = allocArray<WGPUVertexBufferLayout>(input.buffers.size)
        println("buffers $buffers")
        input.buffers.forEachIndexed { index, vertexBufferLayout ->
            map(vertexBufferLayout, buffers[index])
        }
        output.buffers = buffers
        output.bufferCount = input.buffers.size.toULong()
    }
}

private fun map(
    input: RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute,
    output: WGPUVertexAttribute
) {
    println("attribute $output")
    output.format = input.format.uValue
    output.offset = input.offset.toULong()
    output.shaderLocation = input.shaderLocation.toUInt()
}

private fun Arena.map(input: RenderPipelineDescriptor.VertexState.VertexBufferLayout, output: WGPUVertexBufferLayout) {
    println("buffer $output")
    output.arrayStride = input.arrayStride.toULong()
    if (input.attributes.isNotEmpty()) {
        val attributes = allocArray<WGPUVertexAttribute>(input.attributes.size)
        println("attributes $attributes")
        input.attributes.forEachIndexed { index, vertexAttribute ->
            map(vertexAttribute, attributes[index])
        }
        output.attributes = attributes
        output.attributeCount = input.attributes.size.toULong()
    }
    output.stepMode = input.stepMode.uValue
}

