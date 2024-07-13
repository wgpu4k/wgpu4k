package io.ygdrasil.wgpu.internal.js

import io.ygdrasil.wgpu.*
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.ArrayBufferView
import org.khronos.webgl.Uint32Array
import org.w3c.dom.HTMLCanvasElement
import kotlin.js.Promise


external interface GPUCanvasContext {
    var canvas: HTMLCanvasElement
    fun configure(configuration: GPUCanvasConfiguration)
    fun unconfigure()
    fun getCurrentTexture(): GPUTexture
}

external object navigator {
    val gpu: GPU?
}

external interface GPU {
    fun requestAdapter(options: GPURequestAdapterOptions = definedExternally): Promise<GPUAdapter?>

    /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    fun getPreferredCanvasFormat(): String
}

external interface GPUAdapter : JsAny {
    var isFallbackAdapter: Boolean
    fun requestDevice(descriptor: GPUDeviceDescriptor = definedExternally): Promise<GPUDevice>
    fun requestAdapterInfo(): Promise<GPUAdapterInfo>
}

external class GPUDevice : JsAny {
    var queue: GPUQueue
    fun createBindGroup(descriptor: GPUBindGroupDescriptor): GPUBindGroup
    fun createTexture(descriptor: GPUTextureDescriptor): GPUTexture
    fun createBuffer(descriptor: GPUBufferDescriptor): GPUBuffer
    fun createRenderPipeline(canvasConfiguration: GPURenderPipelineDescriptor): GPURenderPipeline
    fun createShaderModule(descriptor: GPUShaderModuleDescriptor): GPUShaderModule
    fun createCommandEncoder(descriptor: GPUCommandEncoderDescriptor = definedExternally): GPUCommandEncoder
    fun createSampler(descriptor: GPUSamplerDescriptor = definedExternally): GPUSampler
    fun createComputePipeline(descriptor: GPUComputePipelineDescriptor): GPUComputePipeline
    fun createBindGroupLayout(descriptor: GPUBindGroupLayoutDescriptor): GPUBindGroupLayout
    fun createRenderBundleEncoder(descriptor: GPURenderBundleEncoderDescriptor): GPURenderBundleEncoder
    fun createQuerySet(descriptor: GPUQuerySetDescriptor): GPUQuerySet
}

external interface GPUQuerySetDescriptor : GPUObjectDescriptorBase {
    var type: String
    var count: GPUSize32
}

external interface GPUComputePipelineDescriptor : GPUPipelineDescriptorBase {
    var compute: GPUProgrammableStage
}

external interface GPURenderBundleEncoderDescriptor : GPURenderPassLayout {
    var depthReadOnly: Boolean
    var stencilReadOnly: Boolean
}

external interface GPURenderPassLayout : GPUObjectDescriptorBase {
    var colorFormats: JsArray<JsString>
    var depthStencilFormat: String
    var sampleCount: GPUSize32
}

external interface GPURenderBundleEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin,
    GPUBindingCommandsMixin, GPURenderCommandsMixin {

    fun finish(descriptor: GPURenderBundleDescriptor = definedExternally): GPURenderBundle
}

external interface GPUComputePipeline : GPUObjectBase, GPUPipelineBase

external interface GPUCommandEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin {
    fun beginRenderPass(descriptor: GPURenderPassDescriptor): GPURenderPassEncoder
    fun beginComputePass(descriptor: GPUComputePassDescriptor? = definedExternally): GPUComputePassEncoder
    fun finish(descriptor: GPUCommandBufferDescriptor = definedExternally): GPUCommandBuffer

    fun copyTextureToTexture(
        source: GPUImageCopyTexture,
        destination: GPUImageCopyTexture,
        copySize: JsArray<JsNumber>,
    )

    fun copyTextureToBuffer(
        source: GPUImageCopyTexture,
        destination: GPUImageCopyBuffer,
        copySize: JsArray<JsNumber>,
    )

    fun copyBufferToTexture(
        source: GPUImageCopyBuffer,
        destination: GPUImageCopyTexture,
        copySize: JsArray<JsNumber>,
    )
}

external interface GPUComputePassEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin,
    GPUBindingCommandsMixin {
    fun setPipeline(pipeline: GPUComputePipeline)
    fun dispatchWorkgroups(
        workgroupCountX: GPUSize32,
        workgroupCountY: GPUSize32 = definedExternally,
        workgroupCountZ: GPUSize32 = definedExternally,
    )

    fun dispatchWorkgroupsIndirect(indirectBuffer: GPUBuffer, indirectOffset: JsNumber)
    fun end()
}

external interface GPUComputePassDescriptor : GPUObjectDescriptorBase {
    var timestampWrites: GPUComputePassTimestampWrites
}

external interface GPUComputePassTimestampWrites {
    var querySet: GPUQuerySet
    var beginningOfPassWriteIndex: GPUSize32
    var endOfPassWriteIndex: GPUSize32
}

external interface GPUImageCopyBuffer : GPUImageDataLayout {
    var buffer: GPUBuffer
}

external interface GPURenderPassEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin,
    GPUBindingCommandsMixin, GPURenderCommandsMixin {

    fun executeBundles(bundles: JsArray<GPURenderBundle>)
    fun end()
}

external interface GPUSamplerDescriptor : GPUObjectDescriptorBase {
    var addressModeU: String
    var addressModeV: String
    var addressModeW: String
    var magFilter: String
    var minFilter: String
    var mipmapFilter: String
    var lodMinClamp: JsNumber
    var lodMaxClamp: JsNumber
    var compare: String
    var maxAnisotropy: JsNumber
}

external interface GPUBindGroupDescriptor : GPUObjectDescriptorBase {
    var layout: GPUBindGroupLayout
    var entries: JsArray<GPUBindGroupEntry>
}

external interface GPUBindGroupEntry : JsAny {
    var binding: GPUIndex32
    var resource: JsAny
}

external interface GPUBufferDescriptor : GPUObjectDescriptorBase {
    var size: JsNumber
    var usage: GPUBufferUsageFlags
    var mappedAtCreation: Boolean
}

external interface GPUBuffer : GPUObjectBase {
    var size: JsNumber
    var usage: GPUFlagsConstant
    var mapState: String /* "unmapped" | "pending" | "mapped" */
    fun mapAsync(mode: GPUMapModeFlags, offset: JsNumber, size: JsNumber): Promise<JsAny?>
    fun getMappedRange(offset: JsNumber = definedExternally, size: JsNumber = definedExternally): ArrayBuffer
    fun unmap()
}

external interface GPUTextureDescriptor : GPUObjectDescriptorBase {
    var size: GPUExtent3DDict
    var mipLevelCount: GPUIntegerCoordinate
    var sampleCount: GPUSize32
    var dimension: String
    var format: String
    var usage: GPUTextureUsageFlags
    var viewFormats: JsArray<JsString>
}

external interface GPUExtent3DDict : JsAny {
    var width: GPUIntegerCoordinate
    var height: GPUIntegerCoordinate
    var depthOrArrayLayers: GPUIntegerCoordinate
}

external interface GPURenderBundle : GPUObjectBase
external interface GPUBindGroup : GPUObjectBase

typealias GPUCommandBufferDescriptor = GPUObjectDescriptorBase

external interface GPUCommandBuffer : GPUObjectBase

external interface GPUBindingCommandsMixin {
    fun setBindGroup(index: GPUIndex32, bindGroup: GPUBindGroup?)

    fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsets: JsArray<JsNumber>,
    )

    fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsetsData: Uint32Array,
        dynamicOffsetsDataStart: JsNumber,
        dynamicOffsetsDataLength: GPUSize32,
    )
}

external interface GPURenderCommandsMixin {
    fun setPipeline(pipeline: GPURenderPipeline)

    fun setVertexBuffer(
        slot: GPUIndex32,
        buffer: GPUBuffer?,
        offset: JsNumber = definedExternally,
        size: JsNumber = definedExternally,
    )

    fun setIndexBuffer(
        buffer: GPUBuffer,
        indexFormat: String, /* "uint16" | "uint32" */
        offset: JsNumber = definedExternally,
        size: JsNumber = definedExternally,
    )

    fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32 = definedExternally,
        firstVertex: GPUSize32 = definedExternally,
        firstInstance: GPUSize32 = definedExternally,
    )

    fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32 = definedExternally,
        firstIndex: GPUSize32 = definedExternally,
        baseVertex: GPUSignedOffset32 = definedExternally,
        firstInstance: GPUSize32 = definedExternally,
    )
}

typealias GPURenderBundleDescriptor = GPUObjectDescriptorBase

external interface GPUDebugCommandsMixin {
    fun pushDebugGroup(groupLabel: String)
    fun popDebugGroup()
    fun insertDebugMarker(markerLabel: String)
}

external interface GPURenderPassDescriptor : GPUObjectDescriptorBase {
    var colorAttachments: JsArray<GPURenderPassColorAttachment>
    var depthStencilAttachment: GPURenderPassDepthStencilAttachment
    var occlusionQuerySet: GPUQuerySet
    var timestampWrites: GPURenderPassTimestampWrites
    var maxDrawCount: JsNumber
}

external interface GPURenderPassTimestampWrites {
    var querySet: GPUQuerySet
    var beginningOfPassWriteIndex: GPUSize32
    var endOfPassWriteIndex: GPUSize32
}

external interface GPURenderPassColorAttachment : JsAny {
    var view: GPUTextureView
    var depthSlice: JsNumber
    var resolveTarget: GPUTextureView
    var clearValue: GPUColorDict
    var loadOp: String /* "load" | "clear" */
    var storeOp: String /* "store" | "discard" */
}

external interface GPUColorDict : JsAny {
    var r: Double
    var g: Double
    var b: Double
    var a: Double
}

external interface GPURenderPassDepthStencilAttachment : JsAny {
    var view: GPUTextureView
    var depthClearValue: Float
    var depthLoadOp: String
    var depthStoreOp: String
    var depthReadOnly: Boolean?
    var stencilClearValue: JsNumber
    var stencilLoadOp: String
    var stencilStoreOp: String
    var stencilReadOnly: Boolean?

}

external interface GPUQuerySet : GPUObjectBase {
    fun destroy()
    var type: String /* "occlusion" | "timestamp" */
    var count: GPUSize32Out
}

external interface GPUTextureView : GPUObjectBase

external interface GPUQueue : GPUObjectBase {
    fun submit(commandBuffers: JsArray<GPUCommandBuffer>)
    fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: JsNumber,
        data: ArrayBufferView,
        dataOffset: JsNumber = definedExternally,
        size: JsNumber = definedExternally,
    )

    fun writeTexture(
        destination: GPUImageCopyTexture,
        data: ArrayBuffer,
        dataLayout: GPUImageDataLayout,
        size: GPUExtent3DDict,
    )

    fun onSubmittedWorkDone(): Promise<Nothing?>
}

external interface GPUImageDataLayout : JsAny {
    var offset: JsNumber
    var bytesPerRow: GPUSize32
    var rowsPerImage: GPUSize32
}

external interface GPUImageCopyTexture : JsAny {
    var texture: GPUTexture
    var mipLevel: GPUIntegerCoordinate
    var origin: JsArray<JsNumber>
    var aspect: String
}

external interface GPUBindGroupLayoutDescriptor : GPUObjectDescriptorBase {
    var entries: JsArray<GPUBindGroupLayoutEntry>
}

external interface GPUBindGroupLayoutEntry : JsAny {
    var binding: GPUIndex32
    var visibility: GPUShaderStageFlags
    var buffer: GPUBufferBindingLayout
    var sampler: GPUSamplerBindingLayout
    var texture: GPUTextureBindingLayout
    var storageTexture: GPUStorageTextureBindingLayout
    var externalTexture: GPUExternalTextureBindingLayout
}

external interface GPUExternalTextureBindingLayout

external interface GPUStorageTextureBindingLayout : JsAny {
    var access: String
    var format: String
    var viewDimension: String
}

external interface GPUTextureBindingLayout : JsAny {
    var sampleType: String
    var viewDimension: String
    var multisampled: Boolean
}

external interface GPUSamplerBindingLayout : JsAny {
    var type: String
}

external interface GPUBufferBindingLayout : JsAny {
    var type: String
    var hasDynamicOffset: Boolean
    var minBindingSize: JsNumber
}

external interface GPUCommandsMixin

external interface GPURenderPipelineDescriptor : GPUPipelineDescriptorBase {
    var vertex: GPUVertexState
    var primitive: GPUPrimitiveState
    var depthStencil: GPUDepthStencilState
    var multisample: GPUMultisampleState
    var fragment: GPUFragmentState
}

external interface GPUTextureViewDescriptor : GPUObjectDescriptorBase {
    var format: String
    var dimension: String
    var aspect: String
    var baseMipLevel: GPUIntegerCoordinate?
    var mipLevelCount: GPUIntegerCoordinate?
    var baseArrayLayer: GPUIntegerCoordinate?
    var arrayLayerCount: GPUIntegerCoordinate?
}

external interface GPUFragmentState : GPUProgrammableStage {
    var targets: JsArray<GPUColorTargetState>
}

external interface GPUColorTargetState : JsAny {
    var format: JsString
    var blend: GPUBlendState
    var writeMask: GPUColorWriteFlags
}

external interface GPUBlendState : JsAny {
    var color: GPUBlendComponent
    var alpha: GPUBlendComponent
}

external interface GPUBlendComponent : JsAny {
    var operation: JsString
    var srcFactor: JsString
    var dstFactor: JsString
}

external interface GPUMultisampleState : JsAny {
    var count: JsNumber
    var mask: JsNumber
    var alphaToCoverageEnabled: Boolean
}

external interface GPUDepthStencilState : JsAny {
    var format: JsString
    var depthWriteEnabled: Boolean
    var depthCompare: JsString
    var stencilFront: GPUStencilFaceState
    var stencilBack: GPUStencilFaceState
    var stencilReadMask: JsNumber
    var stencilWriteMask: JsNumber
    var depthBias: JsNumber
    var depthBiasSlopeScale: Float
    var depthBiasClamp: Float

}

external interface GPUStencilFaceState : JsAny {
    var compare: JsString
    var failOp: JsString
    var depthFailOp: JsString
    var passOp: JsString
}

external interface GPUShaderModuleDescriptor : GPUObjectDescriptorBase {
    var code: JsString
    var sourceMap: JsAny
    var compilationHints: JsArray<GPUShaderModuleCompilationHint>
}

external interface GPUShaderModuleCompilationHint : JsAny {
    var entryPoint: String
    var layout: JsAny /* GPUPipelineLayout? | "auto" */
}

external interface GPUPrimitiveState : JsAny {
    var topology: String
    var stripIndexFormat: String
    var frontFace: String
    var cullMode: String
    var unclippedDepth: Boolean
}

external interface GPUVertexState : GPUProgrammableStage {
    var buffers: JsArray<GPUVertexBufferLayout>
}

external interface GPUVertexBufferLayout : JsAny {
    var arrayStride: JsNumber
    var stepMode: String /* "vertex" | "instance" */
    var attributes: JsArray<GPUVertexAttribute>
}

external interface GPUVertexAttribute : JsAny {
    var format: String
    var offset: JsNumber
    var shaderLocation: JsNumber
}

external interface GPUProgrammableStage : JsAny {
    var module: GPUShaderModule
    var entryPoint: JsString
    var constants: JsAny //Map<JsString, JsNumber>?
}

typealias GPUCommandEncoderDescriptor = GPUObjectDescriptorBase

external interface GPURenderPipeline : GPUObjectBase, GPUPipelineBase

external interface GPUPipelineBase {
    fun getBindGroupLayout(index: JsNumber): GPUBindGroupLayout
}

external interface GPUBindGroupLayout : GPUObjectBase

external interface GPUShaderModule : GPUObjectBase {
    fun getCompilationInfo(): Promise<GPUCompilationInfo>
}

external interface GPUCompilationInfo : JsAny {
    var messages: JsArray<GPUCompilationMessage>
}

external interface GPUCompilationMessage : JsAny {
    var message: String
    var type: String
    var lineNum: JsNumber
    var linePos: JsNumber
    var offset: JsNumber
    var length: JsNumber
}

external interface GPUBufferBinding : JsAny {
    var buffer: GPUBuffer
    var offset: JsNumber
    var size: JsNumber
}

external interface GPUPipelineDescriptorBase : GPUObjectDescriptorBase {
    var layout: JsAny /* GPUPipelineLayout | "auto" */
}

external interface GPUObjectDescriptorBase : JsAny {
    var label: JsString?
}

external interface GPUObjectBase : JsAny {
    var label: JsString
}

external interface GPUDeviceDescriptor

external interface GPUAdapterInfo : JsAny

external interface GPUCanvasConfiguration : JsAny {
    var device: GPUDevice
    var format: JsString
    var usage: JsNumber
    var viewFormats: JsArray<JsAny>
    var colorSpace: JsString
    var alphaMode: JsString
}

external interface GPUPipelineLayout : GPUObjectBase

external interface GPUTexture {
    fun createView(descriptor: GPUTextureViewDescriptor = definedExternally): GPUTextureView

    var width: GPUIntegerCoordinateOut
    var height: GPUIntegerCoordinateOut
    var depthOrArrayLayers: GPUIntegerCoordinateOut
    var mipLevelCount: GPUIntegerCoordinateOut
    var sampleCount: GPUSize32Out
    var dimension: String
    var format: String
    var usage: GPUFlagsConstant
}

external interface GPURequestAdapterOptions

external interface GPUSampler : GPUObjectBase