package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.EnumerationTransformer
import io.ygdrasil.wgpu.TextureViewDescriptor
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureViewDescriptor
import io.ygdrasil.wgpu.mapper

internal val textureViewDescriptorMapper = mapper<TextureViewDescriptor, WGPUTextureViewDescriptor> {
    TextureViewDescriptor::dimension mappedTo WGPUTextureViewDescriptor::dimension withTransformer EnumerationTransformer()
    TextureViewDescriptor::aspect mappedTo WGPUTextureViewDescriptor::aspect withTransformer EnumerationTransformer()
    TextureViewDescriptor::format mappedTo WGPUTextureViewDescriptor::format withTransformer EnumerationTransformer()
}