package io.ygdrasil.webgpu.mapper

import ffi.NativeAddress
import io.ygdrasil.webgpu.ArrayBuffer

internal fun NativeAddress.toArrayBuffer(): ArrayBuffer = ArrayBuffer(toULong())

internal expect fun NativeAddress.toULong(): ULong