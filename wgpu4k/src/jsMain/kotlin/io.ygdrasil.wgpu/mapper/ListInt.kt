package io.ygdrasil.wgpu.mapper

internal fun map(input: List<Int>): Array<ULong> {
    return input.toIntArray().unsafeCast<Array<ULong>>()
}