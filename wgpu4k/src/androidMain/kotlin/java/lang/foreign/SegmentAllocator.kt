package java.lang.foreign

import io.ygdrasil.wgpu.internal.JnaArena

@JvmInline
value class SegmentAllocator(internal val arena: JnaArena) {
    fun allocate(layout: ValueLayout): MemorySegment = MemorySegment(arena.allocate(layout.size), layout.size)
        .also { it.fillWithZero() }

    fun allocate(layout: ValueLayout, size: Long): MemorySegment =
        MemorySegment(arena.allocate(layout.size * size), layout.size * size)

    fun allocateFrom(layout: ValueLayout.OfInt, values: IntArray): MemorySegment =
        allocate(layout, values.size.toLong())
            .also {
                it.pointer.write(0, values, 0, values.size)
            }

    fun allocateFrom(label: String): MemorySegment {
        return arena.allocateFrom(label)
            .let { MemorySegment(it, 0) }
    }
}