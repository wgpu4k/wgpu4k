@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.SupportedLimits
import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.*

internal fun map(input: WGPULimits): SupportedLimits = SupportedLimits(
    maxTextureDimension1D = input.maxTextureDimension1D.toInt(),
    maxTextureDimension2D = input.maxTextureDimension2D.toInt(),
    maxTextureDimension3D = input.maxTextureDimension3D.toInt(),
    maxTextureArrayLayers = input.maxTextureArrayLayers.toInt(),
    maxBindGroups = input.maxBindGroups.toInt(),
    maxBindGroupsPlusVertexBuffers = input.maxBindGroupsPlusVertexBuffers.toInt(),
    maxBindingsPerBindGroup = input.maxBindingsPerBindGroup.toInt(),
    maxDynamicUniformBuffersPerPipelineLayout = input.maxDynamicUniformBuffersPerPipelineLayout.toInt(),
    maxDynamicStorageBuffersPerPipelineLayout = input.maxDynamicStorageBuffersPerPipelineLayout.toInt(),
    maxSampledTexturesPerShaderStage = input.maxSampledTexturesPerShaderStage.toInt(),
    maxSamplersPerShaderStage = input.maxSamplersPerShaderStage.toInt(),
    maxStorageBuffersPerShaderStage = input.maxStorageBuffersPerShaderStage.toInt(),
    maxStorageTexturesPerShaderStage = input.maxStorageTexturesPerShaderStage.toInt(),
    maxUniformBuffersPerShaderStage = input.maxUniformBuffersPerShaderStage.toInt(),
    maxUniformBufferBindingSize = input.maxUniformBufferBindingSize.toLong(),
    maxStorageBufferBindingSize = input.maxStorageBufferBindingSize.toLong(),
    minUniformBufferOffsetAlignment = input.minUniformBufferOffsetAlignment.toInt(),
    minStorageBufferOffsetAlignment = input.minStorageBufferOffsetAlignment.toInt(),
    maxVertexBuffers = input.maxVertexBuffers.toInt(),
    maxBufferSize = input.maxBufferSize.toLong(),
    maxVertexAttributes = input.maxVertexAttributes.toInt(),
    maxVertexBufferArrayStride = input.maxVertexBufferArrayStride.toInt(),
    maxInterStageShaderComponents = input.maxInterStageShaderComponents.toInt(),
    maxInterStageShaderVariables = input.maxInterStageShaderVariables.toInt(),
    maxColorAttachments = input.maxColorAttachments.toInt(),
    maxColorAttachmentBytesPerSample = input.maxColorAttachmentBytesPerSample.toInt(),
    maxComputeWorkgroupStorageSize = input.maxComputeWorkgroupStorageSize.toInt(),
    maxComputeInvocationsPerWorkgroup = input.maxComputeInvocationsPerWorkgroup.toInt(),
    maxComputeWorkgroupSizeX = input.maxComputeWorkgroupSizeX.toInt(),
    maxComputeWorkgroupSizeY = input.maxComputeWorkgroupSizeY.toInt(),
    maxComputeWorkgroupSizeZ = input.maxComputeWorkgroupSizeZ.toInt(),
    maxComputeWorkgroupsPerDimension = input.maxComputeWorkgroupsPerDimension.toInt(),
)