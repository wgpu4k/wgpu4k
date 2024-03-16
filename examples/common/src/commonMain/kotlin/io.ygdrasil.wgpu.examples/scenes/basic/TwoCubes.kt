@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.AutoClosableContext
import io.ygdrasil.wgpu.examples.autoClosableContext
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubePositionOffset
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeUVOffset
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexArray
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexCount
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexSize
import io.ygdrasil.wgpu.examples.scenes.shader.fragmentVertexPositionColorShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.basicVertexShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.js.JsExport
import kotlin.math.PI

@JsExport
class TwoCubesScene : Application.Scene(), AutoCloseable {

	val offset = 256L; // uniformBindGroup offset must be 256-byte aligned

	lateinit var renderPipeline: RenderPipeline
	lateinit var projectionMatrix1: Matrix4
	lateinit var projectionMatrix2: Matrix4
	lateinit var renderPassDescriptor: RenderPassDescriptor
	lateinit var uniformBuffer: Buffer
	lateinit var uniformBindGroup1: BindGroup
	lateinit var uniformBindGroup2: BindGroup
	lateinit var verticesBuffer: Buffer

	val autoClosableContext = AutoClosableContext()

	override fun Application.initialiaze() = with(autoClosableContext) {

		// Create a vertex buffer from the cube data.
		verticesBuffer = device.createBuffer(
			BufferDescriptor(
				size = (cubeVertexArray.size * Float.SIZE_BYTES).toLong(),
				usage = BufferUsage.vertex.value,
				mappedAtCreation = true
			)
		)

		// Util method to use getMappedRange
		verticesBuffer.map(cubeVertexArray)
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
							arrayStride = cubeVertexSize,
							attributes = arrayOf(
								RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
									shaderLocation = 0,
									offset = cubePositionOffset,
									format = VertexFormat.float32x4
								),
								RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
									shaderLocation = 1,
									offset = cubeUVOffset,
									format = VertexFormat.float32x2
								)
							)
						)
					)
				),
				fragment = RenderPipelineDescriptor.FragmentState(
					module = device.createShaderModule(
						ShaderModuleDescriptor(
							code = fragmentVertexPositionColorShader
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

		val matrixSize = 4L * 16L; // 4x4 matrix
		val uniformBufferSize = offset + matrixSize;
		uniformBuffer = device.createBuffer(
			BufferDescriptor(
				size = uniformBufferSize,
				usage = BufferUsage.uniform or BufferUsage.copydst
			)
		).bind()

		uniformBindGroup1 = device.createBindGroup(
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

		uniformBindGroup2 = device.createBindGroup(
			BindGroupDescriptor(
				layout = renderPipeline.getBindGroupLayout(0),
				entries = arrayOf(
					BindGroupDescriptor.BindGroupEntry(
						binding = 0,
						resource = BindGroupDescriptor.BufferBinding(
							buffer = uniformBuffer,
							offset = offset
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
		projectionMatrix1 = Matrix4.perspective(fox, aspect, 1.0, 100.0)
			.translated(-2.0, 0.0, -7.0)
		projectionMatrix2 = Matrix4.perspective(fox, aspect, 1.0, 100.0)
			.translated(2.0, 0.0, -7.0)
	}

	override fun Application.render() = autoClosableContext {

		val transformationMatrix1 = getTransformationMatrix(
			frame / 100.0,
			projectionMatrix1
		)
		val transformationMatrix2 = getTransformationMatrix(
			frame / 100.0,
			projectionMatrix2
		)
		device.queue.writeBuffer(
			uniformBuffer,
			0,
			transformationMatrix1,
			0,
			transformationMatrix1.size.toLong()
		)
		device.queue.writeBuffer(
			uniformBuffer,
			offset,
			transformationMatrix2,
			0,
			transformationMatrix2.size.toLong()
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
		renderPassEncoder.setBindGroup(0, uniformBindGroup1)
		renderPassEncoder.setVertexBuffer(0, verticesBuffer)

		// Bind the bind group (with the transformation matrix) for
		// each cube, and draw.
		renderPassEncoder.setBindGroup(0, uniformBindGroup1);
		renderPassEncoder.draw(cubeVertexCount);

		renderPassEncoder.setBindGroup(0, uniformBindGroup2);
		renderPassEncoder.draw(cubeVertexCount);

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

	viewMatrix = viewMatrix.rotated(
		Angle.fromRadians(Angle.fromRadians(angle).sine),
		Angle.fromRadians(Angle.fromRadians(angle).cosine),
		Angle.fromRadians(0)
	)

	return (projectionMatrix * viewMatrix).copyToColumns()
}