@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import ffi.NativeAddress
import kotlinx.cinterop.COpaque
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toCPointer

internal actual fun ULong.toNativeAddress(): NativeAddress? = takeIf { it != 0uL }
    ?.toLong()?.toCPointer<COpaque>()
    ?.let { NativeAddress(it) }