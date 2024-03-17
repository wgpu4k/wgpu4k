package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.EnumerationTransformer
import io.ygdrasil.wgpu.SamplerDescriptor
import io.ygdrasil.wgpu.internal.jvm.WGPUSamplerDescriptor
import io.ygdrasil.wgpu.mapper

internal val samplerDescriptorMapper = mapper<SamplerDescriptor, WGPUSamplerDescriptor> {
    SamplerDescriptor::minFilter mappedTo WGPUSamplerDescriptor::minFilter withTransformer EnumerationTransformer()
    SamplerDescriptor::magFilter mappedTo WGPUSamplerDescriptor::magFilter withTransformer EnumerationTransformer()

    SamplerDescriptor::addressModeU mappedTo WGPUSamplerDescriptor::addressModeU withTransformer EnumerationTransformer()
    SamplerDescriptor::addressModeV mappedTo WGPUSamplerDescriptor::addressModeV withTransformer EnumerationTransformer()
    SamplerDescriptor::addressModeW mappedTo WGPUSamplerDescriptor::addressModeW withTransformer EnumerationTransformer()

    SamplerDescriptor::mipmapFilter mappedTo WGPUSamplerDescriptor::mipmapFilter withTransformer EnumerationTransformer()

    SamplerDescriptor::compare mappedTo WGPUSamplerDescriptor::compare withTransformer EnumerationTransformer()
}