@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import kotlin.js.ExperimentalWasmJsInterop

actual class RenderPipeline(internal var handler: WGPURenderPipeline) : GPURenderPipeline {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun getBindGroupLayout(index: GPUSize32): GPUBindGroupLayout = handler
        .getBindGroupLayout(index.asJsNumber())
        .let(::BindGroupLayout)


    actual override fun close() {
        // Nothing to do on js
    }

}