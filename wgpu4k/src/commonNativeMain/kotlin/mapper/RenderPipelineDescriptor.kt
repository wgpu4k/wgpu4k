package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
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
import io.ygdrasil.webgpu.toFlagULong
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
import io.ygdrasil.wgpu.toUInt

internal fun MemoryAllocator.map(input: GPURenderPipelineDescriptor) =
    WGPURenderPipelineDescriptor.allocate(this).also { output ->
        map(input.vertex, output.vertex)
        map(input.label, output.label)
        if (input.layout != null) output.layout = (input.layout as PipelineLayout).handler
        map(input.primitive, output.primitive)
        input.depthStencil?.let { output.depthStencil = map(it) }
        input.fragment?.let { output.fragment = map(it) }
        map(input.multisample, output.multisample)
    }

fun MemoryAllocator.map(input: GPUColorTargetState, output: WGPUColorTargetState) {
    println("colorTargetState $output")
    output.format = input.format.value
    output.writeMask = input.writeMask.toFlagULong()
    input.blend?.let { output.blend = map(it) }

}

fun MemoryAllocator.map(input: GPUBlendState): WGPUBlendState =
    WGPUBlendState
        .allocate(this).also { output ->
            println("blend state $output")
            map(input.color, output.color)
            map(input.alpha, output.alpha)

        }

fun map(
    input: GPUBlendComponent,
    output: WGPUBlendComponent
) {
    println("blend component $output")
    output.operation = input.operation.value
    output.srcFactor = input.srcFactor.value
    output.dstFactor = input.dstFactor.value
}

private fun MemoryAllocator.map(input: GPUFragmentState): WGPUFragmentState =
    WGPUFragmentState.allocate(this)
        .also { fragmentState ->
            println("fragment $fragmentState")
            fragmentState.module = (input.module as ShaderModule).handler
            input.entryPoint?.let { map(it, fragmentState.entryPoint) }
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

private fun MemoryAllocator.map(input: GPUDepthStencilState): WGPUDepthStencilState =
    WGPUDepthStencilState.allocate(this)
        .also { output ->
            output.format = input.format.value
            input.depthWriteEnabled?.let { output.depthWriteEnabled = it.toUInt() }
            input.depthCompare?.let { output.depthCompare = it.value }
            map(input.stencilFront, output.stencilFront)
            map(input.stencilBack, output.stencilBack)
            output.stencilReadMask = input.stencilReadMask
            output.stencilWriteMask = input.stencilWriteMask
            output.depthBias = input.depthBias
            output.depthBiasSlopeScale = input.depthBiasSlopeScale
            output.depthBiasClamp = input.depthBiasClamp
        }

fun map(input: GPUStencilFaceState, output: WGPUStencilFaceState) {
    output.compare = input.compare.value
    output.failOp = input.failOp.value
    output.depthFailOp = input.depthFailOp.value
    output.passOp = input.passOp.value
}

private fun map(input: GPUMultisampleState, output: WGPUMultisampleState) {
    output.count = input.count
    output.mask = input.mask
    output.alphaToCoverageEnabled = input.alphaToCoverageEnabled
}

private fun map(input: GPUPrimitiveState, output: WGPUPrimitiveState) {
    output.topology = input.topology.value
    input.stripIndexFormat?.let { output.stripIndexFormat = it.value }
    output.frontFace = input.frontFace.value
    output.cullMode = input.cullMode.value
    //TODO check how to map unclippedDepth https://docs.rs/wgpu/latest/wgpu/struct.PrimitiveState.html
}

private fun MemoryAllocator.map(input: GPUVertexState, output: WGPUVertexState) {
    println("vertex $output")
    output.module = (input.module as ShaderModule).handler
    input.entryPoint?.let { map(it, output.entryPoint) }
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
    input: GPUVertexAttribute,
    output: WGPUVertexAttribute
) {
    println("attribute $output")
    output.format = input.format.value
    output.offset = input.offset
    output.shaderLocation = input.shaderLocation
}

private fun MemoryAllocator.map(
    input: GPUVertexBufferLayout,
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
    output.stepMode = input.stepMode.value
}

