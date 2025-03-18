package io.ygdrasil.webgpu

import io.github.oshai.kotlinlogging.KotlinLogging

actual class Buffer(internal val handler: WGPUBuffer) : GPUBuffer {

    private val logger = KotlinLogging.logger {}

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }
    actual override val size: GPUSize64
        get() = handler.size
    actual override val usage: Set<GPUBufferUsage>
        get() = GPUBufferUsage.entries.filter { it.value and handler.usage != 0uL }.toSet()
    actual override val mapState: GPUBufferMapState
        get() = GPUBufferMapState.of(handler.mapState) ?: error("fail to get MapState")

    actual override fun getMappedRange(
        offset: GPUSize64,
        size: GPUSize64?
    ): ArrayBuffer {
        TODO("Not yet implemented")
    }

    actual override suspend fun mapAsync(
        mode: GPUMapModeFlags,
        offset: GPUSize64,
        size: GPUSize64?
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    actual override fun unmap() {
        handler.unmap()
    }

    actual override fun close() {
        handler.destroy()
    }
}