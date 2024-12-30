package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUSize32

internal fun map(input: List<GPUSize32>): Array<ULong> {
    return input.toUIntArray().unsafeCast<Array<ULong>>()
}