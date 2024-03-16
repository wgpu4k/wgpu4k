@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")

import kotlin.js.*
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*
import tsstdlib.Iterable
import tsstdlib.Record
import tsstdlib.DOMException
import tsstdlib.ReadonlySet

external interface GPUOrigin2DDictStrict : GPUOrigin2DDict {
    var z: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUExtent3DDictStrict : GPUExtent3DDict {
    var depth: Any?
        get() = definedExternally
        set(value) = definedExternally
}

typealias GPUBufferDynamicOffset = Number

typealias GPUBufferUsageFlags = Number

typealias GPUColorWriteFlags = Number

typealias GPUDepthBias = Number

typealias GPUFlagsConstant = Number

typealias GPUIndex32 = Number

typealias GPUIntegerCoordinate = Number

typealias GPUIntegerCoordinateOut = Number

typealias GPUMapModeFlags = Number

typealias GPUPipelineConstantValue = Number

typealias GPUSampleMask = Number

typealias GPUShaderStageFlags = Number

typealias GPUSignedOffset32 = Number

typealias GPUSize32 = Number

typealias GPUSize32Out = Number

typealias GPUSize64 = Number

typealias GPUSize64Out = Number

typealias GPUStencilValue = Number

typealias GPUTextureUsageFlags = Number

external interface GPUBindGroupDescriptor : GPUObjectDescriptorBase {
    var layout: GPUBindGroupLayout
    var entries: Iterable<GPUBindGroupEntry>
}

external interface GPUBindGroupEntry {
    var binding: GPUIndex32
    var resource: dynamic /* GPUSampler | GPUTextureView | GPUBufferBinding | GPUExternalTexture */
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUBindGroupLayoutDescriptor : GPUObjectDescriptorBase {
    var entries: Iterable<GPUBindGroupLayoutEntry>
}

external interface GPUBindGroupLayoutEntry {
    var binding: GPUIndex32
    var visibility: GPUShaderStageFlags
    var buffer: GPUBufferBindingLayout?
        get() = definedExternally
        set(value) = definedExternally
    var sampler: GPUSamplerBindingLayout?
        get() = definedExternally
        set(value) = definedExternally
    var texture: GPUTextureBindingLayout?
        get() = definedExternally
        set(value) = definedExternally
    var storageTexture: GPUStorageTextureBindingLayout?
        get() = definedExternally
        set(value) = definedExternally
    var externalTexture: GPUExternalTextureBindingLayout?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUBlendComponent {
    var operation: String? /* "add" | "subtract" | "reverse-subtract" | "min" | "max" */
        get() = definedExternally
        set(value) = definedExternally
    var srcFactor: String? /* "zero" | "one" | "src" | "one-minus-src" | "src-alpha" | "one-minus-src-alpha" | "dst" | "one-minus-dst" | "dst-alpha" | "one-minus-dst-alpha" | "src-alpha-saturated" | "constant" | "one-minus-constant" */
        get() = definedExternally
        set(value) = definedExternally
    var dstFactor: String? /* "zero" | "one" | "src" | "one-minus-src" | "src-alpha" | "one-minus-src-alpha" | "dst" | "one-minus-dst" | "dst-alpha" | "one-minus-dst-alpha" | "src-alpha-saturated" | "constant" | "one-minus-constant" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUBlendState {
    var color: GPUBlendComponent
    var alpha: GPUBlendComponent
}

external interface GPUBufferBinding {
    var buffer: GPUBuffer
    var offset: GPUSize64?
        get() = definedExternally
        set(value) = definedExternally
    var size: GPUSize64?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUBufferBindingLayout {
    var type: String? /* "uniform" | "storage" | "read-only-storage" */
        get() = definedExternally
        set(value) = definedExternally
    var hasDynamicOffset: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var minBindingSize: GPUSize64?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUBufferDescriptor : GPUObjectDescriptorBase {
    var size: GPUSize64
    var usage: GPUBufferUsageFlags
    var mappedAtCreation: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUCanvasConfiguration {
    var device: GPUDevice
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var usage: GPUTextureUsageFlags?
        get() = definedExternally
        set(value) = definedExternally
    var viewFormats: Iterable<String? /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */>?
        get() = definedExternally
        set(value) = definedExternally
    var colorSpace: Any?
        get() = definedExternally
        set(value) = definedExternally
    var alphaMode: String? /* "opaque" | "premultiplied" */
        get() = definedExternally
        set(value) = definedExternally
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
        get() = definedExternally
        set(value) = definedExternally
    var writeMask: GPUColorWriteFlags?
        get() = definedExternally
        set(value) = definedExternally
}

typealias GPUCommandBufferDescriptor = GPUObjectDescriptorBase

typealias GPUCommandEncoderDescriptor = GPUObjectDescriptorBase

external interface GPUComputePassDescriptor : GPUObjectDescriptorBase {
    var timestampWrites: GPUComputePassTimestampWrites?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUComputePassTimestampWrites {
    var querySet: GPUQuerySet
    var beginningOfPassWriteIndex: GPUSize32?
        get() = definedExternally
        set(value) = definedExternally
    var endOfPassWriteIndex: GPUSize32?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUComputePipelineDescriptor : GPUPipelineDescriptorBase {
    var compute: GPUProgrammableStage
}

external interface GPUDepthStencilState {
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var depthWriteEnabled: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var depthCompare: String? /* "never" | "less" | "equal" | "less-equal" | "greater" | "not-equal" | "greater-equal" | "always" */
        get() = definedExternally
        set(value) = definedExternally
    var stencilFront: GPUStencilFaceState?
        get() = definedExternally
        set(value) = definedExternally
    var stencilBack: GPUStencilFaceState?
        get() = definedExternally
        set(value) = definedExternally
    var stencilReadMask: GPUStencilValue?
        get() = definedExternally
        set(value) = definedExternally
    var stencilWriteMask: GPUStencilValue?
        get() = definedExternally
        set(value) = definedExternally
    var depthBias: GPUDepthBias?
        get() = definedExternally
        set(value) = definedExternally
    var depthBiasSlopeScale: Number?
        get() = definedExternally
        set(value) = definedExternally
    var depthBiasClamp: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUDeviceDescriptor : GPUObjectDescriptorBase {
    var requiredFeatures: Iterable<String? /* "depth-clip-control" | "depth32float-stencil8" | "texture-compression-bc" | "texture-compression-etc2" | "texture-compression-astc" | "timestamp-query" | "indirect-first-instance" | "shader-f16" | "rg11b10ufloat-renderable" | "bgra8unorm-storage" | "float32-filterable" */>?
        get() = definedExternally
        set(value) = definedExternally
    var requiredLimits: Record<String, GPUSize64>?
        get() = definedExternally
        set(value) = definedExternally
    var defaultQueue: GPUQueueDescriptor?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUExtent3DDict {
    var width: GPUIntegerCoordinate
    var height: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var depthOrArrayLayers: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUExternalTextureBindingLayout

external interface GPUExternalTextureDescriptor : GPUObjectDescriptorBase {
    var source: dynamic /* HTMLVideoElement | VideoFrame */
        get() = definedExternally
        set(value) = definedExternally
    var colorSpace: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUFragmentState : GPUProgrammableStage {
    var targets: Iterable<GPUColorTargetState?>
}

external interface GPUImageCopyBuffer : GPUImageDataLayout {
    var buffer: GPUBuffer
}

external interface GPUImageCopyExternalImage {
    var source: dynamic /* ImageBitmap | ImageData | HTMLImageElement | HTMLVideoElement | VideoFrame | HTMLCanvasElement | OffscreenCanvas */
        get() = definedExternally
        set(value) = definedExternally
    var origin: dynamic /* Iterable<GPUIntegerCoordinate>? | GPUOrigin2DDictStrict? */
        get() = definedExternally
        set(value) = definedExternally
    var flipY: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUImageCopyTexture {
    var texture: GPUTexture
    var mipLevel: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var origin: dynamic /* Iterable<GPUIntegerCoordinate>? | GPUOrigin3DDict? */
        get() = definedExternally
        set(value) = definedExternally
    var aspect: String? /* "all" | "stencil-only" | "depth-only" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUImageCopyTextureTagged : GPUImageCopyTexture {
    var colorSpace: Any?
        get() = definedExternally
        set(value) = definedExternally
    var premultipliedAlpha: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUImageDataLayout {
    var offset: GPUSize64?
        get() = definedExternally
        set(value) = definedExternally
    var bytesPerRow: GPUSize32?
        get() = definedExternally
        set(value) = definedExternally
    var rowsPerImage: GPUSize32?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUMultisampleState {
    var count: GPUSize32?
        get() = definedExternally
        set(value) = definedExternally
    var mask: GPUSampleMask?
        get() = definedExternally
        set(value) = definedExternally
    var alphaToCoverageEnabled: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUObjectDescriptorBase {
    var label: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUOrigin2DDict {
    var x: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var y: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUOrigin3DDict {
    var x: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var y: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var z: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUPipelineDescriptorBase : GPUObjectDescriptorBase {
    var layout: dynamic /* GPUPipelineLayout | "auto" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUPipelineErrorInit {
    var reason: String /* "validation" | "internal" */
}

external interface GPUPipelineLayoutDescriptor : GPUObjectDescriptorBase {
    var bindGroupLayouts: Iterable<GPUBindGroupLayout>
}

external interface GPUPrimitiveState {
    var topology: String? /* "point-list" | "line-list" | "line-strip" | "triangle-list" | "triangle-strip" */
        get() = definedExternally
        set(value) = definedExternally
    var stripIndexFormat: String? /* "uint16" | "uint32" */
        get() = definedExternally
        set(value) = definedExternally
    var frontFace: String? /* "ccw" | "cw" */
        get() = definedExternally
        set(value) = definedExternally
    var cullMode: String? /* "none" | "front" | "back" */
        get() = definedExternally
        set(value) = definedExternally
    var unclippedDepth: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUProgrammableStage {
    var module: GPUShaderModule
    var entryPoint: String?
        get() = definedExternally
        set(value) = definedExternally
    var constants: Record<String, GPUPipelineConstantValue>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUQuerySetDescriptor : GPUObjectDescriptorBase {
    var type: String /* "occlusion" | "timestamp" */
    var count: GPUSize32
}

typealias GPUQueueDescriptor = GPUObjectDescriptorBase

typealias GPURenderBundleDescriptor = GPUObjectDescriptorBase

external interface GPURenderBundleEncoderDescriptor : GPURenderPassLayout {
    var depthReadOnly: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var stencilReadOnly: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPURenderPassColorAttachment {
    var view: GPUTextureView
    var depthSlice: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var resolveTarget: GPUTextureView?
        get() = definedExternally
        set(value) = definedExternally
    var clearValue: dynamic /* Iterable<Number>? | GPUColorDict? */
        get() = definedExternally
        set(value) = definedExternally
    var loadOp: String /* "load" | "clear" */
    var storeOp: String /* "store" | "discard" */
}

external interface GPURenderPassDepthStencilAttachment {
    var view: GPUTextureView
    var depthClearValue: Number?
        get() = definedExternally
        set(value) = definedExternally
    var depthLoadOp: String? /* "load" | "clear" */
        get() = definedExternally
        set(value) = definedExternally
    var depthStoreOp: String? /* "store" | "discard" */
        get() = definedExternally
        set(value) = definedExternally
    var depthReadOnly: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var stencilClearValue: GPUStencilValue?
        get() = definedExternally
        set(value) = definedExternally
    var stencilLoadOp: String? /* "load" | "clear" */
        get() = definedExternally
        set(value) = definedExternally
    var stencilStoreOp: String? /* "store" | "discard" */
        get() = definedExternally
        set(value) = definedExternally
    var stencilReadOnly: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPURenderPassDescriptor : GPUObjectDescriptorBase {
    var colorAttachments: Iterable<GPURenderPassColorAttachment?>
    var depthStencilAttachment: GPURenderPassDepthStencilAttachment?
        get() = definedExternally
        set(value) = definedExternally
    var occlusionQuerySet: GPUQuerySet?
        get() = definedExternally
        set(value) = definedExternally
    var timestampWrites: GPURenderPassTimestampWrites?
        get() = definedExternally
        set(value) = definedExternally
    var maxDrawCount: GPUSize64?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPURenderPassLayout : GPUObjectDescriptorBase {
    var colorFormats: Iterable<String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */>
    var depthStencilFormat: String? /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
        get() = definedExternally
        set(value) = definedExternally
    var sampleCount: GPUSize32?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPURenderPassTimestampWrites {
    var querySet: GPUQuerySet
    var beginningOfPassWriteIndex: GPUSize32?
        get() = definedExternally
        set(value) = definedExternally
    var endOfPassWriteIndex: GPUSize32?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPURenderPipelineDescriptor : GPUPipelineDescriptorBase {
    var vertex: GPUVertexState
    var primitive: GPUPrimitiveState?
        get() = definedExternally
        set(value) = definedExternally
    var depthStencil: GPUDepthStencilState?
        get() = definedExternally
        set(value) = definedExternally
    var multisample: GPUMultisampleState?
        get() = definedExternally
        set(value) = definedExternally
    var fragment: GPUFragmentState?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPURequestAdapterOptions {
    var powerPreference: String? /* "low-power" | "high-performance" */
        get() = definedExternally
        set(value) = definedExternally
    var forceFallbackAdapter: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUSamplerBindingLayout {
    var type: String? /* "filtering" | "non-filtering" | "comparison" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUSamplerDescriptor : GPUObjectDescriptorBase {
    var addressModeU: String? /* "clamp-to-edge" | "repeat" | "mirror-repeat" */
        get() = definedExternally
        set(value) = definedExternally
    var addressModeV: String? /* "clamp-to-edge" | "repeat" | "mirror-repeat" */
        get() = definedExternally
        set(value) = definedExternally
    var addressModeW: String? /* "clamp-to-edge" | "repeat" | "mirror-repeat" */
        get() = definedExternally
        set(value) = definedExternally
    var magFilter: String? /* "nearest" | "linear" */
        get() = definedExternally
        set(value) = definedExternally
    var minFilter: String? /* "nearest" | "linear" */
        get() = definedExternally
        set(value) = definedExternally
    var mipmapFilter: String? /* "nearest" | "linear" */
        get() = definedExternally
        set(value) = definedExternally
    var lodMinClamp: Number?
        get() = definedExternally
        set(value) = definedExternally
    var lodMaxClamp: Number?
        get() = definedExternally
        set(value) = definedExternally
    var compare: String? /* "never" | "less" | "equal" | "less-equal" | "greater" | "not-equal" | "greater-equal" | "always" */
        get() = definedExternally
        set(value) = definedExternally
    var maxAnisotropy: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUShaderModuleCompilationHint {
    var entryPoint: String
    var layout: dynamic /* GPUPipelineLayout? | "auto" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUShaderModuleDescriptor : GPUObjectDescriptorBase {
    var code: String
    var sourceMap: Any?
        get() = definedExternally
        set(value) = definedExternally
    var compilationHints: Array<GPUShaderModuleCompilationHint>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUStencilFaceState {
    var compare: String? /* "never" | "less" | "equal" | "less-equal" | "greater" | "not-equal" | "greater-equal" | "always" */
        get() = definedExternally
        set(value) = definedExternally
    var failOp: String? /* "keep" | "zero" | "replace" | "invert" | "increment-clamp" | "decrement-clamp" | "increment-wrap" | "decrement-wrap" */
        get() = definedExternally
        set(value) = definedExternally
    var depthFailOp: String? /* "keep" | "zero" | "replace" | "invert" | "increment-clamp" | "decrement-clamp" | "increment-wrap" | "decrement-wrap" */
        get() = definedExternally
        set(value) = definedExternally
    var passOp: String? /* "keep" | "zero" | "replace" | "invert" | "increment-clamp" | "decrement-clamp" | "increment-wrap" | "decrement-wrap" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUStorageTextureBindingLayout {
    var access: String? /* "write-only" | "read-only" | "read-write" */
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var viewDimension: String? /* "1d" | "2d" | "2d-array" | "cube" | "cube-array" | "3d" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUTextureBindingLayout {
    var sampleType: String? /* "float" | "unfilterable-float" | "depth" | "sint" | "uint" */
        get() = definedExternally
        set(value) = definedExternally
    var viewDimension: String? /* "1d" | "2d" | "2d-array" | "cube" | "cube-array" | "3d" */
        get() = definedExternally
        set(value) = definedExternally
    var multisampled: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUTextureDescriptor : GPUObjectDescriptorBase {
    var size: dynamic /* Iterable<GPUIntegerCoordinate> | GPUExtent3DDictStrict */
        get() = definedExternally
        set(value) = definedExternally
    var mipLevelCount: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var sampleCount: GPUSize32?
        get() = definedExternally
        set(value) = definedExternally
    var dimension: String? /* "1d" | "2d" | "3d" */
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var usage: GPUTextureUsageFlags
    var viewFormats: Iterable<String? /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUTextureViewDescriptor : GPUObjectDescriptorBase {
    var format: String? /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
        get() = definedExternally
        set(value) = definedExternally
    var dimension: String? /* "1d" | "2d" | "2d-array" | "cube" | "cube-array" | "3d" */
        get() = definedExternally
        set(value) = definedExternally
    var aspect: String? /* "all" | "stencil-only" | "depth-only" */
        get() = definedExternally
        set(value) = definedExternally
    var baseMipLevel: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var mipLevelCount: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var baseArrayLayer: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
    var arrayLayerCount: GPUIntegerCoordinate?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUUncapturedErrorEventInit : EventInit {
    var error: GPUError
}

external interface GPUVertexAttribute {
    var format: String /* "uint8x2" | "uint8x4" | "sint8x2" | "sint8x4" | "unorm8x2" | "unorm8x4" | "snorm8x2" | "snorm8x4" | "uint16x2" | "uint16x4" | "sint16x2" | "sint16x4" | "unorm16x2" | "unorm16x4" | "snorm16x2" | "snorm16x4" | "float16x2" | "float16x4" | "float32" | "float32x2" | "float32x3" | "float32x4" | "uint32" | "uint32x2" | "uint32x3" | "uint32x4" | "sint32" | "sint32x2" | "sint32x3" | "sint32x4" | "unorm10-10-10-2" */
    var offset: GPUSize64
    var shaderLocation: GPUIndex32
}

external interface GPUVertexBufferLayout {
    var arrayStride: GPUSize64
    var stepMode: String? /* "vertex" | "instance" */
        get() = definedExternally
        set(value) = definedExternally
    var attributes: Iterable<GPUVertexAttribute>
}

external interface GPUVertexState : GPUProgrammableStage {
    var buffers: Iterable<GPUVertexBufferLayout?>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GPUBindingCommandsMixin {
    fun setBindGroup(index: GPUIndex32, bindGroup: GPUBindGroup?, dynamicOffsets: Iterable<GPUBufferDynamicOffset> = definedExternally): Nothing?
    fun setBindGroup(index: GPUIndex32, bindGroup: GPUBindGroup?): Nothing?
    fun setBindGroup(index: GPUIndex32, bindGroup: GPUBindGroup?, dynamicOffsetsData: Uint32Array, dynamicOffsetsDataStart: GPUSize64, dynamicOffsetsDataLength: GPUSize32): Nothing?
}

external interface GPUCommandsMixin

external interface GPUDebugCommandsMixin {
    fun pushDebugGroup(groupLabel: String): Nothing?
    fun popDebugGroup(): Nothing?
    fun insertDebugMarker(markerLabel: String): Nothing?
}

external interface GPUObjectBase {
    var label: String
}

external interface GPUPipelineBase {
    fun getBindGroupLayout(index: Number): GPUBindGroupLayout
}

external interface GPURenderCommandsMixin {
    fun setPipeline(pipeline: GPURenderPipeline): Nothing?
    fun setIndexBuffer(buffer: GPUBuffer, indexFormat: String /* "uint16" | "uint32" */, offset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally): Nothing?
    fun setVertexBuffer(slot: GPUIndex32, buffer: GPUBuffer?, offset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally): Nothing?
    fun draw(vertexCount: GPUSize32, instanceCount: GPUSize32 = definedExternally, firstVertex: GPUSize32 = definedExternally, firstInstance: GPUSize32 = definedExternally): Nothing?
    fun drawIndexed(indexCount: GPUSize32, instanceCount: GPUSize32 = definedExternally, firstIndex: GPUSize32 = definedExternally, baseVertex: GPUSignedOffset32 = definedExternally, firstInstance: GPUSize32 = definedExternally): Nothing?
    fun drawIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64): Nothing?
    fun drawIndexedIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64): Nothing?
}

external interface NavigatorGPU {
    var gpu: GPU
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPU {
    var __brand: String /* "GPU" */
    fun requestAdapter(options: GPURequestAdapterOptions = definedExternally): Promise<GPUAdapter?>
    fun getPreferredCanvasFormat(): String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var wgslLanguageFeatures: WGSLLanguageFeatures

    companion object {
        var prototype: GPU
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUAdapter {
    var __brand: String /* "GPUAdapter" */
    var features: GPUSupportedFeatures
    var limits: GPUSupportedLimits
    var isFallbackAdapter: Boolean
    fun requestDevice(descriptor: GPUDeviceDescriptor = definedExternally): Promise<GPUDevice>
    fun requestAdapterInfo(): Promise<GPUAdapterInfo>

    companion object {
        var prototype: GPUAdapter
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUAdapterInfo {
    var __brand: String /* "GPUAdapterInfo" */
    var vendor: String
    var architecture: String
    var device: String
    var description: String

    companion object {
        var prototype: GPUAdapterInfo
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUBindGroup : GPUObjectBase {
    var __brand: String /* "GPUBindGroup" */

    companion object {
        var prototype: GPUBindGroup
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUBindGroupLayout : GPUObjectBase {
    var __brand: String /* "GPUBindGroupLayout" */

    companion object {
        var prototype: GPUBindGroupLayout
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUBuffer : GPUObjectBase {
    var __brand: String /* "GPUBuffer" */
    var size: GPUSize64Out
    var usage: GPUFlagsConstant
    var mapState: String /* "unmapped" | "pending" | "mapped" */
    fun mapAsync(mode: GPUMapModeFlags, offset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally): Promise<Nothing?>
    fun getMappedRange(offset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally): ArrayBuffer
    fun unmap(): Nothing?
    fun destroy(): Nothing?

    companion object {
        var prototype: GPUBuffer
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUCanvasContext {
    var __brand: String /* "GPUCanvasContext" */
    var canvas: dynamic /* HTMLCanvasElement | OffscreenCanvas */
        get() = definedExternally
        set(value) = definedExternally
    fun configure(configuration: GPUCanvasConfiguration): Nothing?
    fun unconfigure(): Nothing?
    fun getCurrentTexture(): GPUTexture

    companion object {
        var prototype: GPUCanvasContext
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUCommandBuffer : GPUObjectBase {
    var __brand: String /* "GPUCommandBuffer" */

    companion object {
        var prototype: GPUCommandBuffer
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUCommandEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin {
    var __brand: String /* "GPUCommandEncoder" */
    fun beginRenderPass(descriptor: GPURenderPassDescriptor): GPURenderPassEncoder
    fun beginComputePass(descriptor: GPUComputePassDescriptor = definedExternally): GPUComputePassEncoder
    fun copyBufferToBuffer(source: GPUBuffer, sourceOffset: GPUSize64, destination: GPUBuffer, destinationOffset: GPUSize64, size: GPUSize64): Nothing?
    fun copyBufferToTexture(source: GPUImageCopyBuffer, destination: GPUImageCopyTexture, copySize: Iterable<GPUIntegerCoordinate>): Nothing?
    fun copyBufferToTexture(source: GPUImageCopyBuffer, destination: GPUImageCopyTexture, copySize: GPUExtent3DDictStrict): Nothing?
    fun copyTextureToBuffer(source: GPUImageCopyTexture, destination: GPUImageCopyBuffer, copySize: Iterable<GPUIntegerCoordinate>): Nothing?
    fun copyTextureToBuffer(source: GPUImageCopyTexture, destination: GPUImageCopyBuffer, copySize: GPUExtent3DDictStrict): Nothing?
    fun copyTextureToTexture(source: GPUImageCopyTexture, destination: GPUImageCopyTexture, copySize: Iterable<GPUIntegerCoordinate>): Nothing?
    fun copyTextureToTexture(source: GPUImageCopyTexture, destination: GPUImageCopyTexture, copySize: GPUExtent3DDictStrict): Nothing?
    fun clearBuffer(buffer: GPUBuffer, offset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally): Nothing?
    fun resolveQuerySet(querySet: GPUQuerySet, firstQuery: GPUSize32, queryCount: GPUSize32, destination: GPUBuffer, destinationOffset: GPUSize64): Nothing?
    fun finish(descriptor: GPUCommandBufferDescriptor = definedExternally): GPUCommandBuffer

    companion object {
        var prototype: GPUCommandEncoder
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUCompilationInfo {
    var __brand: String /* "GPUCompilationInfo" */
    var messages: Array<GPUCompilationMessage>

    companion object {
        var prototype: GPUCompilationInfo
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUCompilationMessage {
    var __brand: String /* "GPUCompilationMessage" */
    var message: String
    var type: String /* "error" | "warning" | "info" */
    var lineNum: Number
    var linePos: Number
    var offset: Number
    var length: Number

    companion object {
        var prototype: GPUCompilationMessage
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUComputePassEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin, GPUBindingCommandsMixin {
    var __brand: String /* "GPUComputePassEncoder" */
    fun setPipeline(pipeline: GPUComputePipeline): Nothing?
    fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32 = definedExternally, workgroupCountZ: GPUSize32 = definedExternally): Nothing?
    fun dispatchWorkgroupsIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64): Nothing?
    fun end(): Nothing?

    companion object {
        var prototype: GPUComputePassEncoder
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUComputePipeline : GPUObjectBase, GPUPipelineBase {
    var __brand: String /* "GPUComputePipeline" */

    companion object {
        var prototype: GPUComputePipeline
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUDevice : EventTarget, GPUObjectBase {
    var __brand: String /* "GPUDevice" */
    var features: GPUSupportedFeatures
    var limits: GPUSupportedLimits
    var queue: GPUQueue
    fun destroy(): Nothing?
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
    fun pushErrorScope(filter: String /* "validation" | "out-of-memory" | "internal" */): Nothing?
    fun popErrorScope(): Promise<GPUError?>
    var onuncapturederror: ((self: GPUDevice, ev: GPUUncapturedErrorEvent) -> Any)?

    companion object {
        var prototype: GPUDevice
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUDeviceLostInfo {
    var __brand: String /* "GPUDeviceLostInfo" */
    var reason: String /* "unknown" | "destroyed" */
    var message: String

    companion object {
        var prototype: GPUDeviceLostInfo
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUError {
    var message: String

    companion object {
        var prototype: GPUError
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUExternalTexture : GPUObjectBase {
    var __brand: String /* "GPUExternalTexture" */

    companion object {
        var prototype: GPUExternalTexture
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUInternalError : GPUError {
    var __brand: String /* "GPUInternalError" */

    companion object {
        var prototype: GPUInternalError
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUOutOfMemoryError : GPUError {
    var __brand: String /* "GPUOutOfMemoryError" */

    companion object {
        var prototype: GPUOutOfMemoryError
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUPipelineError : DOMException {
    var __brand: String /* "GPUPipelineError" */
    var reason: String /* "validation" | "internal" */

    companion object {
        var prototype: GPUPipelineError
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUPipelineLayout : GPUObjectBase {
    var __brand: String /* "GPUPipelineLayout" */

    companion object {
        var prototype: GPUPipelineLayout
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUQuerySet : GPUObjectBase {
    var __brand: String /* "GPUQuerySet" */
    fun destroy(): Nothing?
    var type: String /* "occlusion" | "timestamp" */
    var count: GPUSize32Out

    companion object {
        var prototype: GPUQuerySet
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUQueue : GPUObjectBase {
    var __brand: String /* "GPUQueue" */
    fun submit(commandBuffers: Iterable<GPUCommandBuffer>): Nothing?
    fun onSubmittedWorkDone(): Promise<Nothing?>
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: ArrayBufferView, dataOffset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally): Nothing?
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: ArrayBufferView): Nothing?
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: ArrayBufferView, dataOffset: GPUSize64 = definedExternally): Nothing?
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: ArrayBuffer, dataOffset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally): Nothing?
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: ArrayBuffer): Nothing?
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: ArrayBuffer, dataOffset: GPUSize64 = definedExternally): Nothing?
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: SharedArrayBuffer, dataOffset: GPUSize64 = definedExternally, size: GPUSize64 = definedExternally): Nothing?
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: SharedArrayBuffer): Nothing?
    fun writeBuffer(buffer: GPUBuffer, bufferOffset: GPUSize64, data: SharedArrayBuffer, dataOffset: GPUSize64 = definedExternally): Nothing?
    fun writeTexture(destination: GPUImageCopyTexture, data: ArrayBufferView, dataLayout: GPUImageDataLayout, size: Iterable<GPUIntegerCoordinate>): Nothing?
    fun writeTexture(destination: GPUImageCopyTexture, data: ArrayBufferView, dataLayout: GPUImageDataLayout, size: GPUExtent3DDictStrict): Nothing?
    fun writeTexture(destination: GPUImageCopyTexture, data: ArrayBuffer, dataLayout: GPUImageDataLayout, size: Iterable<GPUIntegerCoordinate>): Nothing?
    fun writeTexture(destination: GPUImageCopyTexture, data: ArrayBuffer, dataLayout: GPUImageDataLayout, size: GPUExtent3DDictStrict): Nothing?
    fun writeTexture(destination: GPUImageCopyTexture, data: SharedArrayBuffer, dataLayout: GPUImageDataLayout, size: Iterable<GPUIntegerCoordinate>): Nothing?
    fun writeTexture(destination: GPUImageCopyTexture, data: SharedArrayBuffer, dataLayout: GPUImageDataLayout, size: GPUExtent3DDictStrict): Nothing?
    fun copyExternalImageToTexture(source: GPUImageCopyExternalImage, destination: GPUImageCopyTextureTagged, copySize: Iterable<GPUIntegerCoordinate>): Nothing?
    fun copyExternalImageToTexture(source: GPUImageCopyExternalImage, destination: GPUImageCopyTextureTagged, copySize: GPUExtent3DDictStrict): Nothing?

    companion object {
        var prototype: GPUQueue
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPURenderBundle : GPUObjectBase {
    var __brand: String /* "GPURenderBundle" */

    companion object {
        var prototype: GPURenderBundle
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPURenderBundleEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin, GPUBindingCommandsMixin, GPURenderCommandsMixin {
    var __brand: String /* "GPURenderBundleEncoder" */
    fun finish(descriptor: GPURenderBundleDescriptor = definedExternally): GPURenderBundle

    companion object {
        var prototype: GPURenderBundleEncoder
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPURenderPassEncoder : GPUObjectBase, GPUCommandsMixin, GPUDebugCommandsMixin, GPUBindingCommandsMixin, GPURenderCommandsMixin {
    var __brand: String /* "GPURenderPassEncoder" */
    fun setViewport(x: Number, y: Number, width: Number, height: Number, minDepth: Number, maxDepth: Number): Nothing?
    fun setScissorRect(x: GPUIntegerCoordinate, y: GPUIntegerCoordinate, width: GPUIntegerCoordinate, height: GPUIntegerCoordinate): Nothing?
    fun setBlendConstant(color: Iterable<Number>): Nothing?
    fun setBlendConstant(color: GPUColorDict): Nothing?
    fun setStencilReference(reference: GPUStencilValue): Nothing?
    fun beginOcclusionQuery(queryIndex: GPUSize32): Nothing?
    fun endOcclusionQuery(): Nothing?
    fun executeBundles(bundles: Iterable<GPURenderBundle>): Nothing?
    fun end(): Nothing?

    companion object {
        var prototype: GPURenderPassEncoder
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPURenderPipeline : GPUObjectBase, GPUPipelineBase {
    var __brand: String /* "GPURenderPipeline" */

    companion object {
        var prototype: GPURenderPipeline
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUSampler : GPUObjectBase {
    var __brand: String /* "GPUSampler" */

    companion object {
        var prototype: GPUSampler
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUShaderModule : GPUObjectBase {
    var __brand: String /* "GPUShaderModule" */
    fun getCompilationInfo(): Promise<GPUCompilationInfo>

    companion object {
        var prototype: GPUShaderModule
    }
}

typealias GPUSupportedFeatures = ReadonlySet<String>

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUSupportedLimits {
    var __brand: String /* "GPUSupportedLimits" */
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

    companion object {
        var prototype: GPUSupportedLimits
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUTexture : GPUObjectBase {
    var __brand: String /* "GPUTexture" */
    fun createView(descriptor: GPUTextureViewDescriptor = definedExternally): GPUTextureView
    fun destroy(): Nothing?
    var width: GPUIntegerCoordinateOut
    var height: GPUIntegerCoordinateOut
    var depthOrArrayLayers: GPUIntegerCoordinateOut
    var mipLevelCount: GPUIntegerCoordinateOut
    var sampleCount: GPUSize32Out
    var dimension: String /* "1d" | "2d" | "3d" */
    var format: String /* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */
    var usage: GPUFlagsConstant

    companion object {
        var prototype: GPUTexture
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUTextureView : GPUObjectBase {
    var __brand: String /* "GPUTextureView" */

    companion object {
        var prototype: GPUTextureView
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUUncapturedErrorEvent : Event {
    var __brand: String /* "GPUUncapturedErrorEvent" */
    var error: GPUError

    companion object {
        var prototype: GPUUncapturedErrorEvent
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUValidationError : GPUError {
    var __brand: String /* "GPUValidationError" */

    companion object {
        var prototype: GPUValidationError
    }
}

typealias WGSLLanguageFeatures = ReadonlySet<String>

external interface WorkerNavigator : NavigatorGPU

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUBufferUsage {
    var __brand: String /* "GPUBufferUsage" */
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

    companion object {
        var prototype: GPUBufferUsage
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
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUColorWrite {
    var __brand: String /* "GPUColorWrite" */
    var RED: GPUFlagsConstant
    var GREEN: GPUFlagsConstant
    var BLUE: GPUFlagsConstant
    var ALPHA: GPUFlagsConstant
    var ALL: GPUFlagsConstant

    companion object {
        var prototype: GPUColorWrite
        var RED: GPUFlagsConstant
        var GREEN: GPUFlagsConstant
        var BLUE: GPUFlagsConstant
        var ALPHA: GPUFlagsConstant
        var ALL: GPUFlagsConstant
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUMapMode {
    var __brand: String /* "GPUMapMode" */
    var READ: GPUFlagsConstant
    var WRITE: GPUFlagsConstant

    companion object {
        var prototype: GPUMapMode
        var READ: GPUFlagsConstant
        var WRITE: GPUFlagsConstant
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUShaderStage {
    var __brand: String /* "GPUShaderStage" */
    var VERTEX: GPUFlagsConstant
    var FRAGMENT: GPUFlagsConstant
    var COMPUTE: GPUFlagsConstant

    companion object {
        var prototype: GPUShaderStage
        var VERTEX: GPUFlagsConstant
        var FRAGMENT: GPUFlagsConstant
        var COMPUTE: GPUFlagsConstant
    }
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface GPUTextureUsage {
    var __brand: String /* "GPUTextureUsage" */
    var COPY_SRC: GPUFlagsConstant
    var COPY_DST: GPUFlagsConstant
    var TEXTURE_BINDING: GPUFlagsConstant
    var STORAGE_BINDING: GPUFlagsConstant
    var RENDER_ATTACHMENT: GPUFlagsConstant

    companion object {
        var prototype: GPUTextureUsage
        var COPY_SRC: GPUFlagsConstant
        var COPY_DST: GPUFlagsConstant
        var TEXTURE_BINDING: GPUFlagsConstant
        var STORAGE_BINDING: GPUFlagsConstant
        var RENDER_ATTACHMENT: GPUFlagsConstant
    }
}