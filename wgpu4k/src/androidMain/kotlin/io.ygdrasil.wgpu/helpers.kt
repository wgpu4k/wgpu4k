package io.ygdrasil.wgpu

internal fun Boolean.toInt() = when(this) {
    true -> 1
    false -> 0
}
