package io.ygdrasil.wgpu

actual class RenderBundleEncoder : AutoCloseable {

    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle {
        TODO("Not yet implemented")
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        TODO("Not yet implemented")
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        TODO("Not yet implemented")
    }

    actual fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        TODO("Not yet implemented")
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        TODO("Not yet implemented")
    }

    actual fun drawIndexed(indexCount: GPUSize32, instanceCount: GPUSize32, firstIndex: GPUSize32, baseVertex: GPUSignedOffset32, firstInstance: GPUSize32) {
        TODO("Not yet implemented")
    }

    actual fun draw(vertexCount: GPUSize32, instanceCount: GPUSize32, firstVertex: GPUSize32, firstInstance: GPUSize32) {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}
