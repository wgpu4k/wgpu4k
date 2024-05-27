package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSamplerDescriptor
import java.lang.foreign.MemorySegment

class SamplerDescriptorMapperTest : FreeSpec({

    "test mapping" {
        // Given
        confined { arena ->
            val descriptor = SamplerDescriptor(
                label = "SamplerDescriptor",
                addressModeU = AddressMode.clamptoedge,
                addressModeV = AddressMode.clamptoedge,
                addressModeW = AddressMode.clamptoedge,
                magFilter = FilterMode.linear,
                minFilter = FilterMode.linear,
                mipmapFilter = MipmapFilterMode.linear,
                lodMinClamp = -1000f,
                lodMaxClamp = 1000f,
                compare = CompareFunction.always,
                maxAnisotropy = 1,
            )

            // when
            val actual: MemorySegment = arena.map(descriptor)

            // then
            WGPUSamplerDescriptor.label(actual).getString(0) shouldBe "SamplerDescriptor"

            WGPUSamplerDescriptor.addressModeU(actual) shouldBe AddressMode.clamptoedge.value
            WGPUSamplerDescriptor.addressModeV(actual) shouldBe AddressMode.clamptoedge.value
            WGPUSamplerDescriptor.addressModeW(actual) shouldBe AddressMode.clamptoedge.value

            WGPUSamplerDescriptor.magFilter(actual) shouldBe FilterMode.linear.value
            WGPUSamplerDescriptor.minFilter(actual) shouldBe FilterMode.linear.value

            WGPUSamplerDescriptor.mipmapFilter(actual) shouldBe MipmapFilterMode.linear.value

            WGPUSamplerDescriptor.lodMinClamp(actual) shouldBe -1000f
            WGPUSamplerDescriptor.lodMaxClamp(actual) shouldBe 1000f

            WGPUSamplerDescriptor.compare(actual) shouldBe CompareFunction.always.value
            WGPUSamplerDescriptor.maxAnisotropy(actual) shouldBe 1

        }
    }

})