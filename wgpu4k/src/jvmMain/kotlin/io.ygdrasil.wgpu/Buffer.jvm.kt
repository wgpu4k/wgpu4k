package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import java.lang.foreign.MemorySegment

actual class Buffer(internal val handler: MemorySegment) : AutoCloseable {

    actual val size: GPUSize64
        get() = webgpu_h.wgpuBufferGetSize(handler)

    actual fun getMappedRange(offset: GPUSize64?, size: GPUSize64?): ByteArray {
        webgpu_h.wgpuBufferGetMappedRange(handler, offset ?: 0, size ?: 0)
        TODO()
    }

    actual fun unmap() {
        webgpu_h.wgpuBufferUnmap(handler)
    }

    actual fun map(buffer: FloatArray) {
        webgpu_h.wgpuBufferGetMappedRange(handler, 0L, (buffer.size * Float.SIZE_BYTES).toLong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual override fun close() {
        webgpu_h.wgpuBufferRelease(handler)
    }

}


