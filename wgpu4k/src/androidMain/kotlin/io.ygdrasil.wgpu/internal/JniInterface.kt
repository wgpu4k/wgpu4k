package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor
import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.CommandEncoderDescriptor
import io.ygdrasil.wgpu.ComputePassDescriptor
import io.ygdrasil.wgpu.ComputePipelineDescriptor
import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.GPUFlagsConstant
import io.ygdrasil.wgpu.GPUIndex32
import io.ygdrasil.wgpu.GPUIntegerCoordinateOut
import io.ygdrasil.wgpu.GPUSignedOffset32
import io.ygdrasil.wgpu.GPUSize32
import io.ygdrasil.wgpu.GPUSize32Out
import io.ygdrasil.wgpu.GPUSize64
import io.ygdrasil.wgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.PipelineLayoutDescriptor
import io.ygdrasil.wgpu.QuerySetDescriptor
import io.ygdrasil.wgpu.RenderBundle
import io.ygdrasil.wgpu.RenderBundleDescriptor
import io.ygdrasil.wgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.RenderPassDescriptor
import io.ygdrasil.wgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.SamplerDescriptor
import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.TextureViewDescriptor
import io.ygdrasil.wgpu.WGPUInstanceBackend

class JniInterface  {
    init {
        System.loadLibrary("wgpu4k")
    }

    external fun createWgpuInstance2(): Long

    /*** Instance ***/
    external fun wgpuInstanceRelease(handler: Long)
    external fun wgpuInstanceRequestAdapter(
        handler: Long,
        nothing: Any?,
        nothing1: Any?,
        nothing2: Any?,
    )
    external fun wgpuCreateInstance(backend: WGPUInstanceBackend?): Long

    /*** Texture ***/
    external fun wgpuTextureGetWidth(handler: Long): GPUIntegerCoordinateOut
    external fun wgpuTextureGetHeight(handler: Long): GPUIntegerCoordinateOut
    external fun wgpuTextureGetDepthOrArrayLayers(handler: Long): GPUIntegerCoordinateOut
    external fun wgpuTextureGetMipLevelCount(handler: Long): GPUIntegerCoordinateOut
    external fun wgpuTextureGetSampleCount(handler: Long): GPUSize32Out
    external fun wgpuTextureGetDimension(handler: Long): Int
    external fun wgpuTextureGetFormat(handler: Long): Int
    external fun wgpuTextureGetUsage(handler: Long): GPUFlagsConstant
    external fun wgpuTextureCreateView(
        handler: Long,
        textureViewDescriptor: TextureViewDescriptor?
    ): Long

    external fun wgpuTextureRelease(handler: Long)

    /*** Surface ***/
    external fun wgpuSurfaceGetCurrentTexture(handler: Long): Long
    external fun wgpuSurfacePresent(handler: Long)
    external fun wgpuSurfaceRelease(handler: Long)

    /*** Sampler ***/
    external fun wgpuSamplerRelease(handler: Long)

    /*** ShaderModule ***/
    external fun wgpuShaderModuleRelease(handler: Long)

    /*** TextureView ***/
    external fun wgpuTextureViewRelease(handler: Long)

    /*** RenderPassEncoder ***/
    external fun wgpuRenderPassEncoderRelease(handler: Long)
    external fun wgpuRenderPassEncoderExecuteBundles(
        handler: Long,
        bundlesSize: Long,
        bundles: List<RenderBundle>
    )

    external fun wgpuRenderPassEncoderSetIndexBuffer(
        handler: Long,
        handler1: Long,
        value: Int,
        offset: GPUSize64,
        size: GPUSize64
    )

    external fun wgpuRenderPassEncoderSetVertexBuffer(
        handler: Long,
        slot: Int,
        buffer: Long,
        offset: Long,
        size: GPUSize64
    )

    external fun wgpuRenderPassEncoderSetBindGroup(
        handler: Long,
        index: Int,
        handler1: Long,
        l: Long,
        nothing: Nothing?
    )

    external fun wgpuRenderPassEncoderDraw(
        handler: Long,
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    )

    external fun wgpuRenderPassEncoderSetPipeline(handler: Long, renderPipeline: Long)
    external fun wgpuRenderPassEncoderEnd(handler: Long)

    /*** RenderBundleEncoder ***/
    external fun wgpuRenderBundleEncoderRelease(handler: Long)
    external fun wgpuRenderBundleEncoderDraw(
        handler: Long,
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    )

    external fun wgpuRenderBundleEncoderDrawIndexed(
        handler: Long,
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32
    )

    external fun wgpuRenderBundleEncoderSetIndexBuffer(
        handler: Long,
        handler1: Long,
        value: Int,
        offset: GPUSize64,
        size: GPUSize64
    )

    external fun wgpuRenderBundleEncoderSetVertexBuffer(
        handler: Long,
        slot: GPUIndex32,
        handler1: Long,
        offset: GPUSize64,
        size: GPUSize64
    )

    external fun wgpuRenderBundleEncoderSetPipeline(handler: Long, handler1: Long)
    external fun wgpuRenderBundleEncoderSetBindGroup(
        handler: Long,
        index: GPUIndex32,
        handler1: Long,
        i: Int,
        nothing: Nothing?
    )

    external fun wgpuRenderBundleEncoderFinish(
        handler: Long,
        descriptor: RenderBundleDescriptor
    ): Long

    /*** Queue ***/
    external fun wgpuQueueSubmit(
        handler: Long,
        commandsBufferSize: Long,
        commandsBuffer: List<Long>
    )

    external fun wgpuQueueWriteBuffer(
        handler: Long,
        buffer: Long,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: Long
    )

    external fun wgpuQueueWriteBuffer(
        handler: Long,
        buffer: Long,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: Long
    )

    external fun wgpuQueueWriteTexture(
        handler: Long,
        destination: ImageCopyTextureTagged,
        data: ByteArray,
        toLong: Long,
        width: Int,
        height: Int,
        bytePerPixel: Int
    )

    /*** RenderPipeline ***/
    external fun wgpuRenderPipelineRelease(handler: Long)
    external fun wgpuRenderPipelineGetBindGroupLayout(handler: Long, index: Int): Long

    /*** Device ***/
    external fun wgpuDeviceGetQueue(handler: Long): Long
    external fun wgpuDeviceCreateCommandEncoder(
        handler: Long,
        descriptor: CommandEncoderDescriptor?
    ): Long

    external fun wgpuDeviceCreateShaderModule(
        handler: Long,
        descriptor: ShaderModuleDescriptor
    ): Long

    external fun wgpuDeviceCreatePipelineLayout(
        handler: Long,
        descriptor: PipelineLayoutDescriptor
    ): Long

    external fun wgpuDeviceCreateRenderPipeline(
        handler: Long,
        descriptor: RenderPipelineDescriptor
    ): Long

    external fun wgpuDeviceCreateBuffer(handler: Long, descriptor: BufferDescriptor): Long
    external fun wgpuDeviceCreateBindGroup(handler: Long, descriptor: BindGroupDescriptor): Long
    external fun wgpuDeviceCreateTexture(handler: Long, descriptor: TextureDescriptor): Long
    external fun wgpuDeviceCreateSampler(handler: Long, descriptor: SamplerDescriptor): Long
    external fun wgpuDeviceCreateComputePipeline(
        handler: Long,
        descriptor: ComputePipelineDescriptor
    ): Long

    external fun wgpuDeviceCreateBindGroupLayout(
        handler: Long,
        descriptor: BindGroupLayoutDescriptor
    ): Long

    external fun wgpuDeviceCreateRenderBundleEncoder(
        handler: Long,
        descriptor: RenderBundleEncoderDescriptor
    ): Long

    external fun wgpuDeviceCreateQuerySet(handler: Long, descriptor: QuerySetDescriptor): Long
    external fun wgpuDevicePoll(handler: Long, i: Int)
    external fun wgpuDeviceRelease(handler: Long)

    /*** ComputePipeline ***/

    external fun wgpuComputePipelineGetBindGroupLayout(handler: Long, index: Int): Long
    external fun wgpuComputePipelineRelease(handler: Long)

    /*** ComputePassEncoder ***/

    external fun wgpuComputePassEncoderSetPipeline(handler: Long, handler1: Long)
    external fun wgpuComputePassEncoderDispatchWorkgroups(
        handler: Long,
        workgroupCountX: GPUSize32,
        workgroupCountY: GPUSize32,
        workgroupCountZ: GPUSize32
    )

    external fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(
        handler: Long,
        handler1: Long,
        indirectOffset: GPUSize64
    )

    external fun wgpuComputePassEncoderEnd(handler: Long)
    external fun wgpuComputePassEncoderRelease(handler: Long)

    /*** CommandEncoder ***/

    external fun wgpuCommandEncoderBeginRenderPass(
        handler: Long,
        descriptor: RenderPassDescriptor
    ): Long

    external fun wgpuCommandEncoderFinish(handler: Long): Long
    external fun wgpuCommandEncoderCopyTextureToTexture(
        handler: Long,
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    )

    external fun wgpuCommandEncoderBeginComputePass(
        handler: Long,
        descriptor: ComputePassDescriptor?
    ): Long

    external fun wgpuCommandEncoderCopyTextureToBuffer(
        handler: Long,
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D
    )

    external fun wgpuCommandEncoderCopyBufferToTexture(
        handler: Long,
        source: ImageCopyBuffer,
        destination: ImageCopyTexture,
        copySize: Size3D
    )

    external fun wgpuCommandEncoderRelease(handler: Long)

    /*** CommandBuffer ***/

    external fun wgpuCommandBufferRelease(handler: Long)

    /*** Adapter ***/
    external fun wgpuAdapterRequestDevice(handler: Long, descriptor: DeviceDescriptor): Long
    external fun wgpuAdapterRelease(handler: Long)

    /*** BindGroup ***/

    external fun wgpuBindGroupRelease(handler: Long)

    /*** BindGroupLayout ***/

    external fun wgpuBindGroupLayoutRelease(handler: Long)

    /*** Buffer ***/

    external fun wgpuBufferGetSize(handler: Long): GPUSize64
    external fun wgpuBufferGetUsage(handler: Long): Int
    external fun wgpuBufferGetMapState(handler: Long): Int
    external fun wgpuBufferUnmap(handler: Long)
    external fun wgpuBufferMapAsync(
        handler: Long,
        toFlagInt: Int,
        offset: GPUSize64,
        size: GPUSize64
    )

    external fun wgpuBufferRelease(handler: Long)
    external fun wgpuBufferMapInto(handler: Long, buffer: ByteArray, offset: Int)
    external fun mapFrom(buffer: FloatArray, offset: Int)
    external fun mapFrom(buffer: ByteArray, offset: Int)

    companion object {

        val instance by lazy { JniInterface() }

    }
}