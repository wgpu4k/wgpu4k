package io.ygdrasil.webgpu

internal fun Boolean.toInt() = when (this) {
    true -> 1
    false -> 0
}


