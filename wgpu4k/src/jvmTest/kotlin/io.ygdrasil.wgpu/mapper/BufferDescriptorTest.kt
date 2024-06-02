package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.BufferUsage
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUBufferDescriptor
import java.lang.foreign.MemorySegment

class BufferDescriptorTest : FreeSpec({


    "test mapping" {

        // Given
        confined { arena ->
            val bufferDescriptor = BufferDescriptor(
                size = 40,
                usage = setOf(BufferUsage.vertex, BufferUsage.copydst),
                mappedAtCreation = true
            )

            // When
            val actual: MemorySegment = arena.map(bufferDescriptor)

            // Then
            WGPUBufferDescriptor.size(actual) shouldBe 40
            WGPUBufferDescriptor.usage(actual) shouldBe (BufferUsage.vertex or BufferUsage.copydst)
            WGPUBufferDescriptor.mappedAtCreation(actual) shouldBe 1
        }

    }
})