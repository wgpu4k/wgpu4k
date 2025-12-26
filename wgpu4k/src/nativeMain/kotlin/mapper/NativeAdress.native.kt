@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu.mapper

import ffi.NativeAddress
import io.ygdrasil.webgpu.ArrayBuffer
import io.ygdrasil.webgpu.GPUSize64
import kotlinx.cinterop.ExperimentalForeignApi

internal actual fun NativeAddress.toArrayBuffer(size: GPUSize64): ArrayBuffer = ArrayBuffer.wrap(this.pointer, size)