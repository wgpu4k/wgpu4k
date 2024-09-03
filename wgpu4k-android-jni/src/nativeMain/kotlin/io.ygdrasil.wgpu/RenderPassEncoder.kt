@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong
import platform.android.jclass
import platform.android.jint
import platform.android.jlong
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuRenderPassEncoderSetPipeline")
fun wgpuRenderPassEncoderSetPipeline(env: JNIEnvPointer, thiz: jclass, handler: jlong, renderPipeline: jlong) {
    webgpu.wgpuRenderPassEncoderSetPipeline(handler.toCPointer(), renderPipeline.toCPointer())
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuRenderPassEncoderDraw")
fun wgpuRenderPassEncoderDraw(
    env: JNIEnvPointer,
    thiz: jclass,
    handler: jlong,
    vertexCount: jint,
    instanceCount: jint,
    firstVertex: jint,
    firstInstance: jint
) {
    webgpu.wgpuRenderPassEncoderDraw(handler.toCPointer(), vertexCount.toUInt(), instanceCount.toUInt(), firstVertex.toUInt(), firstInstance.toUInt())
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuCommandEncoderFinish")
fun wgpuCommandEncoderFinish(env: JNIEnvPointer, thiz: jclass, handler: jlong): jlong = memScoped {
    return webgpu.wgpuCommandEncoderFinish(handler.toCPointer(), alloc<webgpu.WGPUCommandBufferDescriptor>().ptr)
        .toLong()
}


