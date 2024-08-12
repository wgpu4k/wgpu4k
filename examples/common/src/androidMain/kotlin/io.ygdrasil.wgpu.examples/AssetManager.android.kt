package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.ImageBitmapHolder
import korlibs.image.bitmap.Bitmap32

actual fun Bitmap32.toBitmapHolder(): ImageBitmapHolder {
    return ImageBitmapHolder(
        convertIntArrayToByteArray(ints),
        width,
        height
    )
}

private fun convertIntArrayToByteArray(intArray: IntArray): ByteArray {
    val byteArray = ByteArray(intArray.size * 4)

    for (i in intArray.indices) {
        val value = intArray[i]
        val byteIndex = i * 4

        byteArray[byteIndex] = (value shr 24).toByte()
        byteArray[byteIndex + 1] = (value shr 16).toByte()
        byteArray[byteIndex + 2] = (value shr 8).toByte()
        byteArray[byteIndex + 3] = value.toByte()
    }

    return byteArray
}