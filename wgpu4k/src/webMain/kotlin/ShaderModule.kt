@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map
import js.promise.await
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.unsafeCast

actual class ShaderModule(val handler: WGPUShaderModule) : GPUShaderModule {
    actual override var label: String
        get() = handler.label
        set(value) {
            handler.label = value
        }

    actual override suspend fun getCompilationInfo(): Result<GPUCompilationInfo> = runCatching {
        handler
            .getCompilationInfo()
            .await()
            .unsafeCast<WGPUCompilationInfo>()
            .let { map(it) }
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}

