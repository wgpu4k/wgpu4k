package io.ygdrasil.webgpu.mapper

import ffi.NativeAddress
import io.ygdrasil.webgpu.ArrayBuffer
import io.ygdrasil.webgpu.GPUSize64

internal actual fun NativeAddress.toArrayBuffer(size: GPUSize64): ArrayBuffer =
    ArrayBuffer.wrap(handler.reinterpret(size.toLong()))