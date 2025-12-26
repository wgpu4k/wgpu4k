@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaque
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.ShortVar
import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.UIntVar
import kotlinx.cinterop.UShortVar
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.sizeOf
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.usePinned
import platform.posix.memcpy

actual fun FloatArray.writeInto(target: ArrayBuffer): Unit = usePinned { pinnedArray ->
    memcpy(
        target.asPointer(),
        pinnedArray.addressOf(0),
        (size * sizeOf<FloatVar>()).toULong()
    )
}

actual fun DoubleArray.writeInto(target: ArrayBuffer): Unit = usePinned { pinnedArray ->
    memcpy(
        target.asPointer(),
        pinnedArray.addressOf(0),
        (size * sizeOf<DoubleVar>()).toULong()
    )
}

actual fun ByteArray.writeInto(target: ArrayBuffer): Unit = usePinned { pinnedArray ->
    memcpy(
        target.asPointer(),
        pinnedArray.addressOf(0),
        (size * sizeOf<ByteVar>()).toULong()
    )
}

actual fun UByteArray.writeInto(target: ArrayBuffer): Unit = usePinned { pinnedArray ->
    memcpy(
        target.asPointer(),
        pinnedArray.addressOf(0),
        (size * sizeOf<UByteVar>()).toULong()
    )
}

actual fun ShortArray.writeInto(target: ArrayBuffer): Unit = usePinned { pinnedArray ->
    memcpy(
        target.asPointer(),
        pinnedArray.addressOf(0),
        (size * sizeOf< ShortVar>()).toULong()
    )
}

actual fun UShortArray.writeInto(target: ArrayBuffer): Unit = usePinned { pinnedArray ->
    memcpy(
        target.asPointer(),
        pinnedArray.addressOf(0),
        (size * sizeOf<UShortVar>()).toULong()
    )
}

actual fun IntArray.writeInto(target: ArrayBuffer): Unit = usePinned { pinnedArray ->
    memcpy(
        target.asPointer(),
        pinnedArray.addressOf(0),
        (size * sizeOf< IntVar>()).toULong()
    )
}

actual fun UIntArray.writeInto(target: ArrayBuffer): Unit = usePinned { pinnedArray ->
    memcpy(
        target.asPointer(),
        pinnedArray.addressOf(0),
        (size * sizeOf<UIntVar>()).toULong()
    )
}

private fun ArrayBuffer.asPointer() = rawPointer.toLong().toCPointer<COpaque>()