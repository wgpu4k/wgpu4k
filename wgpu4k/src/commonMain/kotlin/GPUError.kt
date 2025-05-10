package io.ygdrasil.webgpu

internal data class ValidationError(override val message: String): GPUValidationError
internal data class OutOfMemoryError(override val message: String): GPUOutOfMemoryError
internal data class InternalError(override val message: String): GPUInternalError