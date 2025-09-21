package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUError
import io.ygdrasil.webgpu.InternalError
import io.ygdrasil.webgpu.OutOfMemoryError
import io.ygdrasil.webgpu.ValidationError
import io.ygdrasil.webgpu.WGPUError

internal fun errorOf(value: WGPUError): GPUError = when {
    isGPUValidationError(value) -> ValidationError(value.message)
    isGPUInternalError(value) -> InternalError(value.message)
    isGPUOutOfMemoryError(value) -> OutOfMemoryError(value.message)
    else -> InternalError(value.message)
}

internal expect fun isGPUValidationError(error: WGPUError): Boolean
internal expect fun isGPUInternalError(error: WGPUError): Boolean
internal expect fun isGPUOutOfMemoryError(error: WGPUError): Boolean