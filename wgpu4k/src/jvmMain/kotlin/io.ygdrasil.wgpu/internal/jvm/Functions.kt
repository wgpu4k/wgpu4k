package io.ygdrasil.wgpu.`internal`.jvm

import com.sun.jna.Library
import com.sun.jna.NativeLong
import com.sun.jna.Pointer

public val libWGPULibrary: WGPULibrary by lazy {
	klang.internal.NativeLoad<io.ygdrasil.wgpu.internal.jvm.WGPULibrary>("WGPU")
}

public interface WGPULibrary : Library {
	/**
	 * @param descriptor mapped from (typedef Optional[const WGPUInstanceDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]](WGPUInstanceDescriptor)))*
	 */
	public fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance?

	/**
	 * @param device mapped from WGPUDevice
	 * @param procName mapped from (Char(layout = b1))*
	 */
	public fun wgpuGetProcAddress(device: WGPUDevice?, procName: String?): WGPUProc?

	/**
	 * @param adapter mapped from WGPUAdapter
	 * @param features mapped from (typedef Optional[WGPUFeatureName] = Declared(i4))*
	 */
	public fun wgpuAdapterEnumerateFeatures(adapter: WGPUAdapter?, features: Pointer?): NativeLong

	/**
	 * @param adapter mapped from WGPUAdapter
	 * @param limits mapped from (typedef Optional[WGPUSupportedLimits] =
	 * Declared([a8(nextInChain):[*:b1][i4(maxTextureDimension1D)i4(maxTextureDimension2D)i4(maxTextureDimension3D)i4(maxTextureArrayLayers)i4(maxBindGroups)i4(maxBindGroupsPlusVertexBuffers)i4(maxBindingsPerBindGroup)i4(maxDynamicUniformBuffersPerPipelineLayout)i4(maxDynamicStorageBuffersPerPipelineLayout)i4(maxSampledTexturesPerShaderStage)i4(maxSamplersPerShaderStage)i4(maxStorageBuffersPerShaderStage)i4(maxStorageTexturesPerShaderStage)i4(maxUniformBuffersPerShaderStage)j8(maxUniformBufferBindingSize)j8(maxStorageBufferBindingSize)i4(minUniformBufferOffsetAlignment)i4(minStorageBufferOffsetAlignment)i4(maxVertexBuffers)x4j8(maxBufferSize)i4(maxVertexAttributes)i4(maxVertexBufferArrayStride)i4(maxInterStageShaderComponents)i4(maxInterStageShaderVariables)i4(maxColorAttachments)i4(maxColorAttachmentBytesPerSample)i4(maxComputeWorkgroupStorageSize)i4(maxComputeInvocationsPerWorkgroup)i4(maxComputeWorkgroupSizeX)i4(maxComputeWorkgroupSizeY)i4(maxComputeWorkgroupSizeZ)i4(maxComputeWorkgroupsPerDimension)](limits)](WGPUSupportedLimits)))*
	 */
	public fun wgpuAdapterGetLimits(adapter: WGPUAdapter?, limits: WGPUSupportedLimits?): WGPUBool

	/**
	 * @param adapter mapped from WGPUAdapter
	 * @param properties mapped from (typedef Optional[WGPUAdapterProperties] =
	 * Declared([a8(nextInChain):[*:b1]i4(vendorID)x4a8(vendorName):[*:b1]a8(architecture):[*:b1]i4(deviceID)x4a8(name):[*:b1]a8(driverDescription):[*:b1]i4(adapterType)i4(backendType)](WGPUAdapterProperties)))*
	 */
	public fun wgpuAdapterGetProperties(adapter: WGPUAdapter?, properties: WGPUAdapterProperties?)

	/**
	 * @param adapter mapped from WGPUAdapter
	 * @param feature mapped from WGPUFeatureName
	 */
	public fun wgpuAdapterHasFeature(adapter: WGPUAdapter?, feature: Int): WGPUBool

	/**
	 * @param adapter mapped from WGPUAdapter
	 * @param descriptor mapped from (typedef Optional[const WGPUDeviceDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(requiredFeatureCount)a8(requiredFeatures):[*:b1]a8(requiredLimits):[*:b1][a8(nextInChain):[*:b1]a8(label):[*:b1]](defaultQueue)a8(deviceLostCallback):[*:b1]a8(deviceLostUserdata):[*:b1]](WGPUDeviceDescriptor)))*
	 * @param callback mapped from WGPURequestDeviceCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuAdapterRequestDevice(
		adapter: WGPUAdapter?,
		descriptor: WGPUDeviceDescriptor?,
		callback: WGPURequestDeviceCallback?,
		userdata: Pointer?,
	)

	/**
	 * @param adapter mapped from WGPUAdapter
	 */
	public fun wgpuAdapterReference(adapter: WGPUAdapter?)

	/**
	 * @param adapter mapped from WGPUAdapter
	 */
	public fun wgpuAdapterRelease(adapter: WGPUAdapter?)

	/**
	 * @param bindGroup mapped from WGPUBindGroup
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuBindGroupSetLabel(bindGroup: WGPUBindGroup?, label: String?)

	/**
	 * @param bindGroup mapped from WGPUBindGroup
	 */
	public fun wgpuBindGroupReference(bindGroup: WGPUBindGroup?)

	/**
	 * @param bindGroup mapped from WGPUBindGroup
	 */
	public fun wgpuBindGroupRelease(bindGroup: WGPUBindGroup?)

	/**
	 * @param bindGroupLayout mapped from WGPUBindGroupLayout
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuBindGroupLayoutSetLabel(bindGroupLayout: WGPUBindGroupLayout?, label: String?)

	/**
	 * @param bindGroupLayout mapped from WGPUBindGroupLayout
	 */
	public fun wgpuBindGroupLayoutReference(bindGroupLayout: WGPUBindGroupLayout?)

	/**
	 * @param bindGroupLayout mapped from WGPUBindGroupLayout
	 */
	public fun wgpuBindGroupLayoutRelease(bindGroupLayout: WGPUBindGroupLayout?)

	/**
	 * @param buffer mapped from WGPUBuffer
	 */
	public fun wgpuBufferDestroy(buffer: WGPUBuffer?)

	/**
	 * @param buffer mapped from WGPUBuffer
	 * @param offset mapped from size_t
	 * @param size mapped from size_t
	 */
	public fun wgpuBufferGetConstMappedRange(
		buffer: WGPUBuffer?,
		offset: NativeLong,
		size: NativeLong,
	): Pointer?

	/**
	 * @param buffer mapped from WGPUBuffer
	 */
	public fun wgpuBufferGetMapState(buffer: WGPUBuffer?): Int

	/**
	 * @param buffer mapped from WGPUBuffer
	 * @param offset mapped from size_t
	 * @param size mapped from size_t
	 */
	public fun wgpuBufferGetMappedRange(
		buffer: WGPUBuffer?,
		offset: NativeLong?,
		size: NativeLong?,
	): Pointer?

	/**
	 * @param buffer mapped from WGPUBuffer
	 */
	public fun wgpuBufferGetSize(buffer: WGPUBuffer?): Long

	/**
	 * @param buffer mapped from WGPUBuffer
	 */
	public fun wgpuBufferGetUsage(buffer: WGPUBuffer?): WGPUBufferUsageFlags

	/**
	 * @param buffer mapped from WGPUBuffer
	 * @param mode mapped from WGPUMapModeFlags
	 * @param offset mapped from size_t
	 * @param size mapped from size_t
	 * @param callback mapped from WGPUBufferMapCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuBufferMapAsync(
		buffer: WGPUBuffer?,
		mode: WGPUMapModeFlags,
		offset: NativeLong,
		size: NativeLong,
		callback: WGPUBufferMapCallback?,
		userdata: Pointer?,
	)

	/**
	 * @param buffer mapped from WGPUBuffer
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuBufferSetLabel(buffer: WGPUBuffer?, label: String?)

	/**
	 * @param buffer mapped from WGPUBuffer
	 */
	public fun wgpuBufferUnmap(buffer: WGPUBuffer?)

	/**
	 * @param buffer mapped from WGPUBuffer
	 */
	public fun wgpuBufferReference(buffer: WGPUBuffer?)

	/**
	 * @param buffer mapped from WGPUBuffer
	 */
	public fun wgpuBufferRelease(buffer: WGPUBuffer?)

	/**
	 * @param commandBuffer mapped from WGPUCommandBuffer
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuCommandBufferSetLabel(commandBuffer: WGPUCommandBuffer?, label: String?)

	/**
	 * @param commandBuffer mapped from WGPUCommandBuffer
	 */
	public fun wgpuCommandBufferReference(commandBuffer: WGPUCommandBuffer?)

	/**
	 * @param commandBuffer mapped from WGPUCommandBuffer
	 */
	public fun wgpuCommandBufferRelease(commandBuffer: WGPUCommandBuffer?)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param descriptor mapped from (typedef Optional[const WGPUComputePassDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(timestampWrites):[*:b1]](WGPUComputePassDescriptor)))*
	 */
	public fun wgpuCommandEncoderBeginComputePass(
		commandEncoder: WGPUCommandEncoder?,
		descriptor: WGPUComputePassDescriptor?
	): WGPUComputePassEncoder?

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param descriptor mapped from (typedef Optional[const WGPURenderPassDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(colorAttachmentCount)a8(colorAttachments):[*:b1]a8(depthStencilAttachment):[*:b1]a8(occlusionQuerySet):[*:b1]a8(timestampWrites):[*:b1]](WGPURenderPassDescriptor)))*
	 */
	public fun wgpuCommandEncoderBeginRenderPass(
		commandEncoder: WGPUCommandEncoder?,
		descriptor: WGPURenderPassDescriptor?
	): WGPURenderPassEncoder?

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param buffer mapped from WGPUBuffer
	 * @param offset mapped from uint64_t
	 * @param size mapped from uint64_t
	 */
	public fun wgpuCommandEncoderClearBuffer(
		commandEncoder: WGPUCommandEncoder?,
		buffer: WGPUBuffer?,
		offset: Long,
		size: Long,
	)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param source mapped from WGPUBuffer
	 * @param sourceOffset mapped from uint64_t
	 * @param destination mapped from WGPUBuffer
	 * @param destinationOffset mapped from uint64_t
	 * @param size mapped from uint64_t
	 */
	public fun wgpuCommandEncoderCopyBufferToBuffer(
		commandEncoder: WGPUCommandEncoder?,
		source: WGPUBuffer?,
		sourceOffset: Long,
		destination: WGPUBuffer?,
		destinationOffset: Long,
		size: Long,
	)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param source mapped from (typedef Optional[const WGPUImageCopyBuffer] =
	 * Declared([a8(nextInChain):[*:b1][a8(nextInChain):[*:b1]j8(offset)i4(bytesPerRow)i4(rowsPerImage)](layout)a8(buffer):[*:b1]](WGPUImageCopyBuffer)))*
	 * @param destination mapped from (typedef Optional[const WGPUImageCopyTexture] =
	 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
	 * @param copySize mapped from (typedef Optional[const WGPUExtent3D] =
	 * Declared([i4(width)i4(height)i4(depthOrArrayLayers)](WGPUExtent3D)))*
	 */
	public fun wgpuCommandEncoderCopyBufferToTexture(
		commandEncoder: WGPUCommandEncoder?,
		source: WGPUImageCopyBuffer?,
		destination: WGPUImageCopyTexture?,
		copySize: WGPUExtent3D?,
	)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param source mapped from (typedef Optional[const WGPUImageCopyTexture] =
	 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
	 * @param destination mapped from (typedef Optional[const WGPUImageCopyBuffer] =
	 * Declared([a8(nextInChain):[*:b1][a8(nextInChain):[*:b1]j8(offset)i4(bytesPerRow)i4(rowsPerImage)](layout)a8(buffer):[*:b1]](WGPUImageCopyBuffer)))*
	 * @param copySize mapped from (typedef Optional[const WGPUExtent3D] =
	 * Declared([i4(width)i4(height)i4(depthOrArrayLayers)](WGPUExtent3D)))*
	 */
	public fun wgpuCommandEncoderCopyTextureToBuffer(
		commandEncoder: WGPUCommandEncoder?,
		source: WGPUImageCopyTexture?,
		destination: WGPUImageCopyBuffer?,
		copySize: WGPUExtent3D?,
	)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param source mapped from (typedef Optional[const WGPUImageCopyTexture] =
	 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
	 * @param destination mapped from (typedef Optional[const WGPUImageCopyTexture] =
	 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
	 * @param copySize mapped from (typedef Optional[const WGPUExtent3D] =
	 * Declared([i4(width)i4(height)i4(depthOrArrayLayers)](WGPUExtent3D)))*
	 */
	public fun wgpuCommandEncoderCopyTextureToTexture(
		commandEncoder: WGPUCommandEncoder?,
		source: WGPUImageCopyTexture?,
		destination: WGPUImageCopyTexture?,
		copySize: WGPUExtent3D?,
	)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param descriptor mapped from (typedef Optional[const WGPUCommandBufferDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]](WGPUCommandBufferDescriptor)))*
	 */
	public fun wgpuCommandEncoderFinish(
		commandEncoder: WGPUCommandEncoder?,
		descriptor: WGPUCommandBufferDescriptor?
	): WGPUCommandBuffer?

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param markerLabel mapped from (Char(layout = b1))*
	 */
	public fun wgpuCommandEncoderInsertDebugMarker(
		commandEncoder: WGPUCommandEncoder?,
		markerLabel: String?
	)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 */
	public fun wgpuCommandEncoderPopDebugGroup(commandEncoder: WGPUCommandEncoder?)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param groupLabel mapped from (Char(layout = b1))*
	 */
	public fun wgpuCommandEncoderPushDebugGroup(
		commandEncoder: WGPUCommandEncoder?,
		groupLabel: String?
	)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param querySet mapped from WGPUQuerySet
	 * @param firstQuery mapped from uint32_t
	 * @param queryCount mapped from uint32_t
	 * @param destination mapped from WGPUBuffer
	 * @param destinationOffset mapped from uint64_t
	 */
	public fun wgpuCommandEncoderResolveQuerySet(
		commandEncoder: WGPUCommandEncoder?,
		querySet: WGPUQuerySet?,
		firstQuery: Int,
		queryCount: Int,
		destination: WGPUBuffer?,
		destinationOffset: Long,
	)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuCommandEncoderSetLabel(commandEncoder: WGPUCommandEncoder?, label: String?)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 * @param querySet mapped from WGPUQuerySet
	 * @param queryIndex mapped from uint32_t
	 */
	public fun wgpuCommandEncoderWriteTimestamp(
		commandEncoder: WGPUCommandEncoder?,
		querySet: WGPUQuerySet?,
		queryIndex: Int,
	)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 */
	public fun wgpuCommandEncoderReference(commandEncoder: WGPUCommandEncoder?)

	/**
	 * @param commandEncoder mapped from WGPUCommandEncoder
	 */
	public fun wgpuCommandEncoderRelease(commandEncoder: WGPUCommandEncoder?)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 * @param workgroupCountX mapped from uint32_t
	 * @param workgroupCountY mapped from uint32_t
	 * @param workgroupCountZ mapped from uint32_t
	 */
	public fun wgpuComputePassEncoderDispatchWorkgroups(
		computePassEncoder: WGPUComputePassEncoder?,
		workgroupCountX: Int,
		workgroupCountY: Int,
		workgroupCountZ: Int,
	)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 * @param indirectBuffer mapped from WGPUBuffer
	 * @param indirectOffset mapped from uint64_t
	 */
	public fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(
		computePassEncoder: WGPUComputePassEncoder?,
		indirectBuffer: WGPUBuffer?,
		indirectOffset: Long,
	)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 */
	public fun wgpuComputePassEncoderEnd(computePassEncoder: WGPUComputePassEncoder?)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 * @param markerLabel mapped from (Char(layout = b1))*
	 */
	public fun wgpuComputePassEncoderInsertDebugMarker(
		computePassEncoder: WGPUComputePassEncoder?,
		markerLabel: String?
	)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 */
	public fun wgpuComputePassEncoderPopDebugGroup(computePassEncoder: WGPUComputePassEncoder?)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 * @param groupLabel mapped from (Char(layout = b1))*
	 */
	public fun wgpuComputePassEncoderPushDebugGroup(
		computePassEncoder: WGPUComputePassEncoder?,
		groupLabel: String?
	)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 * @param groupIndex mapped from uint32_t
	 * @param group mapped from WGPUBindGroup
	 * @param dynamicOffsetCount mapped from size_t
	 * @param dynamicOffsets mapped from (typedef Optional[const uint32_t] = UNSIGNED = Int(layout =
	 * i4))*
	 */
	public fun wgpuComputePassEncoderSetBindGroup(
		computePassEncoder: WGPUComputePassEncoder?,
		groupIndex: Int,
		group: WGPUBindGroup?,
		dynamicOffsetCount: NativeLong,
		dynamicOffsets: Pointer?,
	)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuComputePassEncoderSetLabel(
		computePassEncoder: WGPUComputePassEncoder?,
		label: String?
	)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 * @param pipeline mapped from WGPUComputePipeline
	 */
	public fun wgpuComputePassEncoderSetPipeline(
		computePassEncoder: WGPUComputePassEncoder?,
		pipeline: WGPUComputePipeline?
	)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 */
	public fun wgpuComputePassEncoderReference(computePassEncoder: WGPUComputePassEncoder?)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 */
	public fun wgpuComputePassEncoderRelease(computePassEncoder: WGPUComputePassEncoder?)

	/**
	 * @param computePipeline mapped from WGPUComputePipeline
	 * @param groupIndex mapped from uint32_t
	 */
	public fun wgpuComputePipelineGetBindGroupLayout(
		computePipeline: WGPUComputePipeline?,
		groupIndex: Int
	): WGPUBindGroupLayout?

	/**
	 * @param computePipeline mapped from WGPUComputePipeline
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuComputePipelineSetLabel(computePipeline: WGPUComputePipeline?, label: String?)

	/**
	 * @param computePipeline mapped from WGPUComputePipeline
	 */
	public fun wgpuComputePipelineReference(computePipeline: WGPUComputePipeline?)

	/**
	 * @param computePipeline mapped from WGPUComputePipeline
	 */
	public fun wgpuComputePipelineRelease(computePipeline: WGPUComputePipeline?)

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUBindGroupDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1]j8(entryCount)a8(entries):[*:b1]](WGPUBindGroupDescriptor)))*
	 */
	public fun wgpuDeviceCreateBindGroup(device: WGPUDevice?, descriptor: WGPUBindGroupDescriptor?):
		WGPUBindGroup?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUBindGroupLayoutDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(entryCount)a8(entries):[*:b1]](WGPUBindGroupLayoutDescriptor)))*
	 */
	public fun wgpuDeviceCreateBindGroupLayout(
		device: WGPUDevice?,
		descriptor: WGPUBindGroupLayoutDescriptor?
	): WGPUBindGroupLayout?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUBufferDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(usage)x4j8(size)i4(mappedAtCreation)x4](WGPUBufferDescriptor)))*
	 */
	public fun wgpuDeviceCreateBuffer(device: WGPUDevice?, descriptor: WGPUBufferDescriptor?):
		WGPUBuffer?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUCommandEncoderDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]](WGPUCommandEncoderDescriptor)))*
	 */
	public fun wgpuDeviceCreateCommandEncoder(
		device: WGPUDevice?,
		descriptor: WGPUCommandEncoderDescriptor?
	): WGPUCommandEncoder?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUComputePipelineDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1][a8(nextInChain):[*:b1]a8(module):[*:b1]a8(entryPoint):[*:b1]j8(constantCount)a8(constants):[*:b1]](compute)](WGPUComputePipelineDescriptor)))*
	 */
	public fun wgpuDeviceCreateComputePipeline(
		device: WGPUDevice?,
		descriptor: WGPUComputePipelineDescriptor?
	): WGPUComputePipeline?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUComputePipelineDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1][a8(nextInChain):[*:b1]a8(module):[*:b1]a8(entryPoint):[*:b1]j8(constantCount)a8(constants):[*:b1]](compute)](WGPUComputePipelineDescriptor)))*
	 * @param callback mapped from WGPUCreateComputePipelineAsyncCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuDeviceCreateComputePipelineAsync(
		device: WGPUDevice?,
		descriptor: WGPUComputePipelineDescriptor?,
		callback: WGPUCreateComputePipelineAsyncCallback?,
		userdata: Pointer?,
	)

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUPipelineLayoutDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(bindGroupLayoutCount)a8(bindGroupLayouts):[*:b1]](WGPUPipelineLayoutDescriptor)))*
	 */
	public fun wgpuDeviceCreatePipelineLayout(
		device: WGPUDevice?,
		descriptor: WGPUPipelineLayoutDescriptor?
	): WGPUPipelineLayout?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUQuerySetDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(type)i4(count)](WGPUQuerySetDescriptor)))*
	 */
	public fun wgpuDeviceCreateQuerySet(device: WGPUDevice?, descriptor: WGPUQuerySetDescriptor?):
		WGPUQuerySet?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPURenderBundleEncoderDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(colorFormatCount)a8(colorFormats):[*:b1]i4(depthStencilFormat)i4(sampleCount)i4(depthReadOnly)i4(stencilReadOnly)](WGPURenderBundleEncoderDescriptor)))*
	 */
	public fun wgpuDeviceCreateRenderBundleEncoder(
		device: WGPUDevice?,
		descriptor: WGPURenderBundleEncoderDescriptor?
	): WGPURenderBundleEncoder?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPURenderPipelineDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1][a8(nextInChain):[*:b1]a8(module):[*:b1]a8(entryPoint):[*:b1]j8(constantCount)a8(constants):[*:b1]j8(bufferCount)a8(buffers):[*:b1]](vertex)[a8(nextInChain):[*:b1]i4(topology)i4(stripIndexFormat)i4(frontFace)i4(cullMode)](primitive)a8(depthStencil):[*:b1][a8(nextInChain):[*:b1]i4(count)i4(mask)i4(alphaToCoverageEnabled)x4](multisample)a8(fragment):[*:b1]](WGPURenderPipelineDescriptor)))*
	 */
	public fun wgpuDeviceCreateRenderPipeline(
		device: WGPUDevice?,
		descriptor: WGPURenderPipelineDescriptor?
	): WGPURenderPipeline?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPURenderPipelineDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1][a8(nextInChain):[*:b1]a8(module):[*:b1]a8(entryPoint):[*:b1]j8(constantCount)a8(constants):[*:b1]j8(bufferCount)a8(buffers):[*:b1]](vertex)[a8(nextInChain):[*:b1]i4(topology)i4(stripIndexFormat)i4(frontFace)i4(cullMode)](primitive)a8(depthStencil):[*:b1][a8(nextInChain):[*:b1]i4(count)i4(mask)i4(alphaToCoverageEnabled)x4](multisample)a8(fragment):[*:b1]](WGPURenderPipelineDescriptor)))*
	 * @param callback mapped from WGPUCreateRenderPipelineAsyncCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuDeviceCreateRenderPipelineAsync(
		device: WGPUDevice?,
		descriptor: WGPURenderPipelineDescriptor?,
		callback: WGPUCreateRenderPipelineAsyncCallback?,
		userdata: Pointer?,
	)

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUSamplerDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(addressModeU)i4(addressModeV)i4(addressModeW)i4(magFilter)i4(minFilter)i4(mipmapFilter)f4(lodMinClamp)f4(lodMaxClamp)i4(compare)s2(maxAnisotropy)x2](WGPUSamplerDescriptor)))*
	 */
	public fun wgpuDeviceCreateSampler(device: WGPUDevice?, descriptor: WGPUSamplerDescriptor?):
		WGPUSampler?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUShaderModuleDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(hintCount)a8(hints):[*:b1]](WGPUShaderModuleDescriptor)))*
	 */
	public fun wgpuDeviceCreateShaderModule(
		device: WGPUDevice?,
		descriptor: WGPUShaderModuleDescriptor?
	): WGPUShaderModule?

	/**
	 * @param device mapped from WGPUDevice
	 * @param descriptor mapped from (typedef Optional[const WGPUTextureDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(usage)i4(dimension)[i4(width)i4(height)i4(depthOrArrayLayers)](size)i4(format)i4(mipLevelCount)i4(sampleCount)j8(viewFormatCount)a8(viewFormats):[*:b1]](WGPUTextureDescriptor)))*
	 */
	public fun wgpuDeviceCreateTexture(device: WGPUDevice?, descriptor: WGPUTextureDescriptor?):
		WGPUTexture?

	/**
	 * @param device mapped from WGPUDevice
	 */
	public fun wgpuDeviceDestroy(device: WGPUDevice?)

	/**
	 * @param device mapped from WGPUDevice
	 * @param features mapped from (typedef Optional[WGPUFeatureName] = Declared(i4))*
	 */
	public fun wgpuDeviceEnumerateFeatures(device: WGPUDevice?, features: Pointer?): NativeLong

	/**
	 * @param device mapped from WGPUDevice
	 * @param limits mapped from (typedef Optional[WGPUSupportedLimits] =
	 * Declared([a8(nextInChain):[*:b1][i4(maxTextureDimension1D)i4(maxTextureDimension2D)i4(maxTextureDimension3D)i4(maxTextureArrayLayers)i4(maxBindGroups)i4(maxBindGroupsPlusVertexBuffers)i4(maxBindingsPerBindGroup)i4(maxDynamicUniformBuffersPerPipelineLayout)i4(maxDynamicStorageBuffersPerPipelineLayout)i4(maxSampledTexturesPerShaderStage)i4(maxSamplersPerShaderStage)i4(maxStorageBuffersPerShaderStage)i4(maxStorageTexturesPerShaderStage)i4(maxUniformBuffersPerShaderStage)j8(maxUniformBufferBindingSize)j8(maxStorageBufferBindingSize)i4(minUniformBufferOffsetAlignment)i4(minStorageBufferOffsetAlignment)i4(maxVertexBuffers)x4j8(maxBufferSize)i4(maxVertexAttributes)i4(maxVertexBufferArrayStride)i4(maxInterStageShaderComponents)i4(maxInterStageShaderVariables)i4(maxColorAttachments)i4(maxColorAttachmentBytesPerSample)i4(maxComputeWorkgroupStorageSize)i4(maxComputeInvocationsPerWorkgroup)i4(maxComputeWorkgroupSizeX)i4(maxComputeWorkgroupSizeY)i4(maxComputeWorkgroupSizeZ)i4(maxComputeWorkgroupsPerDimension)](limits)](WGPUSupportedLimits)))*
	 */
	public fun wgpuDeviceGetLimits(device: WGPUDevice?, limits: WGPUSupportedLimits?): WGPUBool

	/**
	 * @param device mapped from WGPUDevice
	 */
	public fun wgpuDeviceGetQueue(device: WGPUDevice?): WGPUQueue?

	/**
	 * @param device mapped from WGPUDevice
	 * @param feature mapped from WGPUFeatureName
	 */
	public fun wgpuDeviceHasFeature(device: WGPUDevice?, feature: Int): WGPUBool

	/**
	 * @param device mapped from WGPUDevice
	 * @param callback mapped from WGPUErrorCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuDevicePopErrorScope(
		device: WGPUDevice?,
		callback: WGPUErrorCallback?,
		userdata: Pointer?,
	)

	/**
	 * @param device mapped from WGPUDevice
	 * @param filter mapped from WGPUErrorFilter
	 */
	public fun wgpuDevicePushErrorScope(device: WGPUDevice?, filter: Int)

	/**
	 * @param device mapped from WGPUDevice
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuDeviceSetLabel(device: WGPUDevice?, label: String?)

	/**
	 * @param device mapped from WGPUDevice
	 * @param callback mapped from WGPUErrorCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuDeviceSetUncapturedErrorCallback(
		device: WGPUDevice?,
		callback: WGPUErrorCallback?,
		userdata: Pointer?,
	)

	/**
	 * @param device mapped from WGPUDevice
	 */
	public fun wgpuDeviceReference(device: WGPUDevice?)

	/**
	 * @param device mapped from WGPUDevice
	 */
	public fun wgpuDeviceRelease(device: WGPUDevice?)

	/**
	 * @param instance mapped from WGPUInstance
	 * @param descriptor mapped from (typedef Optional[const WGPUSurfaceDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]](WGPUSurfaceDescriptor)))*
	 */
	public fun wgpuInstanceCreateSurface(instance: WGPUInstance?, descriptor: WGPUSurfaceDescriptor?):
		WGPUSurface?
	public fun wgpuInstanceCreateSurface(instance: WGPUInstance, descriptor: WGPUDarwinSurfaceDescriptor):
		WGPUSurface?

	/**
	 * @param instance mapped from WGPUInstance
	 */
	public fun wgpuInstanceProcessEvents(instance: WGPUInstance?)

	/**
	 * @param instance mapped from WGPUInstance
	 * @param options mapped from (typedef Optional[const WGPURequestAdapterOptions] =
	 * Declared([a8(nextInChain):[*:b1]a8(compatibleSurface):[*:b1]i4(powerPreference)i4(backendType)i4(forceFallbackAdapter)x4](WGPURequestAdapterOptions)))*
	 * @param callback mapped from WGPURequestAdapterCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuInstanceRequestAdapter(
		instance: WGPUInstance?,
		options: WGPURequestAdapterOptions?,
		callback: WGPURequestAdapterCallback?,
		userdata: Pointer?,
	)

	/**
	 * @param instance mapped from WGPUInstance
	 */
	public fun wgpuInstanceReference(instance: WGPUInstance?)

	/**
	 * @param instance mapped from WGPUInstance
	 */
	public fun wgpuInstanceRelease(instance: WGPUInstance?)

	/**
	 * @param pipelineLayout mapped from WGPUPipelineLayout
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuPipelineLayoutSetLabel(pipelineLayout: WGPUPipelineLayout?, label: String?)

	/**
	 * @param pipelineLayout mapped from WGPUPipelineLayout
	 */
	public fun wgpuPipelineLayoutReference(pipelineLayout: WGPUPipelineLayout?)

	/**
	 * @param pipelineLayout mapped from WGPUPipelineLayout
	 */
	public fun wgpuPipelineLayoutRelease(pipelineLayout: WGPUPipelineLayout?)

	/**
	 * @param querySet mapped from WGPUQuerySet
	 */
	public fun wgpuQuerySetDestroy(querySet: WGPUQuerySet?)

	/**
	 * @param querySet mapped from WGPUQuerySet
	 */
	public fun wgpuQuerySetGetCount(querySet: WGPUQuerySet?): Int

	/**
	 * @param querySet mapped from WGPUQuerySet
	 */
	public fun wgpuQuerySetGetType(querySet: WGPUQuerySet?): Int

	/**
	 * @param querySet mapped from WGPUQuerySet
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuQuerySetSetLabel(querySet: WGPUQuerySet?, label: String?)

	/**
	 * @param querySet mapped from WGPUQuerySet
	 */
	public fun wgpuQuerySetReference(querySet: WGPUQuerySet?)

	/**
	 * @param querySet mapped from WGPUQuerySet
	 */
	public fun wgpuQuerySetRelease(querySet: WGPUQuerySet?)

	/**
	 * @param queue mapped from WGPUQueue
	 * @param callback mapped from WGPUQueueWorkDoneCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuQueueOnSubmittedWorkDone(
		queue: WGPUQueue?,
		callback: WGPUQueueWorkDoneCallback?,
		userdata: Pointer?,
	)

	/**
	 * @param queue mapped from WGPUQueue
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuQueueSetLabel(queue: WGPUQueue?, label: String?)

	/**
	 * @param queue mapped from WGPUQueue
	 * @param commandCount mapped from size_t
	 * @param commands mapped from (typedef Optional[const WGPUCommandBuffer] = (Declared())*)*
	 */
	public fun wgpuQueueSubmit(
		queue: WGPUQueue?,
		commandCount: NativeLong,
		commands: Array<WGPUCommandBuffer>?,
	)

	/**
	 * @param queue mapped from WGPUQueue
	 * @param buffer mapped from WGPUBuffer
	 * @param bufferOffset mapped from uint64_t
	 * @param data mapped from (Void)*
	 * @param size mapped from size_t
	 */
	public fun wgpuQueueWriteBuffer(
		queue: WGPUQueue?,
		buffer: WGPUBuffer?,
		bufferOffset: Long,
		`data`: Pointer?,
		size: NativeLong,
	)

	/**
	 * @param queue mapped from WGPUQueue
	 * @param destination mapped from (typedef Optional[const WGPUImageCopyTexture] =
	 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
	 * @param data mapped from (Void)*
	 * @param dataSize mapped from size_t
	 * @param dataLayout mapped from (typedef Optional[const WGPUTextureDataLayout] =
	 * Declared([a8(nextInChain):[*:b1]j8(offset)i4(bytesPerRow)i4(rowsPerImage)](WGPUTextureDataLayout)))*
	 * @param writeSize mapped from (typedef Optional[const WGPUExtent3D] =
	 * Declared([i4(width)i4(height)i4(depthOrArrayLayers)](WGPUExtent3D)))*
	 */
	public fun wgpuQueueWriteTexture(
		queue: WGPUQueue?,
		destination: WGPUImageCopyTexture?,
		`data`: Pointer?,
		dataSize: NativeLong,
		dataLayout: WGPUTextureDataLayout?,
		writeSize: WGPUExtent3D?,
	)

	/**
	 * @param queue mapped from WGPUQueue
	 */
	public fun wgpuQueueReference(queue: WGPUQueue?)

	/**
	 * @param queue mapped from WGPUQueue
	 */
	public fun wgpuQueueRelease(queue: WGPUQueue?)

	/**
	 * @param renderBundle mapped from WGPURenderBundle
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuRenderBundleSetLabel(renderBundle: WGPURenderBundle?, label: String?)

	/**
	 * @param renderBundle mapped from WGPURenderBundle
	 */
	public fun wgpuRenderBundleReference(renderBundle: WGPURenderBundle?)

	/**
	 * @param renderBundle mapped from WGPURenderBundle
	 */
	public fun wgpuRenderBundleRelease(renderBundle: WGPURenderBundle?)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param vertexCount mapped from uint32_t
	 * @param instanceCount mapped from uint32_t
	 * @param firstVertex mapped from uint32_t
	 * @param firstInstance mapped from uint32_t
	 */
	public fun wgpuRenderBundleEncoderDraw(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		vertexCount: Int,
		instanceCount: Int,
		firstVertex: Int,
		firstInstance: Int,
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param indexCount mapped from uint32_t
	 * @param instanceCount mapped from uint32_t
	 * @param firstIndex mapped from uint32_t
	 * @param baseVertex mapped from int32_t
	 * @param firstInstance mapped from uint32_t
	 */
	public fun wgpuRenderBundleEncoderDrawIndexed(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		indexCount: Int,
		instanceCount: Int,
		firstIndex: Int,
		baseVertex: Int,
		firstInstance: Int,
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param indirectBuffer mapped from WGPUBuffer
	 * @param indirectOffset mapped from uint64_t
	 */
	public fun wgpuRenderBundleEncoderDrawIndexedIndirect(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		indirectBuffer: WGPUBuffer?,
		indirectOffset: Long,
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param indirectBuffer mapped from WGPUBuffer
	 * @param indirectOffset mapped from uint64_t
	 */
	public fun wgpuRenderBundleEncoderDrawIndirect(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		indirectBuffer: WGPUBuffer?,
		indirectOffset: Long,
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param descriptor mapped from (typedef Optional[const WGPURenderBundleDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]](WGPURenderBundleDescriptor)))*
	 */
	public fun wgpuRenderBundleEncoderFinish(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		descriptor: WGPURenderBundleDescriptor?
	): WGPURenderBundle?

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param markerLabel mapped from (Char(layout = b1))*
	 */
	public fun wgpuRenderBundleEncoderInsertDebugMarker(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		markerLabel: String?
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 */
	public fun wgpuRenderBundleEncoderPopDebugGroup(renderBundleEncoder: WGPURenderBundleEncoder?)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param groupLabel mapped from (Char(layout = b1))*
	 */
	public fun wgpuRenderBundleEncoderPushDebugGroup(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		groupLabel: String?
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param groupIndex mapped from uint32_t
	 * @param group mapped from WGPUBindGroup
	 * @param dynamicOffsetCount mapped from size_t
	 * @param dynamicOffsets mapped from (typedef Optional[const uint32_t] = UNSIGNED = Int(layout =
	 * i4))*
	 */
	public fun wgpuRenderBundleEncoderSetBindGroup(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		groupIndex: Int,
		group: WGPUBindGroup?,
		dynamicOffsetCount: NativeLong,
		dynamicOffsets: Pointer?,
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param buffer mapped from WGPUBuffer
	 * @param format mapped from WGPUIndexFormat
	 * @param offset mapped from uint64_t
	 * @param size mapped from uint64_t
	 */
	public fun wgpuRenderBundleEncoderSetIndexBuffer(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		buffer: WGPUBuffer?,
		format: Int,
		offset: Long,
		size: Long,
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuRenderBundleEncoderSetLabel(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		label: String?
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param pipeline mapped from WGPURenderPipeline
	 */
	public fun wgpuRenderBundleEncoderSetPipeline(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		pipeline: WGPURenderPipeline?
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 * @param slot mapped from uint32_t
	 * @param buffer mapped from WGPUBuffer
	 * @param offset mapped from uint64_t
	 * @param size mapped from uint64_t
	 */
	public fun wgpuRenderBundleEncoderSetVertexBuffer(
		renderBundleEncoder: WGPURenderBundleEncoder?,
		slot: Int,
		buffer: WGPUBuffer?,
		offset: Long,
		size: Long,
	)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 */
	public fun wgpuRenderBundleEncoderReference(renderBundleEncoder: WGPURenderBundleEncoder?)

	/**
	 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
	 */
	public fun wgpuRenderBundleEncoderRelease(renderBundleEncoder: WGPURenderBundleEncoder?)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param queryIndex mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderBeginOcclusionQuery(
		renderPassEncoder: WGPURenderPassEncoder?,
		queryIndex: Int
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param vertexCount mapped from uint32_t
	 * @param instanceCount mapped from uint32_t
	 * @param firstVertex mapped from uint32_t
	 * @param firstInstance mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderDraw(
		renderPassEncoder: WGPURenderPassEncoder?,
		vertexCount: Int,
		instanceCount: Int?,
		firstVertex: Int?,
		firstInstance: Int?,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param indexCount mapped from uint32_t
	 * @param instanceCount mapped from uint32_t
	 * @param firstIndex mapped from uint32_t
	 * @param baseVertex mapped from int32_t
	 * @param firstInstance mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderDrawIndexed(
		renderPassEncoder: WGPURenderPassEncoder?,
		indexCount: Int,
		instanceCount: Int,
		firstIndex: Int,
		baseVertex: Int,
		firstInstance: Int,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param indirectBuffer mapped from WGPUBuffer
	 * @param indirectOffset mapped from uint64_t
	 */
	public fun wgpuRenderPassEncoderDrawIndexedIndirect(
		renderPassEncoder: WGPURenderPassEncoder?,
		indirectBuffer: WGPUBuffer?,
		indirectOffset: Long,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param indirectBuffer mapped from WGPUBuffer
	 * @param indirectOffset mapped from uint64_t
	 */
	public fun wgpuRenderPassEncoderDrawIndirect(
		renderPassEncoder: WGPURenderPassEncoder?,
		indirectBuffer: WGPUBuffer?,
		indirectOffset: Long,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 */
	public fun wgpuRenderPassEncoderEnd(renderPassEncoder: WGPURenderPassEncoder?)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 */
	public fun wgpuRenderPassEncoderEndOcclusionQuery(renderPassEncoder: WGPURenderPassEncoder?)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param bundleCount mapped from size_t
	 * @param bundles mapped from (typedef Optional[const WGPURenderBundle] = (Declared())*)*
	 */
	public fun wgpuRenderPassEncoderExecuteBundles(
		renderPassEncoder: WGPURenderPassEncoder?,
		bundleCount: NativeLong,
		bundles: WGPURenderBundle?,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param markerLabel mapped from (Char(layout = b1))*
	 */
	public fun wgpuRenderPassEncoderInsertDebugMarker(
		renderPassEncoder: WGPURenderPassEncoder?,
		markerLabel: String?
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 */
	public fun wgpuRenderPassEncoderPopDebugGroup(renderPassEncoder: WGPURenderPassEncoder?)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param groupLabel mapped from (Char(layout = b1))*
	 */
	public fun wgpuRenderPassEncoderPushDebugGroup(
		renderPassEncoder: WGPURenderPassEncoder?,
		groupLabel: String?
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param groupIndex mapped from uint32_t
	 * @param group mapped from WGPUBindGroup
	 * @param dynamicOffsetCount mapped from size_t
	 * @param dynamicOffsets mapped from (typedef Optional[const uint32_t] = UNSIGNED = Int(layout =
	 * i4))*
	 */
	public fun wgpuRenderPassEncoderSetBindGroup(
		renderPassEncoder: WGPURenderPassEncoder?,
		groupIndex: Int,
		group: WGPUBindGroup?,
		dynamicOffsetCount: NativeLong,
		dynamicOffsets: Pointer?,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param color mapped from (typedef Optional[const WGPUColor] =
	 * Declared([d8(r)d8(g)d8(b)d8(a)](WGPUColor)))*
	 */
	public fun wgpuRenderPassEncoderSetBlendConstant(
		renderPassEncoder: WGPURenderPassEncoder?,
		color: WGPUColor?
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param buffer mapped from WGPUBuffer
	 * @param format mapped from WGPUIndexFormat
	 * @param offset mapped from uint64_t
	 * @param size mapped from uint64_t
	 */
	public fun wgpuRenderPassEncoderSetIndexBuffer(
		renderPassEncoder: WGPURenderPassEncoder?,
		buffer: WGPUBuffer?,
		format: Int,
		offset: Long,
		size: Long,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuRenderPassEncoderSetLabel(
		renderPassEncoder: WGPURenderPassEncoder?,
		label: String?
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param pipeline mapped from WGPURenderPipeline
	 */
	public fun wgpuRenderPassEncoderSetPipeline(
		renderPassEncoder: WGPURenderPassEncoder?,
		pipeline: WGPURenderPipeline?
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param x mapped from uint32_t
	 * @param y mapped from uint32_t
	 * @param width mapped from uint32_t
	 * @param height mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderSetScissorRect(
		renderPassEncoder: WGPURenderPassEncoder?,
		x: Int,
		y: Int,
		width: Int,
		height: Int,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param reference mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderSetStencilReference(
		renderPassEncoder: WGPURenderPassEncoder?,
		reference: Int
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param slot mapped from uint32_t
	 * @param buffer mapped from WGPUBuffer
	 * @param offset mapped from uint64_t
	 * @param size mapped from uint64_t
	 */
	public fun wgpuRenderPassEncoderSetVertexBuffer(
		renderPassEncoder: WGPURenderPassEncoder?,
		slot: Int,
		buffer: WGPUBuffer?,
		offset: Long,
		size: Long?,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param x mapped from float
	 * @param y mapped from float
	 * @param width mapped from float
	 * @param height mapped from float
	 * @param minDepth mapped from float
	 * @param maxDepth mapped from float
	 */
	public fun wgpuRenderPassEncoderSetViewport(
		renderPassEncoder: WGPURenderPassEncoder?,
		x: Float,
		y: Float,
		width: Float,
		height: Float,
		minDepth: Float,
		maxDepth: Float,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 */
	public fun wgpuRenderPassEncoderReference(renderPassEncoder: WGPURenderPassEncoder?)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 */
	public fun wgpuRenderPassEncoderRelease(renderPassEncoder: WGPURenderPassEncoder?)

	/**
	 * @param renderPipeline mapped from WGPURenderPipeline
	 * @param groupIndex mapped from uint32_t
	 */
	public fun wgpuRenderPipelineGetBindGroupLayout(
		renderPipeline: WGPURenderPipeline?,
		groupIndex: Int
	): WGPUBindGroupLayout?

	/**
	 * @param renderPipeline mapped from WGPURenderPipeline
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuRenderPipelineSetLabel(renderPipeline: WGPURenderPipeline?, label: String?)

	/**
	 * @param renderPipeline mapped from WGPURenderPipeline
	 */
	public fun wgpuRenderPipelineReference(renderPipeline: WGPURenderPipeline?)

	/**
	 * @param renderPipeline mapped from WGPURenderPipeline
	 */
	public fun wgpuRenderPipelineRelease(renderPipeline: WGPURenderPipeline?)

	/**
	 * @param sampler mapped from WGPUSampler
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuSamplerSetLabel(sampler: WGPUSampler?, label: String?)

	/**
	 * @param sampler mapped from WGPUSampler
	 */
	public fun wgpuSamplerReference(sampler: WGPUSampler?)

	/**
	 * @param sampler mapped from WGPUSampler
	 */
	public fun wgpuSamplerRelease(sampler: WGPUSampler?)

	/**
	 * @param shaderModule mapped from WGPUShaderModule
	 * @param callback mapped from WGPUCompilationInfoCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuShaderModuleGetCompilationInfo(
		shaderModule: WGPUShaderModule?,
		callback: WGPUCompilationInfoCallback?,
		userdata: Pointer?,
	)

	/**
	 * @param shaderModule mapped from WGPUShaderModule
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuShaderModuleSetLabel(shaderModule: WGPUShaderModule?, label: String?)

	/**
	 * @param shaderModule mapped from WGPUShaderModule
	 */
	public fun wgpuShaderModuleReference(shaderModule: WGPUShaderModule?)

	/**
	 * @param shaderModule mapped from WGPUShaderModule
	 */
	public fun wgpuShaderModuleRelease(shaderModule: WGPUShaderModule?)

	/**
	 * @param surface mapped from WGPUSurface
	 * @param config mapped from (typedef Optional[const WGPUSurfaceConfiguration] =
	 * Declared([a8(nextInChain):[*:b1]a8(device):[*:b1]i4(format)i4(usage)j8(viewFormatCount)a8(viewFormats):[*:b1]i4(alphaMode)i4(width)i4(height)i4(presentMode)](WGPUSurfaceConfiguration)))*
	 */
	public fun wgpuSurfaceConfigure(surface: WGPUSurface?, config: WGPUSurfaceConfiguration?)

	/**
	 * @param surface mapped from WGPUSurface
	 * @param adapter mapped from WGPUAdapter
	 * @param capabilities mapped from (typedef Optional[WGPUSurfaceCapabilities] =
	 * Declared([a8(nextInChain):[*:b1]j8(formatCount)a8(formats):[*:b1]j8(presentModeCount)a8(presentModes):[*:b1]j8(alphaModeCount)a8(alphaModes):[*:b1]](WGPUSurfaceCapabilities)))*
	 */
	public fun wgpuSurfaceGetCapabilities(
		surface: WGPUSurface?,
		adapter: WGPUAdapter?,
		capabilities: WGPUSurfaceCapabilities?,
	)

	/**
	 * @param surface mapped from WGPUSurface
	 * @param surfaceTexture mapped from (typedef Optional[WGPUSurfaceTexture] =
	 * Declared([a8(texture):[*:b1]i4(suboptimal)i4(status)](WGPUSurfaceTexture)))*
	 */
	public fun wgpuSurfaceGetCurrentTexture(
		surface: WGPUSurface?,
		surfaceTexture: WGPUSurfaceTexture?
	)

	/**
	 * @param surface mapped from WGPUSurface
	 * @param adapter mapped from WGPUAdapter
	 */
	public fun wgpuSurfaceGetPreferredFormat(surface: WGPUSurface?, adapter: WGPUAdapter?): Int

	/**
	 * @param surface mapped from WGPUSurface
	 */
	public fun wgpuSurfacePresent(surface: WGPUSurface?)

	/**
	 * @param surface mapped from WGPUSurface
	 */
	public fun wgpuSurfaceUnconfigure(surface: WGPUSurface?)

	/**
	 * @param surface mapped from WGPUSurface
	 */
	public fun wgpuSurfaceReference(surface: WGPUSurface?)

	/**
	 * @param surface mapped from WGPUSurface
	 */
	public fun wgpuSurfaceRelease(surface: WGPUSurface?)

	/**
	 * @param capabilities mapped from WGPUSurfaceCapabilities
	 */
	public fun wgpuSurfaceCapabilitiesFreeMembers(capabilities: WGPUSurfaceCapabilities)

	/**
	 * @param texture mapped from WGPUTexture
	 * @param descriptor mapped from (typedef Optional[const WGPUTextureViewDescriptor] =
	 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(format)i4(dimension)i4(baseMipLevel)i4(mipLevelCount)i4(baseArrayLayer)i4(arrayLayerCount)i4(aspect)x4](WGPUTextureViewDescriptor)))*
	 */
	public fun wgpuTextureCreateView(texture: WGPUTexture?, descriptor: WGPUTextureViewDescriptor?):
		WGPUTextureView?

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureDestroy(texture: WGPUTexture?)

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureGetDepthOrArrayLayers(texture: WGPUTexture?): Int

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureGetDimension(texture: WGPUTexture?): Int

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureGetFormat(texture: WGPUTexture?): Int

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureGetHeight(texture: WGPUTexture?): Int

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureGetMipLevelCount(texture: WGPUTexture?): Int

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureGetSampleCount(texture: WGPUTexture?): Int

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureGetUsage(texture: WGPUTexture?): WGPUTextureUsageFlags

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureGetWidth(texture: WGPUTexture?): Int

	/**
	 * @param texture mapped from WGPUTexture
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuTextureSetLabel(texture: WGPUTexture?, label: String?)

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureReference(texture: WGPUTexture?)

	/**
	 * @param texture mapped from WGPUTexture
	 */
	public fun wgpuTextureRelease(texture: WGPUTexture?)

	/**
	 * @param textureView mapped from WGPUTextureView
	 * @param label mapped from (Char(layout = b1))*
	 */
	public fun wgpuTextureViewSetLabel(textureView: WGPUTextureView?, label: String?)

	/**
	 * @param textureView mapped from WGPUTextureView
	 */
	public fun wgpuTextureViewReference(textureView: WGPUTextureView?)

	/**
	 * @param textureView mapped from WGPUTextureView
	 */
	public fun wgpuTextureViewRelease(textureView: WGPUTextureView?)

	/**
	 * @param instance mapped from WGPUInstance
	 * @param report mapped from (typedef Optional[WGPUGlobalReport] =
	 * Declared([[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](surfaces)i4(backendType)x4[[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](adapters)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](devices)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](queues)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](pipelineLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](shaderModules)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroupLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroups)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](commandBuffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderBundles)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderPipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](computePipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](querySets)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](buffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textures)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textureViews)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](samplers)](vulkan)[[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](adapters)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](devices)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](queues)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](pipelineLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](shaderModules)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroupLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroups)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](commandBuffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderBundles)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderPipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](computePipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](querySets)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](buffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textures)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textureViews)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](samplers)](metal)[[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](adapters)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](devices)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](queues)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](pipelineLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](shaderModules)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroupLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroups)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](commandBuffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderBundles)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderPipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](computePipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](querySets)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](buffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textures)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textureViews)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](samplers)](dx12)[[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](adapters)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](devices)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](queues)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](pipelineLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](shaderModules)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroupLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroups)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](commandBuffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderBundles)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderPipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](computePipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](querySets)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](buffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textures)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textureViews)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](samplers)](gl)](WGPUGlobalReport)))*
	 */
	public fun wgpuGenerateReport(instance: WGPUInstance?, report: WGPUGlobalReport?)

	/**
	 * @param instance mapped from WGPUInstance
	 * @param options mapped from (typedef Optional[const WGPUInstanceEnumerateAdapterOptions] =
	 * Declared([a8(nextInChain):[*:b1]i4(backends)x4](WGPUInstanceEnumerateAdapterOptions)))*
	 * @param adapters mapped from (typedef Optional[WGPUAdapter] = (Declared())*)*
	 */
	public fun wgpuInstanceEnumerateAdapters(
		instance: WGPUInstance?,
		options: WGPUInstanceEnumerateAdapterOptions?,
		adapters: WGPUAdapter?,
	): NativeLong

	/**
	 * @param queue mapped from WGPUQueue
	 * @param commandCount mapped from size_t
	 * @param commands mapped from (typedef Optional[const WGPUCommandBuffer] = (Declared())*)*
	 */
	public fun wgpuQueueSubmitForIndex(
		queue: WGPUQueue?,
		commandCount: NativeLong,
		commands: WGPUCommandBuffer?,
	): WGPUSubmissionIndex

	/**
	 * @param device mapped from WGPUDevice
	 * @param wait mapped from WGPUBool
	 * @param wrappedSubmissionIndex mapped from (typedef Optional[const WGPUWrappedSubmissionIndex] =
	 * Declared([a8(queue):[*:b1]j8(submissionIndex)](WGPUWrappedSubmissionIndex)))*
	 */
	public fun wgpuDevicePoll(
		device: WGPUDevice?,
		wait: WGPUBool,
		wrappedSubmissionIndex: WGPUWrappedSubmissionIndex?,
	): WGPUBool

	/**
	 * @param callback mapped from WGPULogCallback
	 * @param userdata mapped from (Void)*
	 */
	public fun wgpuSetLogCallback(callback: WGPULogCallback?, userdata: Pointer?)

	/**
	 * @param level mapped from WGPULogLevel
	 */
	public fun wgpuSetLogLevel(level: Int)

	public fun wgpuGetVersion(): Int

	/**
	 * @param encoder mapped from WGPURenderPassEncoder
	 * @param stages mapped from WGPUShaderStageFlags
	 * @param offset mapped from uint32_t
	 * @param sizeBytes mapped from uint32_t
	 * @param data mapped from (Void)*
	 */
	public fun wgpuRenderPassEncoderSetPushConstants(
		encoder: WGPURenderPassEncoder?,
		stages: WGPUShaderStageFlags,
		offset: Int,
		sizeBytes: Int,
		`data`: Pointer?,
	)

	/**
	 * @param encoder mapped from WGPURenderPassEncoder
	 * @param buffer mapped from WGPUBuffer
	 * @param offset mapped from uint64_t
	 * @param count mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderMultiDrawIndirect(
		encoder: WGPURenderPassEncoder?,
		buffer: WGPUBuffer?,
		offset: Long,
		count: Int,
	)

	/**
	 * @param encoder mapped from WGPURenderPassEncoder
	 * @param buffer mapped from WGPUBuffer
	 * @param offset mapped from uint64_t
	 * @param count mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderMultiDrawIndexedIndirect(
		encoder: WGPURenderPassEncoder?,
		buffer: WGPUBuffer?,
		offset: Long,
		count: Int,
	)

	/**
	 * @param encoder mapped from WGPURenderPassEncoder
	 * @param buffer mapped from WGPUBuffer
	 * @param offset mapped from uint64_t
	 * @param count_buffer mapped from WGPUBuffer
	 * @param count_buffer_offset mapped from uint64_t
	 * @param max_count mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderMultiDrawIndirectCount(
		encoder: WGPURenderPassEncoder?,
		buffer: WGPUBuffer?,
		offset: Long,
		count_buffer: WGPUBuffer?,
		count_buffer_offset: Long,
		max_count: Int,
	)

	/**
	 * @param encoder mapped from WGPURenderPassEncoder
	 * @param buffer mapped from WGPUBuffer
	 * @param offset mapped from uint64_t
	 * @param count_buffer mapped from WGPUBuffer
	 * @param count_buffer_offset mapped from uint64_t
	 * @param max_count mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderMultiDrawIndexedIndirectCount(
		encoder: WGPURenderPassEncoder?,
		buffer: WGPUBuffer?,
		offset: Long,
		count_buffer: WGPUBuffer?,
		count_buffer_offset: Long,
		max_count: Int,
	)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 * @param querySet mapped from WGPUQuerySet
	 * @param queryIndex mapped from uint32_t
	 */
	public fun wgpuComputePassEncoderBeginPipelineStatisticsQuery(
		computePassEncoder: WGPUComputePassEncoder?,
		querySet: WGPUQuerySet?,
		queryIndex: Int,
	)

	/**
	 * @param computePassEncoder mapped from WGPUComputePassEncoder
	 */
	public
	fun wgpuComputePassEncoderEndPipelineStatisticsQuery(computePassEncoder: WGPUComputePassEncoder?)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 * @param querySet mapped from WGPUQuerySet
	 * @param queryIndex mapped from uint32_t
	 */
	public fun wgpuRenderPassEncoderBeginPipelineStatisticsQuery(
		renderPassEncoder: WGPURenderPassEncoder?,
		querySet: WGPUQuerySet?,
		queryIndex: Int,
	)

	/**
	 * @param renderPassEncoder mapped from WGPURenderPassEncoder
	 */
	public
	fun wgpuRenderPassEncoderEndPipelineStatisticsQuery(renderPassEncoder: WGPURenderPassEncoder?)
}

/**
 * @param descriptor mapped from (typedef Optional[const WGPUInstanceDescriptor] =
 * Declared([a8(nextInChain):[*:b1]](WGPUInstanceDescriptor)))*
 */
public fun wgpuCreateInstance(descriptor: WGPUInstanceDescriptor?): WGPUInstance? =
	libWGPULibrary.wgpuCreateInstance(descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param procName mapped from (Char(layout = b1))*
 */
public fun wgpuGetProcAddress(device: WGPUDevice?, procName: String?): WGPUProc? =
	libWGPULibrary.wgpuGetProcAddress(device, procName)

/**
 * @param adapter mapped from WGPUAdapter
 * @param features mapped from (typedef Optional[WGPUFeatureName] = Declared(i4))*
 */
public fun wgpuAdapterEnumerateFeatures(adapter: WGPUAdapter?, features: Pointer?): NativeLong =
	libWGPULibrary.wgpuAdapterEnumerateFeatures(adapter, features)

/**
 * @param adapter mapped from WGPUAdapter
 * @param limits mapped from (typedef Optional[WGPUSupportedLimits] =
 * Declared([a8(nextInChain):[*:b1][i4(maxTextureDimension1D)i4(maxTextureDimension2D)i4(maxTextureDimension3D)i4(maxTextureArrayLayers)i4(maxBindGroups)i4(maxBindGroupsPlusVertexBuffers)i4(maxBindingsPerBindGroup)i4(maxDynamicUniformBuffersPerPipelineLayout)i4(maxDynamicStorageBuffersPerPipelineLayout)i4(maxSampledTexturesPerShaderStage)i4(maxSamplersPerShaderStage)i4(maxStorageBuffersPerShaderStage)i4(maxStorageTexturesPerShaderStage)i4(maxUniformBuffersPerShaderStage)j8(maxUniformBufferBindingSize)j8(maxStorageBufferBindingSize)i4(minUniformBufferOffsetAlignment)i4(minStorageBufferOffsetAlignment)i4(maxVertexBuffers)x4j8(maxBufferSize)i4(maxVertexAttributes)i4(maxVertexBufferArrayStride)i4(maxInterStageShaderComponents)i4(maxInterStageShaderVariables)i4(maxColorAttachments)i4(maxColorAttachmentBytesPerSample)i4(maxComputeWorkgroupStorageSize)i4(maxComputeInvocationsPerWorkgroup)i4(maxComputeWorkgroupSizeX)i4(maxComputeWorkgroupSizeY)i4(maxComputeWorkgroupSizeZ)i4(maxComputeWorkgroupsPerDimension)](limits)](WGPUSupportedLimits)))*
 */
public fun wgpuAdapterGetLimits(adapter: WGPUAdapter?, limits: WGPUSupportedLimits?): WGPUBool =
	libWGPULibrary.wgpuAdapterGetLimits(adapter, limits)

/**
 * @param adapter mapped from WGPUAdapter
 * @param properties mapped from (typedef Optional[WGPUAdapterProperties] =
 * Declared([a8(nextInChain):[*:b1]i4(vendorID)x4a8(vendorName):[*:b1]a8(architecture):[*:b1]i4(deviceID)x4a8(name):[*:b1]a8(driverDescription):[*:b1]i4(adapterType)i4(backendType)](WGPUAdapterProperties)))*
 */
public fun wgpuAdapterGetProperties(adapter: WGPUAdapter?, properties: WGPUAdapterProperties?): Unit =
	libWGPULibrary.wgpuAdapterGetProperties(adapter, properties)

/**
 * @param adapter mapped from WGPUAdapter
 * @param feature mapped from WGPUFeatureName
 */
public fun wgpuAdapterHasFeature(adapter: WGPUAdapter?, feature: Int): WGPUBool =
	libWGPULibrary.wgpuAdapterHasFeature(adapter, feature)

/**
 * @param adapter mapped from WGPUAdapter
 * @param descriptor mapped from (typedef Optional[const WGPUDeviceDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(requiredFeatureCount)a8(requiredFeatures):[*:b1]a8(requiredLimits):[*:b1][a8(nextInChain):[*:b1]a8(label):[*:b1]](defaultQueue)a8(deviceLostCallback):[*:b1]a8(deviceLostUserdata):[*:b1]](WGPUDeviceDescriptor)))*
 * @param callback mapped from WGPURequestDeviceCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuAdapterRequestDevice(
	adapter: WGPUAdapter?,
	descriptor: WGPUDeviceDescriptor?,
	callback: WGPURequestDeviceCallback?,
	userdata: Pointer?,
): Unit = libWGPULibrary.wgpuAdapterRequestDevice(adapter, descriptor, callback, userdata)

/**
 * @param adapter mapped from WGPUAdapter
 */
public fun wgpuAdapterReference(adapter: WGPUAdapter?): Unit =
	libWGPULibrary.wgpuAdapterReference(adapter)

/**
 * @param adapter mapped from WGPUAdapter
 */
public fun wgpuAdapterRelease(adapter: WGPUAdapter?): Unit =
	libWGPULibrary.wgpuAdapterRelease(adapter)

/**
 * @param bindGroup mapped from WGPUBindGroup
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuBindGroupSetLabel(bindGroup: WGPUBindGroup?, label: String?): Unit =
	libWGPULibrary.wgpuBindGroupSetLabel(bindGroup, label)

/**
 * @param bindGroup mapped from WGPUBindGroup
 */
public fun wgpuBindGroupReference(bindGroup: WGPUBindGroup?): Unit =
	libWGPULibrary.wgpuBindGroupReference(bindGroup)

/**
 * @param bindGroup mapped from WGPUBindGroup
 */
public fun wgpuBindGroupRelease(bindGroup: WGPUBindGroup?): Unit =
	libWGPULibrary.wgpuBindGroupRelease(bindGroup)

/**
 * @param bindGroupLayout mapped from WGPUBindGroupLayout
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuBindGroupLayoutSetLabel(bindGroupLayout: WGPUBindGroupLayout?, label: String?): Unit =
	libWGPULibrary.wgpuBindGroupLayoutSetLabel(bindGroupLayout, label)

/**
 * @param bindGroupLayout mapped from WGPUBindGroupLayout
 */
public fun wgpuBindGroupLayoutReference(bindGroupLayout: WGPUBindGroupLayout?): Unit =
	libWGPULibrary.wgpuBindGroupLayoutReference(bindGroupLayout)

/**
 * @param bindGroupLayout mapped from WGPUBindGroupLayout
 */
public fun wgpuBindGroupLayoutRelease(bindGroupLayout: WGPUBindGroupLayout?): Unit =
	libWGPULibrary.wgpuBindGroupLayoutRelease(bindGroupLayout)

/**
 * @param buffer mapped from WGPUBuffer
 */
public fun wgpuBufferDestroy(buffer: WGPUBuffer?): Unit = libWGPULibrary.wgpuBufferDestroy(buffer)

/**
 * @param buffer mapped from WGPUBuffer
 * @param offset mapped from size_t
 * @param size mapped from size_t
 */
public fun wgpuBufferGetConstMappedRange(
	buffer: WGPUBuffer?,
	offset: NativeLong,
	size: NativeLong,
): Pointer? = libWGPULibrary.wgpuBufferGetConstMappedRange(buffer, offset, size)

/**
 * @param buffer mapped from WGPUBuffer
 */
public fun wgpuBufferGetMapState(buffer: WGPUBuffer?): Int =
	libWGPULibrary.wgpuBufferGetMapState(buffer)

/**
 * @param buffer mapped from WGPUBuffer
 * @param offset mapped from size_t
 * @param size mapped from size_t
 */
public fun wgpuBufferGetMappedRange(
	buffer: WGPUBuffer?,
	offset: NativeLong?,
	size: NativeLong?,
): Pointer? = libWGPULibrary.wgpuBufferGetMappedRange(buffer, offset, size)

/**
 * @param buffer mapped from WGPUBuffer
 */
public fun wgpuBufferGetSize(buffer: WGPUBuffer?): Long = libWGPULibrary.wgpuBufferGetSize(buffer)

/**
 * @param buffer mapped from WGPUBuffer
 */
public fun wgpuBufferGetUsage(buffer: WGPUBuffer?): WGPUBufferUsageFlags =
	libWGPULibrary.wgpuBufferGetUsage(buffer)

/**
 * @param buffer mapped from WGPUBuffer
 * @param mode mapped from WGPUMapModeFlags
 * @param offset mapped from size_t
 * @param size mapped from size_t
 * @param callback mapped from WGPUBufferMapCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuBufferMapAsync(
	buffer: WGPUBuffer?,
	mode: WGPUMapModeFlags,
	offset: NativeLong,
	size: NativeLong,
	callback: WGPUBufferMapCallback?,
	userdata: Pointer?,
): Unit = libWGPULibrary.wgpuBufferMapAsync(buffer, mode, offset, size, callback, userdata)

/**
 * @param buffer mapped from WGPUBuffer
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuBufferSetLabel(buffer: WGPUBuffer?, label: String?): Unit =
	libWGPULibrary.wgpuBufferSetLabel(buffer, label)

/**
 * @param buffer mapped from WGPUBuffer
 */
public fun wgpuBufferUnmap(buffer: WGPUBuffer?): Unit = libWGPULibrary.wgpuBufferUnmap(buffer)

/**
 * @param buffer mapped from WGPUBuffer
 */
public fun wgpuBufferReference(buffer: WGPUBuffer?): Unit =
	libWGPULibrary.wgpuBufferReference(buffer)

/**
 * @param buffer mapped from WGPUBuffer
 */
public fun wgpuBufferRelease(buffer: WGPUBuffer?): Unit = libWGPULibrary.wgpuBufferRelease(buffer)

/**
 * @param commandBuffer mapped from WGPUCommandBuffer
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuCommandBufferSetLabel(commandBuffer: WGPUCommandBuffer?, label: String?): Unit =
	libWGPULibrary.wgpuCommandBufferSetLabel(commandBuffer, label)

/**
 * @param commandBuffer mapped from WGPUCommandBuffer
 */
public fun wgpuCommandBufferReference(commandBuffer: WGPUCommandBuffer?): Unit =
	libWGPULibrary.wgpuCommandBufferReference(commandBuffer)

/**
 * @param commandBuffer mapped from WGPUCommandBuffer
 */
public fun wgpuCommandBufferRelease(commandBuffer: WGPUCommandBuffer?): Unit =
	libWGPULibrary.wgpuCommandBufferRelease(commandBuffer)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param descriptor mapped from (typedef Optional[const WGPUComputePassDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(timestampWrites):[*:b1]](WGPUComputePassDescriptor)))*
 */
public fun wgpuCommandEncoderBeginComputePass(
	commandEncoder: WGPUCommandEncoder?,
	descriptor: WGPUComputePassDescriptor?
): WGPUComputePassEncoder? =
	libWGPULibrary.wgpuCommandEncoderBeginComputePass(commandEncoder, descriptor)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param descriptor mapped from (typedef Optional[const WGPURenderPassDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(colorAttachmentCount)a8(colorAttachments):[*:b1]a8(depthStencilAttachment):[*:b1]a8(occlusionQuerySet):[*:b1]a8(timestampWrites):[*:b1]](WGPURenderPassDescriptor)))*
 */
public fun wgpuCommandEncoderBeginRenderPass(
	commandEncoder: WGPUCommandEncoder?,
	descriptor: WGPURenderPassDescriptor?
): WGPURenderPassEncoder? =
	libWGPULibrary.wgpuCommandEncoderBeginRenderPass(commandEncoder, descriptor)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param buffer mapped from WGPUBuffer
 * @param offset mapped from uint64_t
 * @param size mapped from uint64_t
 */
public fun wgpuCommandEncoderClearBuffer(
	commandEncoder: WGPUCommandEncoder?,
	buffer: WGPUBuffer?,
	offset: Long,
	size: Long,
): Unit = libWGPULibrary.wgpuCommandEncoderClearBuffer(commandEncoder, buffer, offset, size)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param source mapped from WGPUBuffer
 * @param sourceOffset mapped from uint64_t
 * @param destination mapped from WGPUBuffer
 * @param destinationOffset mapped from uint64_t
 * @param size mapped from uint64_t
 */
public fun wgpuCommandEncoderCopyBufferToBuffer(
	commandEncoder: WGPUCommandEncoder?,
	source: WGPUBuffer?,
	sourceOffset: Long,
	destination: WGPUBuffer?,
	destinationOffset: Long,
	size: Long,
): Unit = libWGPULibrary.wgpuCommandEncoderCopyBufferToBuffer(
	commandEncoder, source, sourceOffset,
	destination, destinationOffset, size
)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param source mapped from (typedef Optional[const WGPUImageCopyBuffer] =
 * Declared([a8(nextInChain):[*:b1][a8(nextInChain):[*:b1]j8(offset)i4(bytesPerRow)i4(rowsPerImage)](layout)a8(buffer):[*:b1]](WGPUImageCopyBuffer)))*
 * @param destination mapped from (typedef Optional[const WGPUImageCopyTexture] =
 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
 * @param copySize mapped from (typedef Optional[const WGPUExtent3D] =
 * Declared([i4(width)i4(height)i4(depthOrArrayLayers)](WGPUExtent3D)))*
 */
public fun wgpuCommandEncoderCopyBufferToTexture(
	commandEncoder: WGPUCommandEncoder?,
	source: WGPUImageCopyBuffer?,
	destination: WGPUImageCopyTexture?,
	copySize: WGPUExtent3D?,
): Unit = libWGPULibrary.wgpuCommandEncoderCopyBufferToTexture(
	commandEncoder, source, destination,
	copySize
)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param source mapped from (typedef Optional[const WGPUImageCopyTexture] =
 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
 * @param destination mapped from (typedef Optional[const WGPUImageCopyBuffer] =
 * Declared([a8(nextInChain):[*:b1][a8(nextInChain):[*:b1]j8(offset)i4(bytesPerRow)i4(rowsPerImage)](layout)a8(buffer):[*:b1]](WGPUImageCopyBuffer)))*
 * @param copySize mapped from (typedef Optional[const WGPUExtent3D] =
 * Declared([i4(width)i4(height)i4(depthOrArrayLayers)](WGPUExtent3D)))*
 */
public fun wgpuCommandEncoderCopyTextureToBuffer(
	commandEncoder: WGPUCommandEncoder?,
	source: WGPUImageCopyTexture?,
	destination: WGPUImageCopyBuffer?,
	copySize: WGPUExtent3D?,
): Unit = libWGPULibrary.wgpuCommandEncoderCopyTextureToBuffer(
	commandEncoder, source, destination,
	copySize
)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param source mapped from (typedef Optional[const WGPUImageCopyTexture] =
 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
 * @param destination mapped from (typedef Optional[const WGPUImageCopyTexture] =
 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
 * @param copySize mapped from (typedef Optional[const WGPUExtent3D] =
 * Declared([i4(width)i4(height)i4(depthOrArrayLayers)](WGPUExtent3D)))*
 */
public fun wgpuCommandEncoderCopyTextureToTexture(
	commandEncoder: WGPUCommandEncoder?,
	source: WGPUImageCopyTexture?,
	destination: WGPUImageCopyTexture?,
	copySize: WGPUExtent3D?,
): Unit = libWGPULibrary.wgpuCommandEncoderCopyTextureToTexture(
	commandEncoder, source, destination,
	copySize
)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param descriptor mapped from (typedef Optional[const WGPUCommandBufferDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]](WGPUCommandBufferDescriptor)))*
 */
public fun wgpuCommandEncoderFinish(
	commandEncoder: WGPUCommandEncoder?,
	descriptor: WGPUCommandBufferDescriptor?
): WGPUCommandBuffer? =
	libWGPULibrary.wgpuCommandEncoderFinish(commandEncoder, descriptor)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param markerLabel mapped from (Char(layout = b1))*
 */
public fun wgpuCommandEncoderInsertDebugMarker(
	commandEncoder: WGPUCommandEncoder?,
	markerLabel: String?
): Unit = libWGPULibrary.wgpuCommandEncoderInsertDebugMarker(
	commandEncoder,
	markerLabel
)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 */
public fun wgpuCommandEncoderPopDebugGroup(commandEncoder: WGPUCommandEncoder?): Unit =
	libWGPULibrary.wgpuCommandEncoderPopDebugGroup(commandEncoder)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param groupLabel mapped from (Char(layout = b1))*
 */
public fun wgpuCommandEncoderPushDebugGroup(
	commandEncoder: WGPUCommandEncoder?,
	groupLabel: String?
): Unit = libWGPULibrary.wgpuCommandEncoderPushDebugGroup(
	commandEncoder,
	groupLabel
)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param querySet mapped from WGPUQuerySet
 * @param firstQuery mapped from uint32_t
 * @param queryCount mapped from uint32_t
 * @param destination mapped from WGPUBuffer
 * @param destinationOffset mapped from uint64_t
 */
public fun wgpuCommandEncoderResolveQuerySet(
	commandEncoder: WGPUCommandEncoder?,
	querySet: WGPUQuerySet?,
	firstQuery: Int,
	queryCount: Int,
	destination: WGPUBuffer?,
	destinationOffset: Long,
): Unit = libWGPULibrary.wgpuCommandEncoderResolveQuerySet(
	commandEncoder, querySet, firstQuery,
	queryCount, destination, destinationOffset
)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuCommandEncoderSetLabel(commandEncoder: WGPUCommandEncoder?, label: String?): Unit =
	libWGPULibrary.wgpuCommandEncoderSetLabel(commandEncoder, label)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 * @param querySet mapped from WGPUQuerySet
 * @param queryIndex mapped from uint32_t
 */
public fun wgpuCommandEncoderWriteTimestamp(
	commandEncoder: WGPUCommandEncoder?,
	querySet: WGPUQuerySet?,
	queryIndex: Int,
): Unit = libWGPULibrary.wgpuCommandEncoderWriteTimestamp(commandEncoder, querySet, queryIndex)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 */
public fun wgpuCommandEncoderReference(commandEncoder: WGPUCommandEncoder?): Unit =
	libWGPULibrary.wgpuCommandEncoderReference(commandEncoder)

/**
 * @param commandEncoder mapped from WGPUCommandEncoder
 */
public fun wgpuCommandEncoderRelease(commandEncoder: WGPUCommandEncoder?): Unit =
	libWGPULibrary.wgpuCommandEncoderRelease(commandEncoder)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 * @param workgroupCountX mapped from uint32_t
 * @param workgroupCountY mapped from uint32_t
 * @param workgroupCountZ mapped from uint32_t
 */
public fun wgpuComputePassEncoderDispatchWorkgroups(
	computePassEncoder: WGPUComputePassEncoder?,
	workgroupCountX: Int,
	workgroupCountY: Int,
	workgroupCountZ: Int,
): Unit = libWGPULibrary.wgpuComputePassEncoderDispatchWorkgroups(
	computePassEncoder,
	workgroupCountX, workgroupCountY, workgroupCountZ
)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 * @param indirectBuffer mapped from WGPUBuffer
 * @param indirectOffset mapped from uint64_t
 */
public fun wgpuComputePassEncoderDispatchWorkgroupsIndirect(
	computePassEncoder: WGPUComputePassEncoder?,
	indirectBuffer: WGPUBuffer?,
	indirectOffset: Long,
): Unit = libWGPULibrary.wgpuComputePassEncoderDispatchWorkgroupsIndirect(
	computePassEncoder,
	indirectBuffer, indirectOffset
)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 */
public fun wgpuComputePassEncoderEnd(computePassEncoder: WGPUComputePassEncoder?): Unit =
	libWGPULibrary.wgpuComputePassEncoderEnd(computePassEncoder)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 * @param markerLabel mapped from (Char(layout = b1))*
 */
public fun wgpuComputePassEncoderInsertDebugMarker(
	computePassEncoder: WGPUComputePassEncoder?,
	markerLabel: String?
): Unit =
	libWGPULibrary.wgpuComputePassEncoderInsertDebugMarker(computePassEncoder, markerLabel)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 */
public fun wgpuComputePassEncoderPopDebugGroup(computePassEncoder: WGPUComputePassEncoder?): Unit =
	libWGPULibrary.wgpuComputePassEncoderPopDebugGroup(computePassEncoder)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 * @param groupLabel mapped from (Char(layout = b1))*
 */
public fun wgpuComputePassEncoderPushDebugGroup(
	computePassEncoder: WGPUComputePassEncoder?,
	groupLabel: String?
): Unit =
	libWGPULibrary.wgpuComputePassEncoderPushDebugGroup(computePassEncoder, groupLabel)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 * @param groupIndex mapped from uint32_t
 * @param group mapped from WGPUBindGroup
 * @param dynamicOffsetCount mapped from size_t
 * @param dynamicOffsets mapped from (typedef Optional[const uint32_t] = UNSIGNED = Int(layout =
 * i4))*
 */
public fun wgpuComputePassEncoderSetBindGroup(
	computePassEncoder: WGPUComputePassEncoder?,
	groupIndex: Int,
	group: WGPUBindGroup?,
	dynamicOffsetCount: NativeLong,
	dynamicOffsets: Pointer?,
): Unit = libWGPULibrary.wgpuComputePassEncoderSetBindGroup(
	computePassEncoder, groupIndex, group,
	dynamicOffsetCount, dynamicOffsets
)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuComputePassEncoderSetLabel(
	computePassEncoder: WGPUComputePassEncoder?,
	label: String?
): Unit = libWGPULibrary.wgpuComputePassEncoderSetLabel(computePassEncoder, label)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 * @param pipeline mapped from WGPUComputePipeline
 */
public fun wgpuComputePassEncoderSetPipeline(
	computePassEncoder: WGPUComputePassEncoder?,
	pipeline: WGPUComputePipeline?
): Unit =
	libWGPULibrary.wgpuComputePassEncoderSetPipeline(computePassEncoder, pipeline)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 */
public fun wgpuComputePassEncoderReference(computePassEncoder: WGPUComputePassEncoder?): Unit =
	libWGPULibrary.wgpuComputePassEncoderReference(computePassEncoder)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 */
public fun wgpuComputePassEncoderRelease(computePassEncoder: WGPUComputePassEncoder?): Unit =
	libWGPULibrary.wgpuComputePassEncoderRelease(computePassEncoder)

/**
 * @param computePipeline mapped from WGPUComputePipeline
 * @param groupIndex mapped from uint32_t
 */
public fun wgpuComputePipelineGetBindGroupLayout(
	computePipeline: WGPUComputePipeline?,
	groupIndex: Int
): WGPUBindGroupLayout? =
	libWGPULibrary.wgpuComputePipelineGetBindGroupLayout(computePipeline, groupIndex)

/**
 * @param computePipeline mapped from WGPUComputePipeline
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuComputePipelineSetLabel(computePipeline: WGPUComputePipeline?, label: String?): Unit =
	libWGPULibrary.wgpuComputePipelineSetLabel(computePipeline, label)

/**
 * @param computePipeline mapped from WGPUComputePipeline
 */
public fun wgpuComputePipelineReference(computePipeline: WGPUComputePipeline?): Unit =
	libWGPULibrary.wgpuComputePipelineReference(computePipeline)

/**
 * @param computePipeline mapped from WGPUComputePipeline
 */
public fun wgpuComputePipelineRelease(computePipeline: WGPUComputePipeline?): Unit =
	libWGPULibrary.wgpuComputePipelineRelease(computePipeline)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUBindGroupDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1]j8(entryCount)a8(entries):[*:b1]](WGPUBindGroupDescriptor)))*
 */
public fun wgpuDeviceCreateBindGroup(device: WGPUDevice?, descriptor: WGPUBindGroupDescriptor?):
	WGPUBindGroup? = libWGPULibrary.wgpuDeviceCreateBindGroup(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUBindGroupLayoutDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(entryCount)a8(entries):[*:b1]](WGPUBindGroupLayoutDescriptor)))*
 */
public fun wgpuDeviceCreateBindGroupLayout(
	device: WGPUDevice?,
	descriptor: WGPUBindGroupLayoutDescriptor?
): WGPUBindGroupLayout? =
	libWGPULibrary.wgpuDeviceCreateBindGroupLayout(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUBufferDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(usage)x4j8(size)i4(mappedAtCreation)x4](WGPUBufferDescriptor)))*
 */
public fun wgpuDeviceCreateBuffer(device: WGPUDevice?, descriptor: WGPUBufferDescriptor?):
	WGPUBuffer? = libWGPULibrary.wgpuDeviceCreateBuffer(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUCommandEncoderDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]](WGPUCommandEncoderDescriptor)))*
 */
public fun wgpuDeviceCreateCommandEncoder(
	device: WGPUDevice?,
	descriptor: WGPUCommandEncoderDescriptor?
): WGPUCommandEncoder? =
	libWGPULibrary.wgpuDeviceCreateCommandEncoder(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUComputePipelineDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1][a8(nextInChain):[*:b1]a8(module):[*:b1]a8(entryPoint):[*:b1]j8(constantCount)a8(constants):[*:b1]](compute)](WGPUComputePipelineDescriptor)))*
 */
public fun wgpuDeviceCreateComputePipeline(
	device: WGPUDevice?,
	descriptor: WGPUComputePipelineDescriptor?
): WGPUComputePipeline? =
	libWGPULibrary.wgpuDeviceCreateComputePipeline(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUComputePipelineDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1][a8(nextInChain):[*:b1]a8(module):[*:b1]a8(entryPoint):[*:b1]j8(constantCount)a8(constants):[*:b1]](compute)](WGPUComputePipelineDescriptor)))*
 * @param callback mapped from WGPUCreateComputePipelineAsyncCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuDeviceCreateComputePipelineAsync(
	device: WGPUDevice?,
	descriptor: WGPUComputePipelineDescriptor?,
	callback: WGPUCreateComputePipelineAsyncCallback?,
	userdata: Pointer?,
): Unit = libWGPULibrary.wgpuDeviceCreateComputePipelineAsync(
	device, descriptor, callback,
	userdata
)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUPipelineLayoutDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(bindGroupLayoutCount)a8(bindGroupLayouts):[*:b1]](WGPUPipelineLayoutDescriptor)))*
 */
public fun wgpuDeviceCreatePipelineLayout(
	device: WGPUDevice?,
	descriptor: WGPUPipelineLayoutDescriptor?
): WGPUPipelineLayout? =
	libWGPULibrary.wgpuDeviceCreatePipelineLayout(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUQuerySetDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(type)i4(count)](WGPUQuerySetDescriptor)))*
 */
public fun wgpuDeviceCreateQuerySet(device: WGPUDevice?, descriptor: WGPUQuerySetDescriptor?):
	WGPUQuerySet? = libWGPULibrary.wgpuDeviceCreateQuerySet(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPURenderBundleEncoderDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(colorFormatCount)a8(colorFormats):[*:b1]i4(depthStencilFormat)i4(sampleCount)i4(depthReadOnly)i4(stencilReadOnly)](WGPURenderBundleEncoderDescriptor)))*
 */
public fun wgpuDeviceCreateRenderBundleEncoder(
	device: WGPUDevice?,
	descriptor: WGPURenderBundleEncoderDescriptor?
): WGPURenderBundleEncoder? =
	libWGPULibrary.wgpuDeviceCreateRenderBundleEncoder(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPURenderPipelineDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1][a8(nextInChain):[*:b1]a8(module):[*:b1]a8(entryPoint):[*:b1]j8(constantCount)a8(constants):[*:b1]j8(bufferCount)a8(buffers):[*:b1]](vertex)[a8(nextInChain):[*:b1]i4(topology)i4(stripIndexFormat)i4(frontFace)i4(cullMode)](primitive)a8(depthStencil):[*:b1][a8(nextInChain):[*:b1]i4(count)i4(mask)i4(alphaToCoverageEnabled)x4](multisample)a8(fragment):[*:b1]](WGPURenderPipelineDescriptor)))*
 */
public fun wgpuDeviceCreateRenderPipeline(
	device: WGPUDevice?,
	descriptor: WGPURenderPipelineDescriptor?
): WGPURenderPipeline? =
	libWGPULibrary.wgpuDeviceCreateRenderPipeline(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPURenderPipelineDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]a8(layout):[*:b1][a8(nextInChain):[*:b1]a8(module):[*:b1]a8(entryPoint):[*:b1]j8(constantCount)a8(constants):[*:b1]j8(bufferCount)a8(buffers):[*:b1]](vertex)[a8(nextInChain):[*:b1]i4(topology)i4(stripIndexFormat)i4(frontFace)i4(cullMode)](primitive)a8(depthStencil):[*:b1][a8(nextInChain):[*:b1]i4(count)i4(mask)i4(alphaToCoverageEnabled)x4](multisample)a8(fragment):[*:b1]](WGPURenderPipelineDescriptor)))*
 * @param callback mapped from WGPUCreateRenderPipelineAsyncCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuDeviceCreateRenderPipelineAsync(
	device: WGPUDevice?,
	descriptor: WGPURenderPipelineDescriptor?,
	callback: WGPUCreateRenderPipelineAsyncCallback?,
	userdata: Pointer?,
): Unit = libWGPULibrary.wgpuDeviceCreateRenderPipelineAsync(device, descriptor, callback, userdata)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUSamplerDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(addressModeU)i4(addressModeV)i4(addressModeW)i4(magFilter)i4(minFilter)i4(mipmapFilter)f4(lodMinClamp)f4(lodMaxClamp)i4(compare)s2(maxAnisotropy)x2](WGPUSamplerDescriptor)))*
 */
public fun wgpuDeviceCreateSampler(device: WGPUDevice?, descriptor: WGPUSamplerDescriptor?):
	WGPUSampler? = libWGPULibrary.wgpuDeviceCreateSampler(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUShaderModuleDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]j8(hintCount)a8(hints):[*:b1]](WGPUShaderModuleDescriptor)))*
 */
public fun wgpuDeviceCreateShaderModule(
	device: WGPUDevice?,
	descriptor: WGPUShaderModuleDescriptor?
): WGPUShaderModule? =
	libWGPULibrary.wgpuDeviceCreateShaderModule(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 * @param descriptor mapped from (typedef Optional[const WGPUTextureDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(usage)i4(dimension)[i4(width)i4(height)i4(depthOrArrayLayers)](size)i4(format)i4(mipLevelCount)i4(sampleCount)j8(viewFormatCount)a8(viewFormats):[*:b1]](WGPUTextureDescriptor)))*
 */
public fun wgpuDeviceCreateTexture(device: WGPUDevice?, descriptor: WGPUTextureDescriptor?):
	WGPUTexture? = libWGPULibrary.wgpuDeviceCreateTexture(device, descriptor)

/**
 * @param device mapped from WGPUDevice
 */
public fun wgpuDeviceDestroy(device: WGPUDevice?): Unit = libWGPULibrary.wgpuDeviceDestroy(device)

/**
 * @param device mapped from WGPUDevice
 * @param features mapped from (typedef Optional[WGPUFeatureName] = Declared(i4))*
 */
public fun wgpuDeviceEnumerateFeatures(device: WGPUDevice?, features: Pointer?): NativeLong =
	libWGPULibrary.wgpuDeviceEnumerateFeatures(device, features)

/**
 * @param device mapped from WGPUDevice
 * @param limits mapped from (typedef Optional[WGPUSupportedLimits] =
 * Declared([a8(nextInChain):[*:b1][i4(maxTextureDimension1D)i4(maxTextureDimension2D)i4(maxTextureDimension3D)i4(maxTextureArrayLayers)i4(maxBindGroups)i4(maxBindGroupsPlusVertexBuffers)i4(maxBindingsPerBindGroup)i4(maxDynamicUniformBuffersPerPipelineLayout)i4(maxDynamicStorageBuffersPerPipelineLayout)i4(maxSampledTexturesPerShaderStage)i4(maxSamplersPerShaderStage)i4(maxStorageBuffersPerShaderStage)i4(maxStorageTexturesPerShaderStage)i4(maxUniformBuffersPerShaderStage)j8(maxUniformBufferBindingSize)j8(maxStorageBufferBindingSize)i4(minUniformBufferOffsetAlignment)i4(minStorageBufferOffsetAlignment)i4(maxVertexBuffers)x4j8(maxBufferSize)i4(maxVertexAttributes)i4(maxVertexBufferArrayStride)i4(maxInterStageShaderComponents)i4(maxInterStageShaderVariables)i4(maxColorAttachments)i4(maxColorAttachmentBytesPerSample)i4(maxComputeWorkgroupStorageSize)i4(maxComputeInvocationsPerWorkgroup)i4(maxComputeWorkgroupSizeX)i4(maxComputeWorkgroupSizeY)i4(maxComputeWorkgroupSizeZ)i4(maxComputeWorkgroupsPerDimension)](limits)](WGPUSupportedLimits)))*
 */
public fun wgpuDeviceGetLimits(device: WGPUDevice?, limits: WGPUSupportedLimits?): WGPUBool =
	libWGPULibrary.wgpuDeviceGetLimits(device, limits)

/**
 * @param device mapped from WGPUDevice
 */
public fun wgpuDeviceGetQueue(device: WGPUDevice?): WGPUQueue? =
	libWGPULibrary.wgpuDeviceGetQueue(device)

/**
 * @param device mapped from WGPUDevice
 * @param feature mapped from WGPUFeatureName
 */
public fun wgpuDeviceHasFeature(device: WGPUDevice?, feature: Int): WGPUBool =
	libWGPULibrary.wgpuDeviceHasFeature(device, feature)

/**
 * @param device mapped from WGPUDevice
 * @param callback mapped from WGPUErrorCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuDevicePopErrorScope(
	device: WGPUDevice?,
	callback: WGPUErrorCallback?,
	userdata: Pointer?,
): Unit = libWGPULibrary.wgpuDevicePopErrorScope(device, callback, userdata)

/**
 * @param device mapped from WGPUDevice
 * @param filter mapped from WGPUErrorFilter
 */
public fun wgpuDevicePushErrorScope(device: WGPUDevice?, filter: Int): Unit =
	libWGPULibrary.wgpuDevicePushErrorScope(device, filter)

/**
 * @param device mapped from WGPUDevice
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuDeviceSetLabel(device: WGPUDevice?, label: String?): Unit =
	libWGPULibrary.wgpuDeviceSetLabel(device, label)

/**
 * @param device mapped from WGPUDevice
 * @param callback mapped from WGPUErrorCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuDeviceSetUncapturedErrorCallback(
	device: WGPUDevice?,
	callback: WGPUErrorCallback?,
	userdata: Pointer?,
): Unit = libWGPULibrary.wgpuDeviceSetUncapturedErrorCallback(device, callback, userdata)

/**
 * @param device mapped from WGPUDevice
 */
public fun wgpuDeviceReference(device: WGPUDevice?): Unit =
	libWGPULibrary.wgpuDeviceReference(device)

/**
 * @param device mapped from WGPUDevice
 */
public fun wgpuDeviceRelease(device: WGPUDevice?): Unit = libWGPULibrary.wgpuDeviceRelease(device)

/**
 * @param instance mapped from WGPUInstance
 * @param descriptor mapped from (typedef Optional[const WGPUSurfaceDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]](WGPUSurfaceDescriptor)))*
 */
public fun wgpuInstanceCreateSurface(instance: WGPUInstance?, descriptor: WGPUSurfaceDescriptor?):
	WGPUSurface? = libWGPULibrary.wgpuInstanceCreateSurface(instance, descriptor)

public fun wgpuInstanceCreateSurface(instance: WGPUInstance, descriptor: WGPUDarwinSurfaceDescriptor):
	WGPUSurface? = libWGPULibrary.wgpuInstanceCreateSurface(instance, descriptor)

/**
 * @param instance mapped from WGPUInstance
 */
public fun wgpuInstanceProcessEvents(instance: WGPUInstance?): Unit =
	libWGPULibrary.wgpuInstanceProcessEvents(instance)

/**
 * @param instance mapped from WGPUInstance
 * @param options mapped from (typedef Optional[const WGPURequestAdapterOptions] =
 * Declared([a8(nextInChain):[*:b1]a8(compatibleSurface):[*:b1]i4(powerPreference)i4(backendType)i4(forceFallbackAdapter)x4](WGPURequestAdapterOptions)))*
 * @param callback mapped from WGPURequestAdapterCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuInstanceRequestAdapter(
	instance: WGPUInstance?,
	options: WGPURequestAdapterOptions?,
	callback: WGPURequestAdapterCallback?,
	userdata: Pointer?,
): Unit = libWGPULibrary.wgpuInstanceRequestAdapter(instance, options, callback, userdata)

/**
 * @param instance mapped from WGPUInstance
 */
public fun wgpuInstanceReference(instance: WGPUInstance?): Unit =
	libWGPULibrary.wgpuInstanceReference(instance)

/**
 * @param instance mapped from WGPUInstance
 */
public fun wgpuInstanceRelease(instance: WGPUInstance?): Unit =
	libWGPULibrary.wgpuInstanceRelease(instance)

/**
 * @param pipelineLayout mapped from WGPUPipelineLayout
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuPipelineLayoutSetLabel(pipelineLayout: WGPUPipelineLayout?, label: String?): Unit =
	libWGPULibrary.wgpuPipelineLayoutSetLabel(pipelineLayout, label)

/**
 * @param pipelineLayout mapped from WGPUPipelineLayout
 */
public fun wgpuPipelineLayoutReference(pipelineLayout: WGPUPipelineLayout?): Unit =
	libWGPULibrary.wgpuPipelineLayoutReference(pipelineLayout)

/**
 * @param pipelineLayout mapped from WGPUPipelineLayout
 */
public fun wgpuPipelineLayoutRelease(pipelineLayout: WGPUPipelineLayout?): Unit =
	libWGPULibrary.wgpuPipelineLayoutRelease(pipelineLayout)

/**
 * @param querySet mapped from WGPUQuerySet
 */
public fun wgpuQuerySetDestroy(querySet: WGPUQuerySet?): Unit =
	libWGPULibrary.wgpuQuerySetDestroy(querySet)

/**
 * @param querySet mapped from WGPUQuerySet
 */
public fun wgpuQuerySetGetCount(querySet: WGPUQuerySet?): Int =
	libWGPULibrary.wgpuQuerySetGetCount(querySet)

/**
 * @param querySet mapped from WGPUQuerySet
 */
public fun wgpuQuerySetGetType(querySet: WGPUQuerySet?): Int =
	libWGPULibrary.wgpuQuerySetGetType(querySet)

/**
 * @param querySet mapped from WGPUQuerySet
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuQuerySetSetLabel(querySet: WGPUQuerySet?, label: String?): Unit =
	libWGPULibrary.wgpuQuerySetSetLabel(querySet, label)

/**
 * @param querySet mapped from WGPUQuerySet
 */
public fun wgpuQuerySetReference(querySet: WGPUQuerySet?): Unit =
	libWGPULibrary.wgpuQuerySetReference(querySet)

/**
 * @param querySet mapped from WGPUQuerySet
 */
public fun wgpuQuerySetRelease(querySet: WGPUQuerySet?): Unit =
	libWGPULibrary.wgpuQuerySetRelease(querySet)

/**
 * @param queue mapped from WGPUQueue
 * @param callback mapped from WGPUQueueWorkDoneCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuQueueOnSubmittedWorkDone(
	queue: WGPUQueue?,
	callback: WGPUQueueWorkDoneCallback?,
	userdata: Pointer?,
): Unit = libWGPULibrary.wgpuQueueOnSubmittedWorkDone(queue, callback, userdata)

/**
 * @param queue mapped from WGPUQueue
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuQueueSetLabel(queue: WGPUQueue?, label: String?): Unit =
	libWGPULibrary.wgpuQueueSetLabel(queue, label)

/**
 * @param queue mapped from WGPUQueue
 * @param commandCount mapped from size_t
 * @param commands mapped from (typedef Optional[const WGPUCommandBuffer] = (Declared())*)*
 */
public fun wgpuQueueSubmit(
	queue: WGPUQueue?,
	commandCount: NativeLong,
	commands: Array<WGPUCommandBuffer>?,
): Unit = libWGPULibrary.wgpuQueueSubmit(queue, commandCount, commands)

/**
 * @param queue mapped from WGPUQueue
 * @param buffer mapped from WGPUBuffer
 * @param bufferOffset mapped from uint64_t
 * @param data mapped from (Void)*
 * @param size mapped from size_t
 */
public fun wgpuQueueWriteBuffer(
	queue: WGPUQueue?,
	buffer: WGPUBuffer?,
	bufferOffset: Long,
	`data`: Pointer?,
	size: NativeLong,
): Unit = libWGPULibrary.wgpuQueueWriteBuffer(queue, buffer, bufferOffset, data, size)

/**
 * @param queue mapped from WGPUQueue
 * @param destination mapped from (typedef Optional[const WGPUImageCopyTexture] =
 * Declared([a8(nextInChain):[*:b1]a8(texture):[*:b1]i4(mipLevel)[i4(x)i4(y)i4(z)](origin)i4(aspect)x4](WGPUImageCopyTexture)))*
 * @param data mapped from (Void)*
 * @param dataSize mapped from size_t
 * @param dataLayout mapped from (typedef Optional[const WGPUTextureDataLayout] =
 * Declared([a8(nextInChain):[*:b1]j8(offset)i4(bytesPerRow)i4(rowsPerImage)](WGPUTextureDataLayout)))*
 * @param writeSize mapped from (typedef Optional[const WGPUExtent3D] =
 * Declared([i4(width)i4(height)i4(depthOrArrayLayers)](WGPUExtent3D)))*
 */
public fun wgpuQueueWriteTexture(
	queue: WGPUQueue?,
	destination: WGPUImageCopyTexture?,
	`data`: Pointer?,
	dataSize: NativeLong,
	dataLayout: WGPUTextureDataLayout?,
	writeSize: WGPUExtent3D?,
): Unit = libWGPULibrary.wgpuQueueWriteTexture(
	queue, destination, data, dataSize, dataLayout,
	writeSize
)

/**
 * @param queue mapped from WGPUQueue
 */
public fun wgpuQueueReference(queue: WGPUQueue?): Unit = libWGPULibrary.wgpuQueueReference(queue)

/**
 * @param queue mapped from WGPUQueue
 */
public fun wgpuQueueRelease(queue: WGPUQueue?): Unit = libWGPULibrary.wgpuQueueRelease(queue)

/**
 * @param renderBundle mapped from WGPURenderBundle
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuRenderBundleSetLabel(renderBundle: WGPURenderBundle?, label: String?): Unit =
	libWGPULibrary.wgpuRenderBundleSetLabel(renderBundle, label)

/**
 * @param renderBundle mapped from WGPURenderBundle
 */
public fun wgpuRenderBundleReference(renderBundle: WGPURenderBundle?): Unit =
	libWGPULibrary.wgpuRenderBundleReference(renderBundle)

/**
 * @param renderBundle mapped from WGPURenderBundle
 */
public fun wgpuRenderBundleRelease(renderBundle: WGPURenderBundle?): Unit =
	libWGPULibrary.wgpuRenderBundleRelease(renderBundle)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param vertexCount mapped from uint32_t
 * @param instanceCount mapped from uint32_t
 * @param firstVertex mapped from uint32_t
 * @param firstInstance mapped from uint32_t
 */
public fun wgpuRenderBundleEncoderDraw(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	vertexCount: Int,
	instanceCount: Int,
	firstVertex: Int,
	firstInstance: Int,
): Unit = libWGPULibrary.wgpuRenderBundleEncoderDraw(
	renderBundleEncoder, vertexCount,
	instanceCount, firstVertex, firstInstance
)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param indexCount mapped from uint32_t
 * @param instanceCount mapped from uint32_t
 * @param firstIndex mapped from uint32_t
 * @param baseVertex mapped from int32_t
 * @param firstInstance mapped from uint32_t
 */
public fun wgpuRenderBundleEncoderDrawIndexed(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	indexCount: Int,
	instanceCount: Int,
	firstIndex: Int,
	baseVertex: Int,
	firstInstance: Int,
): Unit = libWGPULibrary.wgpuRenderBundleEncoderDrawIndexed(
	renderBundleEncoder, indexCount,
	instanceCount, firstIndex, baseVertex, firstInstance
)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param indirectBuffer mapped from WGPUBuffer
 * @param indirectOffset mapped from uint64_t
 */
public fun wgpuRenderBundleEncoderDrawIndexedIndirect(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	indirectBuffer: WGPUBuffer?,
	indirectOffset: Long,
): Unit = libWGPULibrary.wgpuRenderBundleEncoderDrawIndexedIndirect(
	renderBundleEncoder,
	indirectBuffer, indirectOffset
)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param indirectBuffer mapped from WGPUBuffer
 * @param indirectOffset mapped from uint64_t
 */
public fun wgpuRenderBundleEncoderDrawIndirect(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	indirectBuffer: WGPUBuffer?,
	indirectOffset: Long,
): Unit = libWGPULibrary.wgpuRenderBundleEncoderDrawIndirect(
	renderBundleEncoder, indirectBuffer,
	indirectOffset
)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param descriptor mapped from (typedef Optional[const WGPURenderBundleDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]](WGPURenderBundleDescriptor)))*
 */
public fun wgpuRenderBundleEncoderFinish(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	descriptor: WGPURenderBundleDescriptor?
): WGPURenderBundle? =
	libWGPULibrary.wgpuRenderBundleEncoderFinish(renderBundleEncoder, descriptor)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param markerLabel mapped from (Char(layout = b1))*
 */
public fun wgpuRenderBundleEncoderInsertDebugMarker(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	markerLabel: String?
): Unit =
	libWGPULibrary.wgpuRenderBundleEncoderInsertDebugMarker(renderBundleEncoder, markerLabel)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 */
public fun wgpuRenderBundleEncoderPopDebugGroup(renderBundleEncoder: WGPURenderBundleEncoder?): Unit =
	libWGPULibrary.wgpuRenderBundleEncoderPopDebugGroup(renderBundleEncoder)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param groupLabel mapped from (Char(layout = b1))*
 */
public fun wgpuRenderBundleEncoderPushDebugGroup(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	groupLabel: String?
): Unit =
	libWGPULibrary.wgpuRenderBundleEncoderPushDebugGroup(renderBundleEncoder, groupLabel)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param groupIndex mapped from uint32_t
 * @param group mapped from WGPUBindGroup
 * @param dynamicOffsetCount mapped from size_t
 * @param dynamicOffsets mapped from (typedef Optional[const uint32_t] = UNSIGNED = Int(layout =
 * i4))*
 */
public fun wgpuRenderBundleEncoderSetBindGroup(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	groupIndex: Int,
	group: WGPUBindGroup?,
	dynamicOffsetCount: NativeLong,
	dynamicOffsets: Pointer?,
): Unit = libWGPULibrary.wgpuRenderBundleEncoderSetBindGroup(
	renderBundleEncoder, groupIndex, group,
	dynamicOffsetCount, dynamicOffsets
)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param buffer mapped from WGPUBuffer
 * @param format mapped from WGPUIndexFormat
 * @param offset mapped from uint64_t
 * @param size mapped from uint64_t
 */
public fun wgpuRenderBundleEncoderSetIndexBuffer(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	buffer: WGPUBuffer?,
	format: Int,
	offset: Long,
	size: Long,
): Unit = libWGPULibrary.wgpuRenderBundleEncoderSetIndexBuffer(
	renderBundleEncoder, buffer, format,
	offset, size
)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuRenderBundleEncoderSetLabel(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	label: String?
): Unit = libWGPULibrary.wgpuRenderBundleEncoderSetLabel(
	renderBundleEncoder,
	label
)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param pipeline mapped from WGPURenderPipeline
 */
public fun wgpuRenderBundleEncoderSetPipeline(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	pipeline: WGPURenderPipeline?
): Unit =
	libWGPULibrary.wgpuRenderBundleEncoderSetPipeline(renderBundleEncoder, pipeline)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 * @param slot mapped from uint32_t
 * @param buffer mapped from WGPUBuffer
 * @param offset mapped from uint64_t
 * @param size mapped from uint64_t
 */
public fun wgpuRenderBundleEncoderSetVertexBuffer(
	renderBundleEncoder: WGPURenderBundleEncoder?,
	slot: Int,
	buffer: WGPUBuffer?,
	offset: Long,
	size: Long,
): Unit = libWGPULibrary.wgpuRenderBundleEncoderSetVertexBuffer(
	renderBundleEncoder, slot, buffer,
	offset, size
)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 */
public fun wgpuRenderBundleEncoderReference(renderBundleEncoder: WGPURenderBundleEncoder?): Unit =
	libWGPULibrary.wgpuRenderBundleEncoderReference(renderBundleEncoder)

/**
 * @param renderBundleEncoder mapped from WGPURenderBundleEncoder
 */
public fun wgpuRenderBundleEncoderRelease(renderBundleEncoder: WGPURenderBundleEncoder?): Unit =
	libWGPULibrary.wgpuRenderBundleEncoderRelease(renderBundleEncoder)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param queryIndex mapped from uint32_t
 */
public fun wgpuRenderPassEncoderBeginOcclusionQuery(
	renderPassEncoder: WGPURenderPassEncoder?,
	queryIndex: Int
): Unit =
	libWGPULibrary.wgpuRenderPassEncoderBeginOcclusionQuery(renderPassEncoder, queryIndex)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param vertexCount mapped from uint32_t
 * @param instanceCount mapped from uint32_t
 * @param firstVertex mapped from uint32_t
 * @param firstInstance mapped from uint32_t
 */
public fun wgpuRenderPassEncoderDraw(
	renderPassEncoder: WGPURenderPassEncoder?,
	vertexCount: Int,
	instanceCount: Int?,
	firstVertex: Int?,
	firstInstance: Int?,
): Unit = libWGPULibrary.wgpuRenderPassEncoderDraw(
	renderPassEncoder, vertexCount, instanceCount,
	firstVertex, firstInstance
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param indexCount mapped from uint32_t
 * @param instanceCount mapped from uint32_t
 * @param firstIndex mapped from uint32_t
 * @param baseVertex mapped from int32_t
 * @param firstInstance mapped from uint32_t
 */
public fun wgpuRenderPassEncoderDrawIndexed(
	renderPassEncoder: WGPURenderPassEncoder?,
	indexCount: Int,
	instanceCount: Int,
	firstIndex: Int,
	baseVertex: Int,
	firstInstance: Int,
): Unit = libWGPULibrary.wgpuRenderPassEncoderDrawIndexed(
	renderPassEncoder, indexCount,
	instanceCount, firstIndex, baseVertex, firstInstance
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param indirectBuffer mapped from WGPUBuffer
 * @param indirectOffset mapped from uint64_t
 */
public fun wgpuRenderPassEncoderDrawIndexedIndirect(
	renderPassEncoder: WGPURenderPassEncoder?,
	indirectBuffer: WGPUBuffer?,
	indirectOffset: Long,
): Unit = libWGPULibrary.wgpuRenderPassEncoderDrawIndexedIndirect(
	renderPassEncoder, indirectBuffer,
	indirectOffset
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param indirectBuffer mapped from WGPUBuffer
 * @param indirectOffset mapped from uint64_t
 */
public fun wgpuRenderPassEncoderDrawIndirect(
	renderPassEncoder: WGPURenderPassEncoder?,
	indirectBuffer: WGPUBuffer?,
	indirectOffset: Long,
): Unit = libWGPULibrary.wgpuRenderPassEncoderDrawIndirect(
	renderPassEncoder, indirectBuffer,
	indirectOffset
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 */
public fun wgpuRenderPassEncoderEnd(renderPassEncoder: WGPURenderPassEncoder?): Unit =
	libWGPULibrary.wgpuRenderPassEncoderEnd(renderPassEncoder)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 */
public fun wgpuRenderPassEncoderEndOcclusionQuery(renderPassEncoder: WGPURenderPassEncoder?): Unit =
	libWGPULibrary.wgpuRenderPassEncoderEndOcclusionQuery(renderPassEncoder)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param bundleCount mapped from size_t
 * @param bundles mapped from (typedef Optional[const WGPURenderBundle] = (Declared())*)*
 */
public fun wgpuRenderPassEncoderExecuteBundles(
	renderPassEncoder: WGPURenderPassEncoder?,
	bundleCount: NativeLong,
	bundles: WGPURenderBundle?,
): Unit = libWGPULibrary.wgpuRenderPassEncoderExecuteBundles(
	renderPassEncoder, bundleCount,
	bundles
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param markerLabel mapped from (Char(layout = b1))*
 */
public fun wgpuRenderPassEncoderInsertDebugMarker(
	renderPassEncoder: WGPURenderPassEncoder?,
	markerLabel: String?
): Unit =
	libWGPULibrary.wgpuRenderPassEncoderInsertDebugMarker(renderPassEncoder, markerLabel)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 */
public fun wgpuRenderPassEncoderPopDebugGroup(renderPassEncoder: WGPURenderPassEncoder?): Unit =
	libWGPULibrary.wgpuRenderPassEncoderPopDebugGroup(renderPassEncoder)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param groupLabel mapped from (Char(layout = b1))*
 */
public fun wgpuRenderPassEncoderPushDebugGroup(
	renderPassEncoder: WGPURenderPassEncoder?,
	groupLabel: String?
): Unit =
	libWGPULibrary.wgpuRenderPassEncoderPushDebugGroup(renderPassEncoder, groupLabel)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param groupIndex mapped from uint32_t
 * @param group mapped from WGPUBindGroup
 * @param dynamicOffsetCount mapped from size_t
 * @param dynamicOffsets mapped from (typedef Optional[const uint32_t] = UNSIGNED = Int(layout =
 * i4))*
 */
public fun wgpuRenderPassEncoderSetBindGroup(
	renderPassEncoder: WGPURenderPassEncoder?,
	groupIndex: Int,
	group: WGPUBindGroup?,
	dynamicOffsetCount: NativeLong,
	dynamicOffsets: Pointer?,
): Unit = libWGPULibrary.wgpuRenderPassEncoderSetBindGroup(
	renderPassEncoder, groupIndex, group,
	dynamicOffsetCount, dynamicOffsets
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param color mapped from (typedef Optional[const WGPUColor] =
 * Declared([d8(r)d8(g)d8(b)d8(a)](WGPUColor)))*
 */
public fun wgpuRenderPassEncoderSetBlendConstant(
	renderPassEncoder: WGPURenderPassEncoder?,
	color: WGPUColor?
): Unit =
	libWGPULibrary.wgpuRenderPassEncoderSetBlendConstant(renderPassEncoder, color)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param buffer mapped from WGPUBuffer
 * @param format mapped from WGPUIndexFormat
 * @param offset mapped from uint64_t
 * @param size mapped from uint64_t
 */
public fun wgpuRenderPassEncoderSetIndexBuffer(
	renderPassEncoder: WGPURenderPassEncoder?,
	buffer: WGPUBuffer?,
	format: Int,
	offset: Long,
	size: Long,
): Unit = libWGPULibrary.wgpuRenderPassEncoderSetIndexBuffer(
	renderPassEncoder, buffer, format,
	offset, size
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuRenderPassEncoderSetLabel(renderPassEncoder: WGPURenderPassEncoder?, label: String?):
	Unit = libWGPULibrary.wgpuRenderPassEncoderSetLabel(renderPassEncoder, label)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param pipeline mapped from WGPURenderPipeline
 */
public fun wgpuRenderPassEncoderSetPipeline(
	renderPassEncoder: WGPURenderPassEncoder?,
	pipeline: WGPURenderPipeline?
): Unit =
	libWGPULibrary.wgpuRenderPassEncoderSetPipeline(renderPassEncoder, pipeline)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param x mapped from uint32_t
 * @param y mapped from uint32_t
 * @param width mapped from uint32_t
 * @param height mapped from uint32_t
 */
public fun wgpuRenderPassEncoderSetScissorRect(
	renderPassEncoder: WGPURenderPassEncoder?,
	x: Int,
	y: Int,
	width: Int,
	height: Int,
): Unit = libWGPULibrary.wgpuRenderPassEncoderSetScissorRect(renderPassEncoder, x, y, width, height)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param reference mapped from uint32_t
 */
public fun wgpuRenderPassEncoderSetStencilReference(
	renderPassEncoder: WGPURenderPassEncoder?,
	reference: Int
): Unit =
	libWGPULibrary.wgpuRenderPassEncoderSetStencilReference(renderPassEncoder, reference)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param slot mapped from uint32_t
 * @param buffer mapped from WGPUBuffer
 * @param offset mapped from uint64_t
 * @param size mapped from uint64_t
 */
public fun wgpuRenderPassEncoderSetVertexBuffer(
	renderPassEncoder: WGPURenderPassEncoder?,
	slot: Int,
	buffer: WGPUBuffer?,
	offset: Long,
	size: Long?,
): Unit = libWGPULibrary.wgpuRenderPassEncoderSetVertexBuffer(
	renderPassEncoder, slot, buffer,
	offset, size
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param x mapped from float
 * @param y mapped from float
 * @param width mapped from float
 * @param height mapped from float
 * @param minDepth mapped from float
 * @param maxDepth mapped from float
 */
public fun wgpuRenderPassEncoderSetViewport(
	renderPassEncoder: WGPURenderPassEncoder?,
	x: Float,
	y: Float,
	width: Float,
	height: Float,
	minDepth: Float,
	maxDepth: Float,
): Unit = libWGPULibrary.wgpuRenderPassEncoderSetViewport(
	renderPassEncoder, x, y, width, height,
	minDepth, maxDepth
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 */
public fun wgpuRenderPassEncoderReference(renderPassEncoder: WGPURenderPassEncoder?): Unit =
	libWGPULibrary.wgpuRenderPassEncoderReference(renderPassEncoder)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 */
public fun wgpuRenderPassEncoderRelease(renderPassEncoder: WGPURenderPassEncoder?): Unit =
	libWGPULibrary.wgpuRenderPassEncoderRelease(renderPassEncoder)

/**
 * @param renderPipeline mapped from WGPURenderPipeline
 * @param groupIndex mapped from uint32_t
 */
public fun wgpuRenderPipelineGetBindGroupLayout(
	renderPipeline: WGPURenderPipeline?,
	groupIndex: Int
): WGPUBindGroupLayout? =
	libWGPULibrary.wgpuRenderPipelineGetBindGroupLayout(renderPipeline, groupIndex)

/**
 * @param renderPipeline mapped from WGPURenderPipeline
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuRenderPipelineSetLabel(renderPipeline: WGPURenderPipeline?, label: String?): Unit =
	libWGPULibrary.wgpuRenderPipelineSetLabel(renderPipeline, label)

/**
 * @param renderPipeline mapped from WGPURenderPipeline
 */
public fun wgpuRenderPipelineReference(renderPipeline: WGPURenderPipeline?): Unit =
	libWGPULibrary.wgpuRenderPipelineReference(renderPipeline)

/**
 * @param renderPipeline mapped from WGPURenderPipeline
 */
public fun wgpuRenderPipelineRelease(renderPipeline: WGPURenderPipeline?): Unit =
	libWGPULibrary.wgpuRenderPipelineRelease(renderPipeline)

/**
 * @param sampler mapped from WGPUSampler
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuSamplerSetLabel(sampler: WGPUSampler?, label: String?): Unit =
	libWGPULibrary.wgpuSamplerSetLabel(sampler, label)

/**
 * @param sampler mapped from WGPUSampler
 */
public fun wgpuSamplerReference(sampler: WGPUSampler?): Unit =
	libWGPULibrary.wgpuSamplerReference(sampler)

/**
 * @param sampler mapped from WGPUSampler
 */
public fun wgpuSamplerRelease(sampler: WGPUSampler?): Unit =
	libWGPULibrary.wgpuSamplerRelease(sampler)

/**
 * @param shaderModule mapped from WGPUShaderModule
 * @param callback mapped from WGPUCompilationInfoCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuShaderModuleGetCompilationInfo(
	shaderModule: WGPUShaderModule?,
	callback: WGPUCompilationInfoCallback?,
	userdata: Pointer?,
): Unit = libWGPULibrary.wgpuShaderModuleGetCompilationInfo(shaderModule, callback, userdata)

/**
 * @param shaderModule mapped from WGPUShaderModule
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuShaderModuleSetLabel(shaderModule: WGPUShaderModule?, label: String?): Unit =
	libWGPULibrary.wgpuShaderModuleSetLabel(shaderModule, label)

/**
 * @param shaderModule mapped from WGPUShaderModule
 */
public fun wgpuShaderModuleReference(shaderModule: WGPUShaderModule?): Unit =
	libWGPULibrary.wgpuShaderModuleReference(shaderModule)

/**
 * @param shaderModule mapped from WGPUShaderModule
 */
public fun wgpuShaderModuleRelease(shaderModule: WGPUShaderModule?): Unit =
	libWGPULibrary.wgpuShaderModuleRelease(shaderModule)

/**
 * @param surface mapped from WGPUSurface
 * @param config mapped from (typedef Optional[const WGPUSurfaceConfiguration] =
 * Declared([a8(nextInChain):[*:b1]a8(device):[*:b1]i4(format)i4(usage)j8(viewFormatCount)a8(viewFormats):[*:b1]i4(alphaMode)i4(width)i4(height)i4(presentMode)](WGPUSurfaceConfiguration)))*
 */
public fun wgpuSurfaceConfigure(surface: WGPUSurface?, config: WGPUSurfaceConfiguration?): Unit =
	libWGPULibrary.wgpuSurfaceConfigure(surface, config)

/**
 * @param surface mapped from WGPUSurface
 * @param adapter mapped from WGPUAdapter
 * @param capabilities mapped from (typedef Optional[WGPUSurfaceCapabilities] =
 * Declared([a8(nextInChain):[*:b1]j8(formatCount)a8(formats):[*:b1]j8(presentModeCount)a8(presentModes):[*:b1]j8(alphaModeCount)a8(alphaModes):[*:b1]](WGPUSurfaceCapabilities)))*
 */
public fun wgpuSurfaceGetCapabilities(
	surface: WGPUSurface?,
	adapter: WGPUAdapter?,
	capabilities: WGPUSurfaceCapabilities?,
): Unit = libWGPULibrary.wgpuSurfaceGetCapabilities(surface, adapter, capabilities)

/**
 * @param surface mapped from WGPUSurface
 * @param surfaceTexture mapped from (typedef Optional[WGPUSurfaceTexture] =
 * Declared([a8(texture):[*:b1]i4(suboptimal)i4(status)](WGPUSurfaceTexture)))*
 */
public fun wgpuSurfaceGetCurrentTexture(surface: WGPUSurface?, surfaceTexture: WGPUSurfaceTexture?):
	Unit = libWGPULibrary.wgpuSurfaceGetCurrentTexture(surface, surfaceTexture)

/**
 * @param surface mapped from WGPUSurface
 * @param adapter mapped from WGPUAdapter
 */
public fun wgpuSurfaceGetPreferredFormat(surface: WGPUSurface?, adapter: WGPUAdapter?): Int =
	libWGPULibrary.wgpuSurfaceGetPreferredFormat(surface, adapter)

/**
 * @param surface mapped from WGPUSurface
 */
public fun wgpuSurfacePresent(surface: WGPUSurface?): Unit =
	libWGPULibrary.wgpuSurfacePresent(surface)

/**
 * @param surface mapped from WGPUSurface
 */
public fun wgpuSurfaceUnconfigure(surface: WGPUSurface?): Unit =
	libWGPULibrary.wgpuSurfaceUnconfigure(surface)

/**
 * @param surface mapped from WGPUSurface
 */
public fun wgpuSurfaceReference(surface: WGPUSurface?): Unit =
	libWGPULibrary.wgpuSurfaceReference(surface)

/**
 * @param surface mapped from WGPUSurface
 */
public fun wgpuSurfaceRelease(surface: WGPUSurface?): Unit =
	libWGPULibrary.wgpuSurfaceRelease(surface)

/**
 * @param capabilities mapped from WGPUSurfaceCapabilities
 */
public fun wgpuSurfaceCapabilitiesFreeMembers(capabilities: WGPUSurfaceCapabilities): Unit =
	libWGPULibrary.wgpuSurfaceCapabilitiesFreeMembers(capabilities)

/**
 * @param texture mapped from WGPUTexture
 * @param descriptor mapped from (typedef Optional[const WGPUTextureViewDescriptor] =
 * Declared([a8(nextInChain):[*:b1]a8(label):[*:b1]i4(format)i4(dimension)i4(baseMipLevel)i4(mipLevelCount)i4(baseArrayLayer)i4(arrayLayerCount)i4(aspect)x4](WGPUTextureViewDescriptor)))*
 */
public fun wgpuTextureCreateView(texture: WGPUTexture?, descriptor: WGPUTextureViewDescriptor?):
	WGPUTextureView? = libWGPULibrary.wgpuTextureCreateView(texture, descriptor)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureDestroy(texture: WGPUTexture?): Unit =
	libWGPULibrary.wgpuTextureDestroy(texture)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureGetDepthOrArrayLayers(texture: WGPUTexture?): Int =
	libWGPULibrary.wgpuTextureGetDepthOrArrayLayers(texture)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureGetDimension(texture: WGPUTexture?): Int =
	libWGPULibrary.wgpuTextureGetDimension(texture)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureGetFormat(texture: WGPUTexture?): Int =
	libWGPULibrary.wgpuTextureGetFormat(texture)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureGetHeight(texture: WGPUTexture?): Int =
	libWGPULibrary.wgpuTextureGetHeight(texture)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureGetMipLevelCount(texture: WGPUTexture?): Int =
	libWGPULibrary.wgpuTextureGetMipLevelCount(texture)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureGetSampleCount(texture: WGPUTexture?): Int =
	libWGPULibrary.wgpuTextureGetSampleCount(texture)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureGetUsage(texture: WGPUTexture?): WGPUTextureUsageFlags =
	libWGPULibrary.wgpuTextureGetUsage(texture)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureGetWidth(texture: WGPUTexture?): Int =
	libWGPULibrary.wgpuTextureGetWidth(texture)

/**
 * @param texture mapped from WGPUTexture
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuTextureSetLabel(texture: WGPUTexture?, label: String?): Unit =
	libWGPULibrary.wgpuTextureSetLabel(texture, label)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureReference(texture: WGPUTexture?): Unit =
	libWGPULibrary.wgpuTextureReference(texture)

/**
 * @param texture mapped from WGPUTexture
 */
public fun wgpuTextureRelease(texture: WGPUTexture?): Unit =
	libWGPULibrary.wgpuTextureRelease(texture)

/**
 * @param textureView mapped from WGPUTextureView
 * @param label mapped from (Char(layout = b1))*
 */
public fun wgpuTextureViewSetLabel(textureView: WGPUTextureView?, label: String?): Unit =
	libWGPULibrary.wgpuTextureViewSetLabel(textureView, label)

/**
 * @param textureView mapped from WGPUTextureView
 */
public fun wgpuTextureViewReference(textureView: WGPUTextureView?): Unit =
	libWGPULibrary.wgpuTextureViewReference(textureView)

/**
 * @param textureView mapped from WGPUTextureView
 */
public fun wgpuTextureViewRelease(textureView: WGPUTextureView?): Unit =
	libWGPULibrary.wgpuTextureViewRelease(textureView)

/**
 * @param instance mapped from WGPUInstance
 * @param report mapped from (typedef Optional[WGPUGlobalReport] =
 * Declared([[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](surfaces)i4(backendType)x4[[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](adapters)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](devices)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](queues)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](pipelineLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](shaderModules)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroupLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroups)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](commandBuffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderBundles)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderPipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](computePipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](querySets)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](buffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textures)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textureViews)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](samplers)](vulkan)[[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](adapters)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](devices)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](queues)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](pipelineLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](shaderModules)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroupLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroups)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](commandBuffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderBundles)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderPipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](computePipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](querySets)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](buffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textures)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textureViews)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](samplers)](metal)[[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](adapters)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](devices)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](queues)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](pipelineLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](shaderModules)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroupLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroups)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](commandBuffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderBundles)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderPipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](computePipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](querySets)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](buffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textures)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textureViews)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](samplers)](dx12)[[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](adapters)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](devices)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](queues)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](pipelineLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](shaderModules)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroupLayouts)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](bindGroups)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](commandBuffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderBundles)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](renderPipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](computePipelines)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](querySets)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](buffers)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textures)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](textureViews)[j8(numAllocated)j8(numKeptFromUser)j8(numReleasedFromUser)j8(numError)j8(elementSize)](samplers)](gl)](WGPUGlobalReport)))*
 */
public fun wgpuGenerateReport(instance: WGPUInstance?, report: WGPUGlobalReport?): Unit =
	libWGPULibrary.wgpuGenerateReport(instance, report)

/**
 * @param instance mapped from WGPUInstance
 * @param options mapped from (typedef Optional[const WGPUInstanceEnumerateAdapterOptions] =
 * Declared([a8(nextInChain):[*:b1]i4(backends)x4](WGPUInstanceEnumerateAdapterOptions)))*
 * @param adapters mapped from (typedef Optional[WGPUAdapter] = (Declared())*)*
 */
public fun wgpuInstanceEnumerateAdapters(
	instance: WGPUInstance?,
	options: WGPUInstanceEnumerateAdapterOptions?,
	adapters: WGPUAdapter?,
): NativeLong = libWGPULibrary.wgpuInstanceEnumerateAdapters(instance, options, adapters)

/**
 * @param queue mapped from WGPUQueue
 * @param commandCount mapped from size_t
 * @param commands mapped from (typedef Optional[const WGPUCommandBuffer] = (Declared())*)*
 */
public fun wgpuQueueSubmitForIndex(
	queue: WGPUQueue?,
	commandCount: NativeLong,
	commands: WGPUCommandBuffer?,
): WGPUSubmissionIndex = libWGPULibrary.wgpuQueueSubmitForIndex(queue, commandCount, commands)

/**
 * @param device mapped from WGPUDevice
 * @param wait mapped from WGPUBool
 * @param wrappedSubmissionIndex mapped from (typedef Optional[const WGPUWrappedSubmissionIndex] =
 * Declared([a8(queue):[*:b1]j8(submissionIndex)](WGPUWrappedSubmissionIndex)))*
 */
public fun wgpuDevicePoll(
	device: WGPUDevice?,
	wait: WGPUBool,
	wrappedSubmissionIndex: WGPUWrappedSubmissionIndex?,
): WGPUBool = libWGPULibrary.wgpuDevicePoll(device, wait, wrappedSubmissionIndex)

/**
 * @param callback mapped from WGPULogCallback
 * @param userdata mapped from (Void)*
 */
public fun wgpuSetLogCallback(callback: WGPULogCallback?, userdata: Pointer?): Unit =
	libWGPULibrary.wgpuSetLogCallback(callback, userdata)

/**
 * @param level mapped from WGPULogLevel
 */
public fun wgpuSetLogLevel(level: Int): Unit = libWGPULibrary.wgpuSetLogLevel(level)

public fun wgpuGetVersion(): Int = libWGPULibrary.wgpuGetVersion()

/**
 * @param encoder mapped from WGPURenderPassEncoder
 * @param stages mapped from WGPUShaderStageFlags
 * @param offset mapped from uint32_t
 * @param sizeBytes mapped from uint32_t
 * @param data mapped from (Void)*
 */
public fun wgpuRenderPassEncoderSetPushConstants(
	encoder: WGPURenderPassEncoder?,
	stages: WGPUShaderStageFlags,
	offset: Int,
	sizeBytes: Int,
	`data`: Pointer?,
): Unit = libWGPULibrary.wgpuRenderPassEncoderSetPushConstants(
	encoder, stages, offset, sizeBytes,
	data
)

/**
 * @param encoder mapped from WGPURenderPassEncoder
 * @param buffer mapped from WGPUBuffer
 * @param offset mapped from uint64_t
 * @param count mapped from uint32_t
 */
public fun wgpuRenderPassEncoderMultiDrawIndirect(
	encoder: WGPURenderPassEncoder?,
	buffer: WGPUBuffer?,
	offset: Long,
	count: Int,
): Unit = libWGPULibrary.wgpuRenderPassEncoderMultiDrawIndirect(encoder, buffer, offset, count)

/**
 * @param encoder mapped from WGPURenderPassEncoder
 * @param buffer mapped from WGPUBuffer
 * @param offset mapped from uint64_t
 * @param count mapped from uint32_t
 */
public fun wgpuRenderPassEncoderMultiDrawIndexedIndirect(
	encoder: WGPURenderPassEncoder?,
	buffer: WGPUBuffer?,
	offset: Long,
	count: Int,
): Unit = libWGPULibrary.wgpuRenderPassEncoderMultiDrawIndexedIndirect(
	encoder, buffer, offset,
	count
)

/**
 * @param encoder mapped from WGPURenderPassEncoder
 * @param buffer mapped from WGPUBuffer
 * @param offset mapped from uint64_t
 * @param count_buffer mapped from WGPUBuffer
 * @param count_buffer_offset mapped from uint64_t
 * @param max_count mapped from uint32_t
 */
public fun wgpuRenderPassEncoderMultiDrawIndirectCount(
	encoder: WGPURenderPassEncoder?,
	buffer: WGPUBuffer?,
	offset: Long,
	count_buffer: WGPUBuffer?,
	count_buffer_offset: Long,
	max_count: Int,
): Unit = libWGPULibrary.wgpuRenderPassEncoderMultiDrawIndirectCount(
	encoder, buffer, offset,
	count_buffer, count_buffer_offset, max_count
)

/**
 * @param encoder mapped from WGPURenderPassEncoder
 * @param buffer mapped from WGPUBuffer
 * @param offset mapped from uint64_t
 * @param count_buffer mapped from WGPUBuffer
 * @param count_buffer_offset mapped from uint64_t
 * @param max_count mapped from uint32_t
 */
public fun wgpuRenderPassEncoderMultiDrawIndexedIndirectCount(
	encoder: WGPURenderPassEncoder?,
	buffer: WGPUBuffer?,
	offset: Long,
	count_buffer: WGPUBuffer?,
	count_buffer_offset: Long,
	max_count: Int,
): Unit = libWGPULibrary.wgpuRenderPassEncoderMultiDrawIndexedIndirectCount(
	encoder, buffer, offset,
	count_buffer, count_buffer_offset, max_count
)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 * @param querySet mapped from WGPUQuerySet
 * @param queryIndex mapped from uint32_t
 */
public fun wgpuComputePassEncoderBeginPipelineStatisticsQuery(
	computePassEncoder: WGPUComputePassEncoder?,
	querySet: WGPUQuerySet?,
	queryIndex: Int,
): Unit = libWGPULibrary.wgpuComputePassEncoderBeginPipelineStatisticsQuery(
	computePassEncoder,
	querySet, queryIndex
)

/**
 * @param computePassEncoder mapped from WGPUComputePassEncoder
 */
public
fun wgpuComputePassEncoderEndPipelineStatisticsQuery(computePassEncoder: WGPUComputePassEncoder?):
	Unit = libWGPULibrary.wgpuComputePassEncoderEndPipelineStatisticsQuery(computePassEncoder)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 * @param querySet mapped from WGPUQuerySet
 * @param queryIndex mapped from uint32_t
 */
public fun wgpuRenderPassEncoderBeginPipelineStatisticsQuery(
	renderPassEncoder: WGPURenderPassEncoder?,
	querySet: WGPUQuerySet?,
	queryIndex: Int,
): Unit = libWGPULibrary.wgpuRenderPassEncoderBeginPipelineStatisticsQuery(
	renderPassEncoder,
	querySet, queryIndex
)

/**
 * @param renderPassEncoder mapped from WGPURenderPassEncoder
 */
public
fun wgpuRenderPassEncoderEndPipelineStatisticsQuery(renderPassEncoder: WGPURenderPassEncoder?):
	Unit = libWGPULibrary.wgpuRenderPassEncoderEndPipelineStatisticsQuery(renderPassEncoder)
