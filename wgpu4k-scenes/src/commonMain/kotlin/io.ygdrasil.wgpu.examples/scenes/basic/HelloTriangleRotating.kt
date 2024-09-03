package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.AutoClosableContext
import io.ygdrasil.wgpu.BindGroup
import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.Buffer
import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.BufferUsage
import io.ygdrasil.wgpu.Color
import io.ygdrasil.wgpu.LoadOp
import io.ygdrasil.wgpu.RenderPassDescriptor
import io.ygdrasil.wgpu.RenderPipeline
import io.ygdrasil.wgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.StoreOp
import io.ygdrasil.wgpu.WGPUContext
import io.ygdrasil.wgpu.beginRenderPass
import io.ygdrasil.wgpu.examples.Scene
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.basicVertexPositionShader
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

        val uniformBufferSize = 4L * 16L; // 4x4 matrix
        uniformBuffer = device.createBuffer(
            BufferDescriptor(
                size = uniformBufferSize,
                usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
            )
        ).bind()

        uniformBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = renderPipeline.getBindGroupLayout(0).bind(),
                entries = listOf(
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 0,
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
            0,
            transformationMatrix,
            0,
            transformationMatrix.size.toLong()
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
                        loadOp = LoadOp.clear,
                        clearValue = Color(.0, .0, .0, 1.0),
                        storeOp = StoreOp.store
                    )
                )
            )
        ) {
            setPipeline(renderPipeline)
            setBindGroup(0, uniformBindGroup)
            draw(3)
            end()
        }

        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(listOf(commandBuffer))

    }
}
