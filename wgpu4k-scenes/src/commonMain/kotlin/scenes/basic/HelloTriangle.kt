package io.ygdrasil.webgpu.examples.scenes.basic

import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.ColorAttachment
import io.ygdrasil.webgpu.ColorTargetState
import io.ygdrasil.webgpu.FragmentState
import io.ygdrasil.webgpu.GPURenderPipeline
import io.ygdrasil.webgpu.LoadOp
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.StoreOp
import io.ygdrasil.webgpu.VertexState
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.beginRenderPass
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.webgpu.examples.scenes.shader.vertex.triangleVertexShader

class HelloTriangleScene(wgpuContext: WGPUContext) : Scene(wgpuContext) {

    lateinit var renderPipeline: GPURenderPipeline

    override suspend fun initialize() = with(autoClosableContext) {
        renderPipeline = device.createRenderPipeline(
            RenderPipelineDescriptor(
                vertex = VertexState(
                    entryPoint = "main",
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = triangleVertexShader
                        )
                    ).bind()
                ),
                fragment = FragmentState(
                    entryPoint = "main",
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = redFragmentShader
                        )
                    ).bind(),
                    targets = listOf(
                        ColorTargetState(
                            format = renderingContext.textureFormat
                        )
                    )
                ),
            )
        ).bind()
    }

    override suspend fun AutoClosableContext.render() {

        // Clear the canvas with a render pass
        val encoder = device.createCommandEncoder()
            .bind()

        val texture = renderingContext.getCurrentTexture()
            .bind()

        encoder.beginRenderPass(
            RenderPassDescriptor(
                colorAttachments = listOf(
                    ColorAttachment(
                        view = texture.createView().bind(),
                        loadOp = LoadOp.Clear,
                        clearValue = Color(.0, .0, .0, 1.0),
                        storeOp = StoreOp.Store
                    )
                )
            )
        ) {
            setPipeline(renderPipeline)
            draw(3u)
            end()
        }


        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(listOf(commandBuffer))

    }
}
