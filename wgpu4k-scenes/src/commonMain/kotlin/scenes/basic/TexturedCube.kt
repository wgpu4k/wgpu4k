package io.ygdrasil.webgpu.examples.scenes.basic

import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.BindGroup
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.Buffer
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.BufferUsage
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.CompareFunction
import io.ygdrasil.webgpu.CullMode
import io.ygdrasil.webgpu.FilterMode
import io.ygdrasil.webgpu.ImageCopyExternalImage
import io.ygdrasil.webgpu.ImageCopyTextureTagged
import io.ygdrasil.webgpu.LoadOp
import io.ygdrasil.webgpu.PrimitiveTopology
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipeline
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.SamplerDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.Size3D
import io.ygdrasil.webgpu.StoreOp
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.TextureFormat
import io.ygdrasil.webgpu.TextureUsage
import io.ygdrasil.webgpu.VertexFormat
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.beginRenderPass
import io.ygdrasil.webgpu.examples.AssetManager
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube
import io.ygdrasil.webgpu.examples.scenes.shader.fragment.sampleTextureMixColorShader
import io.ygdrasil.webgpu.examples.scenes.shader.vertex.basicVertexShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI

class TexturedCubeScene(wgpuContext: WGPUContext, assetManager: AssetManager) : Scene(wgpuContext), AssetManager by assetManager {

    lateinit var renderPipeline: RenderPipeline
    lateinit var projectionMatrix: Matrix4
    lateinit var renderPassDescriptor: RenderPassDescriptor
    lateinit var uniformBuffer: Buffer
    lateinit var uniformBindGroup: BindGroup
    lateinit var verticesBuffer: Buffer

    override suspend fun initialize() = with(autoClosableContext) {

        // Create a vertex buffer from the cube data.
        verticesBuffer = device.createBuffer(
            BufferDescriptor(
                size = (Cube.cubeVertexArray.size * Float.SIZE_BYTES).toULong(),
                usage = setOf(BufferUsage.Vertex),
                mappedAtCreation = true
            )
        )

        // Util method to use getMappedRange
        verticesBuffer.mapFrom(Cube.cubeVertexArray)
        verticesBuffer.unmap()

        renderPipeline = device.createRenderPipeline(
            RenderPipelineDescriptor(
                vertex = RenderPipelineDescriptor.VertexState(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = basicVertexShader
                        )
                    ).bind(), // bind to autoClosableContext to release it later
                    buffers = listOf(
                        RenderPipelineDescriptor.VertexState.VertexBufferLayout(
                            arrayStride = Cube.cubeVertexSize,
                            attributes = listOf(
                                RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
                                    shaderLocation = 0u,
                                    offset = Cube.cubePositionOffset,
                                    format = VertexFormat.Float32x4
                                ),
                                RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
                                    shaderLocation = 1u,
                                    offset = Cube.cubeUVOffset,
                                    format = VertexFormat.Float32x2
                                )
                            )
                        )
                    )
                ),
                fragment = RenderPipelineDescriptor.FragmentState(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = sampleTextureMixColorShader
                        )
                    ).bind(), // bind to autoClosableContext to release it later
                    targets = listOf(
                        RenderPipelineDescriptor.FragmentState.ColorTargetState(
                            format = renderingContext.textureFormat
                        )
                    )
                ),
                primitive = RenderPipelineDescriptor.PrimitiveState(
                    topology = PrimitiveTopology.TriangleList,
                    cullMode = CullMode.Back
                ),
                depthStencil = RenderPipelineDescriptor.DepthStencilState(
                    depthWriteEnabled = true,
                    depthCompare = CompareFunction.Less,
                    format = TextureFormat.Depth24Plus
                )
            )
        ).bind()

        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Size3D(renderingContext.width, renderingContext.height),
                format = TextureFormat.Depth24Plus,
                usage = setOf(TextureUsage.RenderAttachment),
            )
        ).bind()

        val uniformBufferSize = 4uL * 16uL // 4x4 matrix
        uniformBuffer = device.createBuffer(
            BufferDescriptor(
                size = uniformBufferSize,
                usage = setOf(BufferUsage.Uniform, BufferUsage.CopyDst)
            )
        ).bind()


        // Fetch the image and upload it into a GPUTexture.
        val imageBitmapWidth = 512u
        val imageBitmapHeight = 512u
        val cubeTexture = device.createTexture(
            TextureDescriptor(
                size = Size3D(imageBitmapWidth, imageBitmapHeight),
                format = renderingContext.textureFormat,
                usage = setOf(TextureUsage.TextureBinding, TextureUsage.CopyDst, TextureUsage.RenderAttachment),
            )
        )

        device.queue.copyExternalImageToTexture(
            ImageCopyExternalImage(source = Di3d),
            ImageCopyTextureTagged(texture = cubeTexture),
            imageBitmapWidth to imageBitmapHeight
        )

        // Create a sampler with linear filtering for smooth interpolation.
        val sampler = device.createSampler(
            SamplerDescriptor(
                magFilter = FilterMode.Linear,
                minFilter = FilterMode.Linear,
            )
        )

        uniformBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = renderPipeline.getBindGroupLayout(0u),
                entries = listOf(
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 0u,
                        resource = BindGroupDescriptor.BufferBinding(
                            buffer = uniformBuffer
                        )
                    ),
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 1u,
                        resource = BindGroupDescriptor.SamplerBinding(
                            sampler = sampler
                        )
                    ),
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 2u,
                        resource = BindGroupDescriptor.TextureViewBinding(
                            view = cubeTexture.createView()
                        )
                    )
                )
            )
        )

        renderPassDescriptor = RenderPassDescriptor(
            colorAttachments = listOf(
                RenderPassDescriptor.ColorAttachment(
                    view = dummyTexture.createView().bind(), // Assigned later
                    loadOp = LoadOp.Clear,
                    clearValue = Color(0.5, 0.5, 0.5, 1.0),
                    storeOp = StoreOp.Store,
                )
            ),
            depthStencilAttachment = RenderPassDescriptor.DepthStencilAttachment(
                view = depthTexture.createView(),
                depthClearValue = 1.0f,
                depthLoadOp = LoadOp.Clear,
                depthStoreOp = StoreOp.Store
            )
        )


        val aspect = renderingContext.width.toDouble() / renderingContext.height.toDouble()
        val fox = Angle.fromRadians((2 * PI) / 5)
        projectionMatrix = Matrix4.perspective(fox, aspect, 1.0, 100.0)
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

        renderPassDescriptor = renderPassDescriptor.copy(
            colorAttachments = listOf(
                renderPassDescriptor.colorAttachments[0].copy(
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
            draw(Cube.cubeVertexCount)
            end()
        }

        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(listOf(commandBuffer))


    }

}


private fun getTransformationMatrix(angle: Double, projectionMatrix: Matrix4): FloatArray {
    var viewMatrix = Matrix4.IDENTITY
    viewMatrix = viewMatrix.translated(0, 0, -4)

    viewMatrix = viewMatrix.rotated(
        Angle.fromRadians(Angle.fromRadians(angle).sine),
        Angle.fromRadians(Angle.fromRadians(angle).cosine),
        Angle.fromRadians(0)
    )

    return (projectionMatrix * viewMatrix).copyToColumns()
}