package io.ygdrasil.wgpu.internal.jna

import com.sun.jna.Callback
import com.sun.jna.Library
import com.sun.jna.Native

val wgpu4kLibrary = Native.load("wgpu4k", Wgpu4kLibrary::class.java)

interface Wgpu4kLibrary: Library {
    fun wgpuAdapterRequestDevice(handler: Long, toAddress: Long, callback: Callback, l: Long)

}
