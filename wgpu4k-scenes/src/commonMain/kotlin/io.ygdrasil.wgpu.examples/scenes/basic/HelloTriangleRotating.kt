package io.ygdrasil.webgpu.examples.scenes.basic

import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.BindGroup
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.Buffer
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.BufferUsage
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.LoadOp
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipeline
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.StoreOp
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.beginRenderPass
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.webgpu.examples.scenes.shader.vertex.basicVertexPositionShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4

class HelloTriangleRotatingScene(wgpuContext: WGPUContext) : Scene(wgpuContext) {

    lateinit var renderPipeline: RenderPipeline
    lateinit var uniformBuffer: Buffer
    lateinit var uniformBindGroup: BindGroup

    override suspend  fun initialize() = with(autoClosableContext) {
        renderPipeline = device.createRenderPipeline(
            RenderPipelineDescriptor(
                vertex = RenderPipelineDescriptor.VertexState(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = basicVertexPositionShader
                        )
                    ).bind()
                ),
                fragment = RenderPipelineDescriptor.FragmentState(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = redFragmentShader
                        )
                    ).bind(),
                    targets = listOf(
                        RenderPipelineDescriptor.FragmentState.ColorTargetState(
                            format = renderingContext.textureFormat
                        )
                    )
                ),
            )
        ).bind()

        val uniformBufferSize = 4uL * 16uL // 4x4 matrix
        uniformBuffer = device.createBuffer(
            BufferDescriptor(
                size = uniformBufferSize,
                usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
            )
        ).bind()

        uniformBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = renderPipeline.getBindGroupLayout(0u).bind(),
                entries = listOf(
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 0u,
                        resource = BindGroupDescriptor.BufferBinding(
                            buffer = uniformBuffer
                        )
                    )
                )
            )
        ).bind()
    }

    override suspend fun AutoClosableContext.render() {

        val transformationMatrix = Matrix4
            .rotation(Angle.fromDegrees(frame), .0, .0, 1.0)
            .copyToColumns()

        device.queue.writeBuffer(
            uniformBuffer,
            0u,
            transformationMatrix,
            0u,
            transformationMatrix.size.toULong()
        )

        val encoder = device.createCommandEncoder()
            .bind()

        val texture = renderingContext.getCurrentTexture()
            .bind()

        encoder.beginRenderPass(
            RenderPassDescriptor(
                colorAttachments = listOf(
                    RenderPassDescriptor.ColorAttachment(
                        view =  texture.createView().bind(),
                        loadOp = LoadOp.Clear,
                        clearValue = Color(.0, .0, .0, 1.0),
                        storeOp = StoreOp.Store
                    )
                )
            )
        ) {
            setPipeline(renderPipeline)
            setBindGroup(0u, uniformBindGroup)
            draw(3u)
            end()
        }

        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(listOf(commandBuffer))

    }
}
