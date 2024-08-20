package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor
import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.CommandEncoderDescriptor
import io.ygdrasil.wgpu.ComputePassDescriptor
import io.ygdrasil.wgpu.ComputePipelineDescriptor
import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.GPUFlagsConstant
import io.ygdrasil.wgpu.GPUIntegerCoordinateOut
import io.ygdrasil.wgpu.GPUSize32
import io.ygdrasil.wgpu.GPUSize32Out
import io.ygdrasil.wgpu.GPUSize64
import io.ygdrasil.wgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.PipelineLayoutDescriptor
import io.ygdrasil.wgpu.PowerPreference
import io.ygdrasil.wgpu.QuerySetDescriptor
import io.ygdrasil.wgpu.RenderBundle
import io.ygdrasil.wgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.RenderPassDescriptor
import io.ygdrasil.wgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.SamplerDescriptor
import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.TextureViewDescriptor
import io.ygdrasil.wgpu.WGPUInstanceBackend

object JniInterfaceV2 {
    init {
        System.loadLibrary("wgpu4kv2")
    }

    /*** Instance ***/
    external fun wgpuCreateInstance(backend: WGPUInstanceBackend?): Long
    external fun wgpuInstanceRequestAdapter(handler: Long, powerPreference: PowerPreference?, surface: Long): Long
    external fun wgpuInstanceCreateSurface(handler: Long, androidSurface: android.view.Surface): Long
    external fun wgpuInstanceRelease(handler: Long)

    /*** Adapter ***/
    external fun wgpuAdapterRequestDevice(handler: Long, descriptor: DeviceDescriptor): Long
    external fun wgpuAdapterRelease(handler: Long)

    /*** Surface ***/
    external fun wgpuSurfaceGetCurrentTexture(handler: Long): Long
    external fun wgpuSurfacePresent(handler: Long)
    external fun wgpuSurfaceGetFormat(handler: Long, adapter: Long): Int
    external fun wgpuSurfaceConfigure(
        handler: Long,
        device: Long,
        usage: Int,
        format: Int,
        alphaMode: Int,
        width: Int,
        height: Int
    )

    external fun wgpuSurfaceRelease(handler: Long)

    /*** Device ***/
    // TODO jni
    external fun wgpuDeviceGetQueue(handler: Long): Long
    external fun wgpuDeviceCreateCommandEncoder(
        handler: Long,
        descriptor: CommandEncoderDescriptor?
    ): Long

    external fun wgpuDeviceCreateShaderModule(
        handler: Long,
        descriptor: ShaderModuleDescriptor
    ): Long

    // TODO jni
    external fun wgpuDeviceCreatePipelineLayout(
        handler: Long,
        descriptor: PipelineLayoutDescriptor
    ): Long

    external fun wgpuDeviceCreateRenderPipeline(
        handler: Long,
        descriptor: RenderPipelineDescriptor
    ): Long

    // TODO jni
    external fun wgpuDeviceCreateBuffer(handler: Long, descriptor: BufferDescriptor): Long

    // TODO jni
    external fun wgpuDeviceCreateBindGroup(handler: Long, descriptor: BindGroupDescriptor): Long

    // TODO jni
    external fun wgpuDeviceCreateTexture(handler: Long, descriptor: TextureDescriptor): Long

    // TODO jni
    external fun wgpuDeviceCreateSampler(handler: Long, descriptor: SamplerDescriptor): Long

    // TODO jni
    external fun wgpuDeviceCreateComputePipeline(
        handler: Long,
        descriptor: ComputePipelineDescriptor
    ): Long

    // TODO jni
    external fun wgpuDeviceCreateBindGroupLayout(
        handler: Long,
        descriptor: BindGroupLayoutDescriptor
    ): Long

    // TODO jni
    external fun wgpuDeviceCreateRenderBundleEncoder(
        handler: Long,
        descriptor: RenderBundleEncoderDescriptor
    ): Long

    // TODO jni
    external fun wgpuDeviceCreateQuerySet(handler: Long, descriptor: QuerySetDescriptor): Long

    // TODO jni
    external fun wgpuDevicePoll(handler: Long, i: Int)
    external fun wgpuDeviceRelease(handler: Long)

    /*** Texture ***/
    // TODO jni
    external fun wgpuTextureGetWidth(handler: Long): GPUIntegerCoordinateOut

    // TODO jni
    external fun wgpuTextureGetHeight(handler: Long): GPUIntegerCoordinateOut

    // TODO jni
    external fun wgpuTextureGetDepthOrArrayLayers(handler: Long): GPUIntegerCoordinateOut

    // TODO jni
    external fun wgpuTextureGetMipLevelCount(handler: Long): GPUIntegerCoordinateOut

    // TODO jni
    external fun wgpuTextureGetSampleCount(handler: Long): GPUSize32Out

    // TODO jni
    external fun wgpuTextureGetDimension(handler: Long): Int

    // TODO jni
    external fun wgpuTextureGetFormat(handler: Long): Int

    // TODO jni
    external fun wgpuTextureGetUsage(handler: Long): GPUFlagsConstant
    external fun wgpuTextureCreateView(
        handler: Long,
        textureViewDescriptor: TextureViewDescriptor?
    ): Long

    external fun wgpuTextureRelease(handler: Long)

    /*** TextureView ***/
    external fun wgpuTextureViewRelease(handler: Long)

    /*** CommandEncoder ***/
    external fun wgpuCommandEncoderBeginRenderPass(
        handler: Long,
        descriptor: RenderPassDescriptor
    ): Long
    external fun wgpuCommandEncoderFinish(handler: Long): Long
    // TODO jni
    external fun wgpuCommandEncoderCopyTextureToTexture(
        handler: Long,
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    )
    // TODO jni
    external fun wgpuCommandEncoderBeginComputePass(
        handler: Long,
        descriptor: ComputePassDescriptor?
    ): Long
    // TODO jni
    external fun wgpuCommandEncoderCopyTextureToBuffer(
        handler: Long,
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D
    )
    // TODO jni
    external fun wgpuCommandEncoderCopyBufferToTexture(
        handler: Long,
        source: ImageCopyBuffer,
        destination: ImageCopyTexture,
        copySize: Size3D
    )
    external fun wgpuCommandEncoderRelease(handler: Long)

    /*** RenderPassEncoder ***/
    // TODO jni
    external fun wgpuRenderPassEncoderExecuteBundles(
        handler: Long,
        bundlesSize: Long,
        bundles: List<RenderBundle>
    )
    // TODO jni
    external fun wgpuRenderPassEncoderSetIndexBuffer(
        handler: Long,
        handler1: Long,
        value: Int,
        offset: GPUSize64,
        size: GPUSize64
    )
    // TODO jni
    external fun wgpuRenderPassEncoderSetVertexBuffer(
        handler: Long,
        slot: Int,
        buffer: Long,
        offset: Long,
        size: GPUSize64
    )
    // TODO jni
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
    external fun wgpuRenderPassEncoderRelease(handler: Long)

    /*** Queue ***/
    external fun wgpuQueueSubmit(
        handler: Long,
        commandsBufferSize: Long,
        commandsBuffer: List<Long>
    )
    // TODO jni
    external fun wgpuQueueWriteBuffer(
        handler: Long,
        buffer: Long,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: Long
    )
    // TODO jni
    external fun wgpuQueueWriteBuffer(
        handler: Long,
        buffer: Long,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: Long
    )
    // TODO jni
    external fun wgpuQueueWriteTexture(
        handler: Long,
        destination: ImageCopyTextureTagged,
        data: ByteArray,
        toLong: Long,
        width: Int,
        height: Int,
        bytePerPixel: Int
    )

    /*** CommandBuffer ***/

    external fun wgpuCommandBufferRelease(handler: Long)

    /*** Sampler ***/
    external fun wgpuSamplerRelease(handler: Long)

    /*** ShaderModule ***/
    external fun wgpuShaderModuleRelease(handler: Long)
}