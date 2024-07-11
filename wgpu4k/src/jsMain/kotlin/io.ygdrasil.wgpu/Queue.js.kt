package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.*
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int32Array

actual class Queue(internal val handler: GPUQueue) {
	actual fun submit(commandsBuffer: List<CommandBuffer>) {
		handler.submit(commandsBuffer.map { it.handler }.toTypedArray())
	}

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
		if (destination.texture.format !in listOf(TextureFormat.rgba8unorm, TextureFormat.rgba8unormsrgb)) {
			error("rgba8unorm asnd rgba8unormsrgb are the only supported texture format supported")
		}

		val image = (source.source as? ImageBitmapHolder)
		if (image == null) error("ImageBitmapHolder required as source")

		val bytePerPixel = destination.texture.format.getBytesPerPixel()

		handler.writeTexture(
			object : GPUImageCopyTexture {
				override var texture: GPUTexture = destination.texture.handler
				override var mipLevel: GPUIntegerCoordinate = destination.mipLevel
				override var origin: Array<GPUIntegerCoordinate> = destination.origin.toArray()
				override var aspect: String = destination.aspect.stringValue
			},
			image.data.unsafeCast<ArrayBuffer>(),
			object : GPUImageDataLayout {
				override var offset: GPUSize64? = 0
				override var bytesPerRow: GPUSize32? = image.width * bytePerPixel
				override var rowsPerImage: GPUSize32? = image.height
			},
			object : GPUExtent3DDict {
				override var width: GPUIntegerCoordinate = image.width
				override var height: GPUIntegerCoordinate? = image.height
				override var depthOrArrayLayers: GPUIntegerCoordinate? = 1
			}
		)
	}

}


actual class ImageBitmapHolder(
	actual val width: Int,
	actual val height: Int,
	val data: ByteArray
) : DrawableHolder

actual sealed interface DrawableHolder