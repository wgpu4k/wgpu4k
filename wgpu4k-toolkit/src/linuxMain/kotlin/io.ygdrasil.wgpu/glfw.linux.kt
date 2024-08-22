@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import cnames.structs.GLFWwindow
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi

actual fun WGPU.getSurface(
    window: CPointer<GLFWwindow>,
    sizeProvider: () -> Pair<Int, Int>
): Surface {
    TODO("Not yet implemented")
}