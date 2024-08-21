package io.ygdrasil.wgpu.internal.js

import io.ygdrasil.wgpu.*
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.ArrayBufferView
import org.khronos.webgl.Uint32Array
import org.w3c.dom.EventInit
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import kotlin.js.Promise

fun <T> createJsObject(): T =
    js("({ })")

external interface GPUBindGroupDescriptor : GPUObjectDescriptorBase {
    var layout: GPUBindGroupLayout
    var entries: Array<GPUBindGroupEntry>
}

external interface GPUBindGroupEntry {
    var binding: GPUIndex32
    var resource: Any /* GPUSampler | GPUTextureView | GPUBufferBinding | GPUExternalTexture */
}

external interface GPUBindGroupLayoutDescriptor : GPUObjectDescriptorBase {
    var entries: Array<GPUBindGroupLayoutEntry>
}

external interface GPUBindGroupLayoutEntry {
    var binding: GPUIndex32
    var visibility: GPUShaderStageFlags
    var buffer: GPUBufferBindingLayout?
    var sampler: GPUSamplerBindingLayout?
    var texture: GPUTextureBindingLayout?
    var storageTexture: GPUStorageTextureBindingLayout?
    var externalTexture: GPUExternalTextureBindingLayout?
}

external interface GPUBlendComponent {
    var operation: String? /* "add" | "subtract" | "reverse-subtract" | "min" | "max" */
    var srcFactor: String? /* "zero" | "one" | "src" | "one-minus-src" | "src-alpha" | "one-minus-src-alpha" | "dst" | "one-minus-dst" | "dst-alpha" | "one-minus-dst-alpha" | "src-alpha-saturated" | "constant" | "one-minus-constant" */
    var dstFactor: String? /* "zero" | "one" | "src" | "one-minus-src" | "src-alpha" | "one-minus-src-alpha" | "dst" | "one-minus-dst" | "dst-alpha" | "one-minus-dst-alpha" | "src-alpha-saturated" | "constant" | "one-minus-constant" */
}

external interface GPUBlendState {
    var color: GPUBlendComponent
    var alpha: GPUBlendComponent
}

external interface GPUBufferBinding {
    var buffer: GPUBuffer
    var offset: GPUSize64?
    var size: GPUSize64?
}

external interface GPUBufferBindingLayout {
    var type: String? /* "uniform" | "storage" | "read-only-storage" */
    var hasDynamicOffset: Boolean?
    var minBindingSize: GPUSize64?
}

external interface GPUBufferDescriptor : GPUObjectDescriptorBase {
    var size: GPUSize64
    var usage: GPUBufferUsageFlags
    var mappedAtCreation: Boolean?
}

external interface GPUCanvasConfiguration {
    var device: GPUDevice
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var usage: GPUTextureUsageFlags?
    var viewFormats: Array<String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */>?
    var colorSpace: Any?
    var alphaMode: String? /* "opaque" | "premultiplied" */
}

external interface GPUColorDict {
    var r: Number
    var g: Number
    var b: Number
    var a: Number
}

external interface GPUColorTargetState {
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var blend: GPUBlendState?
    var writeMask: GPUColorWriteFlags?
}

typealias GPUCommandBufferDescriptor = GPUObjectDescriptorBase

typealias GPUCommandEncoderDescriptor = GPUObjectDescriptorBase

external interface GPUComputePassDescriptor : GPUObjectDescriptorBase {
    var timestampWrites: GPUComputePassTimestampWrites?
}

external interface GPUComputePassTimestampWrites {
    var querySet: GPUQuerySet
    var beginningOfPassWriteIndex: GPUSize32?
    var endOfPassWriteIndex: GPUSize32?
}

external interface GPUComputePipelineDescriptor : GPUPipelineDescriptorBase {
    var compute: GPUProgrammableStage
}

external interface GPUDepthStencilState {
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var depthWriteEnabled: Boolean?
    var depthCompare: String? /* "never" | "less" | "equal" | "less-equal" | "greater" | "not-equal" | "greater-equal" | "always" */
    var stencilFront: GPUStencilFaceState?
    var stencilBack: GPUStencilFaceState?
    var stencilReadMask: GPUStencilValue?
    var stencilWriteMask: GPUStencilValue?
    var depthBias: GPUDepthBias?
    var depthBiasSlopeScale: Float?
    var depthBiasClamp: Float?

}

external interface GPUDeviceDescriptor : GPUObjectDescriptorBase {
    var requiredFeatures: Array<String? /* "depth-clip-control" | "depth32float-stencil8" | "texture-compression-bc" | "texture-compression-etc2" | "texture-compression-astc" | "timestamp-query" | "indirect-first-instance" | "shader-f16" | "rg11b10ufloat-renderable" | "bgra8unorm-storage" | "float32-filterable" */>?
    var requiredLimits: dynamic //Record<String, GPUSize64>?
    var defaultQueue: GPUQueueDescriptor?
}

external interface GPUExtent3DDict {
    var width: GPUIntegerCoordinate
    var height: GPUIntegerCoordinate?
    var depthOrArrayLayers: GPUIntegerCoordinate?
}

external interface GPUExternalTextureBindingLayout

external interface GPUExternalTextureDescriptor : GPUObjectDescriptorBase {
    var source: dynamic /* HTMLVideoElement | VideoFrame */
    var colorSpace: Any?
}

external interface GPUFragmentState : GPUProgrammableStage {
    var targets: Array<GPUColorTargetState?>
}

external interface GPUImageCopyBuffer : GPUImageDataLayout {
    var buffer: GPUBuffer
}

external interface GPUImageCopyExternalImage {
    var source: Any

    /* ImageBitmap | ImageData | HTMLImageElement | HTMLVideoElement | VideoFrame | HTMLCanvasElement | OffscreenCanvas */
    var origin: GPUOrigin2DDict
    var flipY: Boolean
}

external interface GPUImageCopyTexture {
    var texture: GPUTexture
    var mipLevel: GPUIntegerCoordinate
    var origin: Array<GPUIntegerCoordinate>
    var aspect: String
}

external interface GPUImageCopyTextureTagged : GPUImageCopyTexture {
    var colorSpace: String
    var premultipliedAlpha: Boolean
}

external interface GPUImageDataLayout {
    var offset: GPUSize64?
    var bytesPerRow: GPUSize32?
    var rowsPerImage: GPUSize32?
}

external interface GPUMultisampleState {
    var count: GPUSize32?

    @Suppress("INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING")
    var mask: dynamic
    var alphaToCoverageEnabled: Boolean?
}

external interface GPUObjectDescriptorBase {
    var label: String?
}

external interface GPUOrigin2DDict {
    var x: GPUIntegerCoordinate
    var y: GPUIntegerCoordinate
}

external interface GPUOrigin3DDict {
    var x: GPUIntegerCoordinate?
    var y: GPUIntegerCoordinate?
    var z: GPUIntegerCoordinate?
}

external interface GPUPipelineDescriptorBase : GPUObjectDescriptorBase {
    var layout: dynamic /* GPUPipelineLayout | "auto" */
}

external interface GPUPipelineErrorInit {
    var reason: String /* "validation" | "internal" */
}

external interface GPUPipelineLayoutDescriptor : GPUObjectDescriptorBase {
    var bindGroupLayouts: Array<GPUBindGroupLayout>
}

external interface GPUPrimitiveState {
    var topology: String? /* "point-list" | "line-list" | "line-strip" | "triangle-list" | "triangle-strip" */
    var stripIndexFormat: String? /* "uint16" | "uint32" */
    var frontFace: String? /* "ccw" | "cw" */
    var cullMode: String? /* "none" | "front" | "back" */
    var unclippedDepth: Boolean?
}

external interface GPUProgrammableStage {
    var module: GPUShaderModule
    var entryPoint: String?
    var constants: Map<String, GPUPipelineConstantValue>?
}

external interface GPUQuerySetDescriptor : GPUObjectDescriptorBase {
    var type: String /* "occlusion" | "timestamp" */
    var count: GPUSize32
}

typealias GPUQueueDescriptor = GPUObjectDescriptorBase

typealias GPURenderBundleDescriptor = GPUObjectDescriptorBase

external interface GPURenderBundleEncoderDescriptor : GPURenderPassLayout {
    var depthReadOnly: Boolean?
    var stencilReadOnly: Boolean?
}

external interface GPURenderPassColorAttachment {
    var view: GPUTextureView
    var depthSlice: GPUIntegerCoordinate?
    var resolveTarget: GPUTextureView?
    var clearValue: GPUColorDict?
    var loadOp: String /* "load" | "clear" */
    var storeOp: String /* "store" | "discard" */
}

external interface GPURenderPassDepthStencilAttachment {
    var view: GPUTextureView
    var depthClearValue: Number?
    var depthLoadOp: String? /* "load" | "clear" */
    var depthStoreOp: String? /* "store" | "discard" */
    var depthReadOnly: Boolean?
    var stencilClearValue: GPUStencilValue?
    var stencilLoadOp: String? /* "load" | "clear" */
    var stencilStoreOp: String? /* "store" | "discard" */
    var stencilReadOnly: Boolean?

}

external interface GPURenderPassDescriptor : GPUObjectDescriptorBase {
    var colorAttachments: Array<GPURenderPassColorAttachment>
    var depthStencilAttachment: GPURenderPassDepthStencilAttachment?
    var occlusionQuerySet: GPUQuerySet?
    var timestampWrites: GPURenderPassTimestampWrites?
    var maxDrawCount: GPUSize64?
}

external interface GPURenderPassLayout : GPUObjectDescriptorBase {
    var colorFormats: Array<String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */>
    var depthStencilFormat: String? /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var sampleCount: GPUSize32?
}

external interface GPURenderPassTimestampWrites {
    var querySet: GPUQuerySet
    var beginningOfPassWriteIndex: GPUSize32?
    var endOfPassWriteIndex: GPUSize32?
}

external interface GPURenderPipelineDescriptor : GPUPipelineDescriptorBase {
    var vertex: GPUVertexState
    var primitive: GPUPrimitiveState?
    var depthStencil: GPUDepthStencilState?
    var multisample: GPUMultisampleState?
    var fragment: GPUFragmentState?
}

external interface GPURequestAdapterOptions {
    var powerPreference: String? /* "low-power" | "high-performance" */
    var forceFallbackAdapter: Boolean?
}

external interface GPUSamplerBindingLayout {
    var type: String? /* "filtering" | "non-filtering" | "comparison" */
}

external interface GPUSamplerDescriptor : GPUObjectDescriptorBase {
    var addressModeU: String? /* "clamp-to-edge" | "repeat" | "mirror-repeat" */
    var addressModeV: String? /* "clamp-to-edge" | "repeat" | "mirror-repeat" */
    var addressModeW: String? /* "clamp-to-edge" | "repeat" | "mirror-repeat" */
    var magFilter: String? /* "nearest" | "linear" */
    var minFilter: String? /* "nearest" | "linear" */
    var mipmapFilter: String? /* "nearest" | "linear" */
    var lodMinClamp: Number?
    var lodMaxClamp: Number?
    var compare: String? /* "never" | "less" | "equal" | "less-equal" | "greater" | "not-equal" | "greater-equal" | "always" */
    var maxAnisotropy: Number?
}

external interface GPUShaderModuleCompilationHint {
    var entryPoint: String
    var layout: dynamic /* GPUPipelineLayout? | "auto" */
}

external interface GPUShaderModuleDescriptor : GPUObjectDescriptorBase {
    var code: String
    var sourceMap: Any?
    var compilationHints: Array<GPUShaderModuleCompilationHint>?
}

external interface GPUStencilFaceState {
    var compare: String? /* "never" | "less" | "equal" | "less-equal" | "greater" | "not-equal" | "greater-equal" | "always" */
    var failOp: String? /* "keep" | "zero" | "replace" | "invert" | "increment-clamp" | "decrement-clamp" | "increment-wrap" | "decrement-wrap" */
    var depthFailOp: String? /* "keep" | "zero" | "replace" | "invert" | "increment-clamp" | "decrement-clamp" | "increment-wrap" | "decrement-wrap" */
    var passOp: String? /* "keep" | "zero" | "replace" | "invert" | "increment-clamp" | "decrement-clamp" | "increment-wrap" | "decrement-wrap" */
}

external interface GPUStorageTextureBindingLayout {
    var access: String? /* "write-only" | "read-only" | "read-write" */
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var viewDimension: String? /* "1d" | "2d" | "2d-array" | "cube" | "cube-array" | "3d" */
}

external interface GPUTextureBindingLayout {
    var sampleType: String? /* "float" | "unfilterable-float" | "depth" | "sint" | "uint" */
    var viewDimension: String? /* "1d" | "2d" | "2d-array" | "cube" | "cube-array" | "3d" */
    var multisampled: Boolean?
}

external interface GPUTextureDescriptor : GPUObjectDescriptorBase {
    var size: GPUExtent3DDict
    var mipLevelCount: GPUIntegerCoordinate?
    var sampleCount: GPUSize32?
    var dimension: String? /* "1d" | "2d" | "3d" */
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var usage: GPUTextureUsageFlags
    var viewFormats: Array<String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */>
}

external interface GPUTextureViewDescriptor : GPUObjectDescriptorBase {
    var format: String? /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var dimension: String? /* "1d" | "2d" | "2d-array" | "cube" | "cube-array" | "3d" */
    var aspect: String? /* "all" | "stencil-only" | "depth-only" */
    var baseMipLevel: GPUIntegerCoordinate?
    var mipLevelCount: GPUIntegerCoordinate?
    var baseArrayLayer: GPUIntegerCoordinate?
    var arrayLayerCount: GPUIntegerCoordinate?
}

external interface GPUUncapturedErrorEventInit : EventInit {
    var error: GPUError
}

external interface GPUVertexAttribute {
    var format: String

    /* "uint8x2" | "uint8x4" | "sint8x2" | "sint8x4" | "unorm8x2" | "unorm8x4" | "snorm8x2" | "snorm8x4" | "uint16x2" | "uint16x4" | "sint16x2" | "sint16x4" | "unorm16x2" | "unorm16x4" | "snorm16x2" | "snorm16x4" | "float16x2" | "float16x4" | "float32" | "float32x2" | "float32x3" | "float32x4" | "uint32" | "uint32x2" | "uint32x3" | "uint32x4" | "sint32" | "sint32x2" | "sint32x3" | "sint32x4" | "unorm10-10-10-2" */
    var offset: GPUSize64
    var shaderLocation: GPUIndex32
}

external interface GPUVertexBufferLayout {
    var arrayStride: GPUSize64
    var stepMode: String? /* "vertex" | "instance" */
    var attributes: Array<GPUVertexAttribute>
}

external interface GPUVertexState : GPUProgrammableStage {
    var buffers: Array<GPUVertexBufferLayout?>?
}

external interface GPUBindingCommandsMixin {
    fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsets: Array<GPUBufferDynamicOffset> = definedExternally,
    )

    fun setBindGroup(index: GPUIndex32, bindGroup: GPUBindGroup?)
    fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsetsData: Uint32Array,
        dynamicOffsetsDataStart: GPUSize64,
        dynamicOffsetsDataLength: GPUSize32,
    )
}

external interface GPUCommandsMixin

external interface GPUDebugCommandsMixin {
    fun pushDebugGroup(groupLabel: String)
    fun popDebugGroup()
    fun insertDebugMarker(markerLabel: String)
}

external interface GPUObjectBase {
    var label: String
}

external interface GPUPipelineBase {
    fun getBindGroupLayout(index: Number): GPUBindGroupLayout
}

external interface GPURenderCommandsMixin {
    fun setPipeline(pipeline: GPURenderPipeline)
    fun setIndexBuffer(
        buffer: GPUBuffer,
        indexFormat: String, /* "uint16" | "uint32" */
        offset: GPUSize64 = definedExternally,
        size: GPUSize64 = definedExternally,
    )

    fun setVertexBuffer(
        slot: GPUIndex32,
        buffer: GPUBuffer?,
        offset: GPUSize64 = definedExternally,
        size: GPUSize64 = definedExternally,
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

    fun drawIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64)
    fun drawIndexedIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64)
}

external interface NavigatorGPU {
    var gpu: GPU
}

external interface GPU {
    fun requestAdapter(options: GPURequestAdapterOptions = definedExternally): Promise<GPUAdapter?>
    fun getPreferredCanvasFormat(): String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var wgslLanguageFeatures: WGSLLanguageFeatures

}

external interface GPUAdapter {
    var features: GPUSupportedFeatures
    var limits: GPUSupportedLimits
    var isFallbackAdapter: Boolean
    fun requestDevice(descriptor: GPUDeviceDescriptor = definedExternally): Promise<GPUDevice>
    fun requestAdapterInfo(): Promise<GPUAdapterInfo>
}

external interface GPUAdapterInfo {
    var vendor: String
    var architecture: String
    var device: String
    var description: String
}

external interface GPUBindGroup : GPUObjectBase
external interface GPUBindGroupLayout : GPUObjectBase

external interface GPUBuffer : GPUObjectBase {
    var size: GPUSize64Out
    var usage: GPUFlagsConstant
    var mapState: String /* "unmapped" | "pending" | "mapped" */
    fun mapAsync(mode: GPUMapModeFlags, offset: GPUSize64, size: GPUSize64): Promise<Any?>
    fun getMappedRange(offset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally): ArrayBuffer
    fun unmap()
    fun destroy()
}


external interface GPUCanvasContext {
    var canvas: dynamic /* HTMLCanvasElement | OffscreenCanvas */
    fun configure(configuration: GPUCanvasConfiguration)
    fun unconfigure()
    fun getCurrentTexture(): GPUTexture
}

external interface GPUCommandBuffer : GPUObjectBase

external interface GPUCommandEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin {
    fun beginRenderPass(descriptor: GPURenderPassDescriptor): GPURenderPassEncoder
    fun beginComputePass(descriptor: GPUComputePassDescriptor? = definedExternally): GPUComputePassEncoder
    fun copyBufferToBuffer(
        source: GPUBuffer,
        sourceOffset: GPUSize64,
        destination: GPUBuffer,
        destinationOffset: GPUSize64,
        size: GPUSize64,
    )

    fun copyBufferToTexture(
        source: GPUImageCopyBuffer,
        destination: GPUImageCopyTexture,
        copySize: Array<GPUIntegerCoordinate>,
    )

    fun copyBufferToTexture(
        source: GPUImageCopyBuffer,
        destination: GPUImageCopyTexture,
        copySize: GPUExtent3DDict,
    )

    fun copyTextureToBuffer(
        source: GPUImageCopyTexture,
        destination: GPUImageCopyBuffer,
        copySize: Array<GPUIntegerCoordinate>,
    )

    fun copyTextureToBuffer(
        source: GPUImageCopyTexture,
        destination: GPUImageCopyBuffer,
        copySize: GPUExtent3DDict,
    )

    fun copyTextureToTexture(
        source: GPUImageCopyTexture,
        destination: GPUImageCopyTexture,
        copySize: Array<GPUIntegerCoordinate>,
    )

    fun copyTextureToTexture(
        source: GPUImageCopyTexture,
        destination: GPUImageCopyTexture,
        copySize: GPUExtent3DDict,
    )

    fun clearBuffer(buffer: GPUBuffer, offset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally)
    fun resolveQuerySet(
        querySet: GPUQuerySet,
        firstQuery: GPUSize32,
        queryCount: GPUSize32,
        destination: GPUBuffer,
        destinationOffset: GPUSize64,
    )

    fun finish(descriptor: GPUCommandBufferDescriptor = definedExternally): GPUCommandBuffer

}

external interface GPUCompilationInfo {
    var messages: Array<GPUCompilationMessage>
}


external interface GPUCompilationMessage {
    var message: String
    var type: String /* "error" | "warning" | "info" */
    var lineNum: Number
    var linePos: Number
    var offset: Number
    var length: Number
}


external interface GPUComputePassEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin,
    GPUBindingCommandsMixin {
    fun setPipeline(pipeline: GPUComputePipeline)
    fun dispatchWorkgroups(
        workgroupCountX: GPUSize32,
        workgroupCountY: GPUSize32 = definedExternally,
        workgroupCountZ: GPUSize32 = definedExternally,
    )

    fun dispatchWorkgroupsIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64)
    fun end()

}


external interface GPUComputePipeline : GPUObjectBase, GPUPipelineBase

open external class GPUDevice : EventTarget, GPUObjectBase {
    override var label: String
    var features: GPUSupportedFeatures
    var limits: GPUSupportedLimits
    var queue: GPUQueue
    fun destroy()
    fun createBuffer(descriptor: GPUBufferDescriptor): GPUBuffer
    fun createTexture(descriptor: GPUTextureDescriptor): GPUTexture
    fun createSampler(descriptor: GPUSamplerDescriptor = definedExternally): GPUSampler
    fun importExternalTexture(descriptor: GPUExternalTextureDescriptor): GPUExternalTexture
    fun createBindGroupLayout(descriptor: GPUBindGroupLayoutDescriptor): GPUBindGroupLayout
    fun createPipelineLayout(descriptor: GPUPipelineLayoutDescriptor): GPUPipelineLayout
    fun createBindGroup(descriptor: GPUBindGroupDescriptor): GPUBindGroup
    fun createShaderModule(descriptor: GPUShaderModuleDescriptor): GPUShaderModule
    fun createComputePipeline(descriptor: GPUComputePipelineDescriptor): GPUComputePipeline
    fun createRenderPipeline(descriptor: GPURenderPipelineDescriptor): GPURenderPipeline
    fun createComputePipelineAsync(descriptor: GPUComputePipelineDescriptor): Promise<GPUComputePipeline>
    fun createRenderPipelineAsync(descriptor: GPURenderPipelineDescriptor): Promise<GPURenderPipeline>
    fun createCommandEncoder(descriptor: GPUCommandEncoderDescriptor = definedExternally): GPUCommandEncoder
    fun createRenderBundleEncoder(descriptor: GPURenderBundleEncoderDescriptor): GPURenderBundleEncoder
    fun createQuerySet(descriptor: GPUQuerySetDescriptor): GPUQuerySet
    var lost: Promise<GPUDeviceLostInfo>
    fun pushErrorScope(filter: String /* "validation" | "out-of-memory" | "internal" */)
    fun popErrorScope(): Promise<GPUError?>
    var onuncapturederror: ((self: GPUDevice, ev: GPUUncapturedErrorEvent) -> Any)?

}

external interface GPUDeviceLostInfo {
    var __brand: String /* "GPUDeviceLostInfo" */
    var reason: String /* "unknown" | "destroyed" */
    var message: String
}

external interface GPUError {
    var message: String
}

external interface GPUExternalTexture : GPUObjectBase
external interface GPUInternalError : GPUError
external interface GPUOutOfMemoryError : GPUError

open external class GPUPipelineError {
    var reason: String /* "validation" | "internal" */
}

external interface GPUPipelineLayout : GPUObjectBase

external interface GPUQuerySet : GPUObjectBase {
    fun destroy()
    var type: String /* "occlusion" | "timestamp" */
    var count: GPUSize32Out
}

external interface GPUQueue : GPUObjectBase {

    fun submit(commandBuffers: Array<GPUCommandBuffer>)
    fun onSubmittedWorkDone(): Promise<Nothing?>
    fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: GPUSize64,
        data: ArrayBufferView,
        dataOffset: GPUSize64 = definedExternally,
        size: GPUSize64 = definedExternally,
    )

    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: ArrayBufferView)
    fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: GPUSize64,
        data: ArrayBufferView,
        dataOffset: GPUSize64 = definedExternally,
    )

    fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: GPUSize64,
        data: ArrayBuffer,
        dataOffset: GPUSize64 = definedExternally,
        size: GPUSize64 = definedExternally,
    )

    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: ArrayBuffer)

    /*fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: ArrayBuffer, dataOffset: GPUSize64 = definedExternally)
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: SharedArrayBuffer, dataOffset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally)
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: SharedArrayBuffer)
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: SharedArrayBuffer, dataOffset: GPUSize64 = definedExternally)*/
    fun writeTexture(
        destination: GPUImageCopyTexture,
        data: ArrayBufferView,
        dataLayout: GPUImageDataLayout,
        size: Array<GPUIntegerCoordinate>,
    )

    fun writeTexture(
        destination: GPUImageCopyTexture,
        data: ArrayBufferView,
        dataLayout: GPUImageDataLayout,
        size: GPUExtent3DDict,
    )

    fun writeTexture(
        destination: GPUImageCopyTexture,
        data: ArrayBuffer,
        dataLayout: GPUImageDataLayout,
        size: Array<GPUIntegerCoordinate>,
    )

    fun writeTexture(
        destination: GPUImageCopyTexture,
        data: ArrayBuffer,
        dataLayout: GPUImageDataLayout,
        size: GPUExtent3DDict,
    )

    /*fun writeTexture(destination: GPUImageCopyTexture, data: SharedArrayBuffer, dataLayout: GPUImageDataLayout, size: Array<GPUIntegerCoordinate>)
    fun writeTexture(destination: GPUImageCopyTexture, data: SharedArrayBuffer, dataLayout: GPUImageDataLayout, size: GPUExtent3DDictStrict)*/
    fun copyExternalImageToTexture(
        source: GPUImageCopyExternalImage,
        destination: GPUImageCopyTextureTagged,
        copySize: Array<GPUIntegerCoordinate>,
    )

    fun copyExternalImageToTexture(
        source: GPUImageCopyExternalImage,
        destination: GPUImageCopyTextureTagged,
        copySize: GPUExtent3DDict,
    )

}


external interface GPURenderBundle : GPUObjectBase

external interface GPURenderBundleEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin,
    GPUBindingCommandsMixin, GPURenderCommandsMixin {
    fun finish(descriptor: GPURenderBundleDescriptor = definedExternally): GPURenderBundle
}

external interface GPURenderPassEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin,
    GPUBindingCommandsMixin, GPURenderCommandsMixin {
    fun setViewport(x: Number, y: Number, width: Number, height: Number, minDepth: Number, maxDepth: Number)
    fun setScissorRect(
        x: GPUIntegerCoordinate,
        y: GPUIntegerCoordinate,
        width: GPUIntegerCoordinate,
        height: GPUIntegerCoordinate,
    )

    fun setBlendConstant(color: Array<Number>)
    fun setBlendConstant(color: GPUColorDict)
    fun setStencilReference(reference: GPUStencilValue)
    fun beginOcclusionQuery(queryIndex: GPUSize32)
    fun endOcclusionQuery()
    fun executeBundles(bundles: Array<GPURenderBundle>)
    fun end()
}

external interface GPURenderPipeline : GPUObjectBase, GPUPipelineBase
external interface GPUSampler : GPUObjectBase

external interface GPUShaderModule : GPUObjectBase {
    fun getCompilationInfo(): Promise<GPUCompilationInfo>

}

typealias GPUSupportedFeatures = Any//ReadonlySet<String>


external interface GPUSupportedLimits {
    var maxTextureDimension1D: Number
    var maxTextureDimension2D: Number
    var maxTextureDimension3D: Number
    var maxTextureArrayLayers: Number
    var maxBindGroups: Number
    var maxBindGroupsPlusVertexBuffers: Number
    var maxBindingsPerBindGroup: Number
    var maxDynamicUniformBuffersPerPipelineLayout: Number
    var maxDynamicStorageBuffersPerPipelineLayout: Number
    var maxSampledTexturesPerShaderStage: Number
    var maxSamplersPerShaderStage: Number
    var maxStorageBuffersPerShaderStage: Number
    var maxStorageTexturesPerShaderStage: Number
    var maxUniformBuffersPerShaderStage: Number
    var maxUniformBufferBindingSize: Number
    var maxStorageBufferBindingSize: Number
    var minUniformBufferOffsetAlignment: Number
    var minStorageBufferOffsetAlignment: Number
    var maxVertexBuffers: Number
    var maxBufferSize: Number
    var maxVertexAttributes: Number
    var maxVertexBufferArrayStride: Number
    var maxInterStageShaderComponents: Number
    var maxInterStageShaderVariables: Number
    var maxColorAttachments: Number
    var maxColorAttachmentBytesPerSample: Number
    var maxComputeWorkgroupStorageSize: Number
    var maxComputeInvocationsPerWorkgroup: Number
    var maxComputeWorkgroupSizeX: Number
    var maxComputeWorkgroupSizeY: Number
    var maxComputeWorkgroupSizeZ: Number
    var maxComputeWorkgroupsPerDimension: Number

}


external interface GPUTexture : GPUObjectBase {
    fun createView(descriptor: GPUTextureViewDescriptor = definedExternally): GPUTextureView
    fun destroy()
    var width: GPUIntegerCoordinateOut
    var height: GPUIntegerCoordinateOut
    var depthOrArrayLayers: GPUIntegerCoordinateOut
    var mipLevelCount: GPUIntegerCoordinateOut
    var sampleCount: GPUSize32Out
    var dimension: String /* "1d" | "2d" | "3d" */
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var usage: GPUFlagsConstant

}

external interface GPUTextureView : GPUObjectBase

open external class GPUUncapturedErrorEvent : Event {
    var error: GPUError
}


external interface GPUValidationError : GPUError

typealias WGSLLanguageFeatures = Any//ReadonlySet<String>

external interface WorkerNavigator : NavigatorGPU

external interface GPUBufferUsage {
    var MAP_READ: GPUFlagsConstant
    var MAP_WRITE: GPUFlagsConstant
    var COPY_SRC: GPUFlagsConstant
    var COPY_DST: GPUFlagsConstant
    var INDEX: GPUFlagsConstant
    var VERTEX: GPUFlagsConstant
    var UNIFORM: GPUFlagsConstant
    var STORAGE: GPUFlagsConstant
    var INDIRECT: GPUFlagsConstant
    var QUERY_RESOLVE: GPUFlagsConstant

}


external interface GPUColorWrite {
    var RED: GPUFlagsConstant
    var GREEN: GPUFlagsConstant
    var BLUE: GPUFlagsConstant
    var ALPHA: GPUFlagsConstant
    var ALL: GPUFlagsConstant

}

external interface GPUMapMode {
    var READ: GPUFlagsConstant
    var WRITE: GPUFlagsConstant

}


external interface GPUShaderStage {
    var VERTEX: GPUFlagsConstant
    var FRAGMENT: GPUFlagsConstant
    var COMPUTE: GPUFlagsConstant

}

external interface GPUTextureUsage {
    var COPY_SRC: GPUFlagsConstant
    var COPY_DST: GPUFlagsConstant
    var TEXTURE_BINDING: GPUFlagsConstant
    var STORAGE_BINDING: GPUFlagsConstant
    var RENDER_ATTACHMENT: GPUFlagsConstant
}