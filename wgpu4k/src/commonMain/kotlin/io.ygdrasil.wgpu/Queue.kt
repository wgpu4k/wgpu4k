package io.ygdrasil.wgpu

expect class Queue {

	fun submit(commandsBuffer: Array<CommandBuffer>)

	fun writeBuffer(buffer: Buffer, bufferOffset: GPUSize64, data: FloatArray, dataOffset: GPUSize64, size: GPUSize64)

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
	var source: DrawableHolder,
	/* ImageBitmap | ImageData | HTMLImageElement | HTMLVideoElement | VideoFrame | HTMLCanvasElement | OffscreenCanvas */
	var origin: GPUIntegerCoordinates = 0 to 0,
	/* Iterable<GPUIntegerCoordinate>? | GPUOrigin2DDictStrict? */
	var flipY: Boolean = false

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