package io.ygdrasil.wgpu


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

    /**
     * The maximum allowed value for the size.width of a texture created with dimension "1d".
     * */
    val maxTextureDimension1D: GPUSize32,
    /**
     * The maximum allowed value for the size.width and size.height of a texture created with
     * dimension "2d".
     */
    val maxTextureDimension2D: GPUSize32,
    /**
     * The maximum allowed value for the size.width, size.height and size.depthOrArrayLayers of a
     * texture created with dimension "3d".
     */
    val maxTextureDimension3D: GPUSize32,
    /**
     * The maximum allowed value for the size.depthOrArrayLayers of a texture created with dimension
     * "2d".
     */
    val maxTextureArrayLayers: GPUSize32,
    /**
     * The maximum number of [BindGroupLayout] allowed in bindGroupLayouts when creating a
     * [PipelineLayout].
     */
    val maxBindGroups: GPUSize32,
    /**
     * The maximum number of bind group and vertex buffer slots used simultaneously, counting any
     * empty slots below the highest index. Validated in createRenderPipeline() and in draw calls.
     */
    val maxBindGroupsPlusVertexBuffers: GPUSize32,

    /**
     * The number of binding indices available when creating a [BindGroupLayout].
     *
     * **Note**: This limit is normative, but arbitrary. With the default binding slot limits, it is
     * impossible to use 1000 bindings in one bind group, but this allows
     * [BindGroupLayoutEntry.binding] values up to 999. This limit allows implementations to treat
     * binding space as an array, within reasonable memory space, rather than a sparse map
     * structure.
     */
    val maxBindingsPerBindGroup: GPUSize32,
    /**
     * The maximum number of [BindGroupLayoutEntry] entries across a [PipelineLayout] which are
     * uniform buffers with dynamic offsets.
     */
    val maxDynamicUniformBuffersPerPipelineLayout: GPUSize32,
    /**
     * The maximum number of [BindGroupLayoutEntry] entries across a [PipelineLayout] which are
     * storage buffers with dynamic offset
     */
    val maxDynamicStorageBuffersPerPipelineLayout: GPUSize32,
    /**
     * For each possible [ShaderStage] stage, the maximum number of [BindGroupLayoutEntry] entries
     * across a [PipelineLayout] which are sampled textures.
     */
    val maxSampledTexturesPerShaderStage: GPUSize32,
    /**
     * For each possible [ShaderStage] stage, the maximum number of [BindGroupLayoutEntry] entries
     * across a [PipelineLayout] which are samplers.
     */
    val maxSamplersPerShaderStage: GPUSize32,
    /**
     * For each possible GPUShaderStage stage, the maximum number of GPUBindGroupLayoutEntry entries
     * across a GPUPipelineLayout which are storage buffers.
     */
    val maxStorageBuffersPerShaderStage: GPUSize32,
    /**
     * For each possible GPUShaderStage stage, the maximum number of GPUBindGroupLayoutEntry entries
     * across a GPUPipelineLayout which are storage textures.
     */
    val maxStorageTexturesPerShaderStage: GPUSize32,
    /**
     * For each possible GPUShaderStage stage, the maximum number of GPUBindGroupLayoutEntry entries
     * across a GPUPipelineLayout which are uniform buffers.
     */
    val maxUniformBuffersPerShaderStage: GPUSize32,
    /**
     * The maximum GPUBufferBinding.size for bindings with a GPUBindGroupLayoutEntry entry for which
     * entry.buffer?.type is "uniform".
     */
    val maxUniformBufferBindingSize: GPUSize64,
    /**
     * The maximum GPUBufferBinding.size for bindings with a GPUBindGroupLayoutEntry entry for which
     * entry.buffer?.type is "storage" or "read-only-storage".
     */
    val maxStorageBufferBindingSize: GPUSize64,
    /**
     * The required alignment for GPUBufferBinding.offset and the dynamic offsets provided in
     * setBindGroup(), for bindings with a GPUBindGroupLayoutEntry entry for which
     * entry.buffer?.type is "uniform".
     */
    val minUniformBufferOffsetAlignment: GPUSize32,
    /**
     * The required alignment for [BufferBinding.offset] and the dynamic offsets provided in
     * setBindGroup(), for bindings with a GPUBindGroupLayoutEntry entry for which
     * entry.buffer?.type is "storage" or "read-only-storage".
     */
    val minStorageBufferOffsetAlignment: GPUSize32,
    /** The maximum number of buffers when creating a GPURenderPipeline. */
    val maxVertexBuffers: GPUSize32,
    /** The maximum size of size when creating a GPUBuffer. */
    val maxBufferSize: GPUSize64,
    /**
     * The maximum number of attributes in total across buffers when creating a GPURenderPipeline.
     */
    val maxVertexAttributes: GPUSize32,
    /** The maximum allowed arrayStride when creating a GPURenderPipeline. */
    val maxVertexBufferArrayStride: GPUSize32,
    /**
     * The maximum allowed number of components of input or output variables for inter-stage
     * communication (like vertex outputs or fragment inputs).
     */
    val maxInterStageShaderComponents: GPUSize32,
    /**
     * The maximum allowed number of input or output variables for inter-stage communication (like
     * vertex outputs or fragment inputs).
     */
    val maxInterStageShaderVariables: GPUSize32,
    /**
     * The maximum allowed number of color attachments in
     * GPURenderPipelineDescriptor.fragment.targets, GPURenderPassDescriptor.colorAttachments, and
     * GPURenderPassLayout.colorFormats.
     */
    val maxColorAttachments: GPUSize32,
    /**
     * The maximum number of bytes necessary to hold one sample (pixel or subpixel) of render
     * pipeline output data, across all color attachments.
     */
    val maxColorAttachmentBytesPerSample: GPUSize32,
    /**
     * The maximum number of bytes of workgroup storage used for a compute stage GPUShaderModule
     * entry-point.
     */
    val maxComputeWorkgroupStorageSize: GPUSize32,
    /**
     * The maximum value of the product of the workgroup_size dimensions for a compute stage
     * GPUShaderModule entry-point.
     */
    val maxComputeInvocationsPerWorkgroup: GPUSize32,
    /**
     * The maximum value of the workgroup_size X dimension for a compute stage GPUShaderModule
     * entry-point.
     */
    val maxComputeWorkgroupSizeX: GPUSize32,
    /**
     * The maximum value of the workgroup_size Y dimensions for a compute stage GPUShaderModule
     * entry-point.
     */
    val maxComputeWorkgroupSizeY: GPUSize32,
    /**
     * The maximum value of the workgroup_size Z dimensions for a compute stage GPUShaderModule
     * entry-point.
     */
    val maxComputeWorkgroupSizeZ: GPUSize32,
    /**
     * The maximum value for the arguments of dispatchWorkgroups(workgroupCountX, workgroupCountY,
     * workgroupCountZ).
     */
    val maxComputeWorkgroupsPerDimension: GPUSize32,
)