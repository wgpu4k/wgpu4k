package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUBindGroup
import io.ygdrasil.wgpu.internal.jvm.logUnitNative
import io.ygdrasil.wgpu.internal.jvm.wgpuBindGroupRelease

actual class BindGroup(internal val handler: WGPUBindGroup) : AutoCloseable {

	override fun close() {
		logUnitNative { "wgpuBindGroupRelease" to listOf(handler) }
		wgpuBindGroupRelease(handler)
	}
}