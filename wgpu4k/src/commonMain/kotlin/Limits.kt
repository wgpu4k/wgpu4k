package io.ygdrasil.webgpu

data class Limits(
    val maxTextureDimension1D: UInt,
    val maxTextureDimension2D: UInt,
    val maxTextureDimension3D: UInt,
    val maxTextureArrayLayers: UInt,
    val maxBindGroups: UInt,
    val maxBindGroupsPlusVertexBuffers: UInt,
    val maxBindingsPerBindGroup: UInt,
    val maxDynamicUniformBuffersPerPipelineLayout: UInt,
    val maxDynamicStorageBuffersPerPipelineLayout: UInt,
    val maxSampledTexturesPerShaderStage: UInt,
    val maxSamplersPerShaderStage: UInt,
    val maxStorageBuffersPerShaderStage: UInt,
    val maxStorageTexturesPerShaderStage: UInt,
    val maxUniformBuffersPerShaderStage: UInt,
    val maxUniformBufferBindingSize: ULong,
    val maxStorageBufferBindingSize: ULong,
    val minUniformBufferOffsetAlignment: UInt,
    val minStorageBufferOffsetAlignment: UInt,
    val maxVertexBuffers: UInt,
    val maxBufferSize: ULong,
    val maxVertexAttributes: UInt,
    val maxVertexBufferArrayStride: UInt,
    val maxInterStageShaderVariables: UInt,
    val maxColorAttachments: UInt,
    val maxColorAttachmentBytesPerSample: UInt,
    val maxComputeWorkgroupStorageSize: UInt,
    val maxComputeInvocationsPerWorkgroup: UInt,
    val maxComputeWorkgroupSizeX: UInt,
    val maxComputeWorkgroupSizeY: UInt,
    val maxComputeWorkgroupSizeZ: UInt,
    val maxComputeWorkgroupsPerDimension: UInt,
)