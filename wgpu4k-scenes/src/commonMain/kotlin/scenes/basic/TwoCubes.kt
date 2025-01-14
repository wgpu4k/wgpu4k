package io.ygdrasil.webgpu.examples.scenes.basic

import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.BindGroup
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.Buffer
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.BufferUsage
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.CompareFunction
import io.ygdrasil.webgpu.CullMode
import io.ygdrasil.webgpu.LoadOp
import io.ygdrasil.webgpu.PrimitiveTopology
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipeline
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.Size3D
import io.ygdrasil.webgpu.StoreOp
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.TextureFormat
import io.ygdrasil.webgpu.TextureUsage
import io.ygdrasil.webgpu.VertexFormat
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.beginRenderPass
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubePositionOffset
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeUVOffset
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeVertexArray
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeVertexCount
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeVertexSize
import io.ygdrasil.webgpu.examples.scenes.shader.fragment.vertexPositionColorShader
import io.ygdrasil.webgpu.examples.scenes.shader.vertex.basicVertexShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI

class TwoCubesScene(wgpuContext: WGPUContext) : Scene(wgpuContext) {

	val offset = 256uL // uniformBindGroup offset must be 256-byte aligned

	lateinit var renderPipeline: RenderPipeline
	lateinit var projectionMatrix1: Matrix4
	lateinit var projectionMatrix2: Matrix4
	lateinit var renderPassDescriptor: RenderPassDescriptor
	lateinit var uniformBuffer: Buffer
	lateinit var uniformBindGroup1: BindGroup
	lateinit var uniformBindGroup2: BindGroup
	lateinit var verticesBuffer: Buffer

	override suspend fun initialize() = with(autoClosableContext) {

		// Create a vertex buffer from the cube data.
		verticesBuffer = device.createBuffer(
			BufferDescriptor(
				size = (cubeVertexArray.size * Float.SIZE_BYTES).toULong(),
				usage = setOf(BufferUsage.Vertex),
				mappedAtCreation = true
			)
		).bind()

		// Util method to use getMappedRange
		verticesBuffer.mapFrom(cubeVertexArray)
		verticesBuffer.unmap()

		renderPipeline = device.createRenderPipeline(
			RenderPipelineDescriptor(
				vertex = RenderPipelineDescriptor.VertexState(
					module = device.createShaderModule(
						ShaderModuleDescriptor(
							code = basicVertexShader
						)
					).bind(), // bind to autoClosableContext to release it later
					buffers = listOf(
						RenderPipelineDescriptor.VertexState.VertexBufferLayout(
							arrayStride = cubeVertexSize,
							attributes = listOf(
								RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
									shaderLocation = 0u,
									offset = cubePositionOffset,
									format = VertexFormat.Float32x4
								),
								RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
									shaderLocation = 1u,
									offset = cubeUVOffset,
									format = VertexFormat.Float32x2
								)
							)
						)
					)
				),
				fragment = RenderPipelineDescriptor.FragmentState(
					module = device.createShaderModule(
						ShaderModuleDescriptor(
							code = vertexPositionColorShader
						)
					).bind(), // bind to autoClosableContext to release it later
					targets = listOf(
						RenderPipelineDescriptor.FragmentState.ColorTargetState(
							format = renderingContext.textureFormat
						)
					)
				),
				primitive = RenderPipelineDescriptor.PrimitiveState(
					topology = PrimitiveTopology.TriangleList,
					cullMode = CullMode.Back
				),
				depthStencil = RenderPipelineDescriptor.DepthStencilState(
					depthWriteEnabled = true,
					depthCompare = CompareFunction.Less,
					format = TextureFormat.Depth24Plus
				)
			)
		).bind()

		val depthTexture = device.createTexture(
			TextureDescriptor(
				size = Size3D(renderingContext.width, renderingContext.height),
				format = TextureFormat.Depth24Plus,
				usage = setOf(TextureUsage.RenderAttachment),
			)
		).bind()

		val matrixSize = 4uL * 16uL // 4x4 matrix
		val uniformBufferSize = offset + matrixSize
		uniformBuffer = device.createBuffer(
			BufferDescriptor(
				size = uniformBufferSize,
				usage = setOf(BufferUsage.Uniform, BufferUsage.CopyDst)
			)
		).bind()

		uniformBindGroup1 = device.createBindGroup(
			BindGroupDescriptor(
				layout = renderPipeline.getBindGroupLayout(0u),
				entries = listOf(
					BindGroupDescriptor.BindGroupEntry(
						binding = 0u,
						resource = BindGroupDescriptor.BufferBinding(
							buffer = uniformBuffer,
							offset = 0u,
							size = matrixSize
						)
					)
				)
			)
		)

		uniformBindGroup2 = device.createBindGroup(
			BindGroupDescriptor(
				layout = renderPipeline.getBindGroupLayout(0u),
				entries = listOf(
					BindGroupDescriptor.BindGroupEntry(
						binding = 0u,
						resource = BindGroupDescriptor.BufferBinding(
							buffer = uniformBuffer,
							offset = offset,
							size = matrixSize
						)
					)
				)
			)
		)

		renderPassDescriptor = RenderPassDescriptor(
			colorAttachments = listOf(
				RenderPassDescriptor.ColorAttachment(
					view = dummyTexture.createView().bind(), // Assigned later
					loadOp = LoadOp.Clear,
					clearValue = Color(0.5, 0.5, 0.5, 1.0),
					storeOp = StoreOp.Store,
				)
			),
			depthStencilAttachment = RenderPassDescriptor.DepthStencilAttachment(
				view = depthTexture.createView(),
				depthClearValue = 1.0f,
				depthLoadOp = LoadOp.Clear,
				depthStoreOp = StoreOp.Store
			)
		)


		val aspect = renderingContext.width.toDouble() / renderingContext.height.toDouble()
		val fox = Angle.fromRadians((2 * PI) / 5)
		projectionMatrix1 = Matrix4.perspective(fox, aspect, 1.0, 100.0)
			.translated(-2.0, 0.0, -7.0)
		projectionMatrix2 = Matrix4.perspective(fox, aspect, 1.0, 100.0)
			.translated(2.0, 0.0, -7.0)
	}

	override suspend fun AutoClosableContext.render() {

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
			0u,
			transformationMatrix1,
			0u,
			transformationMatrix1.size.toULong()
		)
		device.queue.writeBuffer(
			uniformBuffer,
			offset,
			transformationMatrix2,
			0u,
			transformationMatrix2.size.toULong()
		)

		renderPassDescriptor = renderPassDescriptor.copy(
			colorAttachments = listOf(
				renderPassDescriptor.colorAttachments[0].copy(
					view = renderingContext.getCurrentTexture()
						.bind()
						.createView()
				)
			)
		)

		val encoder = device.createCommandEncoder()
			.bind()

		encoder.beginRenderPass(renderPassDescriptor) {
			setPipeline(renderPipeline)
			setBindGroup(0u, uniformBindGroup1)
			setVertexBuffer(0u, verticesBuffer)

			// Bind the bind group (with the transformation matrix) for
			// each cube, and draw.
			setBindGroup(0u, uniformBindGroup1)
			draw(cubeVertexCount)

			setBindGroup(0u, uniformBindGroup2)
			draw(cubeVertexCount)

			end()
		}

		val commandBuffer = encoder.finish()
			.bind()

		device.queue.submit(listOf(commandBuffer))

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