package io.ygdrasil.wgpu.internal.jvm

import com.sun.jna.Pointer
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CCodeGeneratorKtTest : FreeSpec({

    "log" {
        WGPURenderPipelineDescriptor().apply {
            label = "render_pipeline"
            layout = WGPUPipelineLayout(Pointer(1))
            vertex?.entryPoint = "vs_main"
        }.log() shouldBe """
             |&(const WGPURenderPipelineDescriptor){
             |    .nextInChain = NULL,
             |    .label = "render_pipeline",
             |    .layout = pipeline_layout,
             |    .vertex = 
             |        (const WGPUVertexState){
             |            .nextInChain = NULL,
             |            .module = shader_module,
             |            .entryPoint = "vs_main",
             |            .constantCount = 0,
             |            .constants = NULL,
             |            .bufferCount = 0,
             |            .buffersPtr = NULL,
             |        },
             |    .primitive = 
             |        (const WGPUPrimitiveState){
             |            .topology = WGPUPrimitiveTopology_TriangleList,
             |        },
             |    .depthStencil = NULL,
             |    .multisample = 
             |        (const WGPUMultisampleState){
             |            .count = 1,
             |            .mask = 0xFFFFFFFF,
             |        },
             |    .fragment = 
             |        &(const WGPUFragmentState){
             |            .module = shader_module,
             |            .entryPoint = "fs_main",
             |            .targetCount = 1,
             |            .targets =
             |                (const WGPUColorTargetState[]){
             |                    (const WGPUColorTargetState){
             |                        .format = surface_capabilities.formats[0],
             |                        .writeMask = WGPUColorWriteMask_All,
             |                    },
             |                },
             |        },
             |};
      """.trimMargin()

    }
})
