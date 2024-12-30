package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUSize32

internal fun map(input: List<GPUSize32>): Array<ULong> {
    return input.toUIntArray().unsafeCast<Array<ULong>>()
}