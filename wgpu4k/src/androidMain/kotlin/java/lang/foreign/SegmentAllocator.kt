package java.lang.foreign

import io.ygdrasil.wgpu.internal.JnaArena

@JvmInline
value class SegmentAllocator(private val arena: JnaArena) {
    fun allocate(layout: GroupLayout): MemorySegment = MemorySegment(arena.allocate(layout.size))

    fun allocateFrom(label: String): MemorySegment {
        return arena.allocateFrom(label)
            .let(::MemorySegment)
    }
}