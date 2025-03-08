package io.ygdrasil.webgpu.examples.scenes.graphics.techniques

import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.BindGroup
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.Buffer
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.BufferUsage
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.ComputePipeline
import io.ygdrasil.webgpu.ComputePipelineDescriptor
import io.ygdrasil.webgpu.GPUBlendFactor
import io.ygdrasil.webgpu.GPUBlendOperation
import io.ygdrasil.webgpu.GPUCompareFunction
import io.ygdrasil.webgpu.GPULoadOp
import io.ygdrasil.webgpu.GPUStoreOp
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUTextureViewDimension
import io.ygdrasil.webgpu.GPUVertexFormat
import io.ygdrasil.webgpu.GPUVertexStepMode
import io.ygdrasil.webgpu.ImageCopyExternalImage
import io.ygdrasil.webgpu.ImageCopyTextureTagged
import io.ygdrasil.webgpu.PrimitiveTopology
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipeline
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.Size3D
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.TextureUsage
import io.ygdrasil.webgpu.TextureViewDescriptor
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.examples.AssetManager
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.scenes.shader.compute.probabilityMap
import io.ygdrasil.webgpu.examples.scenes.shader.vertex.particlesShaderFixed
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI
import kotlin.math.ceil
import kotlin.random.Random


class ParticlesScene(wgpuContext: WGPUContext, assetManager: AssetManager) : Scene(wgpuContext), AssetManager by assetManager {

    // Constants
    val numParticles = 50000
    val particlePositionOffset = 0
    val particleColorOffset = 4 * 4
    val particleInstanceByteSize = 3 * 4 + // position
            1 * 4 + // lifetime
            4 * 4 + // color
            3 * 4 + // velocity
            1 * 4 + // padding
            0

    //Variables
    var simulate = true
    var deltaTime = 0.05f
    var rng = Random(0)

    lateinit var simulationUBOBuffer: Buffer
    lateinit var uniformBuffer: Buffer
    lateinit var renderPipeline: RenderPipeline
    lateinit var projectionMatrix: Matrix4
    lateinit var view: Matrix4
    lateinit var renderPassDescriptor: RenderPassDescriptor
    lateinit var computePipeline: ComputePipeline
    lateinit var computeBindGroup: BindGroup
    lateinit var uniformBindGroup: BindGroup
    lateinit var particlesBuffer: Buffer
    lateinit var quadVertexBuffer: Buffer

    override suspend fun initialize() = with(autoClosableContext) {

        particlesBuffer = device.createBuffer(
            BufferDescriptor(
                size = (numParticles * particleInstanceByteSize).toULong(),
                usage = setOf(BufferUsage.Vertex, BufferUsage.Storage),
            )
        ).bind()

        renderPipeline = device.createRenderPipeline(
            RenderPipelineDescriptor(
                vertex = RenderPipelineDescriptor.VertexState(
                    entryPoint = "vs_main",
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = particlesShaderFixed,
                        )
                    ).bind(),
                    buffers = listOf(
                        RenderPipelineDescriptor.VertexState.VertexBufferLayout
                            (
                            // instanced particles buffer
                            arrayStride = particleInstanceByteSize.toULong(),
                            stepMode = GPUVertexStepMode.Instance,
                            attributes = listOf(
                                RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
                                    // position
                                    shaderLocation = 0u,
                                    offset = particlePositionOffset.toULong(),
                                    format = GPUVertexFormat.Float32x3,
                                ),
                                RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
                                    // color
                                    shaderLocation = 1u,
                                    offset = particleColorOffset.toULong(),
                                    format = GPUVertexFormat.Float32x4,
                                ),
                            ),
                        ),
                        RenderPipelineDescriptor.VertexState.VertexBufferLayout(
                            // quad vertex buffer
                            arrayStride = 2uL * 4uL, // vec2f
                            stepMode = GPUVertexStepMode.Vertex,
                            attributes = listOf(
                                RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
                                    // vertex positions
                                    shaderLocation = 2u,
                                    offset = 0u,
                                    format = GPUVertexFormat.Float32x2,
                                ),
                            ),
                        ),
                    ),
                ),
                fragment = RenderPipelineDescriptor.FragmentState(
                    entryPoint = "fs_main",
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = particlesShaderFixed,
                        )
                    ).bind(),
                    targets = listOf(
                        RenderPipelineDescriptor.FragmentState.ColorTargetState(
                            format = renderingContext.textureFormat,
                            blend = RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState(
                                color = RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent(
                                    srcFactor = GPUBlendFactor.SrcAlpha,
                                    dstFactor = GPUBlendFactor.One,
                                    operation = GPUBlendOperation.Add,
                                ),
                                alpha = RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent(
                                    srcFactor = GPUBlendFactor.Zero,
                                    dstFactor = GPUBlendFactor.One,
                                    operation = GPUBlendOperation.Add
                                ),
                            ),
                        ),
                    ),
                ),
                primitive = RenderPipelineDescriptor.PrimitiveState(
                    topology = PrimitiveTopology.TriangleList,
                ),

                depthStencil = RenderPipelineDescriptor.DepthStencilState(
                    depthWriteEnabled = false,
                    depthCompare = GPUCompareFunction.Less,
                    format = GPUTextureFormat.Depth24Plus,
                ),
            )
        )

        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Size3D(renderingContext.width, renderingContext.height),
                format = GPUTextureFormat.Depth24Plus,
                usage = setOf(TextureUsage.RenderAttachment),
            )
        )

        val uniformBufferSize = 4 * 4 * 4 + // modelViewProjectionMatrix : mat4x4f
                3 * 4 + // right : vec3f
                4 + // padding
                3 * 4 + // up : vec3f
                4 + // padding
                0
        uniformBuffer = device.createBuffer(
            BufferDescriptor(
                size = uniformBufferSize.toULong(),
                usage = setOf(BufferUsage.Uniform, BufferUsage.CopyDst),
            )
        ).bind()

        uniformBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = renderPipeline.getBindGroupLayout(0u),
                entries = listOf(
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 0u,
                        resource = BindGroupDescriptor.BufferBinding(
                            buffer = uniformBuffer,
                        ),
                    ),
                ),
            )
        ).bind()

        renderPassDescriptor = RenderPassDescriptor(
            colorAttachments = listOf(
                RenderPassDescriptor.ColorAttachment(
                    view = dummyTexture.createView().bind(), // Assigned later
                    clearValue = Color(.0, .0, .0, 1.0),
                    loadOp = GPULoadOp.Clear,
                    storeOp = GPUStoreOp.Store,
                ),
            ),
            depthStencilAttachment = RenderPassDescriptor.DepthStencilAttachment(
                view = depthTexture.createView().bind(),

                depthClearValue = 1.0f,
                depthLoadOp = GPULoadOp.Clear,
                depthStoreOp = GPUStoreOp.Store,
            )
        )

        //////////////////////////////////////////////////////////////////////////////
        // Quad vertex buffer
        //////////////////////////////////////////////////////////////////////////////
        quadVertexBuffer = device.createBuffer(
            BufferDescriptor(
                size = 6uL * 2uL * 4uL, // 6x vec2f
                usage = setOf(BufferUsage.Vertex),
                mappedAtCreation = true,
            )
        )

        val vertexData = arrayOf(
            -1.0, -1.0, +1.0, -1.0, -1.0, +1.0, -1.0, +1.0, +1.0, -1.0, +1.0, +1.0,
        ).let { FloatArray(it.size) { index -> it[index].toFloat() } }
        quadVertexBuffer.mapFrom(vertexData)
        quadVertexBuffer.unmap()

        //////////////////////////////////////////////////////////////////////////////
        // Texture
        //////////////////////////////////////////////////////////////////////////////
        var textureWidth = 1u
        var textureHeight = 1u
        var numMipLevels = 1u

        val imageBitmap = webgpu4kotlin

        // Calculate number of mip levels required to generate the probability map
        while (
            textureWidth < imageBitmap.width ||
            textureHeight < imageBitmap.height
        ) {
            textureWidth *= 2u
            textureHeight *= 2u
            numMipLevels++
        }
        val texture = device.createTexture(
            TextureDescriptor(
                size = Size3D(imageBitmap.width, imageBitmap.height),
                mipLevelCount = numMipLevels,
                format = GPUTextureFormat.RGBA8Unorm,
                usage =
                setOf(
                    TextureUsage.TextureBinding,
                    TextureUsage.StorageBinding,
                    TextureUsage.CopyDst,
                    TextureUsage.RenderAttachment
                ),
            )
        )
        device.queue.copyExternalImageToTexture(
            ImageCopyExternalImage(source = imageBitmap),
            ImageCopyTextureTagged(texture = texture),
            imageBitmap.width to imageBitmap.height
        )


        //////////////////////////////////////////////////////////////////////////////
        // Probability map generation
        // The 0'th mip level of texture holds the color data and spawn-probability in
        // the alpha channel. The mip levels 1..N are generated to hold spawn
        // probabilities up to the top 1x1 mip level.
        //////////////////////////////////////////////////////////////////////////////
        val probabilityMapImportLevelPipeline = device.createComputePipeline(
            ComputePipelineDescriptor(
                compute = ComputePipelineDescriptor.ProgrammableStage(
                    module = device.createShaderModule(ShaderModuleDescriptor(code = probabilityMap)).bind(),
                    entryPoint = "import_level",
                ),
            )
        ).bind()


        val probabilityMapExportLevelPipeline = device.createComputePipeline(
            ComputePipelineDescriptor(
                compute = ComputePipelineDescriptor.ProgrammableStage(
                    module = device.createShaderModule(ShaderModuleDescriptor(code = probabilityMap)).bind(),
                    entryPoint = "export_level",
                ),
            )
        ).bind()

        val probabilityMapUBOBufferSize = 1 * 4 + // stride
                3 * 4 + // padding
                0

        val probabilityMapUBOBuffer = device.createBuffer(
            BufferDescriptor(
                size = probabilityMapUBOBufferSize.toULong(),
                usage = setOf(BufferUsage.Uniform, BufferUsage.CopyDst),
            )
        )
        val buffer_a = device.createBuffer(
            BufferDescriptor(
                size = textureWidth * textureHeight * 4uL,
                usage = setOf(BufferUsage.Storage),
            )
        )
        val buffer_b = device.createBuffer(
            BufferDescriptor(
                size = textureWidth * textureHeight * 4uL,
                usage = setOf(BufferUsage.Storage),
            )
        )
        device.queue.writeBuffer(
            probabilityMapUBOBuffer,
            0u,
            IntArray(1) { textureWidth.toInt() }
        )

        val commandEncoder = device.createCommandEncoder()
        (0u until numMipLevels).forEach { level ->
            val levelWidth = textureWidth shr level.toInt()
            val levelHeight = textureHeight shr level.toInt()
            val pipeline = if (level == 0u) probabilityMapImportLevelPipeline.getBindGroupLayout(0u)
            else probabilityMapExportLevelPipeline.getBindGroupLayout(0u)
            val probabilityMapBindGroup = device.createBindGroup(
                BindGroupDescriptor(
                    layout = pipeline,
                    entries = listOf(
                        BindGroupDescriptor.BindGroupEntry(
                            // ubo
                            binding = 0u,
                            resource = BindGroupDescriptor.BufferBinding(buffer = probabilityMapUBOBuffer),
                        ),
                        BindGroupDescriptor.BindGroupEntry(
                            // buf_in
                            binding = 1u,
                            resource = BindGroupDescriptor.BufferBinding(if (level and 1u != 0u) buffer_a else buffer_b)
                        ),
                        BindGroupDescriptor.BindGroupEntry(
                            // buf_out
                            binding = 2u,
                            resource = BindGroupDescriptor.BufferBinding(if (level and 1u != 0u) buffer_b else buffer_a)
                        ),
                        BindGroupDescriptor.BindGroupEntry(
                            // tex_in / tex_out
                            binding = 3u,
                            resource = BindGroupDescriptor.TextureViewBinding(
                                view = texture.createView(
                                    TextureViewDescriptor(
                                        format = GPUTextureFormat.RGBA8Unorm,
                                        dimension = GPUTextureViewDimension.TwoD,
                                        baseMipLevel = level,
                                        mipLevelCount = 1u,
                                    )
                                ).bind()
                            ),
                        ),
                    ),
                )
            )


            if (level == 0u) {
                val passEncoder = commandEncoder.beginComputePass()
                passEncoder.setPipeline(probabilityMapImportLevelPipeline)
                passEncoder.setBindGroup(0u, probabilityMapBindGroup)
                passEncoder.dispatchWorkgroups(ceil(levelWidth.toDouble() / 64.0).toUInt(), levelHeight)
                passEncoder.end()
            } else {
                val passEncoder = commandEncoder.beginComputePass()
                passEncoder.setPipeline(probabilityMapExportLevelPipeline)
                passEncoder.setBindGroup(0u, probabilityMapBindGroup)
                passEncoder.dispatchWorkgroups(ceil(levelWidth.toDouble() / 64.0).toUInt(), levelHeight)
                passEncoder.end()
            }
        }
        device.queue.submit(listOf(commandEncoder.finish()))
        //////////////////////////////////////////////////////////////////////////////
        // Simulation compute pipeline
        //////////////////////////////////////////////////////////////////////////////

        val simulationUBOBufferSize = 1 * 4 + // deltaTime
                3 * 4 + // padding
                4 * 4 + // seed
                0
        simulationUBOBuffer = device.createBuffer(
            BufferDescriptor(
                size = simulationUBOBufferSize.toULong(),
                usage = setOf(BufferUsage.Uniform, BufferUsage.CopyDst),
            )
        )

        computePipeline = device.createComputePipeline(
            ComputePipelineDescriptor(
                compute = ComputePipelineDescriptor.ProgrammableStage(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = particlesShaderFixed,
                        )
                    ),
                    entryPoint = "simulate",
                ),
            )
        )

        computeBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = computePipeline.getBindGroupLayout(0u),
                entries = listOf(
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 0u,
                        resource = BindGroupDescriptor.BufferBinding(
                            buffer = simulationUBOBuffer,
                        ),
                    ),
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 1u,
                        resource = BindGroupDescriptor.BufferBinding(
                            buffer = particlesBuffer,
                            offset = 0u,
                            size = (numParticles * particleInstanceByteSize).toULong(),
                        ),
                    ),
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 2u,
                        resource = BindGroupDescriptor.TextureViewBinding(texture.createView()),
                    ),
                ),
            )
        )

        val aspect = renderingContext.width.toDouble() / renderingContext.height.toDouble()
        val fox = Angle.fromRadians((2 * PI) / 5)
        projectionMatrix = Matrix4.perspective(fox, aspect, 1.0, 100.0)

        view = Matrix4.IDENTITY
            .translated(0.0, 0.0, -2.5)
            .rotated(Angle.fromRadians(PI * -0.1), 1, 0, 0)
    }

    override suspend fun AutoClosableContext.render() {

        device.queue.writeBuffer(
            simulationUBOBuffer,
            0u,
            floatArrayOf(
                if (simulate) deltaTime else 0.0f,
                0.0f,
                0.0f,
                0.0f, // padding
                rng.nextFloat() * 100f,
                rng.nextFloat() * 100f, // seed.xy
                1f + rng.nextFloat(),
                1f + rng.nextFloat(), // seed.zw
            )
        )

        val mvp = (projectionMatrix * view).copyToColumns()

        device.queue.writeBuffer(
            uniformBuffer,
            0u,
            floatArrayOf(
                // modelViewProjectionMatrix
                mvp[0], mvp[1], mvp[2], mvp[3],
                mvp[4], mvp[5], mvp[6], mvp[7],
                mvp[8], mvp[9], mvp[10], mvp[11],
                mvp[12], mvp[13], mvp[14], mvp[15],

                view.v00, view.v01, view.v02, // right

                0f, // padding

                view.v10, view.v11, view.v12, // up

                0f, // padding
            )
        )

        renderPassDescriptor = renderPassDescriptor.copy(
            colorAttachments = listOf(
                renderPassDescriptor.colorAttachments[0].copy(
                    view = renderingContext.getCurrentTexture()
                        .bind()
                        .createView()
                )
            )
        )

        val commandEncoder = device.createCommandEncoder()

        commandEncoder.beginComputePass().apply {
            setPipeline(computePipeline)
            setBindGroup(0u, computeBindGroup)
            dispatchWorkgroups(ceil(numParticles / 64.0).toUInt())
            end()
        }

        commandEncoder.beginRenderPass(renderPassDescriptor).apply {
            setPipeline(renderPipeline)
            setBindGroup(0u, uniformBindGroup)
            setVertexBuffer(0u, particlesBuffer)
            setVertexBuffer(1u, quadVertexBuffer)
            draw(6u, numParticles.toUInt(), 0u, 0u)
            end()
        }

        device.queue.submit(listOf(commandEncoder.finish()))

    }
}
