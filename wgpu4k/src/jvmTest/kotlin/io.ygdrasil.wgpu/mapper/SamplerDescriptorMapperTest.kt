package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.WGPUSamplerDescriptor

class SamplerDescriptorMapperTest : FreeSpec({

    "test mapping" {
        // Given
        val descriptor = SamplerDescriptor(
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
        val actual: WGPUSamplerDescriptor = samplerDescriptorMapper.map(descriptor)

        // then
        actual.apply {
            addressModeU shouldBe AddressMode.clamptoedge.value
            addressModeV shouldBe AddressMode.clamptoedge.value
            addressModeW shouldBe AddressMode.clamptoedge.value

            magFilter shouldBe FilterMode.linear.value
            minFilter shouldBe FilterMode.linear.value

            lodMinClamp shouldBe -1000f
            lodMaxClamp shouldBe 1000f
            maxAnisotropy shouldBe 1

            mipmapFilter shouldBe MipmapFilterMode.linear.value

            compare shouldBe CompareFunction.always.value
        }
    }

})