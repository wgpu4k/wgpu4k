@file:OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.internal.callIntMethodFrom
import kotlinx.cinterop.*
import platform.android.*
import kotlin.experimental.ExperimentalNativeApi


@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuCreateInstance")
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

@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuInstanceRelease")
fun wgpuInstanceRelease(env: JNIEnv, thiz: jclass, wgpu: jlong) {
    webgpu.wgpuInstanceRelease(wgpu.toCPointer())
}