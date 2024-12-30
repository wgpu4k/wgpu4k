package io.ygdrasil.webgpu.examples.scenes.basic

import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.LoadOp
import io.ygdrasil.webgpu.PrimitiveTopology
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipeline
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.Size3D
import io.ygdrasil.webgpu.StoreOp
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.TextureUsage
import io.ygdrasil.webgpu.TextureView
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.beginRenderPass
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.webgpu.examples.scenes.shader.vertex.triangleVertexShader

class HelloTriangleMSAAScene(wgpuContext: WGPUContext) : Scene(wgpuContext) {

    lateinit var renderPipeline: RenderPipeline
    lateinit var textureView: TextureView
    val sampleCount = 4u

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
                    targets = listOf(
                        RenderPipelineDescriptor.FragmentState.ColorTargetState(
                            format = renderingContext.textureFormat
                        )
                    )
                ),
                primitive = RenderPipelineDescriptor.PrimitiveState(
                    topology = PrimitiveTopology.triangleList
                ),
                multisample = RenderPipelineDescriptor.MultisampleState(
                    count = sampleCount
                )
            )
        ).bind()

        val texture = device.createTexture(
            TextureDescriptor(
                size = Size3D(renderingContext.width, renderingContext.height),
                sampleCount = sampleCount,
                format = renderingContext.textureFormat,
                usage = setOf(TextureUsage.renderAttachment),
            )
        ).bind()
        textureView = texture.createView().bind()
    }

    override suspend fun AutoClosableContext.render() {


        val encoder = device.createCommandEncoder()
            .bind()

        val renderPassEncoder = encoder.beginRenderPass(
            RenderPassDescriptor(
                colorAttachments = listOf(
                    RenderPassDescriptor.ColorAttachment(
                        view = textureView,
                        resolveTarget = renderingContext.getCurrentTexture().createView().bind(),
                        loadOp = LoadOp.clear,
                        clearValue = Color(.0, .0, .0, 1.0),
                        storeOp = StoreOp.discard
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
