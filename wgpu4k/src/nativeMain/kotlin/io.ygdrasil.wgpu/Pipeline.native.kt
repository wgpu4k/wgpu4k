@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.*

actual class RenderPipeline(internal val handler: WGPURenderPipeline) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout {
        return wgpuRenderPipelineGetBindGroupLayout(handler, index.toUInt())
            ?.let { BindGroupLayout(it) } ?: error("fail to get bindgroup layout")
    }

    actual override fun close() {
        wgpuRenderPipelineRelease(handler)
    }

}

actual class PipelineLayout(internal val handler: WGPUPipelineLayout) : AutoCloseable {

    actual override fun close() {
        wgpuPipelineLayoutRelease(handler)
    }

}