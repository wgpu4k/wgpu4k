package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap32
import korlibs.io.file.Vfs

actual fun Bitmap32.toBitmapHolder(): ImageBitmapHolder {
    return ImageBitmapHolder(width, height, toByteArrayJS())
}

private fun Bitmap32.toByteArrayJS(): ByteArray {
    val bytes = UByteArray(ints.size * 4)
    var byteIndex = 0
    (0 until ints.size).forEach { index ->
        val color = getRgbaAtIndex(index)
        bytes[byteIndex++] = color.r.toUByte()
        bytes[byteIndex++] = color.g.toUByte()
        bytes[byteIndex++] = color.b.toUByte()
        bytes[byteIndex++] = color.a.toUByte()
    }
    return bytes.toByteArray()
}

actual var customVfs: Vfs
    get() = TODO("Not yet implemented")
    set(value) {}