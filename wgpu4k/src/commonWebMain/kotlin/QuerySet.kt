package io.ygdrasil.webgpu

actual class QuerySet(internal val handler: WGPUQuerySet): GPUQuerySet {
    actual override val count: GPUSize32Out
        get() = TODO("Not yet implemented")
    actual override val type: GPUQueryType
        get() = TODO("Not yet implemented")
    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun close() {
        handler.destroy()
    }
}