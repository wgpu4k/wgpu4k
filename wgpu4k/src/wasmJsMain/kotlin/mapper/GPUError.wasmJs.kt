@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.WGPUError

actual fun isGPUValidationError(error: WGPUError): Boolean = js("error instanceof GPUValidationError")
actual fun isGPUInternalError(error: WGPUError): Boolean = js("error instanceof GPUInternalError")
actual fun isGPUOutOfMemoryError(error: WGPUError): Boolean = js("error instanceof GPUOutOfMemoryError")