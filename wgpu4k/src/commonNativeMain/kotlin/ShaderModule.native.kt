package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUCompilationInfo
import io.ygdrasil.wgpu.WGPUCompilationInfoCallback
import io.ygdrasil.wgpu.WGPUCompilationInfoCallbackInfo
import io.ygdrasil.wgpu.WGPUCompilationInfoRequestStatus_Success
import io.ygdrasil.wgpu.WGPUCompilationMessage
import io.ygdrasil.wgpu.WGPUShaderModule
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuShaderModuleGetCompilationInfo
import io.ygdrasil.wgpu.wgpuShaderModuleRelease
import io.ygdrasil.wgpu.wgpuShaderModuleSetLabel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class ShaderModule(val handler: WGPUShaderModule) : GPUShaderModule {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuShaderModuleSetLabel(handler, newLabel)
        }

    actual override fun close() {
        wgpuShaderModuleRelease(handler)
    }

    actual override suspend fun getCompilationInfo(): Result<GPUCompilationInfo> = suspendCoroutine { continuation ->
        memoryScope { scope ->

            val callback = WGPUCompilationInfoCallback.allocate(scope) { status, info, userdata1, userdata2 ->
                continuation.resume(
                    when (status) {
                        WGPUCompilationInfoRequestStatus_Success -> when (info) {
                            null -> Result.failure(IllegalStateException("ComputePipeline is null"))
                            else -> Result.success(map(info))
                        }

                        else -> Result.failure(IllegalStateException("request CompilationInfo fail with status: $status}"))
                    }
                )
            }

            val callbackInfo = WGPUCompilationInfoCallbackInfo.allocate(scope).apply {
                this.callback = callback
                this.userdata2 = scope.bufferOfAddress(callback.handler).handler
            }

            wgpuShaderModuleGetCompilationInfo(handler, callbackInfo)
        }
    }
}

fun map(input: WGPUCompilationInfo): GPUCompilationInfo = object : GPUCompilationInfo {
    override val messages: List<GPUCompilationMessage> = (0 until input.messageCount.toLong())
        // TODO right now, this only mapping the first element, this need to be rework
        .map { map(WGPUCompilationMessage(input.messages?.handler ?: error("message is null"))) }
}

fun map(input: WGPUCompilationMessage): GPUCompilationMessage = object : GPUCompilationMessage {
    override val message: String = input.message.data?.toKString(input.message.length) ?: error("message data is null")
    override val type: GPUCompilationMessageType =
        GPUCompilationMessageType.of(input.type) ?: error("unknown type: ${input.type}")
    override val lineNum: ULong = input.lineNum
    override val linePos: ULong = input.linePos
    override val offset: ULong = input.offset
    override val length: ULong = input.length
}