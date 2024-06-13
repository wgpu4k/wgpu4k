package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPURenderBundleEncoder
import io.ygdrasil.wgpu.mapper.map

actual class RenderBundleEncoder(
    val handler: GPURenderBundleEncoder
) : AutoCloseable {
    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle =
        map(descriptor)
            .let { handler.finish(it) }
            .let(::RenderBundle)

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        handler.setBindGroup(index, bindGroup.handler)
    }

    actual override fun close() {
        // Nothing to do on js
    }

}

