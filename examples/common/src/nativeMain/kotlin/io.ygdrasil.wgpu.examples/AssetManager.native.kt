@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap32
import korlibs.io.file.Vfs
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toCValues

actual fun Bitmap32.toBitmapHolder(): ImageBitmapHolder {
    return ImageBitmapHolder(
        ints.toCValues(),
        width,
        height
    )
}

actual var customVfs: Vfs
    get() = TODO("Not yet implemented")
    set(value) {}