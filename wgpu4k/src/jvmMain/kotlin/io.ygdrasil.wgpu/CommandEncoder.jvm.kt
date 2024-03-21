package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.renderPassDescriptorMapper

actual class CommandEncoder(internal val handler: WGPUCommandEncoder) : AutoCloseable {
	actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder =
		renderPassDescriptorMapper.map<RenderPassDescriptor, WGPURenderPassDescriptor>(descriptor)
			.let { wgpuCommandEncoderBeginRenderPass(handler, it) }
			?.let { RenderPassEncoder(it) }
			?: error("fail to get RenderPassEncoder")

	actual fun finish(): CommandBuffer {
		return CommandBuffer(
			wgpuCommandEncoderFinish(handler, WGPUCommandBufferDescriptor())
				?: error("fail to get CommandBuffer")
		)
	}

	actual fun copyTextureToTexture(
		source: ImageCopyTexture,
		destination: ImageCopyTexture,
		copySize: GPUIntegerCoordinates
	) {
		wgpuCommandEncoderCopyTextureToTexture(
			handler,
			source.convert().also { it.write() },
			destination.convert().also { it.write() },
			copySize.convert().also { it.write() }
		)
	}

	override fun close() {
		wgpuCommandEncoderRelease(handler)
	}

}

private fun Pair<Int, Int>.convert(): WGPUExtent3D = WGPUExtent3D().also {
	it.height = first
	it.width = second
}

private fun ImageCopyTexture.convert(): WGPUImageCopyTexture = WGPUImageCopyTexture().also {

	it.texture = texture.handler
	it.mipLevel = mipLevel
	it.origin = origin.let { (x, y) ->
		WGPUOrigin3D().also {
			it.x = x
			it.y = y
		}
	}
	it.aspect = aspect.value
}
