package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.JnaInterface
import java.lang.foreign.MemorySegment

actual class RenderPipeline(val handler: Long) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout {
        return JnaInterface.wgpuRenderPipelineGetBindGroupLayout(handler, index)
            .let { BindGroupLayout(it) }
    }

    actual override fun close() {
        JnaInterface.wgpuRenderPipelineRelease(handler)
    }

}

actual class PipelineLayout(val handler: Long) {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

}