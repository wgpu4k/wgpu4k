package io.ygdrasil.wgpu.internal.jvm

import java.lang.foreign.Arena
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

fun <T> confined(block: (Arena) -> T) = Arena.ofConfined()
    .use { arena ->
        block(arena)
    }

internal fun List<MemorySegment>.toPointerArray(arena: Arena): MemorySegment? {
    val commands = arena.allocate(MemoryLayout.sequenceLayout(size.toLong(), ValueLayout.ADDRESS))
    forEachIndexed { index, value -> commands.setAtIndex(ValueLayout.ADDRESS, index.toLong(), value) }
    return commands
}
