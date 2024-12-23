package io.ygdrasil.wgpu.mapper

import ffi.ArrayHolder
import ffi.MemoryAllocator

internal fun MemoryAllocator.map(input: List<UInt>): ArrayHolder<UInt>? {
    if (input.isEmpty()) return null
    return allocateBuffer((Int.SIZE_BYTES * input.size).toULong())
        .also { buffer -> buffer.writeUInts(input.toUIntArray()) }
        .let { ArrayHolder(it.handler) }
}
