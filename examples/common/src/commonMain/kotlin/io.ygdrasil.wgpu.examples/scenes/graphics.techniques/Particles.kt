package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.autoClosableContext
import io.ygdrasil.wgpu.examples.scenes.shader.vertex.particlesShader


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

        /*
        {
  const probabilityMapImportLevelPipeline = device.createComputePipeline({
    layout: 'auto',
    compute: {
      module: device.createShaderModule({ code: probabilityMapWGSL }),
      entryPoint: 'import_level',
    },
  });
  const probabilityMapExportLevelPipeline = device.createComputePipeline({
    layout: 'auto',
    compute: {
      module: device.createShaderModule({ code: probabilityMapWGSL }),
      entryPoint: 'export_level',
    },
  });

  const probabilityMapUBOBufferSize =
    1 * 4 + // stride
    3 * 4 + // padding
    0;
  const probabilityMapUBOBuffer = device.createBuffer({
    size: probabilityMapUBOBufferSize,
    usage: GPUBufferUsage.UNIFORM | GPUBufferUsage.COPY_DST,
  });
  const buffer_a = device.createBuffer({
    size: textureWidth * textureHeight * 4,
    usage: GPUBufferUsage.STORAGE,
  });
  const buffer_b = device.createBuffer({
    size: textureWidth * textureHeight * 4,
    usage: GPUBufferUsage.STORAGE,
  });
  device.queue.writeBuffer(
    probabilityMapUBOBuffer,
    0,
    new Int32Array([textureWidth])
  );
  const commandEncoder = device.createCommandEncoder();
  for (let level = 0; level < numMipLevels; level++) {
    const levelWidth = textureWidth >> level;
    const levelHeight = textureHeight >> level;
    const pipeline =
      level == 0
        ? probabilityMapImportLevelPipeline.getBindGroupLayout(0)
        : probabilityMapExportLevelPipeline.getBindGroupLayout(0);
    const probabilityMapBindGroup = device.createBindGroup({
      layout: pipeline,
      entries: [
        {
          // ubo
          binding: 0,
          resource: { buffer: probabilityMapUBOBuffer },
        },
        {
          // buf_in
          binding: 1,
          resource: { buffer: level & 1 ? buffer_a : buffer_b },
        },
        {
          // buf_out
          binding: 2,
          resource: { buffer: level & 1 ? buffer_b : buffer_a },
        },
        {
          // tex_in / tex_out
          binding: 3,
          resource: texture.createView({
            format: 'rgba8unorm',
            dimension: '2d',
            baseMipLevel: level,
            mipLevelCount: 1,
          }),
        },
      ],
    });
    if (level == 0) {
      const passEncoder = commandEncoder.beginComputePass();
      passEncoder.setPipeline(probabilityMapImportLevelPipeline);
      passEncoder.setBindGroup(0, probabilityMapBindGroup);
      passEncoder.dispatchWorkgroups(Math.ceil(levelWidth / 64), levelHeight);
      passEncoder.end();
    } else {
      const passEncoder = commandEncoder.beginComputePass();
      passEncoder.setPipeline(probabilityMapExportLevelPipeline);
      passEncoder.setBindGroup(0, probabilityMapBindGroup);
      passEncoder.dispatchWorkgroups(Math.ceil(levelWidth / 64), levelHeight);
      passEncoder.end();
    }
  }
  device.queue.submit([commandEncoder.finish()]);
}

         */
        //////////////////////////////////////////////////////////////////////////////
        // Simulation compute pipeline
        //////////////////////////////////////////////////////////////////////////////
        /*
        const simulationParams = {
  simulate: true,
  deltaTime: 0.04,
};

const simulationUBOBufferSize =
  1 * 4 + // deltaTime
  3 * 4 + // padding
  4 * 4 + // seed
  0;
const simulationUBOBuffer = device.createBuffer({
  size: simulationUBOBufferSize,
  usage: GPUBufferUsage.UNIFORM | GPUBufferUsage.COPY_DST,
});

const gui = new GUI();
gui.add(simulationParams, 'simulate');
gui.add(simulationParams, 'deltaTime');

const computePipeline = device.createComputePipeline({
  layout: 'auto',
  compute: {
    module: device.createShaderModule({
      code: particleWGSL,
    }),
    entryPoint: 'simulate',
  },
});
const computeBindGroup = device.createBindGroup({
  layout: computePipeline.getBindGroupLayout(0),
  entries: [
    {
      binding: 0,
      resource: {
        buffer: simulationUBOBuffer,
      },
    },
    {
      binding: 1,
      resource: {
        buffer: particlesBuffer,
        offset: 0,
        size: numParticles * particleInstanceByteSize,
      },
    },
    {
      binding: 2,
      resource: texture.createView(),
    },
  ],
});

const aspect = canvas.width / canvas.height;
const projection = mat4.perspective((2 * Math.PI) / 5, aspect, 1, 100.0);
const view = mat4.create();
const mvp = mat4.create();
         */
    }

    override fun Application.render() = autoClosableContext {

        /*
        device.queue.writeBuffer(
    simulationUBOBuffer,
    0,
    new Float32Array([
      simulationParams.simulate ? simulationParams.deltaTime : 0.0,
      0.0,
      0.0,
      0.0, // padding
      Math.random() * 100,
      Math.random() * 100, // seed.xy
      1 + Math.random(),
      1 + Math.random(), // seed.zw
    ])
  );

  mat4.identity(view);
  mat4.translate(view, vec3.fromValues(0, 0, -3), view);
  mat4.rotateX(view, Math.PI * -0.2, view);
  mat4.multiply(projection, view, mvp);

  // prettier-ignore
  device.queue.writeBuffer(
    uniformBuffer,
    0,
    new Float32Array([
      // modelViewProjectionMatrix
      mvp[0], mvp[1], mvp[2], mvp[3],
      mvp[4], mvp[5], mvp[6], mvp[7],
      mvp[8], mvp[9], mvp[10], mvp[11],
      mvp[12], mvp[13], mvp[14], mvp[15],

      view[0], view[4], view[8], // right

      0, // padding

      view[1], view[5], view[9], // up

      0, // padding
    ])
  );
  const swapChainTexture = context.getCurrentTexture();
  // prettier-ignore
  renderPassDescriptor.colorAttachments[0].view = swapChainTexture.createView();

  const commandEncoder = device.createCommandEncoder();
  {
    const passEncoder = commandEncoder.beginComputePass();
    passEncoder.setPipeline(computePipeline);
    passEncoder.setBindGroup(0, computeBindGroup);
    passEncoder.dispatchWorkgroups(Math.ceil(numParticles / 64));
    passEncoder.end();
  }
  {
    const passEncoder = commandEncoder.beginRenderPass(renderPassDescriptor);
    passEncoder.setPipeline(renderPipeline);
    passEncoder.setBindGroup(0, uniformBindGroup);
    passEncoder.setVertexBuffer(0, particlesBuffer);
    passEncoder.setVertexBuffer(1, quadVertexBuffer);
    passEncoder.draw(6, numParticles, 0, 0);
    passEncoder.end();
  }

  device.queue.submit([commandEncoder.finish()]);
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
