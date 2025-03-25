package io.ygdrasil.webgpu.mapper

import ffi.NativeAddress

internal actual fun NativeAddress.toULong(): ULong = rawValue.toULong()