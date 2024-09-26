
package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.internal.toINativeArray
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: List<Int>): Long {
    if (input.isEmpty()) return 0L
    return input.toINativeArray(this.arena).toAddress()
}
