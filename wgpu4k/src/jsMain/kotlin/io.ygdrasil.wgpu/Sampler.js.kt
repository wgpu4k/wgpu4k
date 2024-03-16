@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUSampler

actual class Sampler(internal val handler: GPUSampler) : AutoCloseable {
	override fun close() {
		// Nothing to do on JS
	}
}