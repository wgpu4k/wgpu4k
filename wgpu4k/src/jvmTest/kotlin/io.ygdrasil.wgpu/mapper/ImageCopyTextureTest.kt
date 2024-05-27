package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.Origin3D
import io.ygdrasil.wgpu.Texture
import io.ygdrasil.wgpu.TextureAspect
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUImageCopyTexture
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUOrigin3D
import java.lang.foreign.MemorySegment

class ImageCopyTextureTest : FreeSpec({


    "test mapping" {

        // Given
        confined { arena ->
            val dummyMemory = MemorySegment.ofAddress(1)
            val bufferDescriptor = ImageCopyTexture(
                texture = Texture(dummyMemory),
                mipLevel = 10,
                origin = Origin3D(1, 2, 3),
            )

            // When
            val actual: MemorySegment = arena.map(bufferDescriptor)

            // Then
            WGPUImageCopyTexture.texture(actual) shouldBe dummyMemory
            WGPUImageCopyTexture.mipLevel(actual) shouldBe 10
            WGPUImageCopyTexture.aspect(actual) shouldBe TextureAspect.all.value
            WGPUImageCopyTexture.origin(actual).let { origin ->
                WGPUOrigin3D.x(origin) shouldBe 1
                WGPUOrigin3D.y(origin) shouldBe 2
                WGPUOrigin3D.z(origin) shouldBe 3
            }

        }

    }
})