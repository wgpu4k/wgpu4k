

package io.ygdrasil.wgpu.examples.scenes.basic

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.AssetManager
import io.ygdrasil.wgpu.examples.Scene
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubePositionOffset
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeUVOffset
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexArray
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexCount
import io.ygdrasil.wgpu.examples.scenes.mesh.Cube.cubeVertexSize
import io.ygdrasil.wgpu.examples.scenes.shader.fragment.sampleCubemapShader
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.basicVertexShader
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlin.math.PI

class CubemapScene(wgpuContext: WGPUContext, assetManager: AssetManager) : Scene(wgpuContext), AssetManager by assetManager {

	lateinit var renderPipeline: RenderPipeline
	lateinit var projectionMatrix: Matrix4
	lateinit var renderPassDescriptor: RenderPassDescriptor
	lateinit var uniformBuffer: Buffer
	lateinit var uniformBindGroup: BindGroup
	lateinit var verticesBuffer: Buffer

	val modelMatrix = Matrix4.scale(1000, 1000, 1000)
	val depthLayer = 6

	override suspend fun initialize() = with(autoClosableContext) {

		// Create a vertex buffer from the cube data.
		verticesBuffer = device.createBuffer(
			BufferDescriptor(
				size = (cubeVertexArray.size * Float.SIZE_BYTES).toLong(),
				usage = setOf(BufferUsage.vertex),
				mappedAtCreation = true
			)
		)

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
							code = sampleCubemapShader
						)
					).bind(), // bind to autoClosableContext to release it later
					targets = listOf(
						RenderPipelineDescriptor.FragmentState.ColorTargetState(
							format = renderingContext.textureFormat
						)
					)
				),
				primitive = RenderPipelineDescriptor.PrimitiveState(
					topology = PrimitiveTopology.trianglelist,
					cullMode = CullMode.none
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

		val imageBitmaps = listOf(
			cubemapPosx,
			cubemapNegx,
			cubemapPosy,
			cubemapNegy,
			cubemapPosz,
			cubemapNegz,
		)

		val cubemapTexture = device.createTexture(
			TextureDescriptor(
				// Create a 2d array texture.
				// Assume each image has the same size.
				size = Size3D(imageBitmaps[0].width, imageBitmaps[0].height, depthLayer),
				format = TextureFormat.rgba8unormsrgb,
				usage = setOf(TextureUsage.texturebinding, TextureUsage.copydst, TextureUsage.renderattachment),
			)
		).bind()

		imageBitmaps.forEachIndexed { index, imageBitmap ->
			device.queue.copyExternalImageToTexture(
				ImageCopyExternalImage(source = imageBitmap),
				ImageCopyTextureTagged(texture = cubemapTexture, origin = Origin3D(0, 0, index)),
				imageBitmap.width to imageBitmap.height
			)
		}

		val uniformBufferSize = 4L * 16L // 4x4 matrix
		uniformBuffer = device.createBuffer(
			BufferDescriptor(
				size = uniformBufferSize,
				usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
			)
		).bind()

		val sampler = device.createSampler(
			SamplerDescriptor(
				magFilter = FilterMode.linear,
				minFilter = FilterMode.linear,
			)
		).bind()


		uniformBindGroup = device.createBindGroup(
			BindGroupDescriptor(
				layout = renderPipeline.getBindGroupLayout(0),
				entries = listOf(
					BindGroupDescriptor.BindGroupEntry(
						binding = 0,
						resource = BindGroupDescriptor.BufferBinding(
							buffer = uniformBuffer,
							offset = 0,
							size = uniformBufferSize
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
							view = cubemapTexture.createView(
								TextureViewDescriptor(
									dimension = TextureViewDimension.cube,
									arrayLayerCount = depthLayer
								)
							)
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
		projectionMatrix = Matrix4.perspective(fox, aspect, 1.0, 3000.0)


	}

	override fun AutoClosableContext.render() {

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
		renderPassEncoder.draw(cubeVertexCount)
		renderPassEncoder.end()

		val commandBuffer = encoder.finish()
			.bind()

		device.queue.submit(arrayOf(commandBuffer))
	}

	override fun close() {
		autoClosableContext.close()
	}

	fun getTransformationMatrix(angle: Double, projectionMatrix: Matrix4): FloatArray {

		var viewMatrix = Matrix4.IDENTITY
		viewMatrix *= Matrix4.rotation(
			angle = Angle.fromRadians((PI / 10) * Angle.fromRadians(angle).sine),
			x = 1, y = 0, z = 0
		)
		viewMatrix *= Matrix4.rotation(
			angle = Angle.fromRadians(angle * 0.2),
			x = 0, y = 1, z = 0
		)
		val modelViewProjectionMatrix = viewMatrix * modelMatrix

		return (projectionMatrix * modelViewProjectionMatrix).copyToColumns()
	}
}


