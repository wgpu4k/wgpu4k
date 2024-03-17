package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.EnumerationTransformer
import io.ygdrasil.wgpu.GPUExtent3DDictStrictTransformer
import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureDescriptor
import io.ygdrasil.wgpu.mapper

internal val textureDescriptorMapper = mapper<TextureDescriptor, WGPUTextureDescriptor> {
    TextureDescriptor::format mappedTo WGPUTextureDescriptor::format withTransformer EnumerationTransformer()
    TextureDescriptor::dimension mappedTo WGPUTextureDescriptor::dimension withTransformer EnumerationTransformer()
    TextureDescriptor::size mappedTo WGPUTextureDescriptor::size withTransformer GPUExtent3DDictStrictTransformer()
}
