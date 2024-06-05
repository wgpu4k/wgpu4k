

package io.ygdrasil.wgpu

expect class PipelineLayout

expect class RenderPipeline : AutoCloseable {
    fun getBindGroupLayout(index: Int): BindGroupLayout

    override fun close()
}

data class PipelineLayoutDescriptor(
    val bindGroupLayouts: Array<BindGroupLayout> = arrayOf(),
    val label: String? = null
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
        val buffers: Array<VertexBufferLayout> = arrayOf(),
    ) {
        data class VertexBufferLayout(
            val arrayStride: GPUSize64,
            val attributes: Array<VertexAttribute> = arrayOf(),
            val stepMode: VertexStepMode = VertexStepMode.vertex,
        ) {
            data class VertexAttribute(
                val format: VertexFormat,
                val offset: GPUSize64,
                val shaderLocation: GPUIndex32,
            )
        }
    }

    data class PrimitiveState(
        val topology: PrimitiveTopology = PrimitiveTopology.trianglelist,
        val stripIndexFormat: IndexFormat? = null,
        val frontFace: FrontFace = FrontFace.ccw,
        val cullMode: CullMode = CullMode.none,
        val unclippedDepth: Boolean = false,
    )

    data class DepthStencilState(
        val format: TextureFormat,
        val depthWriteEnabled: Boolean? = null,
        val depthCompare: CompareFunction? = null,

        val stencilFront: StencilFaceState = StencilFaceState(),
        val stencilBack: StencilFaceState = StencilFaceState(),
        val stencilReadMask: GPUStencilValue = 0xFFFFFFFF,
        val stencilWriteMask: GPUStencilValue = 0xFFFFFFFF,
        val depthBias: GPUDepthBias = 0,
        val depthBiasSlopeScale: Float = 0f,
        val depthBiasClamp: Float = 0f,
    ) {
        data class StencilFaceState(
            val compare: CompareFunction = CompareFunction.always,
            val failOp: StencilOperation = StencilOperation.keep,
            val depthFailOp: StencilOperation = StencilOperation.keep,
            val passOp: StencilOperation = StencilOperation.keep,
        )
    }


    data class MultisampleState(
        val count: GPUSize32 = 1,
        val mask: GPUSampleMask = 0xFFFFFFFFu,
        val alphaToCoverageEnabled: Boolean = false
    )

    data class FragmentState(
        val module: ShaderModule,
        val targets: Array<ColorTargetState> = arrayOf(),
        val entryPoint: String = "main"
    ) {

        data class ColorTargetState(
            val format: TextureFormat,
            val writeMask: ColorWriteMask = ColorWriteMask.all,
            val blend: BlendState = BlendState()
        ) {
            data class BlendState(
                val color: BlendComponent = BlendComponent(),
                val alpha: BlendComponent = BlendComponent()
            ) {
                data class BlendComponent(
                    val operation: BlendOperation = BlendOperation.add,
                    val srcFactor: BlendFactor = BlendFactor.one,
                    val dstFactor: BlendFactor = BlendFactor.zero
                )
            }
        }
    }
}
