package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.WGPUTextureView
import io.ygdrasil.wgpu.wgpuTextureViewRelease
import io.ygdrasil.wgpu.wgpuTextureViewSetLabel

actual class TextureView(val handler: WGPUTextureView) : GPUTextureView {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuTextureViewSetLabel(handler, newLabel)
        }

    actual override fun close() {
        wgpuTextureViewRelease(handler)
    }
}