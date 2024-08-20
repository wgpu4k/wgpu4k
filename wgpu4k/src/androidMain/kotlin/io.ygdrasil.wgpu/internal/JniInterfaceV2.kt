package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor
import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.CommandEncoderDescriptor
import io.ygdrasil.wgpu.ComputePipelineDescriptor
import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.PipelineLayoutDescriptor
import io.ygdrasil.wgpu.PowerPreference
import io.ygdrasil.wgpu.QuerySetDescriptor
import io.ygdrasil.wgpu.RenderBundleEncoderDescriptor
import io.ygdrasil.wgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.SamplerDescriptor
import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.TextureDescriptor
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
    external fun wgpuSurfaceGetFormat(handler: Long, adapter: Long) :  Int
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
    // TODO jni
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
    // TODO jni
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

}