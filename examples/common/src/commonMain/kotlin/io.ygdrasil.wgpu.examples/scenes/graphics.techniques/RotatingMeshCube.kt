package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.RenderPipelineDescriptor.VertexState
import io.ygdrasil.wgpu.RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.autoClosableContext
import io.ygdrasil.wgpu.examples.helper.GLTF2RenderContext
import io.ygdrasil.wgpu.examples.helper.convertToVertexType
import io.ygdrasil.wgpu.examples.helper.convertToWGSLFormat
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.basicVertexShader2
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI
import kotlin.math.max

val localVertexCount = 24

class RotatingMeshCubeScene : Application.Scene(), AutoCloseable {

    private lateinit var gltF2RenderContext: GLTF2RenderContext
    lateinit var renderPipeline: RenderPipeline
    lateinit var projectionMatrix: Matrix4
    lateinit var renderPassDescriptor: RenderPassDescriptor
    lateinit var uniformBuffer: Buffer
    lateinit var uniformBindGroup: BindGroup

    override fun Application.initialiaze() = with(autoClosableContext) {

        gltF2RenderContext = GLTF2RenderContext(
            device = device,
            gltf2 = boxMesh,
            autoClosableContext = autoClosableContext
        )
        val mesh = boxMesh.meshes[0]
        val primitive = mesh.primitives[0]
        val vertexInputShaderStringBuilder = StringBuilder("struct VertexInput {\n")
        val vertexBuffers = primitive.attributes.map { (attribute, index) ->
            val accessor = boxMesh.accessors[index]
            val attrString = attribute.str.lowercase()
            vertexInputShaderStringBuilder.append(
                "\t@location(${index}) $attrString: ${accessor.convertToWGSLFormat()},\n"
            )
            val bufferView = boxMesh.bufferViews.get(accessor.bufferView)
            val format = accessor.convertToVertexType()
            VertexState.VertexBufferLayout(
                arrayStride = max(format.sizeInByte, bufferView.byteStride).toLong(),
                attributes = arrayOf(
                    VertexAttribute(
                        format = format,
                        offset = accessor.byteOffset.toLong(),
                        shaderLocation = index
                    )
                )
            )
        }
        vertexInputShaderStringBuilder.append("}\n")
        val vertexInputShaderString = vertexInputShaderStringBuilder.toString()

        renderPipeline = device.createRenderPipeline(
            RenderPipelineDescriptor(
                vertex = VertexState(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = basicVertexShader2
                        )
                    ).bind(), // bind to autoClosableContext to release it later
                    buffers = vertexBuffers.toTypedArray()
                ),
                fragment = RenderPipelineDescriptor.FragmentState(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = redFragmentShader
                        )
                    ).bind(), // bind to autoClosableContext to release it later
                    targets = arrayOf(
                        RenderPipelineDescriptor.FragmentState.ColorTargetState(
                            format = renderingContext.textureFormat
                        )
                    )
                ),
                primitive = RenderPipelineDescriptor.PrimitiveState(
                    topology = PrimitiveTopology.trianglelist,
                    cullMode = CullMode.back
                ),
                depthStencil = RenderPipelineDescriptor.DepthStencilState(
                    depthWriteEnabled = true,
                    depthCompare = CompareFunction.less,
                    format = TextureFormat.depth24plus
                ),
                multisample = RenderPipelineDescriptor.MultisampleState(
                    count = 1,
                    mask = 0xFFFFFFFu
                )
            )
        ).bind()

        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Size3D(renderingContext.width, renderingContext.height),
                format = TextureFormat.depth24plus,
                usage = setOf(TextureUsage.renderattachment),
            )
        ).bind()

        val uniformBufferSize = 4L * 16L; // 4x4 matrix
        uniformBuffer = device.createBuffer(
            BufferDescriptor(
                size = uniformBufferSize,
                usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
            )
        ).bind()

        uniformBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = renderPipeline.getBindGroupLayout(0),
                entries = arrayOf(
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 0,
                        resource = BindGroupDescriptor.BufferBinding(
                            buffer = uniformBuffer
                        )
                    )
                )
            )
        ).bind()

        renderPassDescriptor = RenderPassDescriptor(
            colorAttachments = arrayOf(
                RenderPassDescriptor.ColorAttachment(
                    view = dummyTexture.createView().bind(), // Assigned later
                    loadOp = LoadOp.clear,
                    clearValue = arrayOf(0.5, 0.5, 0.5, 1.0),
                    storeOp = StoreOp.store,
                )
            ),
            depthStencilAttachment = RenderPassDescriptor.RenderPassDepthStencilAttachment(
                view = depthTexture.createView(),
                depthClearValue = 1.0f,
                depthLoadOp = LoadOp.clear,
                depthStoreOp = StoreOp.store
            )
        )


        val aspect = renderingContext.width / renderingContext.height.toDouble()
        val fox = Angle.fromRadians((2 * PI) / 5)
        projectionMatrix = Matrix4.perspective(fox, aspect, 1.0, 100.0)
    }

    override fun Application.render() = autoClosableContext {

        val transformationMatrix = getTransformationMatrix(
            frame / 100.0,
            projectionMatrix
        )
        device.queue.writeBuffer(
            uniformBuffer,
            0,
            transformationMatrix,
            0,
            transformationMatrix.size.toLong()
        )

        renderPassDescriptor = renderPassDescriptor.copy(
            colorAttachments = arrayOf(
                renderPassDescriptor.colorAttachments[0].copy(
                    view = renderingContext.getCurrentTexture()
                        .bind()
                        .createView()
                )
            )
        )

        val encoder = device.createCommandEncoder()
            .bind()

        val renderPassEncoder = encoder.beginRenderPass(renderPassDescriptor)
            .bind()
        renderPassEncoder.setPipeline(renderPipeline)
        renderPassEncoder.setBindGroup(0, uniformBindGroup)
        val gltf2 = gltF2RenderContext.gltf2
        gltf2.meshes.forEach { mesh ->
            mesh.primitives.forEach { primitive ->
                primitive.attributes.forEach { (_, index) ->
                    gltF2RenderContext.buffers[gltf2.bufferViews[index]]?.let { buffer ->
                        renderPassEncoder.setVertexBuffer(index, buffer)
                    }
                }

                if (primitive.indices != null) gltF2RenderContext.buffers[gltf2.bufferViews[primitive.indices]]?.let { buffer ->
                    renderPassEncoder.setIndexBuffer(buffer, IndexFormat.uint16)
                }
            }

        }
        renderPassEncoder.draw(localVertexCount)
        renderPassEncoder.end()

        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(arrayOf(commandBuffer))

        renderingContext.present()

    }

    override fun close() {
        autoClosableContext.close()
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