package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap
import korlibs.image.bitmap.Bitmap32
import kotlin.js.JsExport

@JsExport
interface AssetManager {

	val Di3d: ImageBitmapHolder
	val Di3d2: Bitmap32

	val cubemapPosx: ImageBitmapHolder
	val cubemapNegx: ImageBitmapHolder
	val cubemapPosy: ImageBitmapHolder
	val cubemapNegy: ImageBitmapHolder
	val cubemapPosz: ImageBitmapHolder
	val cubemapNegz: ImageBitmapHolder

	val webgpu4kotlin: ImageBitmapHolder
}