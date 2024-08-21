@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.internal.callIntMethodFrom
import io.ygdrasil.wgpu.internal.callObjectMethodFrom
import io.ygdrasil.wgpu.internal.callStringMethodFrom
import io.ygdrasil.wgpu.internal.toCString
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import platform.sles.jobject
import webgpu.WGPUTextureViewDescriptor

internal fun ArenaBase.mapTextureViewDescriptor(input: jobject, env: JNIEnvPointer)
= alloc<WGPUTextureViewDescriptor>().also { output ->
    output.label = env.callStringMethodFrom(input, "getLabel")?.toCString(env, this)
    env.callObjectMethodFrom(input, "getFormat", "io/ygdrasil/wgpu/TextureFormat")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt()?.let { output.format = it }
    env.callObjectMethodFrom(input, "getDimension", "io/ygdrasil/wgpu/TextureViewDimension")
        ?.let { stepMode -> env.callIntMethodFrom(stepMode, "getValue") }
        ?.toUInt()?.let { output.dimension = it }
    output.aspect = env.callIntMethodFrom(input, "getAspect").toUInt()
    output.baseMipLevel = env.callIntMethodFrom(input, "getBaseMipLevel").toUInt()
    output.mipLevelCount = env.callIntMethodFrom(input, "getMipLevelCount").toUInt()
    output.baseArrayLayer = env.callIntMethodFrom(input, "getBaseArrayLayer").toUInt()
    output.arrayLayerCount = env.callIntMethodFrom(input, "getArrayLayerCount").toUInt()
}