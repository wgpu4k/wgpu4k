

package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Scene
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.vertexPositionColorShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.instancedShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI

val xCount = 4
val yCount = 4
val numInstances = xCount * yCount

class InstancedCubeScene(wgpuContext: WGPUContext) : Scene(wgpuContext) {


	lateinit var renderPipeline: RenderPipeline
	lateinit var projectionMatrix: Matrix4
	lateinit var renderPassDescriptor: RenderPassDescriptor
	lateinit var uniformBuffer: Buffer
	lateinit var uniformBindGroup: BindGroup
	lateinit var verticesBuffer: Buffer
	val modelMatrices = Array<Matrix4?>(numInstances) { null }

	override suspend fun initialize() = with(autoClosableContext) {

		// Create a vertex buffer from the cube data.
		verticesBuffer = device.createBuffer(
			BufferDescriptor(
				size = (Cube.cubeVertexArray.size * Float.SIZE_BYTES).toLong(),
				usage = setOf(BufferUsage.vertex),
				mappedAtCreation = true
			)
		)

		// Util method to use getMappedRange
		verticesBuffer.mapFrom(Cube.cubeVertexArray)
		verticesBuffer.unmap()

		renderPipeline = device.createRenderPipeline(
			RenderPipelineDescriptor(
				vertex = RenderPipelineDescriptor.VertexState(
					module = device.createShaderModule(
						ShaderModuleDescriptor(
							code = instancedShader
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
							code = vertexPositionColorShader
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
					depthCompare = CompareFunction.less,
					format = TextureFormat.depth24plus
				)
			)
		).bind()

		val depthTexture = device.createTexture(
			TextureDescriptor(
				size = Size3D(renderingContext.width, renderingContext.height),
				format = TextureFormat.depth24plus,
				usage = setOf(TextureUsage.renderattachment),
			)
		).bind()

		val uniformBufferSize = numInstances * 4L * 16L; // 4x4 matrix
		uniformBuffer = device.createBuffer(
			BufferDescriptor(
				size = uniformBufferSize,
				usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
			)
		).bind()

		uniformBindGroup = device.createBindGroup(
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

		renderPassDescriptor = RenderPassDescriptor(
			colorAttachments = arrayOf(
				RenderPassDescriptor.ColorAttachment(
					view = dummyTexture.createView().bind(), // Assigned later
					loadOp = LoadOp.clear,
					clearValue = Color(0.5, 0.5, 0.5, 1.0),
					storeOp = StoreOp.store,
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

	override fun AutoClosableContext.render() {

		val transformationMatrix = getTransformationMatrix(
			frame / 100.0,
			modelMatrices
		)
		device.queue.writeBuffer(
			uniformBuffer,
			0,
			transformationMatrix,
			0,
			transformationMatrix.size.toLong()
		)

		renderPassDescriptor = renderPassDescriptor.copy(
			colorAttachments = arrayOf(
				renderPassDescriptor.colorAttachments[0].copy(
					view = renderingContext.getCurrentTexture()
						.bind()
						.createView()
				)
			)
		)


		val encoder = device.createCommandEncoder()
			.bind()

		val renderPassEncoder = encoder.beginRenderPass(renderPassDescriptor)
			.bind()
		renderPassEncoder.setPipeline(renderPipeline)
		renderPassEncoder.setBindGroup(0, uniformBindGroup)
		renderPassEncoder.setVertexBuffer(0, verticesBuffer)
		renderPassEncoder.draw(Cube.cubeVertexCount, numInstances)
		renderPassEncoder.end()

		val commandBuffer = encoder.finish()
			.bind()

		device.queue.submit(arrayOf(commandBuffer))

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