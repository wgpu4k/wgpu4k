package io.ygdrasil.wgpu.mapper

import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.WGPUColor
import io.ygdrasil.wgpu.internal.jvm.WGPURenderPassColorAttachment
import io.ygdrasil.wgpu.internal.jvm.WGPURenderPassDepthStencilAttachment
import io.ygdrasil.wgpu.internal.jvm.WGPURenderPassDescriptor
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureViewImpl
import io.ygdrasil.wgpu.internal.jvm.toPointer

internal val renderPassDescriptorMapper = mapper<RenderPassDescriptor, WGPURenderPassDescriptor> {
    RenderPassDescriptor::colorAttachments mappedTo WGPURenderPassDescriptor::colorAttachments withTransformer MappingTransformer<Array<RenderPassDescriptor.ColorAttachment>, Array<WGPURenderPassColorAttachment.ByReference>> {
        it.originalValue?.toStructureArray { colorTarget ->
            colorAttachmentMapper.map(colorTarget, this)
        }
    }
    RenderPassDescriptor::depthStencilAttachment mappedTo WGPURenderPassDescriptor::depthStencilAttachment withTransformer MappingTransformer {
        it.originalValue?.let(renderPassDepthStencilAttachmentMapper::map)
    }
}

internal val renderPassDepthStencilAttachmentMapper = mapper<RenderPassDescriptor.RenderPassDepthStencilAttachment, WGPURenderPassDepthStencilAttachment.ByReference> {
    RenderPassDescriptor.RenderPassDepthStencilAttachment::stencilStoreOp mappedTo WGPURenderPassDepthStencilAttachment.ByReference::stencilStoreOp withTransformer EnumerationTransformer()
    RenderPassDescriptor.RenderPassDepthStencilAttachment::depthLoadOp mappedTo WGPURenderPassDepthStencilAttachment.ByReference::depthLoadOp withTransformer EnumerationTransformer()
    RenderPassDescriptor.RenderPassDepthStencilAttachment::depthStoreOp mappedTo WGPURenderPassDepthStencilAttachment.ByReference::depthStoreOp withTransformer EnumerationTransformer()
    RenderPassDescriptor.RenderPassDepthStencilAttachment::stencilLoadOp mappedTo WGPURenderPassDepthStencilAttachment.ByReference::stencilLoadOp withTransformer EnumerationTransformer()
    RenderPassDescriptor.RenderPassDepthStencilAttachment::depthReadOnly mappedTo WGPURenderPassDepthStencilAttachment.ByReference::depthReadOnly withTransformer BooleanToIntTransformer()
    RenderPassDescriptor.RenderPassDepthStencilAttachment::view mappedTo WGPURenderPassDepthStencilAttachment.ByReference::view withTransformer MappingTransformer {
        WGPUTextureViewImpl(it.originalValue?.handler?.toPointer())
    }
    RenderPassDescriptor.RenderPassDepthStencilAttachment::stencilClearValue mappedTo WGPURenderPassDepthStencilAttachment.ByReference::stencilClearValue withTransformer LongToIntTransformer()
    RenderPassDescriptor.RenderPassDepthStencilAttachment::stencilReadOnly mappedTo WGPURenderPassDepthStencilAttachment.ByReference::stencilReadOnly withTransformer BooleanToIntTransformer()
}

internal val colorAttachmentMapper =
    mapper<RenderPassDescriptor.ColorAttachment, WGPURenderPassColorAttachment.ByReference> {
        RenderPassDescriptor.ColorAttachment::view mappedTo WGPURenderPassColorAttachment.ByReference::view withTransformer MappingTransformer {
            WGPUTextureViewImpl(it.originalValue?.handler?.toPointer())
        }
        RenderPassDescriptor.ColorAttachment::resolveTarget mappedTo WGPURenderPassColorAttachment.ByReference::resolveTarget withTransformer MappingTransformer {
            WGPUTextureViewImpl(it.originalValue?.handler?.toPointer())
        }
        RenderPassDescriptor.ColorAttachment::loadOp mappedTo WGPURenderPassColorAttachment.ByReference::loadOp withTransformer EnumerationTransformer()
        RenderPassDescriptor.ColorAttachment::storeOp mappedTo WGPURenderPassColorAttachment.ByReference::storeOp withTransformer EnumerationTransformer()
        RenderPassDescriptor.ColorAttachment::clearValue mappedTo WGPURenderPassColorAttachment.ByReference::clearValue withTransformer MappingTransformer {
            it.originalValue?.toWGPUColor()
        }
    }


private fun Array<Number>.toWGPUColor() = map(Number::toDouble)
    .let { (r, g, b, a) ->
        WGPUColor().also {
            it.r = r
            it.g = g
            it.b = b
            it.a = a
        }
    }