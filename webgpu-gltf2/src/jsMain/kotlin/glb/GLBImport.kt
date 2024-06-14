@file:OptIn(ExperimentalJsExport::class)

package glb

import GLBShaderCache
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.RenderPipelineDescriptor.FragmentState
import io.ygdrasil.wgpu.RenderPipelineDescriptor.VertexState.VertexBufferLayout
import io.ygdrasil.wgpu.examples.helper.GLTFRenderMode
import io.ygdrasil.wgpu.internal.js.GPUBindGroupLayout
import io.ygdrasil.wgpu.internal.js.GPUDevice
import io.ygdrasil.wgpu.internal.js.GPURenderBundleEncoder

@JsExport
class GLTFPrimitive(
    val indices: GLTFAccessor?,
    val positions: GLTFAccessor,
    val normals: GLTFAccessor?,
    val texcoords: Array<GLTFAccessor>,
    val material: GLTFMaterial,
    _topology: Int,
) {
    val topology: GLTFRenderMode = GLTFRenderMode.of(_topology) ?: error("fail to get topology")

    // Build the primitive render commands into the bundle
    fun buildRenderBundle(
        _device: GPUDevice,
        shaderCache: GLBShaderCache,
        bindGroupLayouts: Array<GPUBindGroupLayout>,
        _bundleEncoder: GPURenderBundleEncoder,
        swapChainFormat: String,
        depthFormat: String,
    ) {
        val device = Device(_device)
        val bundleEncoder = RenderBundleEncoder(_bundleEncoder)

        val shaderModule = shaderCache.getShader(
            normals != null,
            texcoords.size > 0,
            material.baseColorTexture != null
        )

        val vertexBuffers = mutableListOf(
            VertexBufferLayout(
                arrayStride = positions.byteStride.toLong(),
                attributes = arrayOf(
                    VertexBufferLayout.VertexAttribute(
                        format = VertexFormat.float32x3,
                        offset = 0,
                        shaderLocation = 0
                    )
                )
            )
        )

        if (normals != null) {
            vertexBuffers.add(
                VertexBufferLayout(
                    arrayStride = normals.byteStride.toLong(),
                    attributes = arrayOf(
                        VertexBufferLayout.VertexAttribute(
                            format = VertexFormat.float32x3,
                            offset = 0,
                            shaderLocation = 1
                        )
                    )
                )
            )
        }

        // TODO: Multi-texturing
        if (texcoords.size > 0) {
            vertexBuffers.add(
                VertexBufferLayout(
                    arrayStride = texcoords[0].byteStride.toLong(),
                    attributes = arrayOf(
                        VertexBufferLayout.VertexAttribute(
                            format = VertexFormat.float32x2,
                            offset = 0,
                            shaderLocation = 2
                        )
                    )
                )
            )
        }

        val layout = device.createPipelineLayout(
            PipelineLayoutDescriptor(
                bindGroupLayouts = arrayOf(
                    BindGroupLayout(bindGroupLayouts[0]),
                    BindGroupLayout(bindGroupLayouts[1]),
                    BindGroupLayout(material.bindGroupLayout)
                )
            )
        )

        var vertexStage = RenderPipelineDescriptor.VertexState(
            module = ShaderModule(shaderModule),
            entryPoint = "vertex_main",
            buffers = vertexBuffers.toTypedArray()
        )

        var fragmentStage = FragmentState(
            module = ShaderModule(shaderModule),
            entryPoint = "fragment_main",
            targets = arrayOf(
                FragmentState.ColorTargetState(
                    format = TextureFormat.of(swapChainFormat) ?: error("fail to get texture format $swapChainFormat")
                )
            ),
        )

        val primitive = if (topology == GLTFRenderMode.TRIANGLE_STRIP) {
            RenderPipelineDescriptor.PrimitiveState(
                topology = PrimitiveTopology.trianglestrip,
                stripIndexFormat = if (indices?.componentType == GLTFComponentType.UNSIGNED_SHORT.value) IndexFormat.uint16 else IndexFormat.uint32,
            )
        } else {
            RenderPipelineDescriptor.PrimitiveState(
                topology = PrimitiveTopology.trianglelist
            )
        }

        var pipelineDescriptor = RenderPipelineDescriptor(
            layout = layout,
            vertex = vertexStage,
            fragment = fragmentStage,
            primitive = primitive,
            depthStencil = RenderPipelineDescriptor.DepthStencilState(
                format = TextureFormat.of(depthFormat) ?: error("fail to get depth format $depthFormat"),
                depthWriteEnabled = true,
                depthCompare = CompareFunction.less
            )
        )

        var renderPipeline = device.createRenderPipeline(pipelineDescriptor)

        bundleEncoder.setBindGroup(2, BindGroup(material.bindGroup))
        bundleEncoder.setPipeline(renderPipeline)
        bundleEncoder.setVertexBuffer(
            0,
            positions.view.gpuBuffer,
            positions.byteOffset.toLong()
        )
        if (normals != null) {
            bundleEncoder.setVertexBuffer(
                1, normals.view.gpuBuffer, normals.byteOffset.toLong()
            )
        }
        if (texcoords.size > 0) {
            bundleEncoder.setVertexBuffer(
                2,
                texcoords[0].view.gpuBuffer,
                texcoords[0].byteOffset.toLong()
            )
        }
        if (indices != null) {
            val indexFormat = if(indices.componentType == GLTFComponentType.UNSIGNED_SHORT.value) IndexFormat.uint16 else IndexFormat.uint32

            bundleEncoder.setIndexBuffer(
                indices.view.gpuBuffer,
                indexFormat,
                indices.byteOffset.toLong()
            )
            bundleEncoder.drawIndexed(indices.count)
        } else {
            bundleEncoder.draw(positions.count)
        }
    }
}