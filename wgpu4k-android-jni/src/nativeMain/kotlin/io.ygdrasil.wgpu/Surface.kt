@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong
import platform.android.jclass
import platform.android.jlong
import webgpu.WGPUSurfaceTexture
import kotlin.experimental.ExperimentalNativeApi


@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuSurfaceGetCurrentTexture")
fun wgpuSurfaceGetCurrentTexture(env: JNIEnvPointer, thiz: jclass, handler: jlong): jlong = memScoped {
    val surfaceTexture = alloc<WGPUSurfaceTexture>()
    webgpu.wgpuSurfaceGetCurrentTexture(handler.toCPointer(), surfaceTexture.ptr)
    return surfaceTexture.texture.toLong()
}

