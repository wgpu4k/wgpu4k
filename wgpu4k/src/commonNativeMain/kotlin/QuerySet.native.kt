package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUQuerySet
import io.ygdrasil.wgpu.wgpuQuerySetRelease

actual class QuerySet(internal val handler: WGPUQuerySet) : GPUQuerySet {
    actual override val count: GPUSize32Out
        get() = TODO("Not yet implemented")
    actual override val type: GPUQueryType
        get() = TODO("Not yet implemented")
    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun close() {
        wgpuQuerySetRelease(handler)
    }
}