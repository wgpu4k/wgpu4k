package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap32
import korlibs.image.color.ColorFormat
import korlibs.io.file.Vfs
import korlibs.io.file.std.resourcesVfs

actual var customVfs: Vfs
    get() = resourcesVfs.vfs // only to avoid fail of e2e test on browser
    set(value) {}

internal actual fun Bitmap32.toBitmapHolder(textureFormat: ColorFormat): ImageBitmapHolder {
    return ImageBitmapHolder(width, height, extractBytes(textureFormat))
}