@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: RenderPipelineDescriptor) = WGPURenderPipelineDescriptor.allocate(this).also { output ->
    map(input.vertex, WGPURenderPipelineDescriptor.vertex(output))
    if (input.label != null) WGPURenderPipelineDescriptor.label(output, allocateFrom(input.label))
    if (input.layout != null) WGPURenderPipelineDescriptor.layout(output, input.layout.handler)
    map(input.primitive, WGPURenderPipelineDescriptor.primitive(output))
    if (input.depthStencil != null) WGPURenderPipelineDescriptor.depthStencil(output, map(input.depthStencil))
    if (input.fragment != null) WGPURenderPipelineDescriptor.fragment(output, map(input.fragment))
    map(input.multisample, WGPURenderPipelineDescriptor.multisample(output))
}

fun Arena.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState, output: MemorySegment) {
    println("colorTargetState $output")
    WGPUColorTargetState.format(output, input.format.value)
    WGPUColorTargetState.writeMask(output, input.writeMask.value)
    WGPUColorTargetState.blend(output, map(input.blend))
}

fun Arena.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState): MemorySegment = WGPUBlendState
    .allocate(this).also { output ->
        println("blend state $output")
        map(input.color, WGPUBlendState.color(output))
        map(input.alpha, WGPUBlendState.alpha(output))

    }

fun map(
    input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent,
    output: MemorySegment
) {
    println("blend component $output")
    WGPUBlendComponent.operation(output, input.operation.value)
    WGPUBlendComponent.srcFactor(output, input.srcFactor.value)
    WGPUBlendComponent.dstFactor(output, input.dstFactor.value)
}

private fun Arena.map(input: RenderPipelineDescriptor.FragmentState): MemorySegment =
    WGPUFragmentState.allocate(this)
        .also { fragmentState ->
            println("fragment $fragmentState")
            WGPUFragmentState.module(fragmentState, input.module.handler)
            WGPUFragmentState.entryPoint(fragmentState, allocateFrom(input.entryPoint))
            if (input.targets.isNotEmpty()) {
                WGPUFragmentState.targetCount(fragmentState, input.targets.size.toLong())
                val colorTargets = WGPUColorTargetState.allocateArray(input.targets.size.toLong(), this)
                println("colorTargets $colorTargets")
                input.targets.forEachIndexed { index, colorTargetState ->
                    map(colorTargetState, WGPUColorTargetState.asSlice(colorTargets, index.toLong()))
                }
                WGPUFragmentState.targets(fragmentState, colorTargets)
            }
        }

private fun Arena.map(input: RenderPipelineDescriptor.DepthStencilState): MemorySegment =
    WGPUDepthStencilState.allocate(this)
        .also { depthStencilState ->
            WGPUDepthStencilState.format(depthStencilState, input.format.value)
            if (input.depthWriteEnabled != null) WGPUDepthStencilState.depthWriteEnabled(
                depthStencilState,
                input.depthWriteEnabled.toInt()
            )
            if (input.depthCompare != null) WGPUDepthStencilState.depthCompare(
                depthStencilState,
                input.depthCompare.value
            )
            map(input.stencilFront, WGPUDepthStencilState.stencilFront(depthStencilState))
            map(input.stencilBack, WGPUDepthStencilState.stencilBack(depthStencilState))
            WGPUDepthStencilState.stencilReadMask(depthStencilState, input.stencilReadMask.toInt())
            WGPUDepthStencilState.stencilWriteMask(depthStencilState, input.stencilWriteMask.toInt())
            WGPUDepthStencilState.depthBias(depthStencilState, input.depthBias)
            WGPUDepthStencilState.depthBiasSlopeScale(depthStencilState, input.depthBiasSlopeScale)
            WGPUDepthStencilState.depthBiasClamp(depthStencilState, input.depthBiasClamp)
        }

fun map(input: RenderPipelineDescriptor.DepthStencilState.StencilFaceState, output: MemorySegment?) {
    WGPUStencilFaceState.compare(output, input.compare.value)
    WGPUStencilFaceState.failOp(output, input.failOp.value)
    WGPUStencilFaceState.depthFailOp(output, input.depthFailOp.value)
    WGPUStencilFaceState.passOp(output, input.passOp.value)
}

private fun map(input: RenderPipelineDescriptor.MultisampleState, output: MemorySegment) {
    WGPUMultisampleState.count(output, input.count)
    WGPUMultisampleState.mask(output, input.mask.toInt())
    WGPUMultisampleState.alphaToCoverageEnabled(output, input.alphaToCoverageEnabled.toInt())
}

private fun map(input: RenderPipelineDescriptor.PrimitiveState, output: MemorySegment) {
    WGPUPrimitiveState.topology(output, input.topology.value)
    if (input.stripIndexFormat != null) WGPUPrimitiveState.stripIndexFormat(output, input.stripIndexFormat.value)
    WGPUPrimitiveState.frontFace(output, input.frontFace.value)
    WGPUPrimitiveState.cullMode(output, input.cullMode.value)
    //TODO check how to map unclippedDepth https://docs.rs/wgpu/latest/wgpu/struct.PrimitiveState.html
}

private fun Arena.map(input: RenderPipelineDescriptor.VertexState, output: MemorySegment) {
    println("vertex $output")
    WGPUVertexState.module(output, input.module.handler)
    WGPUVertexState.entryPoint(output, allocateFrom(input.entryPoint))
    // TODO learn how to map this
    WGPUVertexState.constants(output, MemorySegment.NULL)
    WGPUVertexState.constantCount(output, 0L)
    if (input.buffers.isNotEmpty()) {
        val buffers = WGPUVertexBufferLayout.allocateArray(input.buffers.size.toLong(), this)
        println("buffers $buffers")
        input.buffers.forEachIndexed { index, vertexBufferLayout ->
            map(vertexBufferLayout, WGPUVertexBufferLayout.asSlice(buffers, index.toLong()))
        }
        WGPUVertexState.buffers(output, buffers)
        WGPUVertexState.bufferCount(output, input.buffers.size.toLong())
    }
}

private fun map(
    input: RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute,
    output: MemorySegment
) {
    println("attribute $output")
    WGPUVertexAttribute.format(output, input.format.value)
    WGPUVertexAttribute.offset(output, input.offset)
    WGPUVertexAttribute.shaderLocation(output, input.shaderLocation)
}

private fun Arena.map(input: RenderPipelineDescriptor.VertexState.VertexBufferLayout, output: MemorySegment) {
    println("buffer $output")
    WGPUVertexBufferLayout.arrayStride(output, input.arrayStride)
    if (input.attributes.isNotEmpty()) {
        val attributes = WGPUVertexAttribute.allocateArray(input.attributes.size.toLong(), this)
        println("attributes $attributes")
        input.attributes.forEachIndexed { index, vertexAttribute ->
            map(vertexAttribute, WGPUVertexAttribute.asSlice(attributes, index.toLong()))
        }
        WGPUVertexBufferLayout.attributes(output, attributes)
        WGPUVertexBufferLayout.attributeCount(output, input.attributes.size.toLong())
    }
    WGPUVertexBufferLayout.stepMode(output, input.stepMode.value)
}

