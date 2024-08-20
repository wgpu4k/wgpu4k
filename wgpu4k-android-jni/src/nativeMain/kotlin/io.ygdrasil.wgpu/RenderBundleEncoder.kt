@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toCPointer
import platform.android.jclass
import platform.android.jlong
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuRenderBundleEncoderRelease")
fun wgpuRenderBundleEncoderRelease(env: JNIEnvPointer, thiz: jclass, handler: jlong) {
    webgpu.wgpuRenderBundleEncoderRelease(handler.toCPointer())
}