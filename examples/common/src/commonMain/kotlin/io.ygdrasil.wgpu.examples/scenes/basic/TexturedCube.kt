@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.AutoClosableContext
import io.ygdrasil.wgpu.examples.autoClosableContext
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube
import io.ygdrasil.wgpu.examples.scenes.shader.fragmentSampleTextureMixColorShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.basicVertexShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.js.JsExport
import kotlin.math.PI

@JsExport
class TexturedCubeScene : Application.Scene(), AutoCloseable {

	lateinit var renderPipeline: RenderPipeline
	lateinit var projectionMatrix: Matrix4
	lateinit var renderPassDescriptor: RenderPassDescriptor
	lateinit var uniformBuffer: Buffer
	lateinit var uniformBindGroup: BindGroup
	lateinit var verticesBuffer: Buffer

	val autoClosableContext = AutoClosableContext()

	override fun Application.initialiaze() = with(autoClosableContext) {

		// Create a vertex buffer from the cube data.
		verticesBuffer = device.createBuffer(
			BufferDescriptor(
				size = (Cube.cubeVertexArray.size * Float.SIZE_BYTES).toLong(),
				usage = BufferUsage.vertex.value,
				mappedAtCreation = true
			)
		)

		// Util method to use getMappedRange
		verticesBuffer.map(Cube.cubeVertexArray)
		verticesBuffer.unmap()

		renderPipeline = device.createRenderPipeline(
			RenderPipelineDescriptor(
				vertex = RenderPipelineDescriptor.VertexState(
					module = device.createShaderModule(
						ShaderModuleDescriptor(
							code = basicVertexShader
						)
					).bind(), // bind to autoClosableContext to release it later
					buffers = arrayOf(
						RenderPipelineDescriptor.VertexState.VertexBufferLayout(
							arrayStride = Cube.cubeVertexSize,
							attributes = arrayOf(
								RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
									shaderLocation = 0,
									offset = Cube.cubePositionOffset,
									format = VertexFormat.float32x4
								),
								RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
									shaderLocation = 1,
									offset = Cube.cubeUVOffset,
									format = VertexFormat.float32x2
								)
							)
						)
					)
				),
				fragment = RenderPipelineDescriptor.FragmentState(
					module = device.createShaderModule(
						ShaderModuleDescriptor(
							code = fragmentSampleTextureMixColorShader
						)
					).bind(), // bind to autoClosableContext to release it later
					targets = arrayOf(
						RenderPipelineDescriptor.FragmentState.ColorTargetState(
							format = renderingContext.textureFormat
						)
					)
				),
				primitive = RenderPipelineDescriptor.PrimitiveState(
					topology = PrimitiveTopology.trianglelist,
					cullMode = CullMode.back
				),
				depthStencil = RenderPipelineDescriptor.DepthStencilState(
					depthWriteEnabled = true,
					depthCompare = "less",
					format = TextureFormat.depth24plus
				)
			)
		).bind()

		val depthTexture = device.createTexture(
			TextureDescriptor(
				size = GPUExtent3DDictStrict(renderingContext.width, renderingContext.height),
				format = TextureFormat.depth24plus,
				usage = TextureUsage.renderattachment.value,
			)
		).bind()

		val uniformBufferSize = 4L * 16L; // 4x4 matrix
		uniformBuffer = device.createBuffer(
			BufferDescriptor(
				size = uniformBufferSize,
				usage = BufferUsage.uniform or BufferUsage.copydst
			)
		).bind()


		// Fetch the image and upload it into a GPUTexture.
		val imageBitmapWidth = 512
		val imageBitmapHeight = 512
		val cubeTexture = device.createTexture(
			TextureDescriptor(
				size = GPUExtent3DDictStrict(imageBitmapWidth, imageBitmapHeight),
				format = TextureFormat.rgba8unorm,
				usage = TextureUsage.texturebinding or TextureUsage.copydst or TextureUsage.renderattachment,
			)
		)

		device.queue.copyExternalImageToTexture(
			ImageCopyExternalImage(source = Di3d),
			ImageCopyTextureTagged(texture = cubeTexture),
			imageBitmapWidth to imageBitmapHeight
		)

		// Create a sampler with linear filtering for smooth interpolation.
		val sampler = device.createSampler(
			SamplerDescriptor(
				magFilter = "linear",
				minFilter = "linear",
			)
		)

		uniformBindGroup = device.createBindGroup(
			BindGroupDescriptor(
				layout = renderPipeline.getBindGroupLayout(0),
				entries = arrayOf(
					BindGroupDescriptor.BindGroupEntry(
						binding = 0,
						resource = BindGroupDescriptor.BufferBinding(
							buffer = uniformBuffer
						)
					),
					BindGroupDescriptor.BindGroupEntry(
						binding = 1,
						resource = BindGroupDescriptor.SamplerBinding(
							sampler = sampler
						)
					),
					BindGroupDescriptor.BindGroupEntry(
						binding = 2,
						resource = BindGroupDescriptor.TextureViewBinding(
							view = cubeTexture.createView()
						)
					)
				)
			)
		)

		renderPassDescriptor = RenderPassDescriptor(
			colorAttachments = arrayOf(
				RenderPassDescriptor.ColorAttachment(
					view = dummyTexture.createView().bind(), // Assigned later
					loadOp = "clear",
					clearValue = arrayOf(0.5, 0.5, 0.5, 1.0),
					storeOp = "store",
				)
			),
			depthStencilAttachment = RenderPassDescriptor.RenderPassDepthStencilAttachment(
				view = depthTexture.createView(),
				depthClearValue = 1.0f,
				depthLoadOp = LoadOp.clear,
				depthStoreOp = StoreOp.store
			)
		)


		val aspect = renderingContext.width / renderingContext.height.toDouble()
		val fox = Angle.fromRadians((2 * PI) / 5)
		projectionMatrix = Matrix4.perspective(fox, aspect, 1.0, 100.0)
	}

	override fun Application.render() = autoClosableContext {

		val transformationMatrix = getTransformationMatrix(
			frame / 100.0,
			projectionMatrix
		)
		device.queue.writeBuffer(
			uniformBuffer,
			0,
			transformationMatrix,
			0,
			transformationMatrix.size.toLong()
		)

		renderPassDescriptor.colorAttachments[0].view = renderingContext
			.getCurrentTexture()
			.bind()
			.createView()

		val encoder = device.createCommandEncoder()
			.bind()

		val renderPassEncoder = encoder.beginRenderPass(renderPassDescriptor)
			.bind()
		renderPassEncoder.setPipeline(renderPipeline)
		renderPassEncoder.setBindGroup(0, uniformBindGroup)
		renderPassEncoder.setVertexBuffer(0, verticesBuffer)
		renderPassEncoder.draw(Cube.cubeVertexCount)
		renderPassEncoder.end()

		val commandBuffer = encoder.finish()
			.bind()

		device.queue.submit(arrayOf(commandBuffer))

		renderingContext.present()

	}

	override fun close() {
		autoClosableContext.close()
	}

}


private fun getTransformationMatrix(angle: Double, projectionMatrix: Matrix4): FloatArray {
	var viewMatrix = Matrix4.IDENTITY
	viewMatrix = viewMatrix.translated(0, 0, -4)

	viewMatrix = viewMatrix.rotated(
		Angle.fromRadians(Angle.fromRadians(angle).sine),
		Angle.fromRadians(Angle.fromRadians(angle).cosine),
		Angle.fromRadians(0)
	)

	return (projectionMatrix * viewMatrix).copyToColumns()
}