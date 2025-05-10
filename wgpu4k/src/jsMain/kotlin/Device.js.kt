package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.errorOf

internal actual fun configureUncapturedError(handler: WGPUDevice, callback: GPUUncapturedErrorCallback) {
    val device: dynamic = handler
    device.addEventListener("uncapturederror", { event ->
        callback.onUncapturedError(errorOf(event.error))
    })
}
