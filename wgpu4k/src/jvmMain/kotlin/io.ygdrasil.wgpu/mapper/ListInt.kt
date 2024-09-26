
package io.ygdrasil.wgpu.mapper

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

internal fun Arena.map(input: List<Int>): MemorySegment {
    if (input.isEmpty()) return MemorySegment.NULL
    return MemorySegment.ofArray(input.toIntArray())
}
