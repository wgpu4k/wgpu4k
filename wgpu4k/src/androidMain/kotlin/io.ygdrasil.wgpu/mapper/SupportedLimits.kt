package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.SupportedLimits
import io.ygdrasil.wgpu.internal.jna.WGPULimits
import java.lang.foreign.MemorySegment

internal fun map(input: MemorySegment): SupportedLimits = SupportedLimits(
    maxTextureDimension1D = WGPULimits.maxTextureDimension1D(input),
    maxTextureDimension2D = WGPULimits.maxTextureDimension2D(input),
    maxTextureDimension3D = WGPULimits.maxTextureDimension3D(input),
    maxTextureArrayLayers = WGPULimits.maxTextureArrayLayers(input),
    maxBindGroups = WGPULimits.maxBindGroups(input),
    maxBindGroupsPlusVertexBuffers = WGPULimits.maxBindGroupsPlusVertexBuffers(input),
    maxBindingsPerBindGroup = WGPULimits.maxBindingsPerBindGroup(input),
    maxDynamicUniformBuffersPerPipelineLayout = WGPULimits.maxDynamicUniformBuffersPerPipelineLayout(input),
    maxDynamicStorageBuffersPerPipelineLayout = WGPULimits.maxDynamicStorageBuffersPerPipelineLayout(input),
    maxSampledTexturesPerShaderStage = WGPULimits.maxSampledTexturesPerShaderStage(input),
    maxSamplersPerShaderStage = WGPULimits.maxSamplersPerShaderStage(input),
    maxStorageBuffersPerShaderStage = WGPULimits.maxStorageBuffersPerShaderStage(input),
    maxStorageTexturesPerShaderStage = WGPULimits.maxStorageTexturesPerShaderStage(input),
    maxUniformBuffersPerShaderStage = WGPULimits.maxUniformBuffersPerShaderStage(input),
    maxUniformBufferBindingSize = WGPULimits.maxUniformBufferBindingSize(input),
    maxStorageBufferBindingSize = WGPULimits.maxStorageBufferBindingSize(input),
    minUniformBufferOffsetAlignment = WGPULimits.minUniformBufferOffsetAlignment(input),
    minStorageBufferOffsetAlignment = WGPULimits.minStorageBufferOffsetAlignment(input),
    maxVertexBuffers = WGPULimits.maxVertexBuffers(input),
    maxBufferSize = WGPULimits.maxBufferSize(input),
    maxVertexAttributes = WGPULimits.maxVertexAttributes(input),
    maxVertexBufferArrayStride = WGPULimits.maxVertexBufferArrayStride(input),
    maxInterStageShaderComponents = WGPULimits.maxInterStageShaderComponents(input),
    maxInterStageShaderVariables = WGPULimits.maxInterStageShaderVariables(input),
    maxColorAttachments = WGPULimits.maxColorAttachments(input),
    maxColorAttachmentBytesPerSample = WGPULimits.maxColorAttachmentBytesPerSample(input),
    maxComputeWorkgroupStorageSize = WGPULimits.maxComputeWorkgroupStorageSize(input),
    maxComputeInvocationsPerWorkgroup = WGPULimits.maxComputeInvocationsPerWorkgroup(input),
    maxComputeWorkgroupSizeX = WGPULimits.maxComputeWorkgroupSizeX(input),
    maxComputeWorkgroupSizeY = WGPULimits.maxComputeWorkgroupSizeY(input),
    maxComputeWorkgroupSizeZ = WGPULimits.maxComputeWorkgroupSizeZ(input),
    maxComputeWorkgroupsPerDimension = WGPULimits.maxComputeWorkgroupsPerDimension(input),
)