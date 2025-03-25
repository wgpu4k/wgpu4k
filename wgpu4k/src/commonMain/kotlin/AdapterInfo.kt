package io.ygdrasil.webgpu

internal data class AdapterInfo(
    override val architecture: String,
    override val description: String,
    override val device: String,
    override val subgroupMaxSize: UInt,
    override val subgroupMinSize: UInt,
    override val vendor: String
): GPUAdapterInfo