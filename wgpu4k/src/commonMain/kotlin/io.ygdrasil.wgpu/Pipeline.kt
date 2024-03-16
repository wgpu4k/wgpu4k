@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

expect class PipelineLayout

expect class RenderPipeline : AutoCloseable {
    fun getBindGroupLayout(index: Int): BindGroupLayout
}

data class PipelineLayoutDescriptor(
    var bindGroupLayouts: Array<BindGroupLayout> = arrayOf(),
    var label: String? = null
) {
    data class BindGroupLayout(
        var label: String,
        var brand: String
    )
}

data class RenderPipelineDescriptor(
    var vertex: VertexState,
    var label: String? = null,
    var layout: PipelineLayout? = null,
    var primitive: PrimitiveState = PrimitiveState(),
    var depthStencil: DepthStencilState? = null,
    var fragment: FragmentState? = null,
    var multisample: MultisampleState = MultisampleState(),
) {

    data class VertexState(
        var module: ShaderModule,
        var entryPoint: String = "main",
        var constants: Map<String, GPUPipelineConstantValue>? = null,
        var buffers: Array<VertexBufferLayout>? = null,
    ) {
        data class VertexBufferLayout(
            var arrayStride: GPUSize64,
            var attributes: Array<VertexAttribute> = arrayOf(),
            var stepMode: VertexStepMode? = null,
            /* "vertex" | "instance" */
        ) {
            data class VertexAttribute(
                var format: VertexFormat,
                var offset: GPUSize64,
                var shaderLocation: GPUIndex32,
            )

        }
    }


    data class PrimitiveState(
        var topology: PrimitiveTopology = PrimitiveTopology.trianglelist,
        var stripIndexFormat: IndexFormat? = null,
        var frontFace: FrontFace = FrontFace.ccw,
        var cullMode: CullMode = CullMode.none,
        var unclippedDepth: Boolean = false,
    )

    data class DepthStencilState(
        var format: TextureFormat,
        var depthWriteEnabled: Boolean? = null,
        var depthCompare: CompareFunction? = null,

        var stencilFront: StencilFaceState? = StencilFaceState(),
        var stencilBack: StencilFaceState? = StencilFaceState(),
        var stencilReadMask: GPUStencilValue = 0xFFFFFFFF,
        var stencilWriteMask: GPUStencilValue = 0xFFFFFFFF,
        var depthBias: GPUDepthBias = 0,
        var depthBiasSlopeScale: Float = 0f,
        var depthBiasClamp: Float = 0f,
    ) {
        data class StencilFaceState(
            var compare: CompareFunction = CompareFunction.always,
            var failOp: StencilOperation? = StencilOperation.keep,
            var depthFailOp: StencilOperation? = StencilOperation.keep,
            var passOp: StencilOperation? = StencilOperation.keep,
        )
    }


    data class MultisampleState(
        var count: GPUSize32 = 1,
        var mask: GPUSampleMask = 0xFFFFFFFF,
        var alphaToCoverageEnabled: Boolean = false
    )

    data class FragmentState(
        var module: ShaderModule,
        var targets: Array<ColorTargetState?> = arrayOf(),
        var entryPoint: String? = null
    ) {

        data class ColorTargetState(
            var format: TextureFormat,
            var writeMask: ColorWriteMask? = ColorWriteMask.all,
            var blend: BlendState = BlendState()
        ) {
            data class BlendState(
                var color: BlendComponent = BlendComponent(),
                var alpha: BlendComponent = BlendComponent()
            ) {
                data class BlendComponent(
                    var operation: BlendOperation = BlendOperation.add,
                    var srcFactor: BlendFactor = BlendFactor.one,
                    var dstFactor: BlendFactor = BlendFactor.zero
                )
            }
        }
    }
}
