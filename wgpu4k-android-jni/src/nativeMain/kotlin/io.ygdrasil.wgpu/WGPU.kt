@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.internal.callIntMethodFrom
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toKStringFromUtf8
import kotlinx.cinterop.toLong
import platform.android.jclass
import platform.android.jlong
import platform.android.jobject
import webgpu.WGPUAdapter
import webgpu.WGPURequestAdapterStatus
import webgpu.WGPURequestAdapterStatus_Success
import webgpu.wgpuInstanceRequestAdapter
import kotlin.experimental.ExperimentalNativeApi


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

@CName("wgpuInstanceRequestAdapterNoCallback")
fun wgpuInstanceRequestAdapterNoCallback(handler: Long, descriptor: Long) : Long = memScoped {

    val handleRequestAdapter =
        staticCFunction<WGPURequestAdapterStatus, WGPUAdapter, CPointer<ByteVar>?, COpaquePointer, Unit> { status, adapter, message, userData ->
            println("WGPURequestAdapterCallback ${userData} ${adapter}")
            if (status == WGPURequestAdapterStatus_Success) {
                lastFindAdapter = adapter
            } else {
                println("request_adapter status=$status message=${message?.toKStringFromUtf8()}\n")
            }
        }

    wgpuInstanceRequestAdapter(handler.toCPointer(), descriptor.toCPointer(), handleRequestAdapter.reinterpret(), null)

    val adapter = lastFindAdapter
    lastFindAdapter = null
    return adapter?.toLong() ?: 0L
}
