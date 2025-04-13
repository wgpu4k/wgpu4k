package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUError
import io.ygdrasil.webgpu.GPUInternalError
import io.ygdrasil.webgpu.GPUOutOfMemoryError
import io.ygdrasil.webgpu.GPUValidationError
import io.ygdrasil.wgpu.WGPUErrorType
import io.ygdrasil.wgpu.WGPUErrorType_Internal
import io.ygdrasil.wgpu.WGPUErrorType_NoError
import io.ygdrasil.wgpu.WGPUErrorType_OutOfMemory
import io.ygdrasil.wgpu.WGPUErrorType_Unknown
import io.ygdrasil.wgpu.WGPUErrorType_Validation

internal fun errorOf(value: WGPUErrorType): GPUError? = when (value) {
    WGPUErrorType_NoError -> null
    WGPUErrorType_Validation -> object : GPUValidationError {
        override val message: String = "Validation error"
    }
    WGPUErrorType_OutOfMemory -> object : GPUOutOfMemoryError {
        override val message: String = "Validation error"
    }
    WGPUErrorType_Internal -> object : GPUInternalError {
        override val message: String = "Validation error"
    }
    WGPUErrorType_Unknown -> object : GPUError {
        override val message: String = "Unknown error"
    }
    else -> object : GPUError {
        override val message: String = "Unknown error with code $value"
    }
}