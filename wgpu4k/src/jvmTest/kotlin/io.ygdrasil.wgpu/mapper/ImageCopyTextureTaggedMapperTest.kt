package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ygdrasil.wgpu.GPUOrigin3DDict
import io.ygdrasil.wgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.Texture
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUImageCopyTexture
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUOrigin3D
import java.lang.foreign.MemorySegment

class ImageCopyTextureTaggedMapperTest : FreeSpec({

    "test mapping" {

        // Given
        confined { arena ->
            val imageCopyTextureTagged = ImageCopyTextureTagged(
                texture = Texture(MemorySegment.NULL),
                origin = GPUOrigin3DDict(0, 0, 0)
            )

            // When
            val result: MemorySegment = arena.map(imageCopyTextureTagged)

            // Then
            result.apply {
                WGPUImageCopyTexture.texture(result) shouldNotBe null
                WGPUImageCopyTexture.origin(result) shouldNotBe null
                WGPUImageCopyTexture.origin(result)?.let { origin ->
                    WGPUOrigin3D.x(origin) shouldBe 0
                    WGPUOrigin3D.y(result) shouldBe 0
                    WGPUOrigin3D.z(result) shouldBe 0
                }
            }
        }

    }

})