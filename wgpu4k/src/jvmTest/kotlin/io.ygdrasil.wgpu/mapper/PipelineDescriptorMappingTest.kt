package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.*
import java.lang.foreign.MemorySegment

class PipelineDescriptorMappingTest : FreeSpec({

    val cubeVertexSize = 64L
    val cubePositionOffset = 16L
    val cubeUVOffset = 32L

    "test mapping" {
        confined { arena ->
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
            val result: MemorySegment? = arena.map(descriptor)


            // Then
            WGPURenderPipelineDescriptor.vertex(result) shouldNotBe MemorySegment.NULL
            WGPURenderPipelineDescriptor.vertex(result).also { vertex ->
                WGPUVertexState.module(vertex) shouldBe MemorySegment.NULL
                WGPUVertexState.entryPoint(vertex).getString(0) shouldBe "main"
                WGPUVertexState.bufferCount(vertex) shouldBe 1L
                WGPUVertexState.buffers(vertex).asSlice(0).let { buffer ->
                    WGPUVertexBufferLayout.arrayStride(buffer) shouldBe cubeVertexSize
                    WGPUVertexBufferLayout.attributeCount(buffer) shouldBe 2
                    WGPUVertexBufferLayout.attributes(vertex).also { attributes ->
                        //TODO fix this test
                        /*WGPUVertexAttribute.asSlice(attributes, 0).let { attribute ->
                            WGPUVertexAttribute.shaderLocation(attribute) shouldBe 0
                            WGPUVertexAttribute.offset(attribute) shouldBe cubePositionOffset
                            WGPUVertexAttribute.format(attribute) shouldBe VertexFormat.float32x4.value
                        }
                        WGPUVertexAttribute.asSlice(attributes, 1).let { attribute ->
                            WGPUVertexAttribute.shaderLocation(attribute) shouldBe 0
                            WGPUVertexAttribute.offset(attribute) shouldBe cubeUVOffset
                            WGPUVertexAttribute.format(attribute) shouldBe VertexFormat.float32x2.value
                        }*/
                    }
                }
            }

            WGPURenderPipelineDescriptor.fragment(result) shouldNotBe MemorySegment.NULL
            WGPURenderPipelineDescriptor.fragment(result)?.also { fragment ->
                WGPUFragmentState.module(fragment) shouldNotBe null
                WGPUFragmentState.entryPoint(fragment).getString(0) shouldBe "main"
                WGPUFragmentState.targetCount(fragment) shouldBe 1

                WGPUFragmentState.targets(fragment).asSlice(0).also { target ->
                    WGPUColorTargetState.format(target) shouldBe TextureFormat.rgba8unorm.value
                }
            }

            WGPURenderPipelineDescriptor.primitive(result) shouldNotBe MemorySegment.NULL
            WGPURenderPipelineDescriptor.primitive(result)?.also { primitive ->
                WGPUPrimitiveState.topology(primitive) shouldBe PrimitiveTopology.trianglelist.value
                WGPUPrimitiveState.cullMode(primitive) shouldBe CullMode.back.value
            }

            WGPURenderPipelineDescriptor.depthStencil(result) shouldNotBe MemorySegment.NULL
            WGPURenderPipelineDescriptor.depthStencil(result).also { depthStencil ->
                WGPUDepthStencilState.format(depthStencil) shouldBe TextureFormat.depth24plus.value
                WGPUDepthStencilState.depthWriteEnabled(depthStencil) shouldBe 1
                WGPUDepthStencilState.depthCompare(depthStencil) shouldBe CompareFunction.less.value
            }
        }

    }

})