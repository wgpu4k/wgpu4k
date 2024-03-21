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
		actualCopyTextureToTexture(
			source.convert(),
			destination.convert(),
			copySize.convert()
		)
	}

	fun actualCopyTextureToTexture(
		source: WGPUImageCopyTexture,
		destination: WGPUImageCopyTexture,
		copySize: WGPUExtent3D
	) {
		wgpuCommandEncoderCopyTextureToTexture(
			handler,
			source,
			destination,
			copySize
		)
	}


	override fun close() {
		wgpuCommandEncoderRelease(handler)
	}

}

private fun Pair<Int, Int>.convert(): WGPUExtent3D = WGPUExtent3D().also {
	it.height = second
	it.width = first
	it.depthOrArrayLayers = 1
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
