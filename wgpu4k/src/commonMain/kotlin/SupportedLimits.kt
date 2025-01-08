package io.ygdrasil.webgpu


/**
 * The GPUSupportedLimits interface of the WebGPU API describes the limits supported by a [Adapter].
 *
 * The GPUSupportedLimits object for the current adapter is accessed via the [Adapter.limits] property.
 *
 * You should note that, rather than reporting the exact limits of each GPU, browsers will likely report different
 * tier values of different limits to reduce the unique information available to drive-by fingerprinting. For example,
 * the tiers of a certain limit might be 2048, 8192, and 32768. If your GPU's actual limit is 16384, the browser will
 * still report 8192.
 *
 * Given that different browsers will handle this differently and the tier values may change over time, it is hard to
 * provide an accurate account of what limit values to expect — thorough testing is advised.
 *
 * Instance properties
 * The following limits are represented by properties in a GPUSupportedLimits object. See the [Limits](https://gpuweb.github.io/gpuweb/#limits) section of the
 * specification for detailed descriptions of what the limits relate to.
 */
data class SupportedLimits(
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