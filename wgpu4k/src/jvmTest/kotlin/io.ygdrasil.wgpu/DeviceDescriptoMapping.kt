package io.ygdrasil.wgpu

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureDescriptor


class DeviceDescriptoMapping : FreeSpec({


	"test texture descriptor mapping" {

		// Given
		val descriptor = TextureDescriptor(
			size = GPUExtent3DDictStrict(100, 150, 5),
			format = TextureFormat.depth24plus,
			usage = TextureUsage.renderattachment.value,
		)


		// When
		val result: WGPUTextureDescriptor = textureDescriptorMapper.map(descriptor)

		result.apply {
			size.width shouldBe 100
			size.height shouldBe 150
			size.depthOrArrayLayers shouldBe 5
			format shouldBe TextureFormat.depth24plus.value
			usage shouldBe TextureUsage.renderattachment.value

			//TODO add more test
		}

	}

})
