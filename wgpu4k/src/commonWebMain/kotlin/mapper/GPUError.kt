package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUError
import io.ygdrasil.webgpu.WGPUError

internal fun WGPUError.toGPUError(): GPUError = object : GPUError {
    override val message: String
        get() = this@toGPUError.message
}