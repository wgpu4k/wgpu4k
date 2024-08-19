@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.mapper.mapShaderModuleDescriptor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong
import platform.android.jclass
import platform.android.jlong
import platform.android.jobject
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuDeviceCreateShaderModule")
fun wgpuDeviceCreateShaderModule(env: JNIEnvPointer, thiz: jclass, handler: jlong, descriptor: jobject) = memScoped {
    mapShaderModuleDescriptor(descriptor, env)
        .let { webgpu.wgpuDeviceCreateShaderModule(handler.toCPointer(), it.ptr) }
        .toLong()
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuDeviceRelease")
fun wgpuDeviceRelease(env: JNIEnvPointer, thiz: jclass, handler: jlong) {
    webgpu.wgpuDeviceRelease(handler.toCPointer())
}