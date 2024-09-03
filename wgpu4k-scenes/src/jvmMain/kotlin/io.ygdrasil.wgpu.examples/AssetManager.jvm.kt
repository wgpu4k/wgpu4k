package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap32
import korlibs.io.file.Vfs
import java.lang.foreign.Arena
import java.lang.foreign.ValueLayout

actual fun Bitmap32.toBitmapHolder(): ImageBitmapHolder {
    val arena = Arena.ofConfined()
    return ImageBitmapHolder(
        arena,
        arena.allocateFrom(ValueLayout.JAVA_INT, *ints),
        width,
        height
    )
}

actual var customVfs: Vfs
    get() = TODO("Not yet implemented")
    set(value) {}