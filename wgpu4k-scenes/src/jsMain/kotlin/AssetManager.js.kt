package io.ygdrasil.webgpu.examples

import io.ygdrasil.webgpu.ImageBitmapHolder
import io.ygdrasil.webgpu.asArrayBuffer
import korlibs.image.bitmap.Bitmap32
import korlibs.image.color.ColorFormat

internal actual fun Bitmap32.toBitmapHolder(textureFormat: ColorFormat): ImageBitmapHolder {
    return ImageBitmapHolder(width.toUInt(), height.toUInt(), extractBytes(textureFormat).asArrayBuffer())
}