package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k
import java.lang.foreign.MemorySegment

actual class RenderPipeline(val handler: Long) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout {
        return NativeWgpu4k.wgpuRenderPipelineGetBindGroupLayout(handler, index)
            .let { BindGroupLayout(it) }
    }

    actual override fun close() {
        NativeWgpu4k.wgpuRenderPipelineRelease(handler)
    }

}

actual class PipelineLayout(val handler: Long) {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

}