@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toKStringFromUtf8
import kotlinx.cinterop.toLong
import platform.android.jclass
import platform.android.jlong
import platform.sles.jobject
import webgpu.WGPUDevice
import webgpu.WGPURequestDeviceStatus
import webgpu.WGPURequestDeviceStatus_Success
import kotlin.experimental.ExperimentalNativeApi

private var lastFindDevice: WGPUDevice? = null

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuAdapterRequestDevice")
fun wgpuAdapterRequestDevice(env: JNIEnvPointer, thiz: jclass, handler: jlong, descriptor: jobject) : jlong {

    val handleRequestDevice =
        staticCFunction<WGPURequestDeviceStatus, WGPUDevice?, CPointer<ByteVar>?, COpaquePointer?, Unit> { status, device, message, _ ->
            if (status == WGPURequestDeviceStatus_Success) {
                lastFindDevice = device
            } else {
                println(" request_device status=$status message=${message?.toKStringFromUtf8()}\n")
            }

        }

    webgpu.wgpuAdapterRequestDevice(handler.toCPointer(), null, handleRequestDevice, null)

    val device = lastFindDevice
    lastFindDevice = null
    return device?.toLong() ?: 0L
}
