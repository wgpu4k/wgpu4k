package io.ygdrasil.webgpu

expect class PipelineLayout : AutoCloseable {

    override fun close()
}

expect class RenderPipeline : AutoCloseable {
    fun getBindGroupLayout(index: GPUIndex32): BindGroupLayout

    override fun close()
}

data class PipelineLayoutDescriptor(
    val bindGroupLayouts: List<BindGroupLayout> = listOf(),
    val label: String? = null,
)

data class RenderPipelineDescriptor(
    val vertex: VertexState,
    val label: String? = null,
    val layout: PipelineLayout? = null,
    val primitive: PrimitiveState = PrimitiveState(),
    val depthStencil: DepthStencilState? = null,
    val fragment: FragmentState? = null,
    val multisample: MultisampleState = MultisampleState(),
) {

    data class VertexState(
        val module: ShaderModule,
        val entryPoint: String = "main",
        val constants: Map<String, GPUPipelineConstantValue>? = null,
        val buffers: List<VertexBufferLayout> = listOf(),
    ) {
        data class VertexBufferLayout(
            val arrayStride: GPUSize64,
            val attributes: List<VertexAttribute> = listOf(),
            val stepMode: VertexStepMode = VertexStepMode.Vertex,
        ) {
            data class VertexAttribute(
                val format: VertexFormat,
                val offset: GPUSize64,
                val shaderLocation: GPUIndex32,
            )
        }
    }

    data class PrimitiveState(
        val topology: PrimitiveTopology = PrimitiveTopology.TriangleList,
        val stripIndexFormat: IndexFormat? = null,
        val frontFace: FrontFace = FrontFace.CCW,
        val cullMode: CullMode = CullMode.None,
        val unclippedDepth: Boolean = false,
    )

    data class DepthStencilState(
        val format: TextureFormat,
        val depthWriteEnabled: Boolean? = null,
        val depthCompare: CompareFunction? = null,

        val stencilFront: StencilFaceState = StencilFaceState(),
        val stencilBack: StencilFaceState = StencilFaceState(),
        val stencilReadMask: GPUStencilValue = 0xFFFFFFFFu,
        val stencilWriteMask: GPUStencilValue = 0xFFFFFFFFu,
        val depthBias: GPUDepthBias = 0,
        val depthBiasSlopeScale: Float = 0f,
        val depthBiasClamp: Float = 0f,
    ) {
        data class StencilFaceState(
            val compare: CompareFunction = CompareFunction.Always,
            val failOp: StencilOperation = StencilOperation.Keep,
            val depthFailOp: StencilOperation = StencilOperation.Keep,
            val passOp: StencilOperation = StencilOperation.Keep,
        )
    }


    data class MultisampleState(
        val count: GPUSize32 = 1u,
        val mask: GPUSampleMask = 0xFFFFFFFFu,
        val alphaToCoverageEnabled: Boolean = false,
    )

    data class FragmentState(
        val module: ShaderModule,
        val targets: List<ColorTargetState> = listOf(),
        val entryPoint: String = "main",
    ) {

        data class ColorTargetState(
            val format: TextureFormat,
            val writeMask: ColorWriteMask = ColorWriteMask.All,
            val blend: BlendState = BlendState(),
        ) {
            data class BlendState(
                val color: BlendComponent = BlendComponent(),
                val alpha: BlendComponent = BlendComponent(),
            ) {
                data class BlendComponent(
                    val operation: BlendOperation = BlendOperation.Add,
                    val srcFactor: BlendFactor = BlendFactor.One,
                    val dstFactor: BlendFactor = BlendFactor.Zero,
                )
            }
        }
    }
}
