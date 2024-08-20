@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.mapper.mapTextureViewDescriptor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong
import platform.android.jclass
import platform.android.jlong
import platform.android.jobject
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuTextureCreateView")
fun wgpuTextureCreateView(env: JNIEnvPointer, thiz: jclass, handler: jlong, descriptor: jobject?): jlong = memScoped {
    return descriptor
        ?.let { mapTextureViewDescriptor(descriptor, env) }
        .let { webgpu.wgpuTextureCreateView(handler.toCPointer(), it?.ptr) }
        .toLong()
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuTextureRelease")
fun wgpuTextureRelease(env: JNIEnvPointer, thiz: jclass, handler: jlong) {
    webgpu.wgpuTextureRelease(handler.toCPointer())
}