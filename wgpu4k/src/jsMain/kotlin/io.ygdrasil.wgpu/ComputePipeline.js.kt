@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUComputePipeline

actual class ComputePipeline(internal val handler: GPUComputePipeline) : AutoCloseable {

    override fun close() {
        // Nothing to do on js
    }

}