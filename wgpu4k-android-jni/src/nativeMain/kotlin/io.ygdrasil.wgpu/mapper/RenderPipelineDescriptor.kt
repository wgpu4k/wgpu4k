@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.internal.callBooleanMethodFrom
import io.ygdrasil.wgpu.internal.callFloatMethodFrom
import io.ygdrasil.wgpu.internal.callIntMethodFrom
import io.ygdrasil.wgpu.internal.callListMethodFrom
import io.ygdrasil.wgpu.internal.callLongMethodFrom
import io.ygdrasil.wgpu.internal.callObjectMethodFrom
import io.ygdrasil.wgpu.internal.callStringMethodFrom
import io.ygdrasil.wgpu.internal.toCString
import io.ygdrasil.wgpu.internal.toUInt
import kotlinx.cinterop.*
import platform.sles.jobject
import webgpu.*
import kotlin.let
import kotlin.toUInt

internal fun ArenaBase.mapRenderPipelineDescriptor(input: jobject, env: JNIEnvPointer) =
    alloc<WGPURenderPipelineDescriptor>().also { output ->
        println("mapRenderPipelineDescriptor")
        output.label = env.callStringMethodFrom(input, "getLabel")?.toCString(env, this)
        mapVertex(
            env.callObjectMethodFrom(input, "getVertex", "io/ygdrasil/wgpu/RenderPipelineDescriptor\$VertexState")
                ?: error("vertex should not be null"), output.vertex, env
        )
        output.layout = env.callObjectMethodFrom(input, "getLayout", "io/ygdrasil/wgpu/PipelineLayout")
            ?.let { layout -> env.callLongMethodFrom(layout, "getHandler") }
            ?.toCPointer()

        mapPrimitiveState(
            env.callObjectMethodFrom(
                input,
                "getPrimitive",
                "io/ygdrasil/wgpu/RenderPipelineDescriptor\$PrimitiveState"
            ) ?: error("primitive should not be null"), output.primitive, env
        )
        env.callObjectMethodFrom(input, "getDepthStencil", "io/ygdrasil/wgpu/RenderPipelineDescriptor\$DepthStencilState")?.let { depthStencil ->
            output.depthStencil = mapDepthStencilState(depthStencil, env).ptr
        }
        env.callObjectMethodFrom(input, "getFragment", "io/ygdrasil/wgpu/RenderPipelineDescriptor\$FragmentState")?.let { fragment ->
            output.fragment = mapFragment(fragment, env).ptr

        }
        mapMultisampleState(
            env.callObjectMethodFrom(
                input,
                "getMultisample",
                "io/ygdrasil/wgpu/RenderPipelineDescriptor\$MultisampleState"
            ) ?: error("multisample should not be null"), output.multisample, env)
        println("end mapRenderPipelineDescriptor")
    }

private fun ArenaBase.mapFragment(input: jobject, env: JNIEnvPointer): WGPUFragmentState =
    alloc<WGPUFragmentState>().also { output ->
        println("fragment $output")
        output.module = env.callObjectMethodFrom(input, "getModule", "io/ygdrasil/wgpu/ShaderModule")
            ?.let { module -> env.callLongMethodFrom(module, "getHandler") }
            ?.toCPointer()
        output.entryPoint = env.callStringMethodFrom(input, "getEntryPoint")?.toCString(env, this)

        env.callListMethodFrom(input, "getTargets")?.also { getTargets ->
            getTargets.getSize(env)
                .takeIf { it > 0 }
                ?.let { size ->
                    val targets = allocArray<WGPUColorTargetState>(size.toLong())
                    repeat(size) { index ->
                        mapColorTargetState(
                            getTargets.getObject(index, env)?.reinterpret() ?: error("fail to get object"),
                            targets[index],
                            env
                        )
                    }
                    output.targetCount = size.toULong()
                    output.targets = targets
                }
        } ?: error("getTargets should not be null")
    }

private fun ArenaBase.mapColorTargetState(input: jobject, output: WGPUColorTargetState, env: JNIEnvPointer) {
    println("colorTargetState $output")
    output.format = env.callObjectMethodFrom(input, "getFormat", "io/ygdrasil/wgpu/TextureFormat")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getFormat should not be null")
    output.writeMask = env.callObjectMethodFrom(input, "getWriteMask", "io/ygdrasil/wgpu/ColorWriteMask")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getWriteMask should not be null")
    output.blend = mapBlendState(
        env.callObjectMethodFrom(input, "getBlend", "io/ygdrasil/wgpu/RenderPipelineDescriptor\$FragmentState\$ColorTargetState\$BlendState") ?: error("fait to get BlendState"),
        env

    ).ptr
}

private fun ArenaBase.mapBlendState(input: jobject, env: JNIEnvPointer) =
    alloc<WGPUBlendState>().also { output ->
        println("blend state $output")
        mapBlendComponent(
            env.callObjectMethodFrom(input, "getColor", "io/ygdrasil/wgpu/RenderPipelineDescriptor\$FragmentState\$ColorTargetState\$BlendState\$BlendComponent") ?: error("fait to get BlendComponent"),
            output.color,
            env
        )
        mapBlendComponent(
            env.callObjectMethodFrom(input, "getAlpha", "io/ygdrasil/wgpu/RenderPipelineDescriptor\$FragmentState\$ColorTargetState\$BlendState\$BlendComponent") ?: error("fait to get BlendComponent"),
            output.alpha,
            env
        )
    }

private fun mapBlendComponent(
    input: jobject,
    output: WGPUBlendComponent,
    env: JNIEnvPointer
) {
    println("blend component $output")
    output.operation = env.callObjectMethodFrom(input, "getOperation", "io/ygdrasil/wgpu/BlendOperation")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getSrcFactor should not be null")
    output.srcFactor = env.callObjectMethodFrom(input, "getSrcFactor", "io/ygdrasil/wgpu/BlendFactor")
    ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getSrcFactor should not be null")
    output.dstFactor = env.callObjectMethodFrom(input, "getDstFactor", "io/ygdrasil/wgpu/BlendFactor")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getDstFactor should not be null")
}

private fun ArenaBase.mapDepthStencilState(input: jobject, env: JNIEnvPointer): WGPUDepthStencilState =
    alloc<WGPUDepthStencilState>()
        .also { output ->
            output.format = env.callObjectMethodFrom(input, "getFormat", "io/ygdrasil/wgpu/TextureFormat")
                ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
                ?.toUInt() ?: error("getFormat should not be null")
            env.callObjectMethodFrom(input, "getDepthWriteEnabled", "java/lang/Boolean")
                ?.let { depthWriteEnabled -> output.depthWriteEnabled = env.callBooleanMethodFrom(depthWriteEnabled, "booleanValue").toUInt() }
            env.callObjectMethodFrom(input, "getDepthCompare", "io/ygdrasil/wgpu/CompareFunction")
                ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
                ?.toUInt()?.let { output.depthCompare = it }
            mapStencilFaceState(
                env.callObjectMethodFrom(
                    input,
                    "getStencilFront",
                    "io/ygdrasil/wgpu/RenderPipelineDescriptor\$DepthStencilState\$StencilFaceState"
                ) ?: error("multisample should not be null"), output.stencilFront, env
            )
            mapStencilFaceState(
                env.callObjectMethodFrom(
                    input,
                    "getStencilBack",
                    "io/ygdrasil/wgpu/RenderPipelineDescriptor\$DepthStencilState\$StencilFaceState"
                ) ?: error("multisample should not be null"), output.stencilBack, env
            )
            output.stencilReadMask = env.callLongMethodFrom(input, "getStencilReadMask").toUInt()
            output.stencilWriteMask = env.callLongMethodFrom(input, "getStencilWriteMask").toUInt()
            output.depthBias = env.callIntMethodFrom(input, "getDepthBias")
            output.depthBiasSlopeScale = env.callFloatMethodFrom(input, "getDepthBiasSlopeScale")
            output.depthBiasClamp = env.callFloatMethodFrom(input, "getDepthBiasClamp")
        }

private fun mapStencilFaceState(input: jobject, output: WGPUStencilFaceState, env: JNIEnvPointer) {
    println("mapStencilFaceState")
    output.compare = env.callObjectMethodFrom(input, "getCompare", "io/ygdrasil/wgpu/CompareFunction")
    ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getCompare should not be null")
    output.failOp = env.callObjectMethodFrom(input, "getFailOp", "io/ygdrasil/wgpu/StencilOperation")
    ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getFailOp should not be null")
    output.depthFailOp = env.callObjectMethodFrom(input, "getDepthFailOp", "io/ygdrasil/wgpu/StencilOperation")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getDepthFailOp should not be null")
    output.passOp = env.callObjectMethodFrom(input, "getPassOp", "io/ygdrasil/wgpu/StencilOperation")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getPassOp should not be null")
}


private fun mapMultisampleState(input: jobject, output: WGPUMultisampleState, env: JNIEnvPointer) {
    println("mapMultisampleState")
    output.count = env.callIntMethodFrom(input, "getCount").toUInt()
    output.mask = env.callIntMethodFrom(input, "getMask-pVg5ArA").toUInt()
    output.alphaToCoverageEnabled = env.callBooleanMethodFrom(input, "getAlphaToCoverageEnabled").toUInt()
}

private fun mapPrimitiveState(
    input: jobject,
    output: WGPUPrimitiveState,
    env: JNIEnvPointer
) {
    println("mapPrimitiveState $output")
    output.topology = env.callObjectMethodFrom(input, "getTopology", "io/ygdrasil/wgpu/PrimitiveTopology")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getTopology should not be null")
    env.callObjectMethodFrom(input, "getStripIndexFormat", "io/ygdrasil/wgpu/IndexFormat")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt()?.let { output.stripIndexFormat = it }
    output.frontFace = env.callObjectMethodFrom(input, "getFrontFace", "io/ygdrasil/wgpu/FrontFace")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getFrontFace should not be null")
    output.cullMode = env.callObjectMethodFrom(input, "getCullMode", "io/ygdrasil/wgpu/CullMode")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getCullMode should not be null")
    println("done mapPrimitiveState")

    //TODO check how to map unclippedDepth https://docs.rs/wgpu/latest/wgpu/struct.PrimitiveState.html
}

private fun ArenaBase.mapVertex(input: jobject, output: WGPUVertexState, env: JNIEnvPointer) {
    println("mapVertex $output")
    output.entryPoint = env.callStringMethodFrom(input, "getEntryPoint")?.toCString(env, this)
    output.module = env.callObjectMethodFrom(input, "getModule", "io/ygdrasil/wgpu/ShaderModule")
        ?.let { module -> env.callLongMethodFrom(module, "getHandler") }
        ?.toCPointer()

    // TODO learn how to map this
    output.constants = null
    output.constantCount = 0uL

    env.callListMethodFrom(input, "getBuffers")?.also { compilationHints ->
        compilationHints.getSize(env)
            .takeIf { it > 0 }
            ?.let { size ->
                output.bufferCount = size.toULong()
                val buffers = allocArray<WGPUVertexBufferLayout>(size.toLong())
                println("buffers $buffers")
                repeat(size) { index ->
                    mapVertexBufferLayout(
                        compilationHints.getObject(
                            index,
                            env
                        )?.reinterpret() ?: error("fail to get object"),
                        buffers[index],
                        env
                    )
                }
                output.buffers = buffers
            }
    } ?: error("getBuffers should not be null")

}

private fun ArenaBase.mapVertexBufferLayout(
    input: jobject,
    output: WGPUVertexBufferLayout,
    env: JNIEnvPointer
) {
    println("mapVertexBufferLayout $output")
    output.arrayStride = env.callLongMethodFrom(input, "getArrayStride").toULong()

    env.callListMethodFrom(input, "getAttributes")?.also { compilationHints ->
        compilationHints.getSize(env)
            .takeIf { it > 0 }
            ?.let { size ->
                val attributes = allocArray<WGPUVertexAttribute>(size.toLong())
                println("attributes $attributes")
                repeat(size) { index ->
                    mapVertexAttribute(
                        compilationHints.getObject(
                            index,
                            env
                        )?.reinterpret() ?: error("fail to get object"),
                        attributes[index],
                        env
                    )
                }
                output.attributeCount = size.toULong()
                output.attributes = attributes
            }
    } ?: error("getAttributes should not be null")

    output.stepMode = env.callObjectMethodFrom(input, "getStepMode", "io/ygdrasil/wgpu/VertexStepMode")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("stepMode should not be null")
}

private fun mapVertexAttribute(
    input: jobject,
    output: WGPUVertexAttribute,
    env: JNIEnvPointer
) {
    println("mapVertexAttribute $output")
    output.format = env.callObjectMethodFrom(input, "getFormat", "io/ygdrasil/wgpu/VertexFormat")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("stepMode should not be null")
    output.offset = env.callLongMethodFrom(input, "getOffset").toULong()
    output.shaderLocation = env.callIntMethodFrom(input, "getShaderLocation").toUInt()
}

