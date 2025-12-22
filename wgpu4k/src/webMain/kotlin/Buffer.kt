@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import io.github.oshai.kotlinlogging.KotlinLogging
import js.promise.await
import kotlin.js.ExperimentalWasmJsInterop

actual class Buffer(val handler: WGPUBuffer) : GPUBuffer {

    private val logger = KotlinLogging.logger {}

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }
    actual override val size: GPUSize64
        get() = handler.size.toULong()
    actual override val usage: Set<GPUBufferUsage>
        get() = GPUBufferUsage.entries.filter { it.value and handler.usage.toULong() != 0uL }.toSet()
    actual override val mapState: GPUBufferMapState
        get() = GPUBufferMapState.of(handler.mapState) ?: error("fail to get MapState")

    actual override fun getMappedRange(
        offset: GPUSize64,
        size: GPUSize64?
    ): ArrayBuffer = when (size) {
        null -> handler.getMappedRange(offset.asJsNumber())
        else -> handler.getMappedRange(offset.asJsNumber(), size.asJsNumber())
    }

    actual override suspend fun mapAsync(
        mode: GPUMapModeFlags,
        offset: GPUSize64,
        size: GPUSize64?
    ): Result<Unit> = when (size) {
        null -> handler.mapAsync(mode.toFlagInt().asJsNumber(), offset.asJsNumber())
        else -> handler.mapAsync(mode.toFlagInt().asJsNumber(), offset.asJsNumber(), size.asJsNumber())
    }.await()
        .let { Result.success(Unit) }

    actual override fun unmap() {
        handler.unmap()
    }

    actual override fun close() {
        handler.destroy()
    }
}