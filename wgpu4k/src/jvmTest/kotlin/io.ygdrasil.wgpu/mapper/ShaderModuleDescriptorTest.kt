package io.ygdrasil.wgpu.mapper

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.ShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUChainedStruct
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUShaderModuleCompilationHint
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUShaderModuleDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUShaderModuleWGSLDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h.WGPUSType_ShaderModuleWGSLDescriptor
import java.lang.foreign.MemorySegment

class ShaderModuleDescriptorTest : FreeSpec({


    "test mapping" {

        // Given
        confined { arena ->
            val shaderModuleDescriptor = ShaderModuleDescriptor(
                label = "ShaderModuleDescriptor",
                code = "Shader code",
                compilationHints = arrayOf(
                    ShaderModuleDescriptor.CompilationHint(
                        entryPoint = "main"
                    )
                )
            )

            // When
            val actual: MemorySegment = arena.map(shaderModuleDescriptor)

            // Then
            WGPUShaderModuleDescriptor.label(actual).getString(0) shouldBe "ShaderModuleDescriptor"
            WGPUShaderModuleDescriptor.nextInChain(actual).let { nextInChain ->
                WGPUShaderModuleWGSLDescriptor.code(nextInChain).getString(0) shouldBe "Shader code"
                WGPUShaderModuleWGSLDescriptor.chain(nextInChain).let { chain ->
                    WGPUChainedStruct.sType(chain) shouldBe WGPUSType_ShaderModuleWGSLDescriptor()
                }
            }

            WGPUShaderModuleDescriptor.hintCount(actual) shouldBe 1
            WGPUShaderModuleDescriptor.hints(actual).let { hints ->
                WGPUShaderModuleCompilationHint.asSlice(hints, 0).let { hint ->
                    WGPUShaderModuleCompilationHint.entryPoint(hint).getString(0) shouldBe "main"
                }
            }

        }

    }
})