package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.ComputePipelineDescriptor
import io.ygdrasil.wgpu.ComputePipelineDescriptor.ProgrammableStage
import io.ygdrasil.wgpu.PipelineLayout
import io.ygdrasil.wgpu.ShaderModule
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUComputePipelineDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUConstantEntry
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUProgrammableStageDescriptor
import java.lang.foreign.MemorySegment

class ComputePipelineDescriptorTest: FreeSpec({

    "test mapping" {

        // Given
        confined { arena ->
            val dummyMemory = MemorySegment.ofAddress(1)
            val bufferDescriptor = ComputePipelineDescriptor(
                label = "ComputePipelineDescriptor",
                layout = PipelineLayout(dummyMemory),
                compute = ProgrammableStage(
                    module = ShaderModule(dummyMemory),
                    entryPoint = "main",
                    constants = mapOf("key" to 10.0)
                )
            )

            // When
            val actual: MemorySegment = arena.map(bufferDescriptor)

            // Then
            WGPUComputePipelineDescriptor.label(actual).getString(0) shouldBe "ComputePipelineDescriptor"
            WGPUComputePipelineDescriptor.layout(actual) shouldBe dummyMemory
            WGPUComputePipelineDescriptor.compute(actual).also { compute ->
                WGPUProgrammableStageDescriptor.module(compute) shouldBe dummyMemory
                WGPUProgrammableStageDescriptor.entryPoint(compute).getString(0) shouldBe "main"
                WGPUProgrammableStageDescriptor.constantCount(compute) shouldBe 1
                WGPUProgrammableStageDescriptor.constants(compute).also { constants ->
                    WGPUConstantEntry.asSlice(constants, 0).also { constant ->
                        WGPUConstantEntry.key(constant).getString(0) shouldBe "key"
                        WGPUConstantEntry.value(constant) shouldBe 10.0
                    }
                }
            }
        }
    }
})