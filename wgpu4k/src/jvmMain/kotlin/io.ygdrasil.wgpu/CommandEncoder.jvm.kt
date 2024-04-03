package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.computePassDescriptorMapper
import io.ygdrasil.wgpu.mapper.renderPassDescriptorMapper

actual class CommandEncoder(internal val handler: WGPUCommandEncoder) : AutoCloseable {
    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder =
            renderPassDescriptorMapper.map<RenderPassDescriptor, WGPURenderPassDescriptor>(descriptor)
                .also { logUnitNative { "wgpuCommandEncoderBeginRenderPass" to listOf(it) } }
                    .let { wgpuCommandEncoderBeginRenderPass(handler, it) }
                    ?.let { RenderPassEncoder(it) }
                    ?: error("fail to get RenderPassEncoder")

    actual fun finish(): CommandBuffer =
            WGPUCommandBufferDescriptor()
                .also { logUnitNative { "wgpuCommandEncoderFinish" to listOf(it) } }
                    .let { wgpuCommandEncoderFinish(handler, it) }
                    ?.let { CommandBuffer(it) }
                    ?: error("fail to get CommandBuffer")

    actual fun copyTextureToTexture(
            source: ImageCopyTexture,
            destination: ImageCopyTexture,
            copySize: GPUIntegerCoordinates
    ) {
        actualCopyTextureToTexture(
                source.convert(),
                destination.convert(),
                copySize.convert()
        )
    }

    fun actualCopyTextureToTexture(
            source: WGPUImageCopyTexture,
            destination: WGPUImageCopyTexture,
            copySize: WGPUExtent3D
    ) {
        wgpuCommandEncoderCopyTextureToTexture(
                handler,
                source,
                destination,
                copySize
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder =
            descriptor?.let { computePassDescriptorMapper.map<ComputePassDescriptor, WGPUComputePassDescriptor>(descriptor) }
                    .let { wgpuCommandEncoderBeginComputePass(handler, it) }
                    ?.let { ComputePassEncoder(it) }
                    ?: error("fail to get ComputePassEncoder")


    override fun close() {
        logUnitNative { "wgpuCommandEncoderRelease" to listOf(handler) }
        wgpuCommandEncoderRelease(handler)
    }

}

private fun Pair<Int, Int>.convert(): WGPUExtent3D = WGPUExtent3D().also {
    it.height = second
    it.width = first
    it.depthOrArrayLayers = 1
}

private fun ImageCopyTexture.convert(): WGPUImageCopyTexture = WGPUImageCopyTexture().also {

    it.texture = texture.handler
    it.mipLevel = mipLevel
    it.origin = origin.let { (x, y) ->
        WGPUOrigin3D().also {
            it.x = x
            it.y = y
        }
    }
    it.aspect = aspect.value
}
