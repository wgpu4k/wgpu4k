package io.ygdrasil.wgpu.internal

import com.sun.jna.Memory
import com.sun.jna.Pointer
import java.lang.foreign.SegmentAllocator

class JnaArena: AutoCloseable {
    private val autoCloseableMemory = mutableListOf<AutoCloseable>()

    fun allocate(size: Long): Pointer {
        return Memory(size)
            .also { autoCloseableMemory.add(it) }
    }

    override fun close() {
        autoCloseableMemory.forEach { it.close() }
    }

    fun allocateFrom(label: String): Pointer {
        return NativeString(label).pointer
            ?.also { autoCloseableMemory.add(it) }
            ?: error("fail to allocate CString")
    }
}

internal fun <T> scoped(run: JnaArena.(SegmentAllocator) -> T) : T = JnaArena().use { return it.run(SegmentAllocator(it)) }

internal fun Pointer.toAddress() = Pointer.nativeValue(this)

internal fun List<Long>.toLNativeArray(arena: JnaArena): Pointer {
    return arena.allocate(size * Long.SIZE_BYTES.toLong())
        .also { array -> forEachIndexed { index, value -> array.setLong(index * Long.SIZE_BYTES.toLong(), value) } }
}

internal fun List<Int>.toINativeArray(arena: JnaArena): Pointer {
    return arena.allocate(size * Int.SIZE_BYTES.toLong())
        .also { array -> forEachIndexed { index, value -> array.setInt(index * Int.SIZE_BYTES.toLong(), value) } }
}