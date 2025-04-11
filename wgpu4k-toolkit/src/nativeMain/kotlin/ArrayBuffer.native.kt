@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned

actual inline fun arrayBufferOf(input: FloatArray, action: (ArrayBuffer) -> Unit) {
    input.usePinned { pinnedArray ->
        action(
            ArrayBuffer(pinnedArray.addressOf(0).rawValue.toLong().toULong())
        )
    }
}

actual fun FloatArray.writeInto(target: ArrayBuffer) {
    TODO("Not yet implemented")
}