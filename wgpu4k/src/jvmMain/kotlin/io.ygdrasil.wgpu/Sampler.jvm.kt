package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUSampler
import io.ygdrasil.wgpu.internal.jvm.logUnitNative
import io.ygdrasil.wgpu.internal.jvm.wgpuSamplerRelease

actual class Sampler(internal val handler: WGPUSampler?) : AutoCloseable {

	actual override fun close() {
		logUnitNative { "wgpuSamplerRelease" to listOf(handler) }
		wgpuSamplerRelease(handler)
	}
}