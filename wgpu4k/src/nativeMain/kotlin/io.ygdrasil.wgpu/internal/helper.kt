@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.internal

import kotlinx.cinterop.*

fun Boolean.toUInt() = if (this) 1u else 0u

internal fun <E : CPointer<*>> List<E>.toPointerArray(arena: ArenaBase): CPointer<CPointerVarOf<E>> {
    return arena.allocArray<CPointerVarOf<E>>(size).also { array ->
        forEachIndexed { index, entry ->
            array[index] = entry
        }
    }
}