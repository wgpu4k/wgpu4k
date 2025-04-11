@file:Suppress("DEPRECATION")

package io.ygdrasil.webgpu.examples.scenes.basic

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.BindGroupEntry
import io.ygdrasil.webgpu.BufferBinding
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.ColorTargetState
import io.ygdrasil.webgpu.DepthStencilState
import io.ygdrasil.webgpu.Extent3D
import io.ygdrasil.webgpu.FragmentState
import io.ygdrasil.webgpu.GPUBindGroup
import io.ygdrasil.webgpu.GPUBuffer
import io.ygdrasil.webgpu.GPUBufferUsage
import io.ygdrasil.webgpu.GPUCompareFunction
import io.ygdrasil.webgpu.GPUCullMode
import io.ygdrasil.webgpu.GPUFilterMode
import io.ygdrasil.webgpu.GPULoadOp
import io.ygdrasil.webgpu.GPUPrimitiveTopology
import io.ygdrasil.webgpu.GPURenderPassDescriptor
import io.ygdrasil.webgpu.GPURenderPipeline
import io.ygdrasil.webgpu.GPUStoreOp
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUTextureUsage
import io.ygdrasil.webgpu.GPUTextureViewDimension
import io.ygdrasil.webgpu.GPUVertexFormat
import io.ygdrasil.webgpu.ImageCopyExternalImage
import io.ygdrasil.webgpu.ImageCopyTextureTagged
import io.ygdrasil.webgpu.Origin3D
import io.ygdrasil.webgpu.PrimitiveState
import io.ygdrasil.webgpu.RenderPassColorAttachment
import io.ygdrasil.webgpu.RenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.SamplerDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.TextureViewDescriptor
import io.ygdrasil.webgpu.VertexAttribute
import io.ygdrasil.webgpu.VertexBufferLayout
import io.ygdrasil.webgpu.VertexState
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.beginRenderPass
import io.ygdrasil.webgpu.copyExternalImageToTexture
import io.ygdrasil.webgpu.examples.AssetManager
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubePositionOffset
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeUVOffset
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeVertexArray
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeVertexCount
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeVertexSize
import io.ygdrasil.webgpu.examples.scenes.shader.fragment.sampleCubemapShader
import io.ygdrasil.webgpu.examples.scenes.shader.vertex.basicVertexShader
import io.ygdrasil.webgpu.mapFrom
import io.ygdrasil.webgpu.writeBuffer
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI


class CubemapScene(wgpuContext: WGPUContext, assetManager: AssetManager) : Scene(wgpuContext),
    AssetManager by assetManager {

    private val logger = KotlinLogging.logger {}

    lateinit var renderPipeline: GPURenderPipeline
    lateinit var projectionMatrix: Matrix4
    lateinit var renderPassDescriptor: GPURenderPassDescriptor
    lateinit var uniformBuffer: GPUBuffer
    lateinit var uniformBindGroup: GPUBindGroup
    lateinit var verticesBuffer: GPUBuffer

    val modelMatrix = Matrix4.scale(1000, 1000, 1000)
    val depthLayer = 6u

    override suspend fun initialize() = with(autoClosableContext) {
        logger.trace { "Initializing CubeMapScene" }
        // Create a vertex buffer from the cube data.
        verticesBuffer = device.createBuffer(
            BufferDescriptor(
                size = (cubeVertexArray.size * Float.SIZE_BYTES).toULong(),
                usage = setOf(GPUBufferUsage.Vertex),
                mappedAtCreation = true
            )
        )

        // Util method to use getMappedRange
        verticesBuffer.mapFrom(cubeVertexArray)
        verticesBuffer.unmap()

        renderPipeline = device.createRenderPipeline(
            RenderPipelineDescriptor(
                vertex = VertexState(
                    entryPoint = "main",
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = basicVertexShader
                        )
                    ).bind(), // bind to autoClosableContext to release it later
                    buffers = listOf(
                        VertexBufferLayout(
                            arrayStride = cubeVertexSize,
                            attributes = listOf(
                                VertexAttribute(
                                    shaderLocation = 0u,
                                    offset = cubePositionOffset,
                                    format = GPUVertexFormat.Float32x4
                                ),
                                VertexAttribute(
                                    shaderLocation = 1u,
                                    offset = cubeUVOffset,
                                    format = GPUVertexFormat.Float32x2
                                )
                            )
                        )
                    )
                ),
                fragment = FragmentState(
                    entryPoint = "main",
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = sampleCubemapShader
                        )
                    ).bind(), // bind to autoClosableContext to release it later
                    targets = listOf(
                        ColorTargetState(
                            format = renderingContext.textureFormat
                        )
                    )
                ),
                primitive = PrimitiveState(
                    topology = GPUPrimitiveTopology.TriangleList,
                    cullMode = GPUCullMode.None
                ),
                depthStencil = DepthStencilState(
                    depthWriteEnabled = true,
                    depthCompare = GPUCompareFunction.Less,
                    format = GPUTextureFormat.Depth24Plus
                )
            )
        ).bind()

        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Extent3D(renderingContext.width, renderingContext.height),
                format = GPUTextureFormat.Depth24Plus,
                usage = setOf(GPUTextureUsage.RenderAttachment),
            )
        ).bind()

        val imageBitmaps = listOf(
            cubemapPosx,
            cubemapNegx,
            cubemapPosy,
            cubemapNegy,
            cubemapPosz,
            cubemapNegz,
        )

        val cubemapTexture = device.createTexture(
            TextureDescriptor(
                // Create a 2d array texture.
                // Assume each image has the same size.
                size = Extent3D(imageBitmaps[0].width, imageBitmaps[0].height, depthLayer),
                format = renderingContext.textureFormat,
                usage = setOf(GPUTextureUsage.TextureBinding, GPUTextureUsage.CopyDst, GPUTextureUsage.RenderAttachment),
            )
        ).bind()

        imageBitmaps.forEachIndexed { index, imageBitmap ->
            logger.debug { "Copying image bitmap to texture: $index" }
            device.queue.copyExternalImageToTexture(
                ImageCopyExternalImage(source = imageBitmap),
                ImageCopyTextureTagged(texture = cubemapTexture, origin = Origin3D(0u, 0u, index.toUInt())),
                Extent3D(imageBitmap.width, imageBitmap.height, 0u)
            )
        }

        val uniformBufferSize = 4uL * 16uL // 4x4 matrix
        uniformBuffer = device.createBuffer(
            BufferDescriptor(
                size = uniformBufferSize,
                usage = setOf(GPUBufferUsage.Uniform, GPUBufferUsage.CopyDst)
            )
        ).bind()

        val sampler = device.createSampler(
            SamplerDescriptor(
                magFilter = GPUFilterMode.Linear,
                minFilter = GPUFilterMode.Linear,
            )
        ).bind()

        uniformBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = renderPipeline.getBindGroupLayout(0u),
                entries = listOf(
                    BindGroupEntry(
                        binding = 0u,
                        resource = BufferBinding(
                            buffer = uniformBuffer,
                            offset = 0u,
                            size = uniformBufferSize
                        )
                    ),
                    BindGroupEntry(
                        binding = 1u,
                        resource = sampler
                    ),
                    BindGroupEntry(
                        binding = 2u,
                        resource = cubemapTexture.createView(
                            TextureViewDescriptor(
                                dimension = GPUTextureViewDimension.Cube,
                                arrayLayerCount = depthLayer
                            )
                        ).bind()
                    )
                )
            )
        )

        renderPassDescriptor = RenderPassDescriptor(
            colorAttachments = listOf(
                RenderPassColorAttachment(
                    view = dummyTexture.createView().bind(), // Assigned later
                    loadOp = GPULoadOp.Clear,
                    clearValue = Color(0.5, 0.5, 0.5, 1.0),
                    storeOp = GPUStoreOp.Store,
                )
            ),
            depthStencilAttachment = RenderPassDepthStencilAttachment(
                view = depthTexture.createView(),
                depthClearValue = 1.0f,
                depthLoadOp = GPULoadOp.Clear,
                depthStoreOp = GPUStoreOp.Store
            )
        )


        val aspect = renderingContext.width.toDouble() / renderingContext.height.toDouble()
        val fox = Angle.fromRadians((2 * PI) / 5)
        projectionMatrix = Matrix4.perspective(fox, aspect, 1.0, 3000.0)

        logger.trace { "Initialized CubeMapScene" }
    }

    override suspend fun AutoClosableContext.render() {

        val transformationMatrix = getTransformationMatrix(
            frame / 100.0,
            projectionMatrix
        )
        device.queue.writeBuffer(
            uniformBuffer,
            0u,
            transformationMatrix,
            0u,
            transformationMatrix.size.toULong()
        )

        renderPassDescriptor = (renderPassDescriptor as RenderPassDescriptor).copy(
            colorAttachments = listOf(
                (renderPassDescriptor.colorAttachments[0] as RenderPassColorAttachment).copy(
                    view = renderingContext.getCurrentTexture()
                        .bind()
                        .createView()
                )
            )
        )

        val encoder = device.createCommandEncoder()
            .bind()

        encoder.beginRenderPass(renderPassDescriptor) {
            setPipeline(renderPipeline)
            setBindGroup(0u, uniformBindGroup)
            setVertexBuffer(0u, verticesBuffer)
            draw(cubeVertexCount)
            end()
        }


        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(listOf(commandBuffer))
    }

    override fun close() {
        autoClosableContext.close()
    }

    fun getTransformationMatrix(angle: Double, projectionMatrix: Matrix4): FloatArray {

        var viewMatrix = Matrix4.IDENTITY
        viewMatrix *= Matrix4.rotation(
            angle = Angle.fromRadians((PI / 10) * Angle.fromRadians(angle).sine),
            x = 1, y = 0, z = 0
        )
        viewMatrix *= Matrix4.rotation(
            angle = Angle.fromRadians(angle * 0.2),
            x = 0, y = 1, z = 0
        )
        val modelViewProjectionMatrix = viewMatrix * modelMatrix

        return (projectionMatrix * modelViewProjectionMatrix).copyToColumns()
    }
}


