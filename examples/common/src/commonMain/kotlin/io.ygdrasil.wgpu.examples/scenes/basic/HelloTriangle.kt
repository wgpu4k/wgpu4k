package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.autoClosableContext
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.triangleVertexShader

class HelloTriangleScene : Application.Scene() {

    lateinit var renderPipeline: RenderPipeline

    override suspend fun Application.initialiaze() = with(autoClosableContext) {
        renderPipeline = device.createRenderPipeline(
            RenderPipelineDescriptor(
                vertex = RenderPipelineDescriptor.VertexState(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = triangleVertexShader
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
    }

    override fun Application.render() = autoClosableContext {

        // Clear the canvas with a render pass
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
        renderPassEncoder.draw(3)
        renderPassEncoder.end()

        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(arrayOf(commandBuffer))

        surface.present()
    }
}
