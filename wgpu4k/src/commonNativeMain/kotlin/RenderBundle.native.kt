package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPURenderBundle
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuRenderBundleSetLabel

actual class RenderBundle(val handler: WGPURenderBundle, label: String) : GPURenderBundle {
    actual override var label: String = label
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuRenderBundleSetLabel(handler, newLabel)
            field = value
        }
}