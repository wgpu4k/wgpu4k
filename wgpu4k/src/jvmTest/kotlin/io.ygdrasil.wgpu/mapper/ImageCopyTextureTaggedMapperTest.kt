package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ygdrasil.wgpu.GPUOrigin3DDict
import io.ygdrasil.wgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.Texture
import io.ygdrasil.wgpu.internal.jvm.WGPUImageCopyTexture
import io.ygdrasil.wgpu.internal.jvm.WGPUTexture

class ImageCopyTextureTaggedMapperTest : FreeSpec({

    "test mapping" {
        // Given
        val imageCopyTextureTagged = ImageCopyTextureTagged(
            texture = Texture(WGPUTexture()),
            origin = GPUOrigin3DDict(0, 0, 0)
        )

        // When
        val result: WGPUImageCopyTexture = imageCopyTextureTaggedMapper.map(imageCopyTextureTagged)

        // Then
        result.apply {
            texture shouldNotBe null
            origin shouldNotBe null
            origin?.apply {
                x shouldBe 0
                y shouldBe 0
                z shouldBe 0
            }
        }

    }

})