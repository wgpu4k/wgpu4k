package io.ygdrasil.wgpu.mapper

import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.*

internal val renderPipelineDescriptorMapper = mapper<RenderPipelineDescriptor, WGPURenderPipelineDescriptor> {
    RenderPipelineDescriptor::layout mappedTo WGPURenderPipelineDescriptor::layout withTransformer MappingTransformer { it.originalValue?.handler }
    RenderPipelineDescriptor::fragment mappedTo WGPURenderPipelineDescriptor::fragment withTransformer MappingTransformer {
        it.originalValue?.let(fragmentMapper::map)
    }
    RenderPipelineDescriptor::depthStencil mappedTo WGPURenderPipelineDescriptor::depthStencil withTransformer MappingTransformer {
        it.originalValue?.let(depthStencilStateMapper::map)
    }
    RenderPipelineDescriptor::vertex mappedTo WGPURenderPipelineDescriptor::vertex withTransformer MappingTransformer {
        it.originalValue?.let(vertexStateMapper::map)
    }
    RenderPipelineDescriptor::primitive mappedTo WGPURenderPipelineDescriptor::primitive withTransformer MappingTransformer {
        it.originalValue?.let(primitiveStateMapper::map)
    }
    RenderPipelineDescriptor::multisample mappedTo WGPURenderPipelineDescriptor::multisample withTransformer MappingTransformer {
        it.originalValue?.let(multisampleStateMapper::map)
    }
}

private val multisampleStateMapper = mapper<RenderPipelineDescriptor.MultisampleState, WGPUMultisampleState> {
    RenderPipelineDescriptor.MultisampleState::mask mappedTo WGPUMultisampleState::mask withTransformer MappingTransformer { it.originalValue as Int? }
    RenderPipelineDescriptor.MultisampleState::alphaToCoverageEnabled mappedTo WGPUMultisampleState::alphaToCoverageEnabled withTransformer BooleanToIntTransformer()
}

private val primitiveStateMapper = mapper<RenderPipelineDescriptor.PrimitiveState, WGPUPrimitiveState> {
    RenderPipelineDescriptor.PrimitiveState::frontFace mappedTo WGPUPrimitiveState::frontFace withTransformer EnumerationTransformer()
    RenderPipelineDescriptor.PrimitiveState::cullMode mappedTo WGPUPrimitiveState::cullMode withTransformer EnumerationTransformer()
    RenderPipelineDescriptor.PrimitiveState::topology mappedTo WGPUPrimitiveState::topology withTransformer EnumerationTransformer()
    RenderPipelineDescriptor.PrimitiveState::stripIndexFormat mappedTo WGPUPrimitiveState::stripIndexFormat withTransformer EnumerationTransformer()
}

private val fragmentMapper = mapper<RenderPipelineDescriptor.FragmentState, WGPUFragmentState.ByReference> {
    RenderPipelineDescriptor.FragmentState::module mappedTo WGPUFragmentState.ByReference::module withTransformer MappingTransformer { WGPUShaderModuleImpl(it.originalValue?.handler?.toPointer()) }
    RenderPipelineDescriptor.FragmentState::targets mappedTo WGPUFragmentState.ByReference::targets withTransformer MappingTransformer<Array<RenderPipelineDescriptor.FragmentState.ColorTargetState>, Array<WGPUColorTargetState.ByReference>> {
        it.originalValue?.toStructureArray { colorTarget ->
            colorTargetStateMapper.map(colorTarget, this)
        }
    }
}

private val colorTargetStateMapper =
    mapper<RenderPipelineDescriptor.FragmentState.ColorTargetState, WGPUColorTargetState.ByReference> {
        RenderPipelineDescriptor.FragmentState.ColorTargetState::format mappedTo WGPUColorTargetState.ByReference::format withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.FragmentState.ColorTargetState::writeMask mappedTo WGPUColorTargetState.ByReference::writeMask withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.FragmentState.ColorTargetState::blend mappedTo WGPUColorTargetState.ByReference::blend withTransformer MappingTransformer {
            it.originalValue?.let(
                blendStateMapper::map
            )
        }
    }

private val blendStateMapper =
    mapper<RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState, WGPUBlendState.ByReference> {
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState::color mappedTo WGPUBlendState.ByReference::color withTransformer MappingTransformer {
            it.originalValue?.let(
                blendComponentMapper::map
            )
        }
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState::alpha mappedTo WGPUBlendState.ByReference::alpha withTransformer MappingTransformer {
            it.originalValue?.let(
                blendComponentMapper::map
            )
        }
    }

private val blendComponentMapper =
    mapper<RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent, WGPUBlendComponent> {
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent::dstFactor mappedTo WGPUBlendComponent::dstFactor withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent::operation mappedTo WGPUBlendComponent::operation withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent::srcFactor mappedTo WGPUBlendComponent::srcFactor withTransformer EnumerationTransformer()
    }

private val depthStencilStateMapper =
    mapper<RenderPipelineDescriptor.DepthStencilState, WGPUDepthStencilState.ByReference> {
        RenderPipelineDescriptor.DepthStencilState::format mappedTo WGPUDepthStencilState.ByReference::format withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState::depthCompare mappedTo WGPUDepthStencilState.ByReference::depthCompare withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState::depthWriteEnabled mappedTo WGPUDepthStencilState.ByReference::depthWriteEnabled withTransformer BooleanToIntTransformer()
        RenderPipelineDescriptor.DepthStencilState::stencilReadMask mappedTo WGPUDepthStencilState.ByReference::stencilReadMask withTransformer LongToIntTransformer()
        RenderPipelineDescriptor.DepthStencilState::stencilWriteMask mappedTo WGPUDepthStencilState.ByReference::stencilWriteMask withTransformer LongToIntTransformer()
        RenderPipelineDescriptor.DepthStencilState::stencilFront mappedTo WGPUDepthStencilState.ByReference::stencilFront withTransformer MappingTransformer {
            it.originalValue?.let(
                stencilFaceStateMapper::map
            )
        }
        RenderPipelineDescriptor.DepthStencilState::stencilBack mappedTo WGPUDepthStencilState.ByReference::stencilBack withTransformer MappingTransformer {
            it.originalValue?.let(
                stencilFaceStateMapper::map
            )
        }
    }

private val stencilFaceStateMapper =
    mapper<RenderPipelineDescriptor.DepthStencilState.StencilFaceState, WGPUStencilFaceState> {
        RenderPipelineDescriptor.DepthStencilState.StencilFaceState::compare mappedTo WGPUStencilFaceState::compare withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState.StencilFaceState::failOp mappedTo WGPUStencilFaceState::failOp withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState.StencilFaceState::depthFailOp mappedTo WGPUStencilFaceState::depthFailOp withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState.StencilFaceState::passOp mappedTo WGPUStencilFaceState::passOp withTransformer EnumerationTransformer()
    }

private val vertexStateMapper = mapper<RenderPipelineDescriptor.VertexState, WGPUVertexState> {
    RenderPipelineDescriptor.VertexState::module mappedTo WGPUVertexState::module withTransformer MappingTransformer { WGPUShaderModuleImpl(it.originalValue?.handler?.toPointer()) }
    RenderPipelineDescriptor.VertexState::buffers mappedTo WGPUVertexState::buffers withTransformer MappingTransformer<Array<RenderPipelineDescriptor.VertexState.VertexBufferLayout>, Array<WGPUVertexBufferLayout.ByReference>> {
        it.originalValue?.toStructureArray { colorTarget ->
            vertexBufferLayoutMapper.map(colorTarget, this)
        }
    }
}

private val vertexBufferLayoutMapper = mapper<RenderPipelineDescriptor.VertexState.VertexBufferLayout, WGPUVertexBufferLayout.ByReference> {
    RenderPipelineDescriptor.VertexState.VertexBufferLayout::stepMode mappedTo WGPUVertexBufferLayout.ByReference::stepMode withTransformer EnumerationTransformer()
    RenderPipelineDescriptor.VertexState.VertexBufferLayout::attributes mappedTo WGPUVertexBufferLayout.ByReference::attributes withTransformer MappingTransformer<Array<RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute>, Array<WGPUVertexAttribute.ByReference>> {
        it.originalValue?.toStructureArray { colorTarget ->
            vertexAttributeMapper.map(colorTarget, this)
        }
    }
}

private val vertexAttributeMapper = mapper<RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute, WGPUVertexAttribute.ByReference> {
    RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute::format mappedTo WGPUVertexAttribute.ByReference::format withTransformer EnumerationTransformer()
}