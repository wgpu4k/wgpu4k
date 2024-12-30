package io.ygdrasil.webgpu.examples

import io.ygdrasil.webgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap32
import korlibs.image.color.ColorFormat
import korlibs.io.file.Vfs
import korlibs.io.file.std.AndroidResourcesVfs


actual var customVfs: Vfs = AndroidResourcesVfs()
internal actual fun Bitmap32.toBitmapHolder(textureFormat: ColorFormat): ImageBitmapHolder {
    return ImageBitmapHolder(
        extractBytes(textureFormat),
        width,
        height
    )
}