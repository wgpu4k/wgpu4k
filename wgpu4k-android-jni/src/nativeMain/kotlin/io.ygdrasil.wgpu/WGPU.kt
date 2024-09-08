@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.toKStringFromUtf8
import kotlinx.cinterop.toLong
import webgpu.WGPUAdapter
import webgpu.WGPURequestAdapterStatus
import webgpu.WGPURequestAdapterStatus_Success
import webgpu.wgpuInstanceRequestAdapter
import kotlin.experimental.ExperimentalNativeApi


private var lastFindAdapter: WGPUAdapter? = null

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
