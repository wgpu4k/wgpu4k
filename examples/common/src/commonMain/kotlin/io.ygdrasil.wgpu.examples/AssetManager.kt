package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder


interface AssetManager {

	val Di3d: ImageBitmapHolder

	val cubemapPosx: ImageBitmapHolder
	val cubemapNegx: ImageBitmapHolder
	val cubemapPosy: ImageBitmapHolder
	val cubemapNegy: ImageBitmapHolder
	val cubemapPosz: ImageBitmapHolder
	val cubemapNegz: ImageBitmapHolder
}