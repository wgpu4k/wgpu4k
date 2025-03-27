package io.ygdrasil.webgpu

actual class QuerySet(val handler: WGPUQuerySet): GPUQuerySet {
    actual override val count: GPUSize32Out
        get() = handler.count.asUInt()
    actual override val type: GPUQueryType
        get() = GPUQueryType.of(handler.type) ?: error("Unknown query type ${handler.type}")
    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun close() {
        handler.destroy()
    }
}