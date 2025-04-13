package io.ygdrasil.webgpu

import ffi.NativeAddress
import java.lang.foreign.MemorySegment

internal actual fun ULong.toNativeAddress(): NativeAddress? = takeIf { it != 0uL }
    ?.let { MemorySegment.ofAddress(it.toLong())  }
    ?.let { NativeAddress(it) }