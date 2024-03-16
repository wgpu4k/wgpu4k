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
	var origin: GPUIntegerCoordinates? = null,
	/* Iterable<GPUIntegerCoordinate>? | GPUOrigin2DDictStrict? */
	var flipY: Boolean? = null

)

data class ImageCopyTextureTagged(
	var colorSpace: Any? = null,
	var premultipliedAlpha: Boolean? = null,
	var texture: Texture,
	var mipLevel: GPUIntegerCoordinate? = null,
	var origin: GPUExtent3DDictStrict? = null,
	/* Iterable<GPUIntegerCoordinate>? | GPUOrigin3DDict? */
	var aspect: String? = null,
	/* "all" | "stencil-only" | "depth-only" */

)