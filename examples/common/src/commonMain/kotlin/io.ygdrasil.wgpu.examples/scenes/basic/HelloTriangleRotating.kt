package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.autoClosableContext
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.basicVertexPositionShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4

class HelloTriangleRotatingScene : Application.Scene() {

    lateinit var renderPipeline: RenderPipeline
    lateinit var uniformBuffer: Buffer
    lateinit var uniformBindGroup: BindGroup

    override suspend  fun Application.initialiaze() = with(autoClosableContext) {
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
                    targets = arrayOf(
                        RenderPipelineDescriptor.FragmentState.ColorTargetState(
                            format = surface.textureFormat
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
        )
    }

    override fun Application.render() = autoClosableContext {

        val transformationMatrix = Matrix4
            .rotation(Angle.Companion.fromDegrees(frame), .0, .0, 1.0)
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

        val texture = surface.getCurrentTexture()
            .bind()

        val renderPassEncoder = encoder.beginRenderPass(
            RenderPassDescriptor(
                colorAttachments = arrayOf(
                    RenderPassDescriptor.ColorAttachment(
                        view =  texture.createView().bind(),
                        loadOp = LoadOp.clear,
                        clearValue = arrayOf(0, 0, 0, 1.0),
                        storeOp = StoreOp.store
                    )
                )
            )
        ).bind()

        renderPassEncoder.setPipeline(renderPipeline)
        renderPassEncoder.setBindGroup(0, uniformBindGroup)
        renderPassEncoder.draw(3)
        renderPassEncoder.end()

        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(arrayOf(commandBuffer))

        surface.present()
    }
}
