package io.ygdrasil.webgpu.examples.scenes.basic

import io.ygdrasil.webgpu.*
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.scenes.mesh.Cube
import io.ygdrasil.webgpu.examples.scenes.shader.fragment.vertexPositionColorShader
import io.ygdrasil.webgpu.examples.scenes.shader.vertex.instancedShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI

val xCount = 4
val yCount = 4
val numInstances = (xCount * yCount).toULong()

class InstancedCubeScene(wgpuContext: WGPUContext) : Scene(wgpuContext) {


	lateinit var renderPipeline: GPURenderPipeline
	lateinit var projectionMatrix: Matrix4
	lateinit var renderPassDescriptor: GPURenderPassDescriptor
	lateinit var uniformBuffer: GPUBuffer
	lateinit var uniformBindGroup: GPUBindGroup
	lateinit var verticesBuffer: GPUBuffer
	val modelMatrices = Array<Matrix4?>(numInstances.toInt()) { null }

	override suspend fun initialize() = with(autoClosableContext) {

		// Create a vertex buffer from the cube data.
		verticesBuffer = device.createBuffer(
			BufferDescriptor(
				size = (Cube.cubeVertexArray.size * Float.SIZE_BYTES).toULong(),
				usage = setOf(BufferUsage.Vertex),
				mappedAtCreation = true
			)
		)

		// Util method to use getMappedRange
		verticesBuffer.mapFrom(Cube.cubeVertexArray)
		verticesBuffer.unmap()

		renderPipeline = device.createRenderPipeline(
			RenderPipelineDescriptor(
				vertex = VertexState(
					module = device.createShaderModule(
						ShaderModuleDescriptor(
							code = instancedShader
						)
					).bind(), // bind to autoClosableContext to release it later
					buffers = listOf(
						VertexBufferLayout(
							arrayStride = Cube.cubeVertexSize,
							attributes = listOf(
								VertexAttribute(
									shaderLocation = 0u,
									offset = Cube.cubePositionOffset,
									format = VertexFormat.Float32x4
								),
								VertexAttribute(
									shaderLocation = 1u,
									offset = Cube.cubeUVOffset,
									format = VertexFormat.Float32x2
								)
							)
						)
					)
				),
				fragment = FragmentState(
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
					topology = PrimitiveTopology.TriangleList,
					cullMode = CullMode.Back
				),
				depthStencil = DepthStencilState(
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

		val uniformBufferSize = numInstances * 4uL * 16uL // 4x4 matrix
		uniformBuffer = device.createBuffer(
			BufferDescriptor(
				size = uniformBufferSize,
				usage = setOf(BufferUsage.Uniform, BufferUsage.CopyDst)
			)
		).bind()

		uniformBindGroup = device.createBindGroup(
			BindGroupDescriptor(
				layout = renderPipeline.getBindGroupLayout(0u),
				entries = listOf(
					BindGroupEntry(
						binding = 0u,
						resource = BufferBinding(
							buffer = uniformBuffer
						)
					)
				)
			)
		)

		renderPassDescriptor = RenderPassDescriptor(
			colorAttachments = listOf(
				ColorAttachment(
					view = dummyTexture.createView().bind(), // Assigned later
					loadOp = LoadOp.Clear,
					clearValue = Color(0.5, 0.5, 0.5, 1.0),
					storeOp = StoreOp.Store,
				)
			),
			depthStencilAttachment = DepthStencilAttachment(
				view = depthTexture.createView(),
				depthClearValue = 1.0f,
				depthLoadOp = LoadOp.Clear,
				depthStoreOp = StoreOp.Store
			)
		)


		val aspect = renderingContext.width.toDouble() / renderingContext.height.toDouble()
		val fox = Angle.fromRadians((2 * PI) / 5)
		projectionMatrix = Matrix4.perspective(fox, aspect, 1.0, 100.0)

		val step = 4.0
		var m = 0
		(0 until xCount).forEach { x ->
			(0 until yCount).forEach { y ->
				modelMatrices[m] = projectionMatrix
					.translated(
						step * (x - xCount / 2 + 0.5),
						step * (y - yCount / 2 + 0.5),
						-12.0
					)
				m += 1
			}
		}
	}

	override suspend fun AutoClosableContext.render() {

		val transformationMatrix = getTransformationMatrix(
			frame / 100.0,
			modelMatrices
		)
		device.queue.writeBuffer(
			uniformBuffer,
			0u,
			transformationMatrix,
			0u,
			transformationMatrix.size.toULong()
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
			setBindGroup(0u, uniformBindGroup)
			setVertexBuffer(0u, verticesBuffer)
			draw(Cube.cubeVertexCount, numInstances.toUInt(), 0u)
			end()
		}

		val commandBuffer = encoder.finish()
			.bind()

		device.queue.submit(listOf(commandBuffer))

	}

}

private fun getTransformationMatrix(angle: Double, modelMatrices: Array<Matrix4?>): FloatArray {
	val uniform = mutableListOf<FloatArray>()

	var m = 0
	(0 until xCount).forEach { x ->
		(0 until yCount).forEach { y ->
			modelMatrices[m]!!.rotated(
				Angle.fromRadians(Angle.fromRadians((x + 0.5) * angle).sine),
				Angle.fromRadians(Angle.fromRadians((x + 0.5) * angle).cosine),
				Angle.fromRadians(0)
			).copyToColumns()
				.let { uniform.add(it) }
			m += 1
		}
	}

	return uniform.flatMap { it.asList() }.toFloatArray()
}