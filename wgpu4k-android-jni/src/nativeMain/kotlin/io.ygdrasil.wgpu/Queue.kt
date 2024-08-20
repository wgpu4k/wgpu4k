@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JNIEnvPointer
import io.ygdrasil.wgpu.internal.JniList
import io.ygdrasil.wgpu.internal.callLongMethodFrom
import kotlinx.cinterop.CPointerVarOf
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.set
import kotlinx.cinterop.toCPointer
import platform.android.jclass
import platform.android.jlong
import platform.android.jobject
import kotlin.experimental.ExperimentalNativeApi

@CName("Java_io_ygdrasil_wgpu_internal_JniInterfaceV2_wgpuQueueSubmit")
fun wgpuQueueSubmit(env: JNIEnvPointer, thiz: jclass, handler: jlong, commandsBufferSize: jlong, commandsBuffer: jobject) =
    memScoped{
        val commands = if (commandsBufferSize > 0) {
            allocArray<CPointerVarOf<webgpu.WGPUCommandBuffer>>(commandsBufferSize).also { array ->
                JniList(commandsBuffer).also { commandsBuffer ->
                    repeat(commandsBufferSize.toInt()) { index ->
                        array[index] = commandsBuffer.getObject(index, env)
                            ?.let { env.callLongMethodFrom(it, "longValue") }
                            ?.toCPointer()
                            ?: error("fail to get commandsBuffer at index $index")
                    }
                }
            }
        } else {
            null
        }

        webgpu.wgpuQueueSubmit(handler.toCPointer(), commandsBufferSize.toULong(), commands)
    }