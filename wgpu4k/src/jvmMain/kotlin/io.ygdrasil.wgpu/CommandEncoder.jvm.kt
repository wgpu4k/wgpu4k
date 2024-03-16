package io.ygdrasil.wgpu

import com.sun.jna.NativeLong
import io.ygdrasil.wgpu.internal.jvm.*

actual class CommandEncoder(internal val handler: WGPUCommandEncoder) : AutoCloseable {
	actual fun beginRenderPass(renderPassDescriptor: RenderPassDescriptor): RenderPassEncoder {
		return RenderPassEncoder(
			wgpuCommandEncoderBeginRenderPass(handler, renderPassDescriptor.convert())
				?: error("fail to get RenderPassEncoder")
		)
	}

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
			source.convert(),
			destination.convert(),
			copySize.convert()
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
	it.origin = origin?.let { (x, y) ->
		WGPUOrigin3D().also {
			it.x = x
			it.y = y
		}
	}
	it.aspect = aspect?.value
}

private fun RenderPassDescriptor.convert(): WGPURenderPassDescriptor = WGPURenderPassDescriptor().also {
	it.colorAttachmentCount = NativeLong(colorAttachments.size.toLong())
	it.colorAttachments = colorAttachments.map { it.convert() }.toTypedArray()
	it.label = label
	/*override var depthStencilAttachment: GPURenderPassDepthStencilAttachment?
override var occlusionQuerySet: GPUQuerySet?
override var timestampWrites: GPURenderPassTimestampWrites?
override var maxDrawCount: GPUSize64?*/
}

private fun RenderPassDescriptor.ColorAttachment.convert(): WGPURenderPassColorAttachment.ByReference =
	WGPURenderPassColorAttachment.ByReference().also {
		it.view = view.handler
		it.loadOp = WGPULoadOp.WGPULoadOp_Clear.value
		it.storeOp = WGPUStoreOp.WGPUStoreOp_Store.value
		it.resolveTarget = resolveTarget?.handler
		it.clearValue = clearValue?.let { clearValue ->
			WGPUColor().apply {
				r = clearValue.get(0).toDouble()
				g = clearValue.get(1).toDouble()
				b = clearValue.get(2).toDouble()
				a = clearValue.get(3).toDouble()
			}
		}
	}
