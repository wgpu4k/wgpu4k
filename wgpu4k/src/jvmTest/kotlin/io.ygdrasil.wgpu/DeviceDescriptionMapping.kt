package io.ygdrasil.wgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUExtent3D
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUTextureDescriptor
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class DeviceDescriptionMapping : FreeSpec({


    "test texture descriptor mapping" {

        // Given

        confined { arena ->
            val descriptor = TextureDescriptor(
                label = "TextureDescriptor",
                size = GPUExtent3DDictStrict(100, 150, 5),
                format = TextureFormat.depth24plus,
                usage = setOf(TextureUsage.renderattachment, TextureUsage.storagebinding),
                viewFormats = arrayOf(TextureFormat.astc4x4unorm, TextureFormat.astc10x10unorm)
            )


            // When
            val actual: MemorySegment = arena.map(descriptor)

            WGPUTextureDescriptor.label(actual).getString(0) shouldBe "TextureDescriptor"
            WGPUTextureDescriptor.format(actual) shouldBe TextureFormat.depth24plus.value
            WGPUTextureDescriptor.usage(actual) shouldBe (TextureUsage.renderattachment or TextureUsage.storagebinding)
            WGPUTextureDescriptor.sampleCount(actual) shouldBe 1
            WGPUTextureDescriptor.mipLevelCount(actual) shouldBe 1
            WGPUTextureDescriptor.dimension(actual) shouldBe TextureDimension._2d.value
            WGPUTextureDescriptor.size(actual).let { size ->
                WGPUExtent3D.width(size) shouldBe 100
                WGPUExtent3D.height(size) shouldBe 150
                WGPUExtent3D.depthOrArrayLayers(size) shouldBe 5
            }

            WGPUTextureDescriptor.viewFormatCount(actual) shouldBe 2
            WGPUTextureDescriptor.viewFormats(actual).let { viewFormats ->
                viewFormats.getAtIndex(ValueLayout.JAVA_INT, 0) shouldBe TextureFormat.astc4x4unorm.value
                viewFormats.getAtIndex(ValueLayout.JAVA_INT, 1) shouldBe TextureFormat.astc10x10unorm.value
            }
        }
    }

})
