package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.*
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int32Array
import org.w3c.dom.ImageBitmap

@JsExport
actual class Queue(private val handler: GPUQueue) {
	actual fun submit(commandsBuffer: Array<CommandBuffer>) {
		handler.submit(commandsBuffer.map { it.handler }.toTypedArray())
	}

	@JsName("writeBufferF32")
	actual fun writeBuffer(
		buffer: Buffer,
		bufferOffset: GPUSize64,
		data: FloatArray,
		dataOffset: GPUSize64,
		size: GPUSize64
	) {
		handler.writeBuffer(
			buffer.handler,
			bufferOffset,
			data.unsafeCast<Float32Array>(),
			dataOffset,
			size
		)
	}

	@JsName("writeBufferI32")
	actual fun writeBuffer(
		buffer: Buffer,
		bufferOffset: GPUSize64,
		data: IntArray,
		dataOffset: GPUSize64,
		size: GPUSize64
	) {
		handler.writeBuffer(
			buffer.handler,
			bufferOffset,
			data.unsafeCast<Int32Array>(),
			dataOffset,
			size
		)
	}

	actual fun copyExternalImageToTexture(
		source: ImageCopyExternalImage,
		destination: ImageCopyTextureTagged,
		copySize: GPUIntegerCoordinates
	) {
		actualCopyExternalImageToTexture(
			source.convert(),
			destination.convert(),
			copySize.toList().toTypedArray()
		)
	}

	private fun actualCopyExternalImageToTexture(
		source: GPUImageCopyExternalImage,
		destination: GPUImageCopyTextureTagged,
		copySize: Array<GPUIntegerCoordinate>
	) {
		handler.copyExternalImageToTexture(source, destination, copySize)
	}
}

private fun ImageCopyTextureTagged.convert(): GPUImageCopyTextureTagged = object : GPUImageCopyTextureTagged {
	override var texture: GPUTexture = this@convert.texture.handler
	override var mipLevel: GPUIntegerCoordinate = this@convert.mipLevel
	override var origin: Array<GPUIntegerCoordinate> = this@convert.origin.toArray()
	override var aspect: String = this@convert.aspect.stringValue
	override var colorSpace: String = this@convert.colorSpace.value
	override var premultipliedAlpha: Boolean = this@convert.premultipliedAlpha
}

private fun ImageCopyExternalImage.convert(): GPUImageCopyExternalImage = object : GPUImageCopyExternalImage {
	override var source: dynamic = this@convert.source.convert()
	override var origin: GPUOrigin2DDictStrict = object : GPUOrigin2DDictStrict {
		override var x: GPUIntegerCoordinate = this@convert.origin.first
		override var y: GPUIntegerCoordinate = this@convert.origin.second
	}
	override var flipY: Boolean = this@convert.flipY
}

private fun DrawableHolder.convert(): dynamic = let { holder ->
	when (holder) {
		is ImageBitmapHolder -> holder.handler
		else -> error("unreachable statement")
	}
}

actual class ImageBitmapHolder(internal val handler: ImageBitmap) : DrawableHolder {
	actual val width: Int
		get() = handler.width
	actual val height: Int
		get() = handler.height

}
actual sealed interface DrawableHolder