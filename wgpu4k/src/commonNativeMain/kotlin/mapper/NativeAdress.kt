package io.ygdrasil.webgpu.mapper

import ffi.NativeAddress
import io.ygdrasil.webgpu.ArrayBuffer
import io.ygdrasil.webgpu.GPUSize64

internal fun NativeAddress.toArrayBuffer(size: GPUSize64): ArrayBuffer = ArrayBuffer(toULong(), size)

internal expect fun NativeAddress.toULong(): ULong