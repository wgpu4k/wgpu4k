package io.ygdrasil.wgpu

expect class RenderBundleEncoder : AutoCloseable {

    fun finish(descriptor: RenderBundleDescriptor = RenderBundleDescriptor()): RenderBundle

    fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup)

    fun setPipeline(renderPipeline: RenderPipeline)

    fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64 = 0, size: GPUSize64 = buffer.size)

    fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64 = 0, size: GPUSize64 = buffer.size)

    fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32 = 1,
        firstIndex: GPUSize32 = 0,
        baseVertex: GPUSignedOffset32 = 0,
        firstInstance: GPUSize32 = 0,
    )

    fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32 = 1,
        firstVertex: GPUSize32 = 0,
        firstInstance: GPUSize32 = 0,
    )

    /*
     TODO add
     fun drawIndexed(indexCount: GPUSize32, instanceCount: GPUSize32 = definedExternally, firstIndex: GPUSize32 = definedExternally, baseVertex: GPUSignedOffset32 = definedExternally, firstInstance: GPUSize32 = definedExternally): Nothing?
     fun drawIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64): Nothing?
     fun drawIndexedIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64): Nothing?
     */

    override fun close()
}


class RenderBundleDescriptor(val label: String? = null)