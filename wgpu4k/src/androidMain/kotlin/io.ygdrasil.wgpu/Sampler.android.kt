package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.JnaInterface
import java.lang.foreign.MemorySegment

actual class Sampler(val handler: Long) : AutoCloseable {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

    actual override fun close() {
        JnaInterface.wgpuSamplerRelease(handler)
    }

}