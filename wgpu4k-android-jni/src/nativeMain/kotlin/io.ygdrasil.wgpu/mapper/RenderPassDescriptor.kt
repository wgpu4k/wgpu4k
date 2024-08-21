@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.internal.callBooleanMethodFrom
import io.ygdrasil.wgpu.internal.callDoubleMethodFrom
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
import webgpu.WGPUColor
import webgpu.WGPURenderPassColorAttachment
import webgpu.WGPURenderPassDepthStencilAttachment
import webgpu.WGPURenderPassDescriptor
import kotlin.toUInt

internal fun ArenaBase.mapRenderPassDescriptor(input: jobject, env: JNIEnvPointer) =
    alloc<WGPURenderPassDescriptor>().also { output ->
        println("render pass descriptor $output")
        output.label = env.callStringMethodFrom(input, "getLabel")?.toCString(env, this)

        env.callListMethodFrom(input, "getColorAttachments")?.also { getColorAttachments ->
            getColorAttachments.getSize(env)
                .takeIf { it > 0 }
                ?.let { size ->
                    val colorAttachments = allocArray<WGPURenderPassColorAttachment>(size.toLong())
                    println("color attachments $colorAttachments")
                    repeat(size) { index ->
                        mapColorAttachment(
                            getColorAttachments.getObject(index, env)?.reinterpret() ?: error("fail to get object"),
                            colorAttachments[index],
                            env
                        )
                    }
                    output.colorAttachmentCount = size.toULong()
                    output.colorAttachments = colorAttachments
                }
        } ?: error("getColorAttachments should not be null")

        env.callObjectMethodFrom(input, "getDepthStencilAttachment", "io/ygdrasil/wgpu/RenderPassDescriptor\$DepthStencilAttachment")
            ?.let { depthStencilAttachment -> mapRenderPassDepthStencilAttachment(depthStencilAttachment, env) }
            ?.let { output.depthStencilAttachment = it.ptr }
        //TODO map this var occlusionQuerySet: GPUQuerySet?
        //TODO map this var timestampWrites: GPURenderPassTimestampWrites?
        //TODO map this var maxDrawCount: GPUSize64
        // check WGPURenderPassDescriptorMaxDrawCount
    }

private fun ArenaBase.mapColorAttachment(input: jobject, output: WGPURenderPassColorAttachment, env: JNIEnvPointer) {
    println("color attachment $output")
    output.view = env.callObjectMethodFrom(input, "getView", "io/ygdrasil/wgpu/TextureView")
        ?.let { module -> env.callLongMethodFrom(module, "getHandler") }
        ?.toCPointer()
    output.loadOp = env.callObjectMethodFrom(input, "getLoadOp", "io/ygdrasil/wgpu/LoadOp")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getLoadOp should not be null")
    output.storeOp = env.callObjectMethodFrom(input, "getStoreOp", "io/ygdrasil/wgpu/StoreOp")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt() ?: error("getStoreOp should not be null")
    // TODO find how to map this
    //if (input.depthSlice != null) WGPURenderPassColorAttachment.depthSlice(output, input.depthSlice)
    output.resolveTarget = env.callObjectMethodFrom(input, "getResolveTarget", "io/ygdrasil/wgpu/TextureView")
        ?.let { module -> env.callLongMethodFrom(module, "getHandler") }
        ?.toCPointer()
    mapColor(
        env.callObjectMethodFrom(input, "getClearValue", "io/ygdrasil/wgpu/Color")
            ?: error("getClearValue should not be null"),
        output.clearValue,
        env
    )
}

private fun mapColor(input: jobject, output: WGPUColor, env: JNIEnvPointer) {
    output.r = env.callDoubleMethodFrom(input, "getRed")
    output.g = env.callDoubleMethodFrom(input, "getGreen")
    output.b = env.callDoubleMethodFrom(input, "getBlue")
    output.a = env.callDoubleMethodFrom(input, "getAlpha")
}

private fun ArenaBase.mapRenderPassDepthStencilAttachment(input: jobject, env: JNIEnvPointer) =
    alloc<WGPURenderPassDepthStencilAttachment>().also { output ->
        output.view = env.callObjectMethodFrom(input, "getView", "io/ygdrasil/wgpu/TextureView")
            ?.let { module -> env.callLongMethodFrom(module, "getHandler") }
            ?.toCPointer()
        env.callObjectMethodFrom(input, "getView", "io/ygdrasil/wgpu/TextureView")
        env.callObjectMethodFrom(input, "getDepthWriteEnabled", "java/lang/Float")
            ?.let { depthClearValue -> output.depthClearValue = env.callFloatMethodFrom(depthClearValue, "floatValue") }
        env.callObjectMethodFrom(input, "getDepthLoadOp", "io/ygdrasil/wgpu/LoadOp")
            ?.let { depthLoadOp -> env.callIntMethodFrom(depthLoadOp, "getValue") }
            ?.toUInt()?.let { output.depthLoadOp = it }
        env.callObjectMethodFrom(input, "getDepthStoreOp", "io/ygdrasil/wgpu/StoreOp")
            ?.let { depthStoreOp -> env.callIntMethodFrom(depthStoreOp, "getValue") }
            ?.toUInt()?.let { output.depthStoreOp = it }

        output.depthReadOnly = env.callBooleanMethodFrom(input, "getDepthReadOnly").toUInt()
        output.stencilClearValue = env.callLongMethodFrom(input, "getStencilClearValue").toUInt()

        env.callObjectMethodFrom(input, "getStencilLoadOp", "io/ygdrasil/wgpu/LoadOp")
            ?.let { stencilLoadOp -> env.callIntMethodFrom(stencilLoadOp, "getValue") }
            ?.toUInt()?.let { output.stencilLoadOp = it }

        env.callObjectMethodFrom(input, "getStencilStoreOp", "io/ygdrasil/wgpu/StoreOp")
            ?.let { stencilStoreOp -> env.callIntMethodFrom(stencilStoreOp, "getValue") }
            ?.toUInt()?.let { output.stencilStoreOp = it }

        output.stencilReadOnly = env.callBooleanMethodFrom(input, "getStencilReadOnly").toUInt()
    }
