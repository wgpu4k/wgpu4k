package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUColorWriteFlags
import io.ygdrasil.wgpu.GPUPipelineConstantValue
import io.ygdrasil.wgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.internal.js.*

internal fun map(input: RenderPipelineDescriptor): GPURenderPipelineDescriptor =
    createJsObject<GPURenderPipelineDescriptor>().apply {
        vertex = map(input.vertex)
        layout = input.layout?.handler ?: "auto"
        label = input.label ?: undefined
        primitive = map(input.primitive)
        depthStencil = input.depthStencil?.let { map(it) } ?: undefined
        fragment = input.fragment?.convert() ?: undefined
        multisample = input.multisample.convert()
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
        stencilFront = input.stencilFront.convert()
        stencilBack = input.stencilBack.convert()
        stencilReadMask = input.stencilReadMask
        stencilWriteMask = input.stencilWriteMask
        depthBias = input.depthBias
        depthBiasSlopeScale = input.depthBiasSlopeScale
        depthBiasClamp = input.depthBiasClamp
    }

private fun RenderPipelineDescriptor.DepthStencilState.StencilFaceState.convert(): GPUStencilFaceState =
    object : GPUStencilFaceState {
        override var compare: String? = this@convert.compare.stringValue
        override var failOp: String? = this@convert.failOp.stringValue
        override var depthFailOp: String? = this@convert.depthFailOp.stringValue
        override var passOp: String? = this@convert.passOp.stringValue
    }

private fun RenderPipelineDescriptor.MultisampleState.convert(): GPUMultisampleState =
    object : GPUMultisampleState {
        override var count: dynamic = this@convert.count
        override var mask: dynamic = this@convert.mask
        override var alphaToCoverageEnabled: dynamic = this@convert.alphaToCoverageEnabled
    }

private fun RenderPipelineDescriptor.FragmentState.convert(): GPUFragmentState =
    object : GPUFragmentState {
        override var targets: Array<GPUColorTargetState?> = this@convert.targets.map { it.convert() }.toTypedArray()
        override var module: GPUShaderModule = this@convert.module.handler
        override var entryPoint: String? = this@convert.entryPoint

        // TODO not sure how to map this
        override var constants: Map<String, GPUPipelineConstantValue>? = undefined
    }

private fun RenderPipelineDescriptor.FragmentState.ColorTargetState.convert(): GPUColorTargetState =
    object : GPUColorTargetState {
        override var format: String = this@convert.format.actualName
        override var blend: GPUBlendState? = this@convert.blend.convert()
        override var writeMask: GPUColorWriteFlags? = this@convert.writeMask.value
    }

private fun RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.convert(): GPUBlendState =
    object : GPUBlendState {
        override var color: GPUBlendComponent = this@convert.color.convert()
        override var alpha: GPUBlendComponent = this@convert.alpha.convert()
    }

private fun RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent.convert(): GPUBlendComponent =
    object : GPUBlendComponent {
        override var operation: String? = this@convert.operation.stringValue
        override var srcFactor: String? = this@convert.srcFactor.stringValue
        override var dstFactor: String? = this@convert.dstFactor.stringValue
    }
