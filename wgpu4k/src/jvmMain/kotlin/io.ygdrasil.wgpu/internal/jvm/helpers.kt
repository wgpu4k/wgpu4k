package io.ygdrasil.wgpu.internal.jvm

internal fun Boolean.toInt(): WGPUBool = when (this) {
	true -> 1
	else -> 0
}