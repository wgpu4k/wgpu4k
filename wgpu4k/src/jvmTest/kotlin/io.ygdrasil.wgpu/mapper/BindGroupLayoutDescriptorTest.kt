package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry.*
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.*
import java.lang.foreign.MemorySegment


class BindGroupLayoutDescriptorTest : FreeSpec({

    "test mapping" {

        // Given
        confined { arena ->
            val bufferDescriptor = BindGroupLayoutDescriptor(
                label = "BindGroupLayoutDescriptor",
                entries = arrayOf(
                    BindGroupLayoutDescriptor.Entry(
                        binding = 0,
                        visibility = setOf(ShaderStage.vertex),
                        bindingType = BufferBindingLayout(
                            hasDynamicOffset = true,
                            minBindingSize = 2
                        )
                    ),
                    BindGroupLayoutDescriptor.Entry(
                        binding = 1,
                        visibility = setOf(ShaderStage.fragment),
                        bindingType = SamplerBindingLayout()
                    ),
                    BindGroupLayoutDescriptor.Entry(
                        binding = 2,
                        visibility = setOf(ShaderStage.compute),
                        bindingType = TextureBindingLayout()
                    ),
                    BindGroupLayoutDescriptor.Entry(
                        binding = 3,
                        visibility = setOf(ShaderStage.vertex, ShaderStage.fragment),
                        bindingType = StorageTextureBindingLayout(
                            format = TextureFormat.depth24plus
                        )
                    ),
                )
            )

            // When
            val actual: MemorySegment = arena.map(bufferDescriptor)

            // Then
            WGPUBindGroupLayoutDescriptor.label(actual).getString(0) shouldBe "BindGroupLayoutDescriptor"
            WGPUBindGroupLayoutDescriptor.entryCount(actual) shouldBe 4
            WGPUBindGroupLayoutDescriptor.entries(actual).let { entries ->
                WGPUBindGroupLayoutEntry.asSlice(entries, 0).let { entry ->
                    WGPUBindGroupLayoutEntry.binding(entry) shouldBe 0
                    WGPUBindGroupLayoutEntry.visibility(entry) shouldBe ShaderStage.vertex.value
                    WGPUBindGroupLayoutEntry.buffer(entry).let { buffer ->
                        WGPUBufferBindingLayout.nextInChain(buffer) shouldNotBe MemorySegment.NULL
                        WGPUBufferBindingLayout.type(buffer) shouldBe BufferBindingType.uniform.value
                        WGPUBufferBindingLayout.hasDynamicOffset(buffer) shouldBe 1
                        WGPUBufferBindingLayout.minBindingSize(buffer) shouldBe 2
                    }
                }

                WGPUBindGroupLayoutEntry.asSlice(entries, 1).let { entry ->
                    WGPUBindGroupLayoutEntry.binding(entry) shouldBe 1
                    WGPUBindGroupLayoutEntry.visibility(entry) shouldBe ShaderStage.fragment.value
                    WGPUBindGroupLayoutEntry.sampler(entry).let { sampler ->
                        WGPUSamplerBindingLayout.nextInChain(sampler) shouldNotBe MemorySegment.NULL
                        WGPUSamplerBindingLayout.type(sampler) shouldBe SamplerBindingType.filtering.value
                    }
                }

                WGPUBindGroupLayoutEntry.asSlice(entries, 2).let { entry ->
                    WGPUBindGroupLayoutEntry.binding(entry) shouldBe 2
                    WGPUBindGroupLayoutEntry.visibility(entry) shouldBe ShaderStage.compute.value
                    WGPUBindGroupLayoutEntry.texture(entry).let { texture ->
                        WGPUTextureBindingLayout.nextInChain(texture) shouldNotBe MemorySegment.NULL
                        WGPUTextureBindingLayout.sampleType(texture) shouldBe TextureSampleType.float.value
                        WGPUTextureBindingLayout.viewDimension(texture) shouldBe TextureViewDimension._2d.value
                        WGPUTextureBindingLayout.multisampled(texture) shouldBe 0
                    }
                }

                WGPUBindGroupLayoutEntry.asSlice(entries, 3).let { entry ->
                    WGPUBindGroupLayoutEntry.binding(entry) shouldBe 3
                    WGPUBindGroupLayoutEntry.visibility(entry) shouldBe (ShaderStage.vertex or ShaderStage.fragment)
                    WGPUBindGroupLayoutEntry.storageTexture(entry).let { storageTexture ->
                        WGPUStorageTextureBindingLayout.nextInChain(storageTexture) shouldNotBe MemorySegment.NULL
                        WGPUStorageTextureBindingLayout.format(storageTexture) shouldBe TextureFormat.depth24plus.value
                        WGPUStorageTextureBindingLayout.access(storageTexture) shouldBe StorageTextureAccess.writeonly.value
                        WGPUStorageTextureBindingLayout.viewDimension(storageTexture) shouldBe TextureViewDimension._2d.value
                    }
                }
            }

        }

    }
})