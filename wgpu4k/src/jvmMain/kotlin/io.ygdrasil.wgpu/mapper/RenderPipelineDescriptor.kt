package io.ygdrasil.wgpu.mapper

import com.sun.jna.Structure
import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURenderPipelineDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUVertexState
import io.ygdrasil.wgpu.internal.jvm.toMemory
import io.ygdrasil.wgpu.internal.jvm.toPointer
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

/**
 * data class RenderPipelineDescriptor(
 *     var vertex: VertexState,
 *     var label: String? = null,
 *     var layout: PipelineLayout? = null,
 *     var primitive: PrimitiveState = PrimitiveState(),
 *     var depthStencil: DepthStencilState? = null,
 *     var fragment: FragmentState? = null,
 *     var multisample: MultisampleState = MultisampleState(),
 * ) {
 *
 *     data class VertexState(
 *         var module: ShaderModule,
 *         var entryPoint: String = "main",
 *         var constants: Map<String, GPUPipelineConstantValue>? = null,
 *         var buffers: Array<VertexBufferLayout> = arrayOf(),
 *     ) {
 *         data class VertexBufferLayout(
 *             var arrayStride: GPUSize64,
 *             var attributes: Array<VertexAttribute> = arrayOf(),
 *             var stepMode: VertexStepMode = VertexStepMode.vertex,
 *         ) {
 *             data class VertexAttribute(
 *                 var format: VertexFormat,
 *                 var offset: GPUSize64,
 *                 var shaderLocation: GPUIndex32,
 *             )
 *
 *         }
 *     }
 *
 *
 *     data class PrimitiveState(
 *         var topology: PrimitiveTopology = PrimitiveTopology.trianglelist,
 *         var stripIndexFormat: IndexFormat? = null,
 *         var frontFace: FrontFace = FrontFace.ccw,
 *         var cullMode: CullMode = CullMode.none,
 *         var unclippedDepth: Boolean = false,
 *     )
 *
 *     data class DepthStencilState(
 *         var format: TextureFormat,
 *         var depthWriteEnabled: Boolean? = null,
 *         var depthCompare: CompareFunction? = null,
 *
 *         var stencilFront: StencilFaceState = StencilFaceState(),
 *         var stencilBack: StencilFaceState = StencilFaceState(),
 *         var stencilReadMask: GPUStencilValue = 0xFFFFFFFF,
 *         var stencilWriteMask: GPUStencilValue = 0xFFFFFFFF,
 *         var depthBias: GPUDepthBias = 0,
 *         var depthBiasSlopeScale: Float = 0f,
 *         var depthBiasClamp: Float = 0f,
 *     ) {
 *         data class StencilFaceState(
 *             var compare: CompareFunction = CompareFunction.always,
 *             var failOp: StencilOperation? = StencilOperation.keep,
 *             var depthFailOp: StencilOperation? = StencilOperation.keep,
 *             var passOp: StencilOperation? = StencilOperation.keep,
 *         )
 *     }
 *
 *
 *     data class MultisampleState(
 *         var count: GPUSize32 = 1,
 *         var mask: GPUSampleMask = 0xFFFFFFFFu,
 *         var alphaToCoverageEnabled: Boolean = false
 *     )
 *
 *     data class FragmentState(
 *         var module: ShaderModule,
 *         var targets: Array<ColorTargetState> = arrayOf(),
 *         var entryPoint: String = "main"
 *     ) {
 *
 *         data class ColorTargetState(
 *             var format: TextureFormat,
 *             var writeMask: ColorWriteMask = ColorWriteMask.all,
 *             var blend: BlendState = BlendState()
 *         ) {
 *             data class BlendState(
 *                 var color: BlendComponent = BlendComponent(),
 *                 var alpha: BlendComponent = BlendComponent()
 *             ) {
 *                 data class BlendComponent(
 *                     var operation: BlendOperation = BlendOperation.add,
 *                     var srcFactor: BlendFactor = BlendFactor.one,
 *                     var dstFactor: BlendFactor = BlendFactor.zero
 *                 )
 *             }
 *         }
 *     }
 * }
 */

fun Structure.toMemory() = also { it.write() }.pointer.toMemory()

internal fun Arena.map(input: RenderPipelineDescriptor) = WGPURenderPipelineDescriptor.allocate(this).also { output ->
    map(input.vertex, WGPURenderPipelineDescriptor.vertex(output))
    if (input.label != null) WGPURenderPipelineDescriptor.label(output, allocateFrom(input.label))
    // TODO map this
    WGPURenderPipelineDescriptor.layout(output, MemorySegment.NULL)
    WGPURenderPipelineDescriptor.primitive(output, primitiveStateMapper.map<RenderPipelineDescriptor.PrimitiveState, io.ygdrasil.wgpu.internal.jvm.WGPUPrimitiveState>(input.primitive).toMemory())
    if (input.depthStencil != null) WGPURenderPipelineDescriptor.depthStencil(output, depthStencilStateMapper.map<RenderPipelineDescriptor.DepthStencilState, io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference>(input.depthStencil).toMemory())
    if (input.fragment != null) WGPURenderPipelineDescriptor.fragment(output, fragmentMapper.map<RenderPipelineDescriptor.FragmentState, io.ygdrasil.wgpu.internal.jvm.WGPUFragmentState.ByReference>(input.fragment).toMemory())
    WGPURenderPipelineDescriptor.multisample(output, multisampleStateMapper.map<RenderPipelineDescriptor.MultisampleState, io.ygdrasil.wgpu.internal.jvm.WGPUMultisampleState>(input.multisample).toMemory())
}


private fun Arena.map(input: RenderPipelineDescriptor.VertexState, output: MemorySegment) {
    WGPUVertexState.module(output, input.module.handler)
    WGPUVertexState.entryPoint(output, allocateFrom(input.entryPoint))
    // TODO learn how to map this
    WGPUVertexState.constants(output, MemorySegment.NULL)
    WGPUVertexState.constantCount(output, 0L)
    // TODO map this
    //WGPUVertexState.buffers(output, )
    WGPUVertexState.bufferCount(output, input.buffers.size.toLong())
}

internal val renderPipelineDescriptorMapper = mapper<RenderPipelineDescriptor, io.ygdrasil.wgpu.internal.jvm.WGPURenderPipelineDescriptor> {
    RenderPipelineDescriptor::layout mappedTo io.ygdrasil.wgpu.internal.jvm.WGPURenderPipelineDescriptor::layout withTransformer MappingTransformer { io.ygdrasil.wgpu.internal.jvm.WGPUPipelineLayoutImpl(it.originalValue?.handler?.toPointer()) }
    RenderPipelineDescriptor::fragment mappedTo io.ygdrasil.wgpu.internal.jvm.WGPURenderPipelineDescriptor::fragment withTransformer MappingTransformer {
        it.originalValue?.let(fragmentMapper::map)
    }
    RenderPipelineDescriptor::depthStencil mappedTo io.ygdrasil.wgpu.internal.jvm.WGPURenderPipelineDescriptor::depthStencil withTransformer MappingTransformer {
        it.originalValue?.let(depthStencilStateMapper::map)
    }
    RenderPipelineDescriptor::vertex mappedTo io.ygdrasil.wgpu.internal.jvm.WGPURenderPipelineDescriptor::vertex withTransformer MappingTransformer {
        it.originalValue?.let(vertexStateMapper::map)
    }
    RenderPipelineDescriptor::primitive mappedTo io.ygdrasil.wgpu.internal.jvm.WGPURenderPipelineDescriptor::primitive withTransformer MappingTransformer {
        it.originalValue?.let(primitiveStateMapper::map)
    }
    RenderPipelineDescriptor::multisample mappedTo io.ygdrasil.wgpu.internal.jvm.WGPURenderPipelineDescriptor::multisample withTransformer MappingTransformer {
        it.originalValue?.let(multisampleStateMapper::map)
    }
}

private val multisampleStateMapper = mapper<RenderPipelineDescriptor.MultisampleState, io.ygdrasil.wgpu.internal.jvm.WGPUMultisampleState> {
    RenderPipelineDescriptor.MultisampleState::mask mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUMultisampleState::mask withTransformer MappingTransformer { it.originalValue as Int? }
    RenderPipelineDescriptor.MultisampleState::alphaToCoverageEnabled mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUMultisampleState::alphaToCoverageEnabled withTransformer BooleanToIntTransformer()
}

private val primitiveStateMapper = mapper<RenderPipelineDescriptor.PrimitiveState, io.ygdrasil.wgpu.internal.jvm.WGPUPrimitiveState> {
    RenderPipelineDescriptor.PrimitiveState::frontFace mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUPrimitiveState::frontFace withTransformer EnumerationTransformer()
    RenderPipelineDescriptor.PrimitiveState::cullMode mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUPrimitiveState::cullMode withTransformer EnumerationTransformer()
    RenderPipelineDescriptor.PrimitiveState::topology mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUPrimitiveState::topology withTransformer EnumerationTransformer()
    RenderPipelineDescriptor.PrimitiveState::stripIndexFormat mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUPrimitiveState::stripIndexFormat withTransformer EnumerationTransformer()
}

private val fragmentMapper = mapper<RenderPipelineDescriptor.FragmentState, io.ygdrasil.wgpu.internal.jvm.WGPUFragmentState.ByReference> {
    RenderPipelineDescriptor.FragmentState::module mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUFragmentState.ByReference::module withTransformer MappingTransformer { io.ygdrasil.wgpu.internal.jvm.WGPUShaderModuleImpl(it.originalValue?.handler?.toPointer()) }
    RenderPipelineDescriptor.FragmentState::targets mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUFragmentState.ByReference::targets withTransformer MappingTransformer<Array<RenderPipelineDescriptor.FragmentState.ColorTargetState>, Array<io.ygdrasil.wgpu.internal.jvm.WGPUColorTargetState.ByReference>> {
        it.originalValue?.toStructureArray { colorTarget ->
            colorTargetStateMapper.map(colorTarget, this)
        }
    }
}

private val colorTargetStateMapper =
    mapper<RenderPipelineDescriptor.FragmentState.ColorTargetState, io.ygdrasil.wgpu.internal.jvm.WGPUColorTargetState.ByReference> {
        RenderPipelineDescriptor.FragmentState.ColorTargetState::format mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUColorTargetState.ByReference::format withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.FragmentState.ColorTargetState::writeMask mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUColorTargetState.ByReference::writeMask withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.FragmentState.ColorTargetState::blend mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUColorTargetState.ByReference::blend withTransformer MappingTransformer {
            it.originalValue?.let(
                blendStateMapper::map
            )
        }
    }

private val blendStateMapper =
    mapper<RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState, io.ygdrasil.wgpu.internal.jvm.WGPUBlendState.ByReference> {
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState::color mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUBlendState.ByReference::color withTransformer MappingTransformer {
            it.originalValue?.let(
                blendComponentMapper::map
            )
        }
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState::alpha mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUBlendState.ByReference::alpha withTransformer MappingTransformer {
            it.originalValue?.let(
                blendComponentMapper::map
            )
        }
    }

private val blendComponentMapper =
    mapper<RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent, io.ygdrasil.wgpu.internal.jvm.WGPUBlendComponent> {
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent::dstFactor mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUBlendComponent::dstFactor withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent::operation mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUBlendComponent::operation withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent::srcFactor mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUBlendComponent::srcFactor withTransformer EnumerationTransformer()
    }

private val depthStencilStateMapper =
    mapper<RenderPipelineDescriptor.DepthStencilState, io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference> {
        RenderPipelineDescriptor.DepthStencilState::format mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference::format withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState::depthCompare mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference::depthCompare withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState::depthWriteEnabled mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference::depthWriteEnabled withTransformer BooleanToIntTransformer()
        RenderPipelineDescriptor.DepthStencilState::stencilReadMask mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference::stencilReadMask withTransformer LongToIntTransformer()
        RenderPipelineDescriptor.DepthStencilState::stencilWriteMask mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference::stencilWriteMask withTransformer LongToIntTransformer()
        RenderPipelineDescriptor.DepthStencilState::stencilFront mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference::stencilFront withTransformer MappingTransformer {
            it.originalValue?.let(
                stencilFaceStateMapper::map
            )
        }
        RenderPipelineDescriptor.DepthStencilState::stencilBack mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference::stencilBack withTransformer MappingTransformer {
            it.originalValue?.let(
                stencilFaceStateMapper::map
            )
        }
    }

private val stencilFaceStateMapper =
    mapper<RenderPipelineDescriptor.DepthStencilState.StencilFaceState, io.ygdrasil.wgpu.internal.jvm.WGPUStencilFaceState> {
        RenderPipelineDescriptor.DepthStencilState.StencilFaceState::compare mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUStencilFaceState::compare withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState.StencilFaceState::failOp mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUStencilFaceState::failOp withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState.StencilFaceState::depthFailOp mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUStencilFaceState::depthFailOp withTransformer EnumerationTransformer()
        RenderPipelineDescriptor.DepthStencilState.StencilFaceState::passOp mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUStencilFaceState::passOp withTransformer EnumerationTransformer()
    }

private val vertexStateMapper = mapper<RenderPipelineDescriptor.VertexState, io.ygdrasil.wgpu.internal.jvm.WGPUVertexState> {
    RenderPipelineDescriptor.VertexState::module mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUVertexState::module withTransformer MappingTransformer { io.ygdrasil.wgpu.internal.jvm.WGPUShaderModuleImpl(it.originalValue?.handler?.toPointer()) }
    RenderPipelineDescriptor.VertexState::buffers mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUVertexState::buffers withTransformer MappingTransformer<Array<RenderPipelineDescriptor.VertexState.VertexBufferLayout>, Array<io.ygdrasil.wgpu.internal.jvm.WGPUVertexBufferLayout.ByReference>> {
        it.originalValue?.toStructureArray { colorTarget ->
            vertexBufferLayoutMapper.map(colorTarget, this)
        }
    }
}

private val vertexBufferLayoutMapper = mapper<RenderPipelineDescriptor.VertexState.VertexBufferLayout, io.ygdrasil.wgpu.internal.jvm.WGPUVertexBufferLayout.ByReference> {
    RenderPipelineDescriptor.VertexState.VertexBufferLayout::stepMode mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUVertexBufferLayout.ByReference::stepMode withTransformer EnumerationTransformer()
    RenderPipelineDescriptor.VertexState.VertexBufferLayout::attributes mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUVertexBufferLayout.ByReference::attributes withTransformer MappingTransformer<Array<RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute>, Array<io.ygdrasil.wgpu.internal.jvm.WGPUVertexAttribute.ByReference>> {
        it.originalValue?.toStructureArray { colorTarget ->
            vertexAttributeMapper.map(colorTarget, this)
        }
    }
}

private val vertexAttributeMapper = mapper<RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute, io.ygdrasil.wgpu.internal.jvm.WGPUVertexAttribute.ByReference> {
    RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute::format mappedTo io.ygdrasil.wgpu.internal.jvm.WGPUVertexAttribute.ByReference::format withTransformer EnumerationTransformer()
}