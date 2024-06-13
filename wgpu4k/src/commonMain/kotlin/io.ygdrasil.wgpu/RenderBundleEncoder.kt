package io.ygdrasil.wgpu

expect class RenderBundleEncoder: AutoCloseable {

    fun finish(descriptor: RenderBundleDescriptor = RenderBundleDescriptor()): RenderBundle

    fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup)

    override fun close()
}


class RenderBundleDescriptor(val label: String? = null)