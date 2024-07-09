package io.ygdrasil.wgpu.internal.js

import io.ygdrasil.wgpu.GPUColorWriteFlags
import org.w3c.dom.HTMLCanvasElement
import kotlin.js.Promise

fun <T : JsAny>createJsObject(): T =
    js("({ })")

fun <T : JsAny> List<T>.toJsArray(): JsArray<JsAny> {
    val output: JsArray<JsAny> = JsArray()
    forEachIndexed { index, value ->
        output[index] = value
    }
    return output
}


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
    fun createRenderPipeline(canvasConfiguration: GPURenderPipelineDescriptor): GPURenderPipeline
}

external interface GPURenderPipelineDescriptor : GPUPipelineDescriptorBase {
    var vertex: GPUVertexState
    var primitive: GPUPrimitiveState?
    var depthStencil: GPUDepthStencilState?
    var multisample: GPUMultisampleState?
    var fragment: GPUFragmentState?
}

external interface GPUFragmentState : GPUProgrammableStage {
    var targets: JsArray<GPUColorTargetState?>
}

external interface GPUColorTargetState: JsAny {
    var format: String
    var blend: GPUBlendState?
    var writeMask: GPUColorWriteFlags?
}

external interface GPUBlendState {
    var color: GPUBlendComponent
    var alpha: GPUBlendComponent
}

external interface GPUBlendComponent {
    var operation: String?
    var srcFactor: String?
    var dstFactor: String?
}

external interface GPUMultisampleState {
    var count: JsNumber?
    var mask: JsNumber?
    var alphaToCoverageEnabled: Boolean?
}

external interface GPUDepthStencilState {
    var format: String
    var depthWriteEnabled: Boolean?
    var depthCompare: String?
    var stencilFront: GPUStencilFaceState?
    var stencilBack: GPUStencilFaceState?
    var stencilReadMask: JsNumber?
    var stencilWriteMask: JsNumber?
    var depthBias: JsNumber?
    var depthBiasSlopeScale: Float?
    var depthBiasClamp: Float?

}

external interface GPUStencilFaceState {
    var compare: String?
    var failOp: String?
    var depthFailOp: String?
    var passOp: String?
}


external interface GPUPrimitiveState {
    var topology: String?
    var stripIndexFormat: String?
    var frontFace: String?
    var cullMode: String?
    var unclippedDepth: Boolean?
}

external interface GPUVertexState : GPUProgrammableStage {
    var buffers: JsArray<GPUVertexBufferLayout>?
}

external interface GPUVertexBufferLayout : JsAny {
    var arrayStride: JsNumber
    var stepMode: String? /* "vertex" | "instance" */
    var attributes: JsArray<GPUVertexAttribute>
}

external interface GPUVertexAttribute : JsAny {
    var format: String
    var offset: JsNumber
    var shaderLocation: JsNumber
}

external interface GPUProgrammableStage {
    var module: GPUShaderModule
    var entryPoint: JsString?
    var constants: JsAny //Map<JsString, JsNumber>?
}

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


external interface GPUPipelineDescriptorBase : GPUObjectDescriptorBase {
    var layout: JsAny /* GPUPipelineLayout | "auto" */
}

external interface GPUObjectDescriptorBase : JsAny {
    var label: JsString?
}

external interface GPUObjectBase {
    var label: JsString
}

external interface GPUDeviceDescriptor

external interface GPUAdapterInfo : JsAny

external interface GPUCanvasConfiguration : JsAny {
    var device: GPUDevice
    var format: JsString
    var usage: JsNumber?
    var viewFormats: JsArray<JsAny>?
    var colorSpace: JsString?
    var alphaMode: JsString?
}

external interface GPUTexture

external interface GPURequestAdapterOptions