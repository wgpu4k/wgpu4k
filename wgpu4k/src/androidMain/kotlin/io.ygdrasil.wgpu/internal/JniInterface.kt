package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.*

@Deprecated("created to test JNI rust binding, must be remove in favor of work in progress kotlin JNI binding use on JniInterfaceV2")
class JniInterface  {

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

    /*** RenderPipeline ***/
    external fun wgpuRenderPipelineRelease(handler: Long)
    external fun wgpuRenderPipelineGetBindGroupLayout(handler: Long, index: Int): Long

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