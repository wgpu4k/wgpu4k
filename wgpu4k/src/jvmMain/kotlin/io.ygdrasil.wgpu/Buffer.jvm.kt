package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import java.lang.foreign.MemorySegment

actual class Buffer(internal val handler: MemorySegment) : AutoCloseable {

    actual val size: GPUSize64
        get() = wgpu_h.wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = wgpu_h.wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0 }.toSet() }

    actual fun unmap() {
        wgpu_h.wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: FloatArray) {
        wgpu_h.wgpuBufferGetMappedRange(handler, 0L, (buffer.size * Float.SIZE_BYTES).toLong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual fun mapFrom(buffer: ByteArray){
        wgpu_h.wgpuBufferGetMappedRange(handler, 0L, (buffer.size * Byte.SIZE_BYTES).toLong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual override fun close() {
        wgpu_h.wgpuBufferRelease(handler)
    }

}


