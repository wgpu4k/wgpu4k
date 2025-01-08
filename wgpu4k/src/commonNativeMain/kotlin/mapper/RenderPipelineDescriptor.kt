package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.WGPUBlendComponent
import io.ygdrasil.wgpu.WGPUBlendState
import io.ygdrasil.wgpu.WGPUColorTargetState
import io.ygdrasil.wgpu.WGPUDepthStencilState
import io.ygdrasil.wgpu.WGPUFragmentState
import io.ygdrasil.wgpu.WGPUMultisampleState
import io.ygdrasil.wgpu.WGPUPrimitiveState
import io.ygdrasil.wgpu.WGPURenderPipelineDescriptor
import io.ygdrasil.wgpu.WGPUStencilFaceState
import io.ygdrasil.wgpu.WGPUVertexAttribute
import io.ygdrasil.wgpu.WGPUVertexBufferLayout
import io.ygdrasil.wgpu.WGPUVertexState

private val logger = KotlinLogging.logger {}

internal fun MemoryAllocator.map(input: RenderPipelineDescriptor) =
    WGPURenderPipelineDescriptor.allocate(this).also { output ->
        map(input.vertex, output.vertex)
        if (input.label != null) output.label = allocateFrom(input.label)
        if (input.layout != null) output.layout = input.layout.handler
        map(input.primitive, output.primitive)
        if (input.depthStencil != null) output.depthStencil = map(input.depthStencil)
        if (input.fragment != null) output.fragment = map(input.fragment)
        map(input.multisample, output.multisample)
    }

fun MemoryAllocator.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState, output: WGPUColorTargetState) {
    logger.trace { "colorTargetState $output" }
    output.format = input.format.value
    output.writeMask = input.writeMask.value.toUInt()
    output.blend = map(input.blend)
}

fun MemoryAllocator.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState): WGPUBlendState =
    WGPUBlendState
        .allocate(this).also { output ->
            logger.trace { "blend state $output" }
            map(input.color, output.color)
            map(input.alpha, output.alpha)

        }

fun map(
    input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent,
    output: WGPUBlendComponent
) {
    logger.trace { "blend component $output" }
    output.operation = input.operation.value
    output.srcFactor = input.srcFactor.value
    output.dstFactor = input.dstFactor.value
}

private fun MemoryAllocator.map(input: RenderPipelineDescriptor.FragmentState): WGPUFragmentState =
    WGPUFragmentState.allocate(this)
        .also { fragmentState ->
            logger.trace { "fragment $fragmentState" }
            fragmentState.module = input.module.handler
            fragmentState.entryPoint = allocateFrom(input.entryPoint)
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
            output.format = input.format.value
            if (input.depthWriteEnabled != null) output.depthWriteEnabled = input.depthWriteEnabled
            if (input.depthCompare != null) output.depthCompare = input.depthCompare.value
            map(input.stencilFront, output.stencilFront)
            map(input.stencilBack, output.stencilBack)
            output.stencilReadMask = input.stencilReadMask
            output.stencilWriteMask = input.stencilWriteMask
            output.depthBias = input.depthBias
            output.depthBiasSlopeScale = input.depthBiasSlopeScale
            output.depthBiasClamp = input.depthBiasClamp
        }

fun map(input: RenderPipelineDescriptor.DepthStencilState.StencilFaceState, output: WGPUStencilFaceState) {
    output.compare = input.compare.value
    output.failOp = input.failOp.value
    output.depthFailOp = input.depthFailOp.value
    output.passOp = input.passOp.value
}

private fun map(input: RenderPipelineDescriptor.MultisampleState, output: WGPUMultisampleState) {
    output.count = input.count
    output.mask = input.mask
    output.alphaToCoverageEnabled = input.alphaToCoverageEnabled
}

private fun map(input: RenderPipelineDescriptor.PrimitiveState, output: WGPUPrimitiveState) {
    output.topology = input.topology.value
    if (input.stripIndexFormat != null) output.stripIndexFormat = input.stripIndexFormat.value
    output.frontFace = input.frontFace.value
    output.cullMode = input.cullMode.value
    //TODO check how to map unclippedDepth https://docs.rs/wgpu/latest/wgpu/struct.PrimitiveState.html
}

private fun MemoryAllocator.map(input: RenderPipelineDescriptor.VertexState, output: WGPUVertexState) {
    logger.trace { "vertex $output" }
    output.module = input.module.handler
    output.entryPoint = allocateFrom(input.entryPoint)
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
    logger.trace { "attribute $output" }
    output.format = input.format.value
    output.offset = input.offset
    output.shaderLocation = input.shaderLocation
}

private fun MemoryAllocator.map(
    input: RenderPipelineDescriptor.VertexState.VertexBufferLayout,
    output: WGPUVertexBufferLayout
) {
    logger.trace { "buffer $output" }
    output.arrayStride = input.arrayStride
    if (input.attributes.isNotEmpty()) {
        output.attributes = WGPUVertexAttribute.allocateArray(this, input.attributes.size.toUInt(), { index, value ->
            val vertexAttribute = input.attributes[index.toInt()]
            map(vertexAttribute, value)
        })
        output.attributeCount = input.attributes.size.toULong()
    }
    output.stepMode = input.stepMode.value
}

