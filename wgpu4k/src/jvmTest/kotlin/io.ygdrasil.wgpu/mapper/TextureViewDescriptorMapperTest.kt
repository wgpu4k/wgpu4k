package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.ygdrasil.wgpu.TextureViewDescriptor
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureViewDescriptor

class TextureViewDescriptorMapperTest : FreeSpec({

    "test mapping" {
        // Given
        val textureViewDescriptor = TextureViewDescriptor()


        // When
        val result: WGPUTextureViewDescriptor = textureViewDescriptorMapper.map(textureViewDescriptor)

        // Then
        // TODO add tests
    }
})