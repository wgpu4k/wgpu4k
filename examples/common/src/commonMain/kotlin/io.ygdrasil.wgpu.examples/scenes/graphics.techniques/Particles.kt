package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.autoClosableContext
import io.ygdrasil.wgpu.examples.scenes.shader.compute.probabilityMap
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.particlesShader
import kotlin.math.ceil


class ParticlesScene : Application.Scene() {

    val numParticles = 50000
    val particlePositionOffset = 0
    val particleColorOffset = 4 * 4
    val particleInstanceByteSize = 3 * 4 + // position
            1 * 4 + // lifetime
            4 * 4 + // color
            3 * 4 + // velocity
            1 * 4 + // padding
            0

    lateinit var renderPipeline: RenderPipeline

    override fun Application.initialiaze() = with(autoClosableContext) {

        val particlesBuffer = device.createBuffer(
            BufferDescriptor(
                size = (numParticles * particleInstanceByteSize).toLong(),
                usage = BufferUsage.vertex or BufferUsage.storage,
            )
        ).bind()

        val renderPipeline = device.createRenderPipeline(
            RenderPipelineDescriptor(
                vertex = RenderPipelineDescriptor.VertexState(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = particlesShader,
                        )
                    ).bind(),
                    buffers = arrayOf(
                        RenderPipelineDescriptor.VertexState.VertexBufferLayout
                            (
                            // instanced particles buffer
                            arrayStride = particleInstanceByteSize.toLong(),
                            stepMode = VertexStepMode.instance,
                            attributes = arrayOf(
                                RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
                                    // position
                                    shaderLocation = 0,
                                    offset = particlePositionOffset.toLong(),
                                    format = VertexFormat.float32x3,
                                ),
                                RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
                                    // color
                                    shaderLocation = 1,
                                    offset = particleColorOffset.toLong(),
                                    format = VertexFormat.float32x4,
                                ),
                            ),
                        ),
                        RenderPipelineDescriptor.VertexState.VertexBufferLayout(
                            // quad vertex buffer
                            arrayStride = 2 * 4, // vec2f
                            stepMode = VertexStepMode.vertex,
                            attributes = arrayOf(
                                RenderPipelineDescriptor.VertexState.VertexBufferLayout.VertexAttribute(
                                    // vertex positions
                                    shaderLocation = 2,
                                    offset = 0,
                                    format = VertexFormat.float32x2,
                                ),
                            ),
                        ),
                    ),
                ),
                fragment = RenderPipelineDescriptor.FragmentState(
                    module = device.createShaderModule(
                        ShaderModuleDescriptor(
                            code = particlesShader,
                        )
                    ).bind(),
                    targets = arrayOf(
                        RenderPipelineDescriptor.FragmentState.ColorTargetState(
                            format = renderingContext.textureFormat,
                            blend = RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState(
                                color = RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent(
                                    srcFactor = BlendFactor.srcalpha,
                                    dstFactor = BlendFactor.one,
                                    operation = BlendOperation.add,
                                ),
                                alpha = RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent(
                                    srcFactor = BlendFactor.zero,
                                    dstFactor = BlendFactor.one,
                                    operation = BlendOperation.add
                                ),
                            ),
                        ),
                    ),
                ),
                primitive = RenderPipelineDescriptor.PrimitiveState(
                    topology = PrimitiveTopology.trianglelist,
                ),

                depthStencil = RenderPipelineDescriptor.DepthStencilState(
                    depthWriteEnabled = false,
                    depthCompare = CompareFunction.less,
                    format = TextureFormat.depth24plus,
                ),
            )
        )

        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = GPUExtent3DDictStrict(renderingContext.width, renderingContext.height),
                format = TextureFormat.depth24plus,
                usage = TextureUsage.renderattachment.value,
            )
        )

        val uniformBufferSize = 4 * 4 * 4 + // modelViewProjectionMatrix : mat4x4f
                3 * 4 + // right : vec3f
                4 + // padding
                3 * 4 + // up : vec3f
                4 + // padding
                0;
        val uniformBuffer = device.createBuffer(
            BufferDescriptor(
                size = uniformBufferSize.toLong(),
                usage = BufferUsage.uniform or BufferUsage.copydst,
            )
        ).bind();

        val uniformBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = renderPipeline.getBindGroupLayout(0),
                entries = arrayOf(
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 0,
                        resource = BindGroupDescriptor.BufferBinding(
                            buffer = uniformBuffer,
                        ),
                    ),
                ),
            )
        ).bind()

        val renderPassDescriptor = RenderPassDescriptor(
            colorAttachments = arrayOf(
                RenderPassDescriptor.ColorAttachment(
                    view = dummyTexture.createView().bind(), // Assigned later
                    clearValue = arrayOf(0.0, 0.0, 0.0, 1.0),
                    loadOp = LoadOp.clear,
                    storeOp = StoreOp.store,
                ),
            ),
            depthStencilAttachment = RenderPassDescriptor.RenderPassDepthStencilAttachment(
                view = depthTexture.createView().bind(),

                depthClearValue = 1.0f,
                depthLoadOp = LoadOp.clear,
                depthStoreOp = StoreOp.store,
            )
        )

        //////////////////////////////////////////////////////////////////////////////
        // Quad vertex buffer
        //////////////////////////////////////////////////////////////////////////////
        val quadVertexBuffer = device.createBuffer(
            BufferDescriptor(
                size = 6 * 2 * 4, // 6x vec2f
                usage = BufferUsage.vertex.value,
                mappedAtCreation = true,
            )
        )

        val vertexData = arrayOf(
            -1.0, -1.0, +1.0, -1.0, -1.0, +1.0, -1.0, +1.0, +1.0, -1.0, +1.0, +1.0,
        ).let { FloatArray(it.size) { index -> it[index].toFloat() } }
        quadVertexBuffer.map(vertexData)
        quadVertexBuffer.unmap()

        //////////////////////////////////////////////////////////////////////////////
        // Texture
        //////////////////////////////////////////////////////////////////////////////
        var textureWidth = 1
        var textureHeight = 1
        var numMipLevels = 1

        val imageBitmap = Di3d

        // Calculate number of mip levels required to generate the probability map
        while (
            textureWidth < imageBitmap.width ||
            textureHeight < imageBitmap.height
        ) {
            textureWidth *= 2;
            textureHeight *= 2;
            numMipLevels++
        }
        val texture = device.createTexture(
            TextureDescriptor(
                size = GPUExtent3DDictStrict(imageBitmap.width, imageBitmap.height),
                mipLevelCount = numMipLevels,
                format = TextureFormat.rgba8unorm,
                usage =
                TextureUsage.texturebinding or TextureUsage.storagebinding or TextureUsage.copydst or TextureUsage.renderattachment,
            )
        )
        device.queue.copyExternalImageToTexture(
            ImageCopyExternalImage(source = imageBitmap),
            ImageCopyTextureTagged(texture = texture),
            imageBitmap.width to imageBitmap.height
        )


        //////////////////////////////////////////////////////////////////////////////
        // Probability map generation
        // The 0'th mip level of texture holds the color data and spawn-probability in
        // the alpha channel. The mip levels 1..N are generated to hold spawn
        // probabilities up to the top 1x1 mip level.
        //////////////////////////////////////////////////////////////////////////////
        val probabilityMapImportLevelPipeline = device.createComputePipeline(
            ComputePipelineDescriptor(
                compute = ComputePipelineDescriptor.ProgrammableStage(
                    module = device.createShaderModule(ShaderModuleDescriptor(code = probabilityMap)).bind(),
                    entryPoint = "import_level",
                ),
            )
        ).bind()


        val probabilityMapExportLevelPipeline = device.createComputePipeline(
            ComputePipelineDescriptor(
                compute = ComputePipelineDescriptor.ProgrammableStage(
                    module = device.createShaderModule(ShaderModuleDescriptor(code = probabilityMap)).bind(),
                    entryPoint = "export_level",
                ),
            )
        ).bind()

        val probabilityMapUBOBufferSize = 1 * 4 + // stride
                3 * 4 + // padding
                0

        val probabilityMapUBOBuffer = device.createBuffer(
            BufferDescriptor(
                size = probabilityMapUBOBufferSize.toLong(),
                usage = BufferUsage.uniform or BufferUsage.copydst,
            )
        )
        val buffer_a = device.createBuffer(
            BufferDescriptor(
                size = textureWidth * textureHeight * 4L,
                usage = BufferUsage.storage.value,
            )
        )
        val buffer_b = device.createBuffer(
            BufferDescriptor(
                size = textureWidth * textureHeight * 4L,
                usage = BufferUsage.storage.value,
            )
        )
        device.queue.writeBuffer(
            probabilityMapUBOBuffer,
            0,
            IntArray(1) { textureWidth }
        )

        val commandEncoder = device.createCommandEncoder()
        (0 until numMipLevels).forEach { level ->
            val levelWidth = textureWidth shr level
            val levelHeight = textureHeight shr level
            val pipeline = if (level == 0) probabilityMapImportLevelPipeline.getBindGroupLayout(0)
            else probabilityMapExportLevelPipeline.getBindGroupLayout(0)
            val probabilityMapBindGroup = device.createBindGroup(
                BindGroupDescriptor(
                    layout = pipeline,
                    entries = arrayOf(
                        BindGroupDescriptor.BindGroupEntry(
                            // ubo
                            binding = 0,
                            resource = BindGroupDescriptor.BufferBinding(buffer = probabilityMapUBOBuffer),
                        ),
                        BindGroupDescriptor.BindGroupEntry(
                            // buf_in
                            binding = 1,
                            resource = BindGroupDescriptor.BufferBinding(if (level and 1 != 0) buffer_a else buffer_b)
                        ),
                        BindGroupDescriptor.BindGroupEntry(
                            // buf_out
                            binding = 2,
                            resource = BindGroupDescriptor.BufferBinding(if (level and 1 != 0) buffer_b else buffer_a)
                        ),
                        BindGroupDescriptor.BindGroupEntry(
                            // tex_in / tex_out
                            binding = 3,
                            resource = BindGroupDescriptor.TextureViewBinding(
                                view = texture.createView(
                                    TextureViewDescriptor(
                                        format = TextureFormat.rgba8unorm,
                                        dimension = TextureViewDimension._2d,
                                        baseMipLevel = level,
                                        mipLevelCount = 1,
                                    )
                                ).bind()
                            ),
                        ),
                    ),
                )
            )


            if (level == 0) {
                val passEncoder = commandEncoder.beginComputePass()
                passEncoder.setPipeline(probabilityMapImportLevelPipeline)
                passEncoder.setBindGroup(0, probabilityMapBindGroup)
                passEncoder.dispatchWorkgroups(ceil(levelWidth / 64.0).toInt(), levelHeight, 0)
                passEncoder.end()
            } else {
                val passEncoder = commandEncoder.beginComputePass()
                passEncoder.setPipeline(probabilityMapExportLevelPipeline);
                passEncoder.setBindGroup(0, probabilityMapBindGroup);
                passEncoder.dispatchWorkgroups(ceil(levelWidth / 64.0).toInt(), levelHeight, 0)
                passEncoder.end()
            }
        }
        device.queue.submit(arrayOf(commandEncoder.finish()));
        //////////////////////////////////////////////////////////////////////////////
        // Simulation compute pipeline
        //////////////////////////////////////////////////////////////////////////////
        /*
        val simulationParams = {
  simulate= true,
  deltaTime= 0.04,
};

val simulationUBOBufferSize =
  1 * 4 + // deltaTime
  3 * 4 + // padding
  4 * 4 + // seed
  0;
val simulationUBOBuffer = device.createBuffer({
  size= simulationUBOBufferSize,
  usage= GPUBufferUsage.UNIFORM | GPUBufferUsage.COPY_DST,
});

val gui = new GUI();
gui.add(simulationParams, "simulate");
gui.add(simulationParams, "deltaTime");

val computePipeline = device.createComputePipeline({
  layout= "auto",
  compute= {
    module= device.createShaderModule({
      code= particleWGSL,
    }),
    entryPoint= "simulate",
  },
});
val computeBindGroup = device.createBindGroup({
  layout= computePipeline.getBindGroupLayout(0),
  entries= arrayOf(
    {
      binding= 0,
      resource= {
        buffer= simulationUBOBuffer,
      },
    },
    {
      binding= 1,
      resource= {
        buffer= particlesBuffer,
        offset= 0,
        size= numParticles * particleInstanceByteSize,
      },
    },
    {
      binding= 2,
      resource= texture.createView(),
    },
  ),
});

val aspect = canvas.width / canvas.height;
val projection = mat4.perspective((2 * Math.PI) / 5, aspect, 1, 100.0);
val view = mat4.create();
val mvp = mat4.create();
         */
    }

    override fun Application.render() = autoClosableContext {

        /*
        device.queue.writeBuffer(
    simulationUBOBuffer,
    0,
    new Float32Array(arrayOf(
      simulationParams.simulate ? simulationParams.deltaTime : 0.0,
      0.0,
      0.0,
      0.0, // padding
      Math.random() * 100,
      Math.random() * 100, // seed.xy
      1 + Math.random(),
      1 + Math.random(), // seed.zw
    ))
  );

  mat4.identity(view);
  mat4.translate(view, vec3.fromValues(0, 0, -3), view);
  mat4.rotateX(view, Math.PI * -0.2, view);
  mat4.multiply(projection, view, mvp);

  // prettier-ignore
  device.queue.writeBuffer(
    uniformBuffer,
    0,
    new Float32Array(arrayOf(
      // modelViewProjectionMatrix
      mvp[0], mvp[1], mvp[2], mvp[3],
      mvp[4], mvp[5], mvp[6], mvp[7],
      mvp[8], mvp[9], mvp[10], mvp[11],
      mvp[12], mvp[13], mvp[14], mvp[15],

      view[0], view[4], view[8], // right

      0, // padding

      view[1], view[5], view[9], // up

      0, // padding
    ))
  );
  val swapChainTexture = context.getCurrentTexture();
  // prettier-ignore
  renderPassDescriptor.colorAttachments[0].view = swapChainTexture.createView();

  val commandEncoder = device.createCommandEncoder();
  {
    val passEncoder = commandEncoder.beginComputePass();
    passEncoder.setPipeline(computePipeline);
    passEncoder.setBindGroup(0, computeBindGroup);
    passEncoder.dispatchWorkgroups(Math.ceil(numParticles / 64));
    passEncoder.end();
  }
  {
    val passEncoder = commandEncoder.beginRenderPass(renderPassDescriptor);
    passEncoder.setPipeline(renderPipeline);
    passEncoder.setBindGroup(0, uniformBindGroup);
    passEncoder.setVertexBuffer(0, particlesBuffer);
    passEncoder.setVertexBuffer(1, quadVertexBuffer);
    passEncoder.draw(6, numParticles, 0, 0);
    passEncoder.end();
  }

  device.queue.submit(arrayOf(commandEncoder.finish()));
         */

        // Clear the canvas with a render pass
        val encoder = device.createCommandEncoder()
            .bind()

        val texture = renderingContext.getCurrentTexture()
            .bind()

        val renderPassEncoder = encoder.beginRenderPass(
            RenderPassDescriptor(
                colorAttachments = arrayOf(
                    RenderPassDescriptor.ColorAttachment(
                        view = texture.createView().bind(),
                        loadOp = LoadOp.clear,
                        clearValue = arrayOf(0, 0, 0, 1.0),
                        storeOp = StoreOp.store
                    )
                )
            )
        ).bind()

        renderPassEncoder.setPipeline(renderPipeline)
        renderPassEncoder.draw(3)
        renderPassEncoder.end()

        val commandBuffer = encoder.finish()
            .bind()

        device.queue.submit(arrayOf(commandBuffer))

        renderingContext.present()
    }
}
