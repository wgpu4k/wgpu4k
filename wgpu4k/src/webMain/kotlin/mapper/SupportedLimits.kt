@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUSupportedLimits
import io.ygdrasil.webgpu.Limits
import io.ygdrasil.webgpu.WGPUSupportedLimits
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.toULong
import js.collections.JsMap
import kotlin.OptIn
import kotlin.also
import kotlin.collections.forEach
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsAny
import kotlin.js.toInt
import kotlin.js.toJsString
import kotlin.let
import kotlin.to
import kotlin.toUInt

internal fun map(input: GPUSupportedLimits): JsMap<JsAny, JsAny> = listOf(
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
).map { (key, value) -> key.toJsString() to value }
    .let {
        JsMap<JsAny, JsAny>()
            .also { map -> it.forEach { (key, value) -> map.set(key, value)} }
    }

internal fun map(input: WGPUSupportedLimits): GPUSupportedLimits = Limits(
    maxTextureDimension1D = input.maxTextureDimension1D.toInt().toUInt(),
    maxTextureDimension2D = input.maxTextureDimension2D.toInt().toUInt(),
    maxTextureDimension3D = input.maxTextureDimension3D.toInt().toUInt(),
    maxTextureArrayLayers = input.maxTextureArrayLayers.toInt().toUInt(),
    maxBindGroups = input.maxBindGroups.toInt().toUInt(),
    maxBindGroupsPlusVertexBuffers = input.maxBindGroupsPlusVertexBuffers.toInt().toUInt(),
    maxBindingsPerBindGroup = input.maxBindingsPerBindGroup.toInt().toUInt(),
    maxDynamicUniformBuffersPerPipelineLayout = input.maxDynamicUniformBuffersPerPipelineLayout.toInt().toUInt(),
    maxDynamicStorageBuffersPerPipelineLayout = input.maxDynamicStorageBuffersPerPipelineLayout.toInt().toUInt(),
    maxSampledTexturesPerShaderStage = input.maxSampledTexturesPerShaderStage.toInt().toUInt(),
    maxSamplersPerShaderStage = input.maxSamplersPerShaderStage.toInt().toUInt(),
    maxStorageBuffersPerShaderStage = input.maxStorageBuffersPerShaderStage.toInt().toUInt(),
    maxStorageTexturesPerShaderStage = input.maxStorageTexturesPerShaderStage.toInt().toUInt(),
    maxUniformBuffersPerShaderStage = input.maxUniformBuffersPerShaderStage.toInt().toUInt(),
    maxUniformBufferBindingSize = input.maxUniformBufferBindingSize.toULong(),
    maxStorageBufferBindingSize = input.maxStorageBufferBindingSize.toULong(),
    minUniformBufferOffsetAlignment = input.minUniformBufferOffsetAlignment.toInt().toUInt(),
    minStorageBufferOffsetAlignment = input.minStorageBufferOffsetAlignment.toInt().toUInt(),
    maxVertexBuffers = input.maxVertexBuffers.toInt().toUInt(),
    maxBufferSize = input.maxBufferSize.toULong(),
    maxVertexAttributes = input.maxVertexAttributes.toInt().toUInt(),
    maxVertexBufferArrayStride = input.maxVertexBufferArrayStride.toInt().toUInt(),
    maxInterStageShaderVariables = input.maxInterStageShaderVariables.toInt().toUInt(),
    maxColorAttachments = input.maxColorAttachments.toInt().toUInt(),
    maxColorAttachmentBytesPerSample = input.maxColorAttachmentBytesPerSample.toInt().toUInt(),
    maxComputeWorkgroupStorageSize = input.maxComputeWorkgroupStorageSize.toInt().toUInt(),
    maxComputeInvocationsPerWorkgroup = input.maxComputeInvocationsPerWorkgroup.toInt().toUInt(),
    maxComputeWorkgroupSizeX = input.maxComputeWorkgroupSizeX.toInt().toUInt(),
    maxComputeWorkgroupSizeY = input.maxComputeWorkgroupSizeY.toInt().toUInt(),
    maxComputeWorkgroupSizeZ = input.maxComputeWorkgroupSizeZ.toInt().toUInt(),
    maxComputeWorkgroupsPerDimension = input.maxComputeWorkgroupsPerDimension.toInt().toUInt(),
)