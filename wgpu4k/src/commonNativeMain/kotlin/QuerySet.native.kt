package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUQuerySet
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuQuerySetGetCount
import io.ygdrasil.wgpu.wgpuQuerySetGetType
import io.ygdrasil.wgpu.wgpuQuerySetRelease
import io.ygdrasil.wgpu.wgpuQuerySetSetLabel

actual class QuerySet(val handler: WGPUQuerySet) : GPUQuerySet {
    actual override val count: GPUSize32Out
        get() = wgpuQuerySetGetCount(handler)
    actual override val type: GPUQueryType
        get() = wgpuQuerySetGetType(handler)
            .let { GPUQueryType.of(it) ?: error("Unknown query type $it") }
    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuQuerySetSetLabel(handler, newLabel)
        }

    actual override fun close() {
        wgpuQuerySetRelease(handler)
    }
}