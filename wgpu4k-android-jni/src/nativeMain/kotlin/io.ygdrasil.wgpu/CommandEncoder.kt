@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.mapper.mapRenderPassDescriptor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong
import platform.android.jclass
import platform.android.jlong
import platform.android.jobject
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuCommandEncoderBeginRenderPass")
fun wgpuCommandEncoderBeginRenderPass(env: JNIEnvPointer, thiz: jclass, handler: jlong, descriptor: jobject): jlong = memScoped {
    return mapRenderPassDescriptor(descriptor, env)
        .let { webgpu.wgpuCommandEncoderBeginRenderPass(handler.toCPointer(), it.ptr) }
        .toLong()
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuCommandEncoderRelease")
fun wgpuCommandEncoderRelease(env: JNIEnvPointer, thiz: jclass, handler: jlong) {
    webgpu.wgpuCommandEncoderRelease(handler.toCPointer())
}