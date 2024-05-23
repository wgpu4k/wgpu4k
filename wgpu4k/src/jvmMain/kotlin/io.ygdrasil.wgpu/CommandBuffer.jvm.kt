package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUCommandBuffer
import io.ygdrasil.wgpu.internal.jvm.logUnitNative
import io.ygdrasil.wgpu.internal.jvm.wgpuCommandBufferRelease

actual class CommandBuffer(internal val handler: WGPUCommandBuffer) : AutoCloseable {
	actual override fun close() {
		logUnitNative { "wgpuCommandBufferRelease" to listOf(handler) }
		wgpuCommandBufferRelease(handler)
	}
}