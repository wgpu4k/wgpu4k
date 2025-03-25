package io.ygdrasil.webgpu.mapper

import com.sun.jna.Pointer
import ffi.NativeAddress

internal actual fun NativeAddress.toULong(): ULong = Pointer.nativeValue(this).toULong()
