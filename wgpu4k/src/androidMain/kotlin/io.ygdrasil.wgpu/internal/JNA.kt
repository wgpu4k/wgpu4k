package io.ygdrasil.wgpu.internal

import com.sun.jna.Memory
import com.sun.jna.Pointer
import java.lang.foreign.SegmentAllocator

class JnaArena: AutoCloseable {
    private val autoCloseableMemory = mutableListOf<AutoCloseable>()

    fun allocate(size: Long): Memory {
        return Memory(size)
            .also { autoCloseableMemory.add(it) }
    }

    override fun close() {
        autoCloseableMemory.forEach { it.close() }
    }
}

internal fun scoped(run: JnaArena.(SegmentAllocator) -> Unit) = JnaArena().use { it.run(SegmentAllocator(it)) }

internal fun Memory.toAddress() = Pointer.nativeValue(this)

internal fun List<Long>.toNativeArray(arena: JnaArena): Memory {
    return arena.allocate(size * Long.SIZE_BYTES.toLong())
        .also { array -> forEachIndexed { index, value -> array.setLong(index * Long.SIZE_BYTES.toLong(), value) } }
}