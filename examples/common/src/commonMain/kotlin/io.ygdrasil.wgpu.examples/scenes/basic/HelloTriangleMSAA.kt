package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.examples.*
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.triangleVertexShader

class HelloTriangleMSAAScene : Application.Scene() {

    lateinit var renderPipeline: RenderPipeline
    lateinit var textureView: TextureView
    val  sampleCount = 4

    override fun Application.initialiaze() = with(autoClosableContext) {
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
                primitive = RenderPipelineDescriptor.PrimitiveState(
                    topology = PrimitiveTopology.trianglelist
                ),
                multisample = RenderPipelineDescriptor.MultisampleState(
                    count = sampleCount
                )
            )
        ).bind()

        val texture = device.createTexture(
            TextureDescriptor(
                size = GPUExtent3DDictStrict(renderingContext.width, renderingContext.height),
                sampleCount = sampleCount,
                format = renderingContext.textureFormat,
                usage = TextureUsage.renderattachment.value,
            )
        ).bind()
        textureView = texture.createView().bind()
    }

    override fun Application.render() = autoClosableContext {


        val encoder = device.createCommandEncoder()
            .bind()

        val renderPassEncoder = encoder.beginRenderPass(
            RenderPassDescriptor(
                colorAttachments = arrayOf(
                    RenderPassDescriptor.ColorAttachment(
                        view = textureView,
                        resolveTarget = renderingContext.getCurrentTexture().createView().bind(),
                        loadOp = LoadOp.clear,
                        clearValue = arrayOf(0, 0, 0, 1.0),
                        storeOp = StoreOp.discard
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

        renderingContext.present()
    }
}
