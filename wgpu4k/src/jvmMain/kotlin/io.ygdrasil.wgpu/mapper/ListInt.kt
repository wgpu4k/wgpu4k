
package io.ygdrasil.wgpu.mapper

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

internal fun Arena.map(input: List<Int>): MemorySegment {
    if (input.isEmpty()) return MemorySegment.NULL
    return allocate((Int.SIZE_BYTES * input.size).toLong())
        .also { output -> input.forEachIndexed { index, value -> output.setAtIndex(ValueLayout.JAVA_INT, index.toLong(), value) }}
}
