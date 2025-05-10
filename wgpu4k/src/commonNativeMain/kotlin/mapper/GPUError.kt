package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUError
import io.ygdrasil.webgpu.InternalError
import io.ygdrasil.webgpu.OutOfMemoryError
import io.ygdrasil.webgpu.ValidationError
import io.ygdrasil.wgpu.WGPUErrorType
import io.ygdrasil.wgpu.WGPUErrorType_Internal
import io.ygdrasil.wgpu.WGPUErrorType_NoError
import io.ygdrasil.wgpu.WGPUErrorType_OutOfMemory
import io.ygdrasil.wgpu.WGPUErrorType_Unknown
import io.ygdrasil.wgpu.WGPUErrorType_Validation

internal fun errorOf(value: WGPUErrorType, message: String?): GPUError? = when (value) {
    WGPUErrorType_NoError -> null
    WGPUErrorType_Validation -> ValidationError(message ?: "Validation error")
    WGPUErrorType_OutOfMemory -> OutOfMemoryError(message ?: "Validation error")
    WGPUErrorType_Internal -> InternalError(message ?: "Validation error")
    WGPUErrorType_Unknown -> InternalError(message ?: "Unknown error")
    else -> InternalError(message ?: "Unknown error with code $value")
}
