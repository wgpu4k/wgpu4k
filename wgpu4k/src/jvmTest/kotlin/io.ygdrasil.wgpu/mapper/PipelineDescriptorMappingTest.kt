package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.WGPURenderPipelineDescriptor
import java.lang.foreign.MemorySegment

class PipelineDescriptorMappingTest : FreeSpec({

    val cubeVertexSize = 64L
    val cubePositionOffset = 16L
    val cubeUVOffset = 32L

    "test mapping" {
        // Given
        val descriptor = RenderPipelineDescriptor(
            vertex = RenderPipelineDescriptor.VertexState(
                module = ShaderModule(MemorySegment.NULL), // bind to autoClosableContext to release it later
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
                module = ShaderModule(MemorySegment.NULL),
                targets = arrayOf(
                    RenderPipelineDescriptor.FragmentState.ColorTargetState(
                        format = TextureFormat.rgba8unorm
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

        // When
        val result: WGPURenderPipelineDescriptor = renderPipelineDescriptorMapper.map<RenderPipelineDescriptor, WGPURenderPipelineDescriptor>(descriptor)
            // And simulate a write to a native memory
            .also { it.write() }

        // Then
        result.apply {
            vertex shouldNotBe null
            vertex?.apply {
                module shouldNotBe null
                entryPoint shouldBe "main"
                bufferCount?.toLong() shouldBe 1L
                buffers?.size shouldBe 1
                buffers?.get(0)?.apply {
                    arrayStride shouldBe cubeVertexSize
                    attributeCount?.toLong() shouldBe 2
                    attributes?.size shouldBe 2
                    attributes?.get(0)?.apply {
                        shaderLocation shouldBe 0
                        offset shouldBe cubePositionOffset
                        format shouldBe VertexFormat.float32x4.value
                    }
                    attributes?.get(1)?.apply {
                        shaderLocation shouldBe 1
                        offset shouldBe cubeUVOffset
                        format shouldBe VertexFormat.float32x2.value
                    }
                }
            }

            fragment shouldNotBe null
            fragment?.apply {
                module shouldNotBe null
                entryPoint shouldBe "main"
                targetCount?.toLong() shouldBe 1
                targets?.size shouldBe 1
                targets?.get(0)?.apply {
                    format shouldBe TextureFormat.rgba8unorm.value
                }
            }

            primitive shouldNotBe null
            primitive?.apply {
                topology shouldBe PrimitiveTopology.trianglelist.value
                cullMode shouldBe CullMode.back.value
            }

            depthStencil shouldNotBe null
            depthStencil?.apply {
                format shouldBe TextureFormat.depth24plus.value
                depthWriteEnabled shouldBe 1
                depthCompare shouldBe CompareFunction.less.value
            }
        }


    }

})