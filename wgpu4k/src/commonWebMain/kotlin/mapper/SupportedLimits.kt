package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUSupportedLimits
import io.ygdrasil.webgpu.JsMap
import io.ygdrasil.webgpu.Limits
import io.ygdrasil.webgpu.WGPUSupportedLimits
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.asJsString
import io.ygdrasil.webgpu.asUInt
import io.ygdrasil.webgpu.asULong
import io.ygdrasil.webgpu.toJsMap
import kotlin.js.JsAny

internal fun map(input: GPUSupportedLimits): JsMap<JsAny, JsAny> = mapOf(
    "maxTextureDimension1D" to input.maxTextureDimension1D.asJsNumber(),
    "maxTextureDimension2D" to input.maxTextureDimension2D.asJsNumber(),
    "maxTextureDimension3D" to input.maxTextureDimension3D.asJsNumber(),
    "maxTextureArrayLayers" to input.maxTextureArrayLayers.asJsNumber(),
    "maxBindGroups" to input.maxBindGroups.asJsNumber(),
    "maxBindGroupsPlusVertexBuffers" to input.maxBindGroupsPlusVertexBuffers.asJsNumber(),
    "maxBindingsPerBindGroup" to input.maxBindingsPerBindGroup.asJsNumber(),
    "maxDynamicUniformBuffersPerPipelineLayout" to input.maxDynamicUniformBuffersPerPipelineLayout.asJsNumber(),
    "maxDynamicStorageBuffersPerPipelineLayout" to input.maxDynamicStorageBuffersPerPipelineLayout.asJsNumber(),
    "maxSampledTexturesPerShaderStage" to input.maxSampledTexturesPerShaderStage.asJsNumber(),
    "maxSamplersPerShaderStage" to input.maxSamplersPerShaderStage.asJsNumber(),
    "maxStorageBuffersPerShaderStage" to input.maxStorageBuffersPerShaderStage.asJsNumber(),
    "maxStorageTexturesPerShaderStage" to input.maxStorageTexturesPerShaderStage.asJsNumber(),
    "maxUniformBuffersPerShaderStage" to input.maxUniformBuffersPerShaderStage.asJsNumber(),
    "maxUniformBufferBindingSize" to input.maxUniformBufferBindingSize.asJsNumber(),
    "maxStorageBufferBindingSize" to input.maxStorageBufferBindingSize.asJsNumber(),
    "minUniformBufferOffsetAlignment" to input.minUniformBufferOffsetAlignment.asJsNumber(),
    "minStorageBufferOffsetAlignment" to input.minStorageBufferOffsetAlignment.asJsNumber(),
    "maxVertexBuffers" to input.maxVertexBuffers.asJsNumber(),
    "maxBufferSize" to input.maxBufferSize.asJsNumber(),
    "maxVertexAttributes" to input.maxVertexAttributes.asJsNumber(),
    "maxVertexBufferArrayStride" to input.maxVertexBufferArrayStride.asJsNumber(),
    "maxInterStageShaderVariables" to input.maxInterStageShaderVariables.asJsNumber(),
    "maxColorAttachments" to input.maxColorAttachments.asJsNumber(),
    "maxColorAttachmentBytesPerSample" to input.maxColorAttachmentBytesPerSample.asJsNumber(),
    "maxComputeWorkgroupStorageSize" to input.maxComputeWorkgroupStorageSize.asJsNumber(),
    "maxComputeInvocationsPerWorkgroup" to input.maxComputeInvocationsPerWorkgroup.asJsNumber(),
    "maxComputeWorkgroupSizeX" to input.maxComputeWorkgroupSizeX.asJsNumber(),
    "maxComputeWorkgroupSizeY" to input.maxComputeWorkgroupSizeY.asJsNumber(),
    "maxComputeWorkgroupSizeZ" to input.maxComputeWorkgroupSizeZ.asJsNumber(),
    "maxComputeWorkgroupsPerDimension" to input.maxComputeWorkgroupsPerDimension.asJsNumber(),
).mapKeys { it.key.asJsString() }
    .toJsMap<JsAny, JsAny>()

internal fun map(input: WGPUSupportedLimits): GPUSupportedLimits = Limits(
    maxTextureDimension1D = input.maxTextureDimension1D.asUInt(),
    maxTextureDimension2D = input.maxTextureDimension2D.asUInt(),
    maxTextureDimension3D = input.maxTextureDimension3D.asUInt(),
    maxTextureArrayLayers = input.maxTextureArrayLayers.asUInt(),
    maxBindGroups = input.maxBindGroups.asUInt(),
    maxBindGroupsPlusVertexBuffers = input.maxBindGroupsPlusVertexBuffers.asUInt(),
    maxBindingsPerBindGroup = input.maxBindingsPerBindGroup.asUInt(),
    maxDynamicUniformBuffersPerPipelineLayout = input.maxDynamicUniformBuffersPerPipelineLayout.asUInt(),
    maxDynamicStorageBuffersPerPipelineLayout = input.maxDynamicStorageBuffersPerPipelineLayout.asUInt(),
    maxSampledTexturesPerShaderStage = input.maxSampledTexturesPerShaderStage.asUInt(),
    maxSamplersPerShaderStage = input.maxSamplersPerShaderStage.asUInt(),
    maxStorageBuffersPerShaderStage = input.maxStorageBuffersPerShaderStage.asUInt(),
    maxStorageTexturesPerShaderStage = input.maxStorageTexturesPerShaderStage.asUInt(),
    maxUniformBuffersPerShaderStage = input.maxUniformBuffersPerShaderStage.asUInt(),
    maxUniformBufferBindingSize = input.maxUniformBufferBindingSize.asULong(),
    maxStorageBufferBindingSize = input.maxStorageBufferBindingSize.asULong(),
    minUniformBufferOffsetAlignment = input.minUniformBufferOffsetAlignment.asUInt(),
    minStorageBufferOffsetAlignment = input.minStorageBufferOffsetAlignment.asUInt(),
    maxVertexBuffers = input.maxVertexBuffers.asUInt(),
    maxBufferSize = input.maxBufferSize.asULong(),
    maxVertexAttributes = input.maxVertexAttributes.asUInt(),
    maxVertexBufferArrayStride = input.maxVertexBufferArrayStride.asUInt(),
    maxInterStageShaderVariables = input.maxInterStageShaderVariables.asUInt(),
    maxColorAttachments = input.maxColorAttachments.asUInt(),
    maxColorAttachmentBytesPerSample = input.maxColorAttachmentBytesPerSample.asUInt(),
    maxComputeWorkgroupStorageSize = input.maxComputeWorkgroupStorageSize.asUInt(),
    maxComputeInvocationsPerWorkgroup = input.maxComputeInvocationsPerWorkgroup.asUInt(),
    maxComputeWorkgroupSizeX = input.maxComputeWorkgroupSizeX.asUInt(),
    maxComputeWorkgroupSizeY = input.maxComputeWorkgroupSizeY.asUInt(),
    maxComputeWorkgroupSizeZ = input.maxComputeWorkgroupSizeZ.asUInt(),
    maxComputeWorkgroupsPerDimension = input.maxComputeWorkgroupsPerDimension.asUInt(),
)