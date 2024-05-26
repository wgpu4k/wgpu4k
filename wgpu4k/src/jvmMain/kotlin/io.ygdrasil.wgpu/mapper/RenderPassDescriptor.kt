package io.ygdrasil.wgpu.mapper

import com.sun.jna.Pointer
import com.sun.jna.Structure
import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureView
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureViewImpl
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUColor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURenderPassColorAttachment
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURenderPassDepthStencilAttachment
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURenderPassDescriptor
import io.ygdrasil.wgpu.internal.jvm.toMemory
import io.ygdrasil.wgpu.internal.jvm.toPointer
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

internal fun Arena.map(input: RenderPassDescriptor): MemorySegment =
    WGPURenderPassDescriptor.allocate(this).also { renderPassDescriptor ->
        println("render pass descriptor $renderPassDescriptor")
        if (input.label != null) WGPURenderPassDescriptor.label(renderPassDescriptor, allocateFrom(input.label))

        if (input.colorAttachments.isNotEmpty()) {
            WGPURenderPassDescriptor.colorAttachmentCount(renderPassDescriptor, input.colorAttachments.size.toLong())
            val colorAttachments =
                    WGPURenderPassColorAttachment.allocateArray(input.colorAttachments.size.toLong(), this)
            println("color attachments $colorAttachments")

            input.colorAttachments.forEachIndexed { index, colorAttachment ->
                map(colorAttachment, WGPURenderPassColorAttachment.asSlice(colorAttachments, index.toLong()))
            }

            WGPURenderPassDescriptor.colorAttachments(renderPassDescriptor, colorAttachments)

            val array: Array<WGPURenderPassColorAttachment2.ByReference> =
                input.colorAttachments.toStructureArray { colorTarget ->
                    colorAttachmentMapper.map(colorTarget, this)
                }
            WGPURenderPassDescriptor.colorAttachments(renderPassDescriptor, array.first().toMemory())

        }

        if (input.depthStencilAttachment != null) WGPURenderPassDescriptor.depthStencilAttachment(
            renderPassDescriptor,
            map(input.depthStencilAttachment)
        )
        //TODO map this var occlusionQuerySet: GPUQuerySet?
        //TODO map this var timestampWrites: GPURenderPassTimestampWrites?
        //TODO map this var maxDrawCount: GPUSize64
        // check WGPURenderPassDescriptorMaxDrawCount
    }

internal fun Arena.map(input: RenderPassDescriptor.ColorAttachment, output: MemorySegment) {
    println("color attachment $output")
    WGPURenderPassColorAttachment.view(output, input.view.handler)
    WGPURenderPassColorAttachment.loadOp(output, input.loadOp.value)
    WGPURenderPassColorAttachment.storeOp(output, input.storeOp.value)
    // TODO find how to map this
    //if (input.depthSlice != null) WGPURenderPassColorAttachment.depthSlice(output, input.depthSlice)
    if (input.resolveTarget != null) WGPURenderPassColorAttachment.resolveTarget(output, input.resolveTarget.handler)
    map(input.clearValue, WGPURenderPassColorAttachment.clearValue(output))
}

internal fun Arena.map(input: Array<Number>, output: MemorySegment) = input.map { it.toDouble() }
    .also { (r, g, b, a) ->
        WGPUColor.r(output, r)
        WGPUColor.g(output, g)
        WGPUColor.b(output, b)
        WGPUColor.a(output, a)
    }


internal fun Arena.map(input: RenderPassDescriptor.RenderPassDepthStencilAttachment): MemorySegment =
    WGPURenderPassDepthStencilAttachment.allocate(this).also { depthStencilAttachment ->
        WGPURenderPassDepthStencilAttachment.view(depthStencilAttachment, input.view.handler)
        if (input.depthClearValue != null) WGPURenderPassDepthStencilAttachment.depthClearValue(
            depthStencilAttachment,
            input.depthClearValue
        )
        if (input.depthLoadOp != null) WGPURenderPassDepthStencilAttachment.depthLoadOp(
            depthStencilAttachment,
            input.depthLoadOp.value
        )
        if (input.depthStoreOp != null) WGPURenderPassDepthStencilAttachment.depthStoreOp(
            depthStencilAttachment,
            input.depthStoreOp.value
        )
        WGPURenderPassDepthStencilAttachment.depthReadOnly(depthStencilAttachment, input.depthReadOnly.toInt())
        WGPURenderPassDepthStencilAttachment.stencilClearValue(depthStencilAttachment, input.stencilClearValue.toInt())
        if (input.stencilLoadOp != null) WGPURenderPassDepthStencilAttachment.stencilLoadOp(
            depthStencilAttachment,
            input.stencilLoadOp.value
        )
        if (input.stencilStoreOp != null) WGPURenderPassDepthStencilAttachment.stencilStoreOp(
            depthStencilAttachment,
            input.stencilStoreOp.value
        )
        WGPURenderPassDepthStencilAttachment.stencilReadOnly(depthStencilAttachment, input.stencilReadOnly.toInt())
    }

internal val colorAttachmentMapper =
    mapper<RenderPassDescriptor.ColorAttachment, WGPURenderPassColorAttachment2.ByReference> {
        RenderPassDescriptor.ColorAttachment::view mappedTo WGPURenderPassColorAttachment2.ByReference::view withTransformer MappingTransformer {
            WGPUTextureViewImpl(it.originalValue?.handler?.toPointer())
        }
        RenderPassDescriptor.ColorAttachment::resolveTarget mappedTo WGPURenderPassColorAttachment2.ByReference::resolveTarget withTransformer MappingTransformer {
            WGPUTextureView(it.originalValue?.handler?.toPointer())
        }
        RenderPassDescriptor.ColorAttachment::loadOp mappedTo WGPURenderPassColorAttachment2.ByReference::loadOp withTransformer EnumerationTransformer()
        RenderPassDescriptor.ColorAttachment::storeOp mappedTo WGPURenderPassColorAttachment2.ByReference::storeOp withTransformer EnumerationTransformer()
        RenderPassDescriptor.ColorAttachment::clearValue mappedTo WGPURenderPassColorAttachment2.ByReference::clearValue withTransformer MappingTransformer {
            it.originalValue?.toWGPUColor2()
        }
    }


private fun Array<Number>.toWGPUColor2() = map(Number::toDouble)
    .let { (r, g, b, a) ->
        WGPUColor2().also {
            it.r = r
            it.g = g
            it.b = b
            it.a = a
        }
    }


@Structure.FieldOrder("nextInChain", "view", "resolveTarget", "loadOp", "storeOp", "clearValue")
open class WGPURenderPassColorAttachment2 : Structure {
    /**
     * mapped from (typedef Optional[const WGPUChainedStruct] =
     * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
     */
    @JvmField
    var nextInChain: Pointer? = null

    /**
     * mapped from WGPUTextureView
     */
    @JvmField
    var view: WGPUTextureView = WGPUTextureViewImpl()

    /**
     * mapped from WGPUTextureView
     */
    @JvmField
    var resolveTarget: WGPUTextureView? = null

    /**
     * mapped from WGPULoadOp
     */
    @JvmField
    var loadOp: Int = 0

    /**
     * mapped from WGPUStoreOp
     */
    @JvmField
    var storeOp: Int = 0

    /**
     * mapped from WGPUColor
     */
    @JvmField
    var clearValue: WGPUColor2? = null

    constructor(pointer: Pointer?) : super(pointer)

    constructor()

    class ByReference(
        pointer: Pointer? = null,
    ) : WGPURenderPassColorAttachment2(pointer), Structure.ByReference

    class ByValue(
        pointer: Pointer? = null,
    ) : WGPURenderPassColorAttachment2(pointer), Structure.ByValue
}

@Structure.FieldOrder("r", "g", "b", "a")
open class WGPUColor2 : Structure {
    /**
     * mapped from double
     */
    @JvmField
    var r: Double = 0.0

    /**
     * mapped from double
     */
    @JvmField
    var g: Double = 0.0

    /**
     * mapped from double
     */
    @JvmField
    var b: Double = 0.0

    /**
     * mapped from double
     */
    @JvmField
    var a: Double = 0.0

    constructor(pointer: Pointer?) : super(pointer)

    constructor()

    class ByReference(
        pointer: Pointer? = null,
    ) : WGPUColor2(pointer), Structure.ByReference

    class ByValue(
        pointer: Pointer? = null,
    ) : WGPUColor2(pointer), Structure.ByValue
}