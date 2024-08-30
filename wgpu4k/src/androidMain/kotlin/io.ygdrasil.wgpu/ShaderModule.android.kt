package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.JnaInterface
import java.lang.foreign.MemorySegment

actual class ShaderModule(val handler: Long) : AutoCloseable {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

    actual override fun close() {
        JnaInterface.wgpuShaderModuleRelease(handler)
    }

}