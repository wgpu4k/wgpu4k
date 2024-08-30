@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.mapper.mapRenderPipelineDescriptor
import io.ygdrasil.wgpu.mapper.mapShaderModuleDescriptor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toLong
import platform.android.jclass
import platform.android.jlong
import platform.android.jobject
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuDeviceCreateShaderModule")
fun wgpuDeviceCreateShaderModule(env: JNIEnvPointer, thiz: jclass, handler: jlong, descriptor: jobject): jlong = memScoped {
    return mapShaderModuleDescriptor(descriptor, env)
        .let { webgpu.wgpuDeviceCreateShaderModule(handler.toCPointer(), it.ptr) }
        .toLong()
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuDeviceCreateRenderPipeline")
fun wgpuDeviceCreateRenderPipeline(env: JNIEnvPointer, thiz: jclass, handler: jlong, descriptor: jobject): jlong = memScoped {
    return mapRenderPipelineDescriptor(descriptor, env)
        .let { webgpu.wgpuDeviceCreateRenderPipeline(handler.toCPointer(), it.ptr) }
        .toLong()
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuDeviceCreateCommandEncoder")
fun wgpuDeviceCreateCommandEncoder(env: JNIEnvPointer, thiz: jclass, handler: jlong, descriptor: jobject): jlong = memScoped {
    return webgpu.wgpuDeviceCreateCommandEncoder(handler.toCPointer(), alloc<webgpu.WGPUCommandEncoderDescriptor>().ptr)
        .toLong()
}

@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuDeviceGetQueue")
fun wgpuDeviceGetQueue(env: JNIEnvPointer, thiz: jclass, handler: jlong): jlong {
    return webgpu.wgpuDeviceGetQueue(handler.toCPointer())
        .toLong()
}
