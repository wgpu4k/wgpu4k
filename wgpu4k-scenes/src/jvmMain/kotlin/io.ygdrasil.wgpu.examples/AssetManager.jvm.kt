package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap32
import korlibs.image.color.ColorFormat
import korlibs.io.file.Vfs
import java.lang.foreign.Arena
import java.lang.foreign.ValueLayout

actual var customVfs: Vfs
    get() = TODO("Not yet implemented")
    set(value) {}

internal actual fun Bitmap32.toBitmapHolder(textureFormat: ColorFormat): ImageBitmapHolder {
    val arena = Arena.ofConfined()
    return ImageBitmapHolder(
        arena,
        arena.allocateFrom(ValueLayout.JAVA_BYTE, *extractBytes(textureFormat)),
        width.toUInt(),
        height.toUInt()
    )
}