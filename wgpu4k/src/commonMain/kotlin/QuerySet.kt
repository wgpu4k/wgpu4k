package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class QuerySet : GPUQuerySet {

    override val count: GPUSize32Out
    override val type: GPUQueryType
    override var label: String

    override fun close()
}