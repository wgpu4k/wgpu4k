package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.GenericAssetManager
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.triangleVertexShader

class HelloTriangleScene(wgpuContext: WGPUContext, assetManager: GenericAssetManager) : Application.Scene(wgpuContext, assetManager) {

    lateinit var renderPipeline: RenderPipeline

    override suspend fun initialize() = with(autoClosableContext) {
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
                            format = renderingContext.textureFormat
                        )
                    )
                ),
            )
        ).bind()
    }

    override fun render() = autoClosableContext {

        // Clear the canvas with a render pass
        val encoder = device.createCommandEncoder()
            .bind()

        val texture = renderingContext.getCurrentTexture()
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

    }
}
