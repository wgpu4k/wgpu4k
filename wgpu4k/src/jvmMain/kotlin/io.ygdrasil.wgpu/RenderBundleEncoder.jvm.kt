package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.MemorySegment

actual class RenderBundleEncoder(internal val handler: MemorySegment) : AutoCloseable {
    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle  = confined { arena ->
        arena.map(descriptor)
            .let { wgpu_h.wgpuRenderBundleEncoderFinish(handler, it) }
            .let(::RenderBundle)
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup){
        wgpu_h.wgpuRenderBundleEncoderSetBindGroup(handler, index, bindGroup.handler,0, MemorySegment.NULL)
    }

    actual override fun close() {
        wgpu_h.wgpuRenderBundleEncoderRelease(handler)
    }
}