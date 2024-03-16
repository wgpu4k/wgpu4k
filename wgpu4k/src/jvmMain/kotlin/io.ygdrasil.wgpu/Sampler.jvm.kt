package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUSampler
import io.ygdrasil.wgpu.internal.jvm.wgpuSamplerRelease

actual class Sampler(internal val handler: WGPUSampler?) : AutoCloseable {

	override fun close() {
		wgpuSamplerRelease(handler)
	}
}