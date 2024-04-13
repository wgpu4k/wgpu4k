package io.ygdrasil.wgpu.examples.helper.korge

import com.sun.jna.Memory
import io.ygdrasil.wgpu.ImageBitmapHolder
import io.ygdrasil.wgpu.TextureFormat
import io.ygdrasil.wgpu.getBytesPerPixel
import korlibs.image.bitmap.Bitmap32

fun Bitmap32.toImageBitmapHolder(): ImageBitmapHolder = ImageBitmapHolder(
    Memory((width * height * TextureFormat.rgba8unorm.getBytesPerPixel()).toLong()).also {
        it.write(0, ints, 0, ints.size)
    },
    width,
    height
)