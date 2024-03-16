package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.*

class SimpleTriangleScene : Application.Scene() {

	lateinit var renderPipeline: RenderPipeline

	override fun Application.initialiaze() {
		val shaderModule = device.createShaderModule(
			ShaderModuleDescriptor(
				code = shader
			)
		)

		val pipelineLayout = device.createPipelineLayout(PipelineLayoutDescriptor())

		renderPipeline = device.createRenderPipeline(
			RenderPipelineDescriptor(
				layout = pipelineLayout,
				vertex = RenderPipelineDescriptor.VertexState(
					module = shaderModule,
					entryPoint = "vs_main"
				),
				fragment = RenderPipelineDescriptor.FragmentState(
					module = shaderModule,
					entryPoint = "fs_main",
					targets = arrayOf(
						RenderPipelineDescriptor.FragmentState.ColorTargetState(
							format = renderingContext.textureFormat,
							writeMask = ColorWriteMask.all
						)
					)
				),
				primitive = RenderPipelineDescriptor.PrimitiveState(
					topology = PrimitiveTopology.trianglelist
				),
				multisample = RenderPipelineDescriptor.MultisampleState(
					count = 1,
					mask = 0xFFFFFFF
				)
			)
		)
	}

	override fun Application.render() = autoClosableContext {

		// Clear the canvas with a render pass
		val encoder = device.createCommandEncoder()
			.bind()

		val texture = renderingContext.getCurrentTexture()
			.bind()
		val view = texture.createView()
			.bind()

		val renderPassEncoder = encoder.beginRenderPass(
			RenderPassDescriptor(
				colorAttachments = arrayOf(
					RenderPassDescriptor.ColorAttachment(
						view = view,
						loadOp = "clear",
						clearValue = arrayOf(0, 1.0, 0, 1.0),
						storeOp = "store"
					)
				)
			)
		)
			.bind()

		renderPassEncoder.setPipeline(renderPipeline)
		renderPassEncoder.draw(3, 1, 0, 0)
		renderPassEncoder.end()

		val commandBuffer = encoder.finish()
			.bind()

		device.queue.submit(arrayOf(commandBuffer))

		renderingContext.present()

	}
}

private val shader = """
	@vertex
	fn vs_main(@builtin(vertex_index) in_vertex_index: u32) -> @builtin(position) vec4<f32> {
	    let x = f32(i32(in_vertex_index) - 1);
	    let y = f32(i32(in_vertex_index & 1u) * 2 - 1);
	    return vec4<f32>(x, y, 0.0, 1.0);
	}

	@fragment
	fn fs_main() -> @location(0) vec4<f32> {
	    return vec4<f32>(1.0, 0.0, 0.0, 1.0);
	}
""".trimIndent()