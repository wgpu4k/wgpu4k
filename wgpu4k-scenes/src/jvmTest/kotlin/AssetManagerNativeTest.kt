@file:Suppress("DEPRECATION")

package io.ygdrasil.webgpu.examples

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec
import io.ygdrasil.kffi.globalMemory
import korlibs.image.bitmap.Bitmap32
import korlibs.image.color.RGBA

class AssetManagerNativeTest : FreeSpec({
    "closing a bitmap holder does not close global memory" {
        val holder = Bitmap32(1, 1, intArrayOf(0)).toBitmapHolder(RGBA)

        holder.close()

        shouldNotThrowAny {
            globalMemory.allocateBuffer(1uL)
        }
    }
})
