@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.internal.callIntMethodFrom
import kotlinx.cinterop.*
import platform.android.*
import webgpu.WGPUAdapter
import webgpu.WGPURequestAdapterStatus
import webgpu.WGPURequestAdapterStatus_Success
import webgpu.WGPUSType_SurfaceDescriptorFromAndroidNativeWindow
import webgpu.wgpuInstanceRequestAdapter
import kotlin.experimental.ExperimentalNativeApi


@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuCreateInstance")
fun wgpuCreateInstance(env: JNIEnvPointer, thiz: jclass, backendHolder: jobject?) : jlong = memScoped {
    println("wgpuCreateInstance ${backendHolder}")

    return if (backendHolder == null) {
        webgpu.wgpuCreateInstance(null).toLong()
    } else {
        val backend = env.callIntMethodFrom(backendHolder, "getValue")

        val descriptor = alloc<webgpu.WGPUInstanceDescriptor>().apply  {
            nextInChain = alloc<webgpu.WGPUInstanceExtras>().apply {
                chain.sType = webgpu.WGPUSType_InstanceExtras
                backends = backend.toUInt()
            }.ptr.reinterpret()
        }

        webgpu.wgpuCreateInstance(descriptor.ptr).toLong()
    }
}



@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuInstanceCreateSurface")
fun wgpuInstanceCreateSurface(env: JNIEnvPointer, thiz: jclass, handler: jlong, surface: jobject) : jlong = memScoped {

    val native_window = ANativeWindow_fromSurface(env.reinterpret(), surface)

    val next_in_chain = alloc<webgpu.WGPUSurfaceDescriptorFromAndroidNativeWindow> {
        chain.sType = WGPUSType_SurfaceDescriptorFromAndroidNativeWindow
        window = native_window
    }

    val descriptor = alloc<webgpu.WGPUSurfaceDescriptor> {
        nextInChain = next_in_chain.ptr.reinterpret()
    }

    val surface = webgpu.wgpuInstanceCreateSurface(handler.toCPointer(), descriptor.ptr)
    return surface.toLong()
}

private var lastFindAdapter: WGPUAdapter? = null
@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuInstanceRequestAdapter")
fun wgpuInstanceRequestAdapter(env: JNIEnvPointer, thiz: jclass, handler: jlong, powerPreference: jobject?, surface: jlong) : jlong = memScoped {

    val powerPreference =  if (powerPreference == null) {
        0u
    } else {
        env.callIntMethodFrom(powerPreference, "getValue").toUInt()
    }

    val options = alloc<webgpu.WGPURequestAdapterOptions> {
        this.powerPreference = powerPreference
        compatibleSurface = surface.toCPointer()
    }

    val handleRequestAdapter =
        staticCFunction<WGPURequestAdapterStatus, WGPUAdapter, CPointer<ByteVar>?, COpaquePointer, Unit> { status, adapter, message, userData ->
            println("WGPURequestAdapterCallback ${userData} ${adapter}")
            if (status == WGPURequestAdapterStatus_Success) {
                lastFindAdapter = adapter
            } else {
                println("request_adapter status=$status message=${message?.toKStringFromUtf8()}\n")
            }
        }

    wgpuInstanceRequestAdapter(handler.toCPointer(), options.ptr, handleRequestAdapter.reinterpret(), null)

    val adapter = lastFindAdapter
    lastFindAdapter = null
    return adapter?.toLong() ?: 0L
}


@CName("Java_io_ygdrasil_wgpu_internal_JniInterface_wgpuInstanceRelease")
fun wgpuInstanceRelease(env: JNIEnvPointer, thiz: jclass, handler: jlong) {
    webgpu.wgpuInstanceRelease(handler.toCPointer())
}