package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.BindGroupDescriptor.BufferBinding
import io.ygdrasil.wgpu.BindGroupDescriptor.SamplerBinding
import io.ygdrasil.wgpu.BindGroupDescriptor.TextureViewBinding
import io.ygdrasil.wgpu.BindGroupLayout
import io.ygdrasil.wgpu.Buffer
import io.ygdrasil.wgpu.Sampler
import io.ygdrasil.wgpu.TextureView
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUBindGroupDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUBindGroupEntry
import java.lang.foreign.MemorySegment

class BindGroupDescriptorTest : FreeSpec({

    "test mapping" {

        // Given
        confined { arena ->
            val dummyMemory = MemorySegment.ofAddress(1)
            val bindGroupDescriptor = BindGroupDescriptor(
                label = "bindGroupDescriptor",
                layout = BindGroupLayout(dummyMemory),
                entries = arrayOf(
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 1,
                        resource = BufferBinding(
                            buffer = Buffer(dummyMemory),
                            offset = 20,
                            size = 40
                        )
                    ),
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 2,
                        resource = TextureViewBinding(
                            view = TextureView(dummyMemory)
                        )
                    ),
                    BindGroupDescriptor.BindGroupEntry(
                        binding = 3,
                        resource = SamplerBinding(
                            sampler = Sampler(dummyMemory)
                        )
                    )
                )
            )

            // When
            var result: MemorySegment = arena.map(bindGroupDescriptor)

            // Then
            WGPUBindGroupDescriptor.label(result).getString(0) shouldBe "bindGroupDescriptor"
            WGPUBindGroupDescriptor.layout(result) shouldBe dummyMemory
            WGPUBindGroupDescriptor.entryCount(result) shouldBe 3
            WGPUBindGroupDescriptor.entries(result).let { entries ->
                WGPUBindGroupEntry.asSlice(entries, 0).let { entry ->
                    WGPUBindGroupEntry.buffer(entry) shouldBe dummyMemory
                    WGPUBindGroupEntry.offset(entry) shouldBe 20
                    WGPUBindGroupEntry.size(entry) shouldBe 40
                }

                WGPUBindGroupEntry.asSlice(entries, 1).let { entry ->
                    WGPUBindGroupEntry.binding(entry) shouldBe 2
                    WGPUBindGroupEntry.textureView(entry) shouldBe dummyMemory
                }

                WGPUBindGroupEntry.asSlice(entries, 2).let { entry ->
                    WGPUBindGroupEntry.binding(entry) shouldBe 3
                    WGPUBindGroupEntry.sampler(entry) shouldBe dummyMemory
                }
            }
        }
    }

})