

package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Scene
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubePositionOffset
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeUVOffset
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexArray
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexCount
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexSize
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.vertexPositionColorShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.basicVertexShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI

class TwoCubesScene(wgpuContext: WGPUContext) : Scene(wgpuContext) {

	val offset = 256L; // uniformBindGroup offset must be 256-byte aligned

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
				size = (cubeVertexArray.size * Float.SIZE_BYTES).toLong(),
				usage = setOf(BufferUsage.vertex),
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

		val matrixSize = 4L * 16L; // 4x4 matrix
		val uniformBufferSize = offset + matrixSize;
		uniformBuffer = device.createBuffer(
			BufferDescriptor(
				size = uniformBufferSize,
				usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
			)
		).bind()

		uniformBindGroup1 = device.createBindGroup(
			BindGroupDescriptor(
				layout = renderPipeline.getBindGroupLayout(0),
				entries = arrayOf(
					BindGroupDescriptor.BindGroupEntry(
						binding = 0,
						resource = BindGroupDescriptor.BufferBinding(
							buffer = uniformBuffer,
							offset = 0,
							size = matrixSize
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
							offset = offset,
							size = matrixSize
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
		projectionMatrix1 = Matrix4.perspective(fox, aspect, 1.0, 100.0)
			.translated(-2.0, 0.0, -7.0)
		projectionMatrix2 = Matrix4.perspective(fox, aspect, 1.0, 100.0)
			.translated(2.0, 0.0, -7.0)
	}

	override fun AutoClosableContext.render() {

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