package io.ygdrasil.wgpu

expect class Queue {

	fun submit(commandsBuffer: Array<CommandBuffer> = arrayOf())

	fun writeBuffer(
		buffer: Buffer,
		bufferOffset: GPUSize64,
		data: FloatArray,
		dataOffset: GPUSize64 = 0,
		size: GPUSize64 = data.size.toLong()
	)

	fun writeBuffer(
		buffer: Buffer,
		bufferOffset: GPUSize64,
		data: IntArray,
		dataOffset: GPUSize64 = 0,
		size: GPUSize64 = data.size.toLong()
	)

	fun copyExternalImageToTexture(
		source: ImageCopyExternalImage,
		destination: ImageCopyTextureTagged,
		copySize: GPUIntegerCoordinates
	)

}

expect sealed interface DrawableHolder
expect class ImageBitmapHolder : DrawableHolder {
	val width: Int
	val height: Int
}

data class ImageCopyExternalImage(
	val source: DrawableHolder,
	/* ImageBitmap | ImageData | HTMLImageElement | HTMLVideoElement | VideoFrame | HTMLCanvasElement | OffscreenCanvas */
	val origin: GPUIntegerCoordinates = 0 to 0,
	/* Iterable<GPUIntegerCoordinate>? | GPUOrigin2DDictStrict? */
	val flipY: Boolean = false

)

data class ImageCopyTextureTagged(
	var colorSpace: PredefinedColorSpace = PredefinedColorSpace.srgb,
	var premultipliedAlpha: Boolean = false,
	var texture: Texture,
	var mipLevel: GPUIntegerCoordinate = 0,
	var origin: GPUOrigin3DDict = GPUOrigin3DDict(),
	var aspect: TextureAspect = TextureAspect.all,

)

enum class PredefinedColorSpace(val value: String) {
	srgb("srgb"),
	displayp3("display-p3")
}