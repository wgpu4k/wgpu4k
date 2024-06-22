package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.WGPUBufferMapCallback
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.util.concurrent.atomic.AtomicInteger

actual class Buffer(internal val handler: MemorySegment) : AutoCloseable {

    actual val size: GPUSize64
        get() = wgpu_h.wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = wgpu_h.wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0 }.toSet() }

    actual fun unmap() {
        wgpu_h.wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: FloatArray, offset: Int) {
        wgpu_h.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Float.SIZE_BYTES).toLong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual fun mapFrom(buffer: ByteArray, offset: Int){
        wgpu_h.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Byte.SIZE_BYTES).toLong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64): Deferred<BufferMapAsyncStatus> {
        val arena = Arena.ofConfined()
        val atomicStatus = AtomicInteger(-1)
        val callback = WGPUBufferMapCallback.allocate( { status, data ->
            println("done")
            runBlocking {
                atomicStatus.set(status)
            }
        }, arena)

        wgpu_h.wgpuBufferMapAsync(handler, mode.toFlagInt(), offset, size, callback, callback)

        println("mhh ${atomicStatus.get()}")
        return CoroutineScope(Dispatchers.IO).async {
            while(atomicStatus.get() == -1) {
                sleep(1000)
                yield()
            }
            arena.close()
            BufferMapAsyncStatus.of(atomicStatus.get()) ?: error("fail to get BufferMapAsyncStatus with raw value ${atomicStatus.get()}")
        }
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        wgpu_h.wgpuBufferGetMappedRange(handler, offset.toLong(), buffer.size.toLong())
            .asByteBuffer()
            .get(buffer,0, buffer.size)
    }

    actual override fun close() {
        wgpu_h.wgpuBufferRelease(handler)
    }

}


