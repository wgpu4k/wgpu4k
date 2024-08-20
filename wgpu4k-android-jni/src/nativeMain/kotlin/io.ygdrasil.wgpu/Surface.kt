@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong
import platform.android.JNIEnv
import platform.android.jclass
import platform.android.jint
import platform.android.jlong
import webgpu.WGPUPresentMode_Fifo
import webgpu.WGPUSurfaceTexture
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuSurfaceGetFormat")
fun wgpuSurfaceGetFormat(env: JNIEnv, thiz: jclass, surface: jlong, adapter: jlong): jint = memScoped {

    val capabilities = alloc<webgpu.WGPUSurfaceCapabilities>()

    webgpu.wgpuSurfaceGetCapabilities(surface.toCPointer(), adapter.toCPointer(), capabilities.ptr)

    return if (capabilities.formatCount == 0uL) {
        println("surface format is empty")
        0
    } else {
        capabilities.formats?.get(0)?.toInt() ?: 0
    }
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuSurfaceConfigure")
fun wgpuSurfaceConfigure(
    env: JNIEnv,
    thiz: jclass,
    surface: jlong,
    device: jlong,
    usages: jint,
    format: jint,
    alphaMode: jint,
    width: jint,
    height: jint
) = memScoped {

    val canvas_configuration = alloc<webgpu.WGPUSurfaceConfiguration> {
        this.device = device.toCPointer()
        this.format = format.toUInt()
        usage = usages.toUInt()
        viewFormatCount = 0uL
        this.alphaMode = alphaMode.toUInt()
        this.width = width.toUInt()
        this.height = height.toUInt()
        presentMode = WGPUPresentMode_Fifo
    }

    webgpu.wgpuSurfaceConfigure(surface.toCPointer(), canvas_configuration.ptr)
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuSurfaceGetCurrentTexture")
fun wgpuSurfaceGetCurrentTexture(env: JNIEnvPointer, thiz: jclass, handler: jlong): jlong = memScoped {
    val surfaceTexture = alloc<WGPUSurfaceTexture>()
    webgpu.wgpuSurfaceGetCurrentTexture(handler.toCPointer(), surfaceTexture.ptr)
    return surfaceTexture.texture.toLong()
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuSurfacePresent")
fun wgpuSurfacePresent(env: JNIEnvPointer, thiz: jclass, handler: jlong) {
    webgpu.wgpuSurfacePresent(handler.toCPointer())
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuSurfaceRelease")
fun wgpuSurfaceRelease(env: JNIEnvPointer, thiz: jclass, handler: jlong) {
    webgpu.wgpuSurfaceRelease(handler.toCPointer())
}