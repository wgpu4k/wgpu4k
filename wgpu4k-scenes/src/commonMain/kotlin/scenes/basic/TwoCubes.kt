package io.ygdrasil.webgpu.examples.scenes.basic

import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.BindGroupEntry
import io.ygdrasil.webgpu.BufferBinding
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.ColorTargetState
import io.ygdrasil.webgpu.DepthStencilState
import io.ygdrasil.webgpu.Extent3D
import io.ygdrasil.webgpu.FragmentState
import io.ygdrasil.webgpu.GPUBindGroup
import io.ygdrasil.webgpu.GPUBuffer
import io.ygdrasil.webgpu.GPUBufferUsage
import io.ygdrasil.webgpu.GPUCompareFunction
import io.ygdrasil.webgpu.GPUCullMode
import io.ygdrasil.webgpu.GPULoadOp
import io.ygdrasil.webgpu.GPUPrimitiveTopology
import io.ygdrasil.webgpu.GPURenderPassDescriptor
import io.ygdrasil.webgpu.GPURenderPipeline
import io.ygdrasil.webgpu.GPUStoreOp
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUTextureUsage
import io.ygdrasil.webgpu.GPUVertexFormat
import io.ygdrasil.webgpu.PrimitiveState
import io.ygdrasil.webgpu.RenderPassColorAttachment
import io.ygdrasil.webgpu.RenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPipelineDescriptor
import io.ygdrasil.webgpu.ShaderModuleDescriptor
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.VertexAttribute
import io.ygdrasil.webgpu.VertexBufferLayout
import io.ygdrasil.webgpu.VertexState
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.asArraybuffer
import io.ygdrasil.webgpu.beginRenderPass
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubePositionOffset
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeUVOffset
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeVertexArray
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeVertexCount
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube.cubeVertexSize
import io.ygdrasil.webgpu.examples.scenes.shader.fragment.vertexPositionColorShader
import io.ygdrasil.webgpu.examples.scenes.shader.vertex.basicVertexShader
import io.ygdrasil.webgpu.writeInto
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI

class TwoCubesScene(wgpuContext: WGPUContext) : Scene(wgpuContext) {

	val offset = 256uL // uniformBindGroup offset must be 256-byte aligned

	lateinit var renderPipeline: GPURenderPipeline
	lateinit var projectionMatrix1: Matrix4
	lateinit var projectionMatrix2: Matrix4
	lateinit var renderPassDescriptor: GPURenderPassDescriptor
	lateinit var uniformBuffer: GPUBuffer
	lateinit var uniformBindGroup1: GPUBindGroup
	lateinit var uniformBindGroup2: GPUBindGroup
	lateinit var verticesBuffer: GPUBuffer

	override suspend fun initialize() = with(autoClosableContext) {

		// Create a vertex buffer from the cube data.
		verticesBuffer = device.createBuffer(
			BufferDescriptor(
				size = (cubeVertexArray.size * Float.SIZE_BYTES).toULong(),
				usage = setOf(GPUBufferUsage.Vertex),
				mappedAtCreation = true
			)
		).bind()

		cubeVertexArray
			.writeInto(verticesBuffer.getMappedRange())
		verticesBuffer.unmap()

		renderPipeline = device.createRenderPipeline(
			RenderPipelineDescriptor(
				vertex = VertexState(
					entryPoint = "main",
					module = device.createShaderModule(
						ShaderModuleDescriptor(
							code = basicVertexShader
						)
					).bind(), // bind to autoClosableContext to release it later
					buffers = listOf(
						VertexBufferLayout(
							arrayStride = cubeVertexSize,
							attributes = listOf(
								VertexAttribute(
									shaderLocation = 0u,
									offset = cubePositionOffset,
									format = GPUVertexFormat.Float32x4
								),
								VertexAttribute(
									shaderLocation = 1u,
									offset = cubeUVOffset,
									format = GPUVertexFormat.Float32x2
								)
							)
						)
					)
				),
				fragment = FragmentState(
					entryPoint = "main",
					module = device.createShaderModule(
						ShaderModuleDescriptor(
							code = vertexPositionColorShader
						)
					).bind(), // bind to autoClosableContext to release it later
					targets = listOf(
						ColorTargetState(
							format = renderingContext.textureFormat
						)
					)
				),
				primitive = PrimitiveState(
					topology = GPUPrimitiveTopology.TriangleList,
					cullMode = GPUCullMode.Back
				),
				depthStencil = DepthStencilState(
					depthWriteEnabled = true,
					depthCompare = GPUCompareFunction.Less,
					format = GPUTextureFormat.Depth24Plus
				)
			)
		).bind()

		val depthTexture = device.createTexture(
			TextureDescriptor(
				size = Extent3D(renderingContext.width, renderingContext.height),
				format = GPUTextureFormat.Depth24Plus,
				usage = setOf(GPUTextureUsage.RenderAttachment),
			)
		).bind()

		val matrixSize = 4uL * 16uL // 4x4 matrix
		val uniformBufferSize = offset + matrixSize
		uniformBuffer = device.createBuffer(
			BufferDescriptor(
				size = uniformBufferSize,
				usage = setOf(GPUBufferUsage.Uniform, GPUBufferUsage.CopyDst)
			)
		).bind()

		uniformBindGroup1 = device.createBindGroup(
			BindGroupDescriptor(
				layout = renderPipeline.getBindGroupLayout(0u),
				entries = listOf(
					BindGroupEntry(
						binding = 0u,
						resource = BufferBinding(
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
					BindGroupEntry(
						binding = 0u,
						resource = BufferBinding(
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
				RenderPassColorAttachment(
					view = dummyTexture.createView().bind(), // Assigned later
					loadOp = GPULoadOp.Clear,
					clearValue = Color(0.5, 0.5, 0.5, 1.0),
					storeOp = GPUStoreOp.Store,
				)
			),
			depthStencilAttachment = RenderPassDepthStencilAttachment(
				view = depthTexture.createView(),
				depthClearValue = 1.0f,
				depthLoadOp = GPULoadOp.Clear,
				depthStoreOp = GPUStoreOp.Store
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

		transformationMatrix1.asArraybuffer {
			device.queue.writeBuffer(uniformBuffer, 0u, it)
		}

		transformationMatrix2.asArraybuffer {
			device.queue.writeBuffer(uniformBuffer, offset, it)
		}

		renderPassDescriptor = (renderPassDescriptor as RenderPassDescriptor).copy(
			colorAttachments = listOf(
				(renderPassDescriptor.colorAttachments[0] as RenderPassColorAttachment).copy(
					view = renderingContext.getCurrentTexture()
						.createView()
                        .bind()
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