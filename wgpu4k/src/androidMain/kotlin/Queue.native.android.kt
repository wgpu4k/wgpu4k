package io.ygdrasil.webgpu

import com.sun.jna.Pointer
import ffi.NativeAddress

internal actual fun ULong.toNativeAddress(): NativeAddress? = takeIf { it != 0uL }
    ?.let { Pointer(it.toLong())  }