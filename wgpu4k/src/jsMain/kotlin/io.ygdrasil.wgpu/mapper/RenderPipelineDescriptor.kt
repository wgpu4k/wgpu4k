package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.js.*

internal fun map(input: RenderPipelineDescriptor): GPURenderPipelineDescriptor = object : GPURenderPipelineDescriptor {
    override var vertex: GPUVertexState = input.vertex.convert()
    override var layout: dynamic = input.layout?.handler ?: "auto"
    override var label: dynamic = input.label ?: undefined
    override var primitive: GPUPrimitiveState? = input.primitive.convert()
    override var depthStencil: GPUDepthStencilState? = input.depthStencil?.convert() ?: undefined
    override var fragment: GPUFragmentState? = input.fragment?.convert() ?: undefined
    override var multisample: GPUMultisampleState? = input.multisample.convert()
}

private fun RenderPipelineDescriptor.VertexState.convert(): GPUVertexState =
    object : GPUVertexState {
        override var module: GPUShaderModule = this@convert.module.handler
        override var entryPoint: String? = this@convert.entryPoint
        // TODO map this
        override var constants: Map<String, GPUPipelineConstantValue>? = undefined
        override var buffers: Array<GPUVertexBufferLayout?>? = this@convert.buffers
            .map { it.convert() }.toTypedArray()
    }

private fun RenderPipelineDescriptor.VertexState.VertexBufferLayout.convert(): GPUVertexBufferLayout =
    object : GPUVertexBufferLayout {
        override var arrayStride: GPUSize64 = this@convert.arrayStride
        override var attributes: Array<GPUVertexAttribute> = this@convert.attributes
            .map { it.convert() }.toTypedArray()
        override var stepMode: String? = this@convert.stepMode.name
    }

private fun RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute.convert(): GPUVertexAttribute =
    object : GPUVertexAttribute {
        override var format: String = this@convert.format.name
        override var offset: GPUSize64 = this@convert.offset
        override var shaderLocation: GPUIndex32 = this@convert.shaderLocation
    }

private fun RenderPipelineDescriptor.PrimitiveState.convert(): GPUPrimitiveState =
    object : GPUPrimitiveState {
        override var topology: String? = this@convert.topology.stringValue
        override var stripIndexFormat: String? = this@convert.stripIndexFormat?.name ?: undefined
        override var frontFace: String? = this@convert.frontFace.name
        override var cullMode: String? = this@convert.cullMode.name
        override var unclippedDepth: Boolean? = this@convert.unclippedDepth
    }

private fun RenderPipelineDescriptor.DepthStencilState.convert(): GPUDepthStencilState =
    object : GPUDepthStencilState {
        override var format: String = this@convert.format.actualName
        override var depthWriteEnabled: Boolean? = this@convert.depthWriteEnabled ?: undefined
        override var depthCompare: String? = this@convert.depthCompare?.stringValue ?: undefined
        override var stencilFront: GPUStencilFaceState? = this@convert.stencilFront.convert()
        override var stencilBack: GPUStencilFaceState? = this@convert.stencilBack.convert()
        override var stencilReadMask: GPUStencilValue? = this@convert.stencilReadMask
        override var stencilWriteMask: GPUStencilValue? = this@convert.stencilWriteMask
        override var depthBias: GPUDepthBias? = this@convert.depthBias
        override var depthBiasSlopeScale: Float? = this@convert.depthBiasSlopeScale
        override var depthBiasClamp: Float? = this@convert.depthBiasClamp
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
