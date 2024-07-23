@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import webgpu.*

val deviceState = MutableStateFlow<WGPUDevice?>(null)

actual class Adapter(val handler: WGPUAdapter) : AutoCloseable {
    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? {

        val handleRequestDevice: WGPURequestDeviceCallback =
            staticCFunction<WGPURequestDeviceStatus, WGPUDevice?, CPointer<ByteVar>?, COpaquePointer?, Unit> { status, device, message, _ ->
                if (status == WGPURequestDeviceStatus_Success) {
                    deviceState.update { device }
                } else {
                    println(" request_device status=$status message=${message?.toKStringFromUtf8()}\n")
                }

            }.reinterpret()

        wgpuAdapterRequestDevice(handler, null, handleRequestDevice, null)

        return deviceState.value?.let { Device(it) }
    }

    actual override fun close() {
        wgpuAdapterRelease(handler)
    }
}