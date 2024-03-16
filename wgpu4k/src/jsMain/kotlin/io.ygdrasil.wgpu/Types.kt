package io.ygdrasil.wgpu

internal fun GPUExtent3DDictStrict.setJsCompliant(): GPUExtent3DDictStrict = apply {
	height = height ?: undefined
	depthOrArrayLayers = depthOrArrayLayers ?: undefined
}