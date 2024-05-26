package io.ygdrasil.wgpu.mapper

import com.sun.jna.Structure
import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.panama.*
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
    map(input.primitive, WGPURenderPipelineDescriptor.primitive(output))
    if (input.depthStencil != null) WGPURenderPipelineDescriptor.depthStencil(output, depthStencilStateMapper.map<RenderPipelineDescriptor.DepthStencilState, io.ygdrasil.wgpu.internal.jvm.WGPUDepthStencilState.ByReference>(input.depthStencil).toMemory())
    if (input.fragment != null) WGPURenderPipelineDescriptor.fragment(output, fragmentMapper.map<RenderPipelineDescriptor.FragmentState, io.ygdrasil.wgpu.internal.jvm.WGPUFragmentState.ByReference>(input.fragment).toMemory())
    map(input.multisample, WGPURenderPipelineDescriptor.multisample(output))
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