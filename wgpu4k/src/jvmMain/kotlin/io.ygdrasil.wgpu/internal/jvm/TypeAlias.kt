package io.ygdrasil.wgpu.`internal`.jvm

import com.sun.jna.Callback
import com.sun.jna.NativeLong
import com.sun.jna.Pointer

public typealias WGPUFlags = Int

public typealias `WGPUFlags$Array` = IntArray

public typealias WGPUBool = Int

public typealias `WGPUBool$Array` = IntArray

public typealias WGPUAdapter = WGPUAdapterImpl

public typealias WGPUBindGroup = WGPUBindGroupImpl

public typealias WGPUBindGroupLayout = WGPUBindGroupLayoutImpl

public typealias WGPUBuffer = WGPUBufferImpl

public typealias WGPUCommandBuffer = WGPUCommandBufferImpl

public typealias WGPUCommandEncoder = WGPUCommandEncoderImpl

public typealias WGPUComputePassEncoder = WGPUComputePassEncoderImpl

public typealias WGPUComputePipeline = WGPUComputePipelineImpl

public typealias WGPUDevice = WGPUDeviceImpl

public typealias WGPUInstance = WGPUInstanceImpl

public typealias WGPUPipelineLayout = WGPUPipelineLayoutImpl

public typealias WGPUQuerySet = WGPUQuerySetImpl

public typealias WGPUQueue = WGPUQueueImpl

public typealias WGPURenderBundle = WGPURenderBundleImpl

public typealias WGPURenderBundleEncoder = WGPURenderBundleEncoderImpl

public typealias WGPURenderPassEncoder = WGPURenderPassEncoderImpl

public typealias WGPURenderPipeline = WGPURenderPipelineImpl

public typealias WGPUSampler = WGPUSamplerImpl

public typealias WGPUShaderModule = WGPUShaderModuleImpl

public typealias WGPUSurface = WGPUSurfaceImpl

public typealias WGPUTexture = WGPUTextureImpl

public typealias WGPUTextureView = WGPUTextureViewImpl

public typealias WGPUBufferUsageFlags = Int

public typealias `WGPUBufferUsageFlags$Array` = IntArray

public typealias WGPUColorWriteMaskFlags = Int

public typealias `WGPUColorWriteMaskFlags$Array` = IntArray

public typealias WGPUMapModeFlags = Int

public typealias `WGPUMapModeFlags$Array` = IntArray

public typealias WGPUShaderStageFlags = Int

public typealias `WGPUShaderStageFlags$Array` = IntArray

public typealias WGPUTextureUsageFlags = Int

public typealias `WGPUTextureUsageFlags$Array` = IntArray

public interface WGPUBufferMapCallback : Callback {
	public operator fun invoke(param1: Unit, param2: Unit)
}

public interface WGPUCompilationInfoCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUCompilationInfo,
		param3: Unit,
	)
}

public interface WGPUCreateComputePipelineAsyncCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUComputePipelineImpl,
		param3: Byte,
		param4: Unit,
	)
}

public interface WGPUCreateRenderPipelineAsyncCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPURenderPipelineImpl,
		param3: Byte,
		param4: Unit,
	)
}

public interface WGPUDeviceLostCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Byte,
		param3: Unit,
	)
}

public interface WGPUErrorCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Byte,
		param3: Unit,
	)
}

public interface WGPUProc : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUQueueWorkDoneCallback : Callback {
	public operator fun invoke(param1: Unit, param2: Unit)
}

public interface WGPURequestAdapterCallback : Callback {
	public operator fun invoke(
		param1: Int,
		param2: WGPUAdapterImpl,
		param3: String?,
		param4: Pointer?,
	)
}

public interface WGPURequestDeviceCallback : Callback {
	public operator fun invoke(
		param1: Int,
		param2: WGPUDeviceImpl,
		param3: String?,
		param4: Pointer?,
	)
}

public interface WGPUProcCreateInstance : Callback {
	public operator fun invoke(param1: WGPUInstanceImpl): WGPUInstanceImpl
}

public interface WGPUProcGetProcAddress : Callback {
	public operator fun invoke(param1: Pointer?, param2: Byte)
}

public interface WGPUProcAdapterEnumerateFeatures : Callback {
	public operator fun invoke(param1: NativeLong, param2: Int): NativeLong
}

public interface WGPUProcAdapterGetLimits : Callback {
	public operator fun invoke(param1: Int, param2: WGPUSupportedLimits): Int
}

public interface WGPUProcAdapterGetProperties : Callback {
	public operator fun invoke(param1: Unit, param2: WGPUAdapterProperties)
}

public interface WGPUProcAdapterHasFeature : Callback {
	public operator fun invoke(param1: Int, param2: Int): Int
}

public interface WGPUProcAdapterRequestDevice : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUDeviceDescriptor,
		param3: Unit,
		param4: WGPUDeviceImpl,
		param5: Byte,
		param6: Unit,
		param7: Unit,
	)
}

public interface WGPUProcAdapterReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcAdapterRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcBindGroupSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcBindGroupReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcBindGroupRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcBindGroupLayoutSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcBindGroupLayoutReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcBindGroupLayoutRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcBufferDestroy : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcBufferGetConstMappedRange : Callback {
	public operator fun invoke(
		param1: Pointer?,
		param2: NativeLong,
		param3: NativeLong,
	): Pointer
}

public interface WGPUProcBufferGetMapState : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcBufferGetMappedRange : Callback {
	public operator fun invoke(
		param1: Pointer?,
		param2: NativeLong,
		param3: NativeLong,
	): Pointer
}

public interface WGPUProcBufferGetSize : Callback {
	public operator fun invoke(param1: NativeLong): NativeLong
}

public interface WGPUProcBufferGetUsage : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcBufferMapAsync : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: NativeLong,
		param4: NativeLong,
		param5: Unit,
		param6: Unit,
		param7: Unit,
	)
}

public interface WGPUProcBufferSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcBufferUnmap : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcBufferReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcBufferRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcCommandBufferSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcCommandBufferReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcCommandBufferRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcCommandEncoderBeginComputePass : Callback {
	public operator fun invoke(param1: WGPUComputePassEncoderImpl, param2: WGPUComputePassDescriptor):
		WGPUComputePassEncoderImpl
}

public interface WGPUProcCommandEncoderBeginRenderPass : Callback {
	public operator fun invoke(param1: WGPURenderPassEncoderImpl, param2: WGPURenderPassDescriptor):
		WGPURenderPassEncoderImpl
}

public interface WGPUProcCommandEncoderClearBuffer : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: NativeLong,
		param4: NativeLong,
	)
}

public interface WGPUProcCommandEncoderCopyBufferToBuffer : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: NativeLong,
		param4: WGPUBufferImpl,
		param5: NativeLong,
		param6: NativeLong,
	)
}

public interface WGPUProcCommandEncoderCopyBufferToTexture : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUImageCopyBuffer,
		param3: WGPUImageCopyTexture,
		param4: WGPUExtent3D,
	)
}

public interface WGPUProcCommandEncoderCopyTextureToBuffer : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUImageCopyTexture,
		param3: WGPUImageCopyBuffer,
		param4: WGPUExtent3D,
	)
}

public interface WGPUProcCommandEncoderCopyTextureToTexture : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUImageCopyTexture,
		param3: WGPUImageCopyTexture,
		param4: WGPUExtent3D,
	)
}

public interface WGPUProcCommandEncoderFinish : Callback {
	public operator fun invoke(param1: WGPUCommandBufferImpl, param2: WGPUCommandBufferDescriptor):
		WGPUCommandBufferImpl
}

public interface WGPUProcCommandEncoderInsertDebugMarker : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcCommandEncoderPopDebugGroup : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcCommandEncoderPushDebugGroup : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcCommandEncoderResolveQuerySet : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUQuerySetImpl,
		param3: Int,
		param4: Int,
		param5: WGPUBufferImpl,
		param6: NativeLong,
	)
}

public interface WGPUProcCommandEncoderSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcCommandEncoderWriteTimestamp : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUQuerySetImpl,
		param3: Int,
	)
}

public interface WGPUProcCommandEncoderReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcCommandEncoderRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcComputePassEncoderDispatchWorkgroups : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: Int,
		param4: Int,
	)
}

public interface WGPUProcComputePassEncoderDispatchWorkgroupsIndirect : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: NativeLong,
	)
}

public interface WGPUProcComputePassEncoderEnd : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcComputePassEncoderInsertDebugMarker : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcComputePassEncoderPopDebugGroup : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcComputePassEncoderPushDebugGroup : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcComputePassEncoderSetBindGroup : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: WGPUBindGroupImpl,
		param4: NativeLong,
		param5: Int,
	)
}

public interface WGPUProcComputePassEncoderSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcComputePassEncoderSetPipeline : Callback {
	public operator fun invoke(param1: Unit, param2: WGPUComputePipelineImpl)
}

public interface WGPUProcComputePassEncoderReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcComputePassEncoderRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcComputePipelineGetBindGroupLayout : Callback {
	public operator fun invoke(param1: WGPUBindGroupLayoutImpl, param2: Int): WGPUBindGroupLayoutImpl
}

public interface WGPUProcComputePipelineSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcComputePipelineReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcComputePipelineRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcDeviceCreateBindGroup : Callback {
	public operator fun invoke(param1: WGPUBindGroupImpl, param2: WGPUBindGroupDescriptor):
		WGPUBindGroupImpl
}

public interface WGPUProcDeviceCreateBindGroupLayout : Callback {
	public operator fun invoke(
		param1: WGPUBindGroupLayoutImpl,
		param2: WGPUBindGroupLayoutDescriptor
	): WGPUBindGroupLayoutImpl
}

public interface WGPUProcDeviceCreateBuffer : Callback {
	public operator fun invoke(param1: WGPUBufferImpl, param2: WGPUBufferDescriptor): WGPUBufferImpl
}

public interface WGPUProcDeviceCreateCommandEncoder : Callback {
	public operator fun invoke(param1: WGPUCommandEncoderImpl, param2: WGPUCommandEncoderDescriptor):
		WGPUCommandEncoderImpl
}

public interface WGPUProcDeviceCreateComputePipeline : Callback {
	public operator fun invoke(
		param1: WGPUComputePipelineImpl,
		param2: WGPUComputePipelineDescriptor
	): WGPUComputePipelineImpl
}

public interface WGPUProcDeviceCreateComputePipelineAsync : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUComputePipelineDescriptor,
		param3: Unit,
		param4: WGPUComputePipelineImpl,
		param5: Byte,
		param6: Unit,
		param7: Unit,
	)
}

public interface WGPUProcDeviceCreatePipelineLayout : Callback {
	public operator fun invoke(param1: WGPUPipelineLayoutImpl, param2: WGPUPipelineLayoutDescriptor):
		WGPUPipelineLayoutImpl
}

public interface WGPUProcDeviceCreateQuerySet : Callback {
	public operator fun invoke(param1: WGPUQuerySetImpl, param2: WGPUQuerySetDescriptor):
		WGPUQuerySetImpl
}

public interface WGPUProcDeviceCreateRenderBundleEncoder : Callback {
	public operator fun invoke(
		param1: WGPURenderBundleEncoderImpl,
		param2: WGPURenderBundleEncoderDescriptor
	): WGPURenderBundleEncoderImpl
}

public interface WGPUProcDeviceCreateRenderPipeline : Callback {
	public operator fun invoke(param1: WGPURenderPipelineImpl, param2: WGPURenderPipelineDescriptor):
		WGPURenderPipelineImpl
}

public interface WGPUProcDeviceCreateRenderPipelineAsync : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPURenderPipelineDescriptor,
		param3: Unit,
		param4: WGPURenderPipelineImpl,
		param5: Byte,
		param6: Unit,
		param7: Unit,
	)
}

public interface WGPUProcDeviceCreateSampler : Callback {
	public operator fun invoke(param1: WGPUSamplerImpl, param2: WGPUSamplerDescriptor):
		WGPUSamplerImpl
}

public interface WGPUProcDeviceCreateShaderModule : Callback {
	public operator fun invoke(param1: WGPUShaderModuleImpl, param2: WGPUShaderModuleDescriptor):
		WGPUShaderModuleImpl
}

public interface WGPUProcDeviceCreateTexture : Callback {
	public operator fun invoke(param1: WGPUTextureImpl, param2: WGPUTextureDescriptor):
		WGPUTextureImpl
}

public interface WGPUProcDeviceDestroy : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcDeviceEnumerateFeatures : Callback {
	public operator fun invoke(param1: NativeLong, param2: Int): NativeLong
}

public interface WGPUProcDeviceGetLimits : Callback {
	public operator fun invoke(param1: Int, param2: WGPUSupportedLimits): Int
}

public interface WGPUProcDeviceGetQueue : Callback {
	public operator fun invoke(param1: WGPUQueueImpl): WGPUQueueImpl
}

public interface WGPUProcDeviceHasFeature : Callback {
	public operator fun invoke(param1: Int, param2: Int): Int
}

public interface WGPUProcDevicePopErrorScope : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Unit,
		param3: Byte,
		param4: Unit,
		param5: Unit,
	)
}

public interface WGPUProcDevicePushErrorScope : Callback {
	public operator fun invoke(param1: Unit, param2: Int)
}

public interface WGPUProcDeviceSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcDeviceSetUncapturedErrorCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Unit,
		param3: Byte,
		param4: Unit,
		param5: Unit,
	)
}

public interface WGPUProcDeviceReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcDeviceRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcInstanceCreateSurface : Callback {
	public operator fun invoke(param1: WGPUSurfaceImpl, param2: WGPUSurfaceDescriptor):
		WGPUSurfaceImpl
}

public interface WGPUProcInstanceProcessEvents : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcInstanceRequestAdapter : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPURequestAdapterOptions,
		param3: Unit,
		param4: WGPUAdapterImpl,
		param5: Byte,
		param6: Unit,
		param7: Unit,
	)
}

public interface WGPUProcInstanceReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcInstanceRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcPipelineLayoutSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcPipelineLayoutReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcPipelineLayoutRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcQuerySetDestroy : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcQuerySetGetCount : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcQuerySetGetType : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcQuerySetSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcQuerySetReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcQuerySetRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcQueueOnSubmittedWorkDone : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Unit,
		param3: Unit,
		param4: Unit,
	)
}

public interface WGPUProcQueueSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcQueueSubmit : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: NativeLong,
		param3: WGPUCommandBufferImpl,
	)
}

public interface WGPUProcQueueWriteBuffer : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: NativeLong,
		param4: Unit,
		param5: NativeLong,
	)
}

public interface WGPUProcQueueWriteTexture : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUImageCopyTexture,
		param3: Unit,
		param4: NativeLong,
		param5: WGPUTextureDataLayout,
		param6: WGPUExtent3D,
	)
}

public interface WGPUProcQueueReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcQueueRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderBundleSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcRenderBundleReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderBundleRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderBundleEncoderDraw : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: Int,
		param4: Int,
		param5: Int,
	)
}

public interface WGPUProcRenderBundleEncoderDrawIndexed : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: Int,
		param4: Int,
		param5: Int,
		param6: Int,
	)
}

public interface WGPUProcRenderBundleEncoderDrawIndexedIndirect : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: NativeLong,
	)
}

public interface WGPUProcRenderBundleEncoderDrawIndirect : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: NativeLong,
	)
}

public interface WGPUProcRenderBundleEncoderFinish : Callback {
	public operator fun invoke(param1: WGPURenderBundleImpl, param2: WGPURenderBundleDescriptor):
		WGPURenderBundleImpl
}

public interface WGPUProcRenderBundleEncoderInsertDebugMarker : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcRenderBundleEncoderPopDebugGroup : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderBundleEncoderPushDebugGroup : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcRenderBundleEncoderSetBindGroup : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: WGPUBindGroupImpl,
		param4: NativeLong,
		param5: Int,
	)
}

public interface WGPUProcRenderBundleEncoderSetIndexBuffer : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: Int,
		param4: NativeLong,
		param5: NativeLong,
	)
}

public interface WGPUProcRenderBundleEncoderSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcRenderBundleEncoderSetPipeline : Callback {
	public operator fun invoke(param1: Unit, param2: WGPURenderPipelineImpl)
}

public interface WGPUProcRenderBundleEncoderSetVertexBuffer : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: WGPUBufferImpl,
		param4: NativeLong,
		param5: NativeLong,
	)
}

public interface WGPUProcRenderBundleEncoderReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderBundleEncoderRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderPassEncoderBeginOcclusionQuery : Callback {
	public operator fun invoke(param1: Unit, param2: Int)
}

public interface WGPUProcRenderPassEncoderDraw : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: Int,
		param4: Int,
		param5: Int,
	)
}

public interface WGPUProcRenderPassEncoderDrawIndexed : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: Int,
		param4: Int,
		param5: Int,
		param6: Int,
	)
}

public interface WGPUProcRenderPassEncoderDrawIndexedIndirect : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: NativeLong,
	)
}

public interface WGPUProcRenderPassEncoderDrawIndirect : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: NativeLong,
	)
}

public interface WGPUProcRenderPassEncoderEnd : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderPassEncoderEndOcclusionQuery : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderPassEncoderExecuteBundles : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: NativeLong,
		param3: WGPURenderBundleImpl,
	)
}

public interface WGPUProcRenderPassEncoderInsertDebugMarker : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcRenderPassEncoderPopDebugGroup : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderPassEncoderPushDebugGroup : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcRenderPassEncoderSetBindGroup : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: WGPUBindGroupImpl,
		param4: NativeLong,
		param5: Int,
	)
}

public interface WGPUProcRenderPassEncoderSetBlendConstant : Callback {
	public operator fun invoke(param1: Unit, param2: WGPUColor)
}

public interface WGPUProcRenderPassEncoderSetIndexBuffer : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUBufferImpl,
		param3: Int,
		param4: NativeLong,
		param5: NativeLong,
	)
}

public interface WGPUProcRenderPassEncoderSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcRenderPassEncoderSetPipeline : Callback {
	public operator fun invoke(param1: Unit, param2: WGPURenderPipelineImpl)
}

public interface WGPUProcRenderPassEncoderSetScissorRect : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: Int,
		param4: Int,
		param5: Int,
	)
}

public interface WGPUProcRenderPassEncoderSetStencilReference : Callback {
	public operator fun invoke(param1: Unit, param2: Int)
}

public interface WGPUProcRenderPassEncoderSetVertexBuffer : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Int,
		param3: WGPUBufferImpl,
		param4: NativeLong,
		param5: NativeLong,
	)
}

public interface WGPUProcRenderPassEncoderSetViewport : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Float,
		param3: Float,
		param4: Float,
		param5: Float,
		param6: Float,
		param7: Float,
	)
}

public interface WGPUProcRenderPassEncoderReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderPassEncoderRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderPipelineGetBindGroupLayout : Callback {
	public operator fun invoke(param1: WGPUBindGroupLayoutImpl, param2: Int): WGPUBindGroupLayoutImpl
}

public interface WGPUProcRenderPipelineSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcRenderPipelineReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcRenderPipelineRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcSamplerSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcSamplerReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcSamplerRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcShaderModuleGetCompilationInfo : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Unit,
		param3: WGPUCompilationInfo,
		param4: Unit,
		param5: Unit,
	)
}

public interface WGPUProcShaderModuleSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcShaderModuleReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcShaderModuleRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcSurfaceConfigure : Callback {
	public operator fun invoke(param1: Unit, param2: WGPUSurfaceConfiguration)
}

public interface WGPUProcSurfaceGetCapabilities : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUAdapterImpl,
		param3: WGPUSurfaceCapabilities,
	)
}

public interface WGPUProcSurfaceGetCurrentTexture : Callback {
	public operator fun invoke(param1: Unit, param2: WGPUSurfaceTexture)
}

public interface WGPUProcSurfaceGetPreferredFormat : Callback {
	public operator fun invoke(param1: Int, param2: WGPUAdapterImpl): Int
}

public interface WGPUProcSurfacePresent : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcSurfaceUnconfigure : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcSurfaceReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcSurfaceRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcSurfaceCapabilitiesFreeMembers : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcTextureCreateView : Callback {
	public operator fun invoke(param1: WGPUTextureViewImpl, param2: WGPUTextureViewDescriptor):
		WGPUTextureViewImpl
}

public interface WGPUProcTextureDestroy : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcTextureGetDepthOrArrayLayers : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcTextureGetDimension : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcTextureGetFormat : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcTextureGetHeight : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcTextureGetMipLevelCount : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcTextureGetSampleCount : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcTextureGetUsage : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcTextureGetWidth : Callback {
	public operator fun invoke(param1: Int): Int
}

public interface WGPUProcTextureSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcTextureReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcTextureRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcTextureViewSetLabel : Callback {
	public operator fun invoke(param1: Unit, param2: Byte)
}

public interface WGPUProcTextureViewReference : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUProcTextureViewRelease : Callback {
	public operator fun invoke(param1: Unit)
}

public typealias WGPUInstanceBackendFlags = Int

public typealias `WGPUInstanceBackendFlags$Array` = IntArray

public typealias WGPUInstanceFlags = Int

public typealias `WGPUInstanceFlags$Array` = IntArray

public typealias WGPUSubmissionIndex = NativeLong

public interface WGPULogCallback : Callback {
	public operator fun invoke(
		level: Int,
		message: String,
		param3: Pointer?,
	)
}
