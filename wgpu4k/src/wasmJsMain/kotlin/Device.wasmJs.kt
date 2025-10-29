@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.errorOf

internal actual fun configureUncapturedError(handler: WGPUDevice, callback: GPUUncapturedErrorCallback) {
    handler.unsafeCast<DeviceInternal>()
        .addEventListener("uncapturederror", { event ->
            callback.onUncapturedError(errorOf(event.error))
        })
}

internal external class DeviceInternal : JsAny {
    fun addEventListener(type: String, callback: (Event) -> Unit)
}

internal external class Event : JsAny {
    val error: WGPUError
}
