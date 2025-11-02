package io.ygdrasil.webgpu.examples

import ffi.globalMemory
import io.ygdrasil.webgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap32
import korlibs.image.color.ColorFormat
import korlibs.io.file.Vfs
import korlibs.io.file.VfsFile
import korlibs.io.file.std.resourcesVfs
import korlibs.io.file.std.rootLocalVfs

expect var androidVfs: Vfs?

actual suspend fun String.asVsfFile(): VfsFile = resourcesVfs[this].takeIf { it.exists() }
    ?: rootLocalVfs[this].takeIf { it.exists() }
    ?: androidVfs?.get(this)
    ?: error("fail to get file $this from file system")

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