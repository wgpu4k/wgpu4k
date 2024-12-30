package io.ygdrasil.webgpu.examples

import ffi.globalMemory
import io.ygdrasil.webgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap32
import korlibs.image.color.ColorFormat
import korlibs.io.file.Vfs

actual var customVfs: Vfs
    get() = TODO("Not yet implemented")
    set(value) {}

internal actual fun Bitmap32.toBitmapHolder(textureFormat: ColorFormat): ImageBitmapHolder {
    val bytes = extractBytes(textureFormat)
    val buffer = globalMemory.allocateBuffer((Byte.SIZE_BYTES * bytes.size).toULong()).apply {
        writeBytes(bytes)
    }
    return ImageBitmapHolder(
        globalMemory,
        buffer.handler,
        width.toUInt(),
        height.toUInt()
    )
}