@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.internal.toCArray
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UIntVar

internal fun ArenaBase.map(input: List<Int>): CPointer<UIntVar>? {
    if (input.isEmpty()) return null
    return input.map { it.toUInt() }.toCArray(this)
}
