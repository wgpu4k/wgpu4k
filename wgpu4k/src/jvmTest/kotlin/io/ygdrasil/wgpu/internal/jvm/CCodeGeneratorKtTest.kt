package io.ygdrasil.wgpu.internal.jvm

import com.sun.jna.Pointer
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ygdrasil.wgpu.ColorWriteMask
import io.ygdrasil.wgpu.PrimitiveTopology
import io.ygdrasil.wgpu.TextureFormat

class CCodeGeneratorKtTest : FreeSpec({

    val descriptor = WGPURenderPipelineDescriptor().apply {
        label = "render_pipeline"
        layout = WGPUPipelineLayout(Pointer(1))

        vertex = WGPUVertexState()
        vertex?.entryPoint = "vs_main"
        vertex?.module = WGPUShaderModuleImpl(Pointer(2))

        primitive = WGPUPrimitiveState()
        primitive?.topology = PrimitiveTopology.trianglelist.value

        multisample = WGPUMultisampleState()
        multisample?.count = 1
        multisample?.mask = 0xFFFFFFF

        fragment = WGPUFragmentState.ByReference()
        fragment?.module = WGPUShaderModuleImpl(Pointer(3))
        fragment?.entryPoint = "fs_main"
        fragment?.targets = arrayOf(
            WGPUColorTargetState.ByReference().apply {
                format = TextureFormat.depth24plus.value
                writeMask = ColorWriteMask.all.value
            }
        )

        write()
    }

    "log native" {
        shouldLogNative = true
        logNative {
            Triple(
                "createRenderPipelineDescriptor",
                listOf(descriptor, 1),
                WGPURenderPipelineDescriptor::class
            )
        }
        // TODO find how to test standard out
    }

    "log" {
        descriptor.log() shouldBe """
             |&(const WGPURenderPipelineDescriptor){
             |    .nextInChain = NULL,
             |    .label = "render_pipeline",
             |    .layout = pipelineLayoutImpl1,
             |    .vertex = 
             |        (const WGPUVertexState){
             |            .nextInChain = NULL,
             |            .module = shaderModuleImpl1,
             |            .entryPoint = "vs_main",
             |            .constantCount = NULL,
             |            .constantsPtr = NULL,
             |            .constants = NULL,
             |            .bufferCount = NULL,
             |            .buffersPtr = NULL,
             |            .buffers = NULL,
             |        },
             |    .primitive = 
             |        (const WGPUPrimitiveState){
             |            .nextInChain = NULL,
             |            .topology = 3,
             |            .stripIndexFormat = NULL,
             |            .frontFace = NULL,
             |            .cullMode = NULL,
             |        },
             |    .depthStencil = NULL,
             |    .multisample = 
             |        (const WGPUMultisampleState){
             |            .nextInChain = NULL,
             |            .count = 1,
             |            .mask = 268435455,
             |            .alphaToCoverageEnabled = NULL,
             |        },
             |    .fragment = 
             |        &(const WGPUFragmentState){
             |            .nextInChain = NULL,
             |            .module = shaderModuleImpl2,
             |            .entryPoint = "fs_main",
             |            .constantCount = 0,
             |            .constants = NULL,
             |            .targetCount = 1,
             |            .targetsPtr = Memory of 32 bytes,
             |            .targets = 
             |                (const WGPUColorTargetState[]){
             |                    (const WGPUColorTargetState){
             |                        .nextInChain = NULL,
             |                        .format = 40,
             |                        .blend = NULL,
             |                        .writeMask = 15,
             |                    }
             |                },
             |        },
             |}
      """.trimMargin()

    }
})
