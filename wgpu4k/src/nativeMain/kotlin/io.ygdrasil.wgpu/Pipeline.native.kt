@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPUPipelineLayout
import webgpu.WGPURenderPipeline

actual class RenderPipeline(internal val handler: WGPURenderPipeline) : AutoCloseable {
    actual fun getBindGroupLayout(index: Int): BindGroupLayout {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        TODO("Not yet implemented")
    }

}

actual class PipelineLayout(internal val handler: WGPUPipelineLayout)