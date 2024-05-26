package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.TextureAspect
import io.ygdrasil.wgpu.TextureFormat
import io.ygdrasil.wgpu.TextureViewDescriptor
import io.ygdrasil.wgpu.TextureViewDimension
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUTextureViewDescriptor

class TextureViewDescriptorMapperTest : FreeSpec({

    "test mapping" {
        // Given
        confined { arena ->
            val textureViewDescriptor = TextureViewDescriptor(
                label = "TextureViewDescriptor",
                format = TextureFormat.astc4x4unorm,
                dimension = TextureViewDimension._1d,
                baseMipLevel = 2,
                baseArrayLayer = 3
            )

            // When
            val result = arena.map(textureViewDescriptor)

            // Then
            WGPUTextureViewDescriptor.label(result).getString(0) shouldBe "TextureViewDescriptor"
            WGPUTextureViewDescriptor.format(result) shouldBe TextureFormat.astc4x4unorm.value
            WGPUTextureViewDescriptor.dimension(result) shouldBe TextureViewDimension._1d.value
            WGPUTextureViewDescriptor.aspect(result) shouldBe TextureAspect.all.value
            WGPUTextureViewDescriptor.baseMipLevel(result) shouldBe 2
            WGPUTextureViewDescriptor.mipLevelCount(result) shouldBe 1
            WGPUTextureViewDescriptor.baseArrayLayer(result) shouldBe 3
            WGPUTextureViewDescriptor.arrayLayerCount(result) shouldBe 1
        }
    }
})