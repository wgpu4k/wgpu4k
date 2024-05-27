package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.ComputePassDescriptor
import io.ygdrasil.wgpu.QuerySet
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUComputePassDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUComputePassTimestampWrites
import java.lang.foreign.MemorySegment

class ComputePassDescriptorTest : FreeSpec({


    "test mapping" {

        // Given
        confined { arena ->
            val dummyMemory = MemorySegment.ofAddress(1)
            val bufferDescriptor = ComputePassDescriptor(
                label = "ComputePassDescriptor",
                timestampWrites = ComputePassDescriptor.ComputePassTimestampWrites(
                    QuerySet(dummyMemory),
                    beginningOfPassWriteIndex = 1,
                    endOfPassWriteIndex = 2
                )
            )

            // When
            val actual: MemorySegment = arena.map(bufferDescriptor)

            // Then
            WGPUComputePassDescriptor.label(actual).getString(0) shouldBe "ComputePassDescriptor"
            WGPUComputePassDescriptor.timestampWrites(actual).let { timestampWrites ->
                WGPUComputePassTimestampWrites.querySet(timestampWrites) shouldBe dummyMemory
                WGPUComputePassTimestampWrites.beginningOfPassWriteIndex(timestampWrites) shouldBe 1
                WGPUComputePassTimestampWrites.endOfPassWriteIndex(timestampWrites) shouldBe 2
            }
        }

    }
})