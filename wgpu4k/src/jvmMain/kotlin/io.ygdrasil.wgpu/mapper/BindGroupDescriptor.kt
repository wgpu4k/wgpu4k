package io.ygdrasil.wgpu.mapper

import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.BindGroupLayoutTransformer
import io.ygdrasil.wgpu.internal.jvm.WGPUBindGroupDescriptor
import io.ygdrasil.wgpu.internal.jvm.WGPUBindGroupEntry
import io.ygdrasil.wgpu.mapper
import io.ygdrasil.wgpu.toStructureArray

internal val bindGroupDescriptorMapper = mapper<BindGroupDescriptor, WGPUBindGroupDescriptor> {
    BindGroupDescriptor::layout mappedTo WGPUBindGroupDescriptor::layout withTransformer BindGroupLayoutTransformer()
    BindGroupDescriptor::entries mappedTo WGPUBindGroupDescriptor::entries withTransformer MappingTransformer<Array<BindGroupDescriptor.BindGroupEntry>, Array<WGPUBindGroupEntry.ByReference>> { context ->
        context.originalValue?.toStructureArray { bindGroupEntry ->
            binding = bindGroupEntry.binding
            when (val resource = bindGroupEntry.resource) {
                is BindGroupDescriptor.BufferBinding -> {
                    size = resource.size
                    offset = resource.offset
                    buffer = resource.buffer.handler
                }

                is BindGroupDescriptor.SamplerBinding -> sampler = resource.sampler.handler
                is BindGroupDescriptor.TextureViewBinding -> textureView = resource.view.handler
            }
        }
    }
}
