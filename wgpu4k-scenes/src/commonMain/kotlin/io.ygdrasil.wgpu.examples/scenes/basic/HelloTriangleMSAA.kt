package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.AutoClosableContext
import io.ygdrasil.wgpu.Color
import io.ygdrasil.wgpu.LoadOp
import io.ygdrasil.wgpu.PrimitiveTopology
import io.ygdrasil.wgpu.RenderPassDescriptor
import io.ygdrasil.wgpu.RenderPipeline
import io.ygdrasil.wgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.StoreOp
import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.TextureUsage
import io.ygdrasil.wgpu.TextureView
import io.ygdrasil.wgpu.WGPUContext
import io.ygdrasil.wgpu.beginRenderPass
import io.ygdrasil.wgpu.examples.Scene
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.redFragmentShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.triangleVertexShader

class HelloTriangleMSAAScene(wgpuContext: WGPUContext) : Scene(wgpuContext) {

    lateinit var renderPipeline: RenderPipeline
    lateinit var textureView: TextureView
    val  sampleCount = 4

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
                    topology = PrimitiveTopology.trianglelist
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
            draw(3)
            end()
        }

        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(listOf(commandBuffer))

    }
}
