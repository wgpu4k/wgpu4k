package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k
import java.lang.foreign.MemorySegment

actual class BindGroupLayout(val handler: Long) : AutoCloseable {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

    actual override fun close() {
        NativeWgpu4k.wgpuBindGroupLayoutRelease(handler)
    }
}