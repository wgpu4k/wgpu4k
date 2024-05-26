package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import java.lang.foreign.MemorySegment

actual class Buffer(internal val handler: MemorySegment) : AutoCloseable {

    actual val size: GPUSize64
        get() = wgpu_h.wgpuBufferGetSize(handler)

    actual fun getMappedRange(offset: GPUSize64?, size: GPUSize64?): ByteArray {
        wgpu_h.wgpuBufferGetMappedRange(handler, offset ?: 0, size ?: 0)
        TODO()
    }

    actual fun unmap() {
        wgpu_h.wgpuBufferUnmap(handler)
    }

    actual fun map(buffer: FloatArray) {
        wgpu_h.wgpuBufferGetMappedRange(handler, 0L, (buffer.size * Float.SIZE_BYTES).toLong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual override fun close() {
        wgpu_h.wgpuBufferRelease(handler)
    }

}


