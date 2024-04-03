package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUCommandBuffer
import io.ygdrasil.wgpu.internal.jvm.logNative
import io.ygdrasil.wgpu.internal.jvm.wgpuCommandBufferRelease

actual class CommandBuffer(internal val handler: WGPUCommandBuffer) : AutoCloseable {
	override fun close() {
		logNative { "wgpuCommandBufferRelease" to listOf(handler) }
		wgpuCommandBufferRelease(handler)
	}
}