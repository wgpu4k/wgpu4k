@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.internal.callBooleanMethodFrom
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
        /*
        if (input.depthStencil != null) output.depthStencil = map(input.depthStencil).ptr
        if (input.fragment != null) output.fragment = map(input.fragment).ptr*/
        mapMultisampleState(
            env.callObjectMethodFrom(
                input,
                "getMultisample",
                "io/ygdrasil/wgpu/RenderPipelineDescriptor\$MultisampleState"
            ) ?: error("multisample should not be null"), output.multisample, env)
        println("end mapRenderPipelineDescriptor")
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
                            env,
                            "io/ygdrasil/wgpu/RenderPipelineDescriptor/VertexState\$VertexBufferLayout"
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
                            env,
                            "io/ygdrasil/wgpu/RenderPipelineDescriptor/VertexState/VertexBufferLayout\$VertexAttribute"
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


/*
fun ArenaBase.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState, output: WGPUColorTargetState) {
    println("colorTargetState $output")
    output.format = input.format.uValue
    output.writeMask = input.writeMask.uValue
    output.blend = map(input.blend).ptr
}

fun ArenaBase.map(input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState) =
    alloc<WGPUBlendState>().also { output ->
        println("blend state $output")
        map(input.color, output.color)
        map(input.alpha, output.alpha)
    }

fun map(
    input: RenderPipelineDescriptor.FragmentState.ColorTargetState.BlendState.BlendComponent,
    output: WGPUBlendComponent
) {
    println("blend component $output")
    output.operation = input.operation.uValue
    output.srcFactor = input.srcFactor.uValue
    output.dstFactor = input.dstFactor.uValue
}

private fun ArenaBase.map(input: RenderPipelineDescriptor.FragmentState): WGPUFragmentState =
    alloc<WGPUFragmentState>().also { output ->
        println("fragment $output")
        output.module = input.module.handler
        output.entryPoint = input.entryPoint.cstr.getPointer(this)
        if (input.targets.isNotEmpty()) {
            output.targetCount = input.targets.size.toULong()
            val colorTargets = allocArray<WGPUColorTargetState>(input.targets.size)
            println("colorTargets $colorTargets")
            input.targets.forEachIndexed { index, colorTargetState ->
                map(colorTargetState, colorTargets[index])
            }
            output.targets = colorTargets
        }
    }

private fun ArenaBase.map(input: RenderPipelineDescriptor.DepthStencilState): WGPUDepthStencilState =
    alloc<WGPUDepthStencilState>()
        .also { output ->
            output.format = input.format.uValue
            if (input.depthWriteEnabled != null) output.depthWriteEnabled = input.depthWriteEnabled.toUInt()
            if (input.depthCompare != null) output.depthCompare = input.depthCompare.uValue
            map(input.stencilFront, output.stencilFront)
            map(input.stencilBack, output.stencilBack)
            output.stencilReadMask = input.stencilReadMask.toUInt()
            output.stencilWriteMask = input.stencilWriteMask.toUInt()
            output.depthBias = input.depthBias
            output.depthBiasSlopeScale = input.depthBiasSlopeScale
            output.depthBiasClamp = input.depthBiasClamp
        }

fun map(input: RenderPipelineDescriptor.DepthStencilState.StencilFaceState, output: WGPUStencilFaceState) {
    output.compare = input.compare.uValue
    output.failOp = input.failOp.uValue
    output.depthFailOp = input.depthFailOp.uValue
    output.passOp = input.passOp.uValue
}


*/
