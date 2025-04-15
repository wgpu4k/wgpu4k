package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUCompilationInfo
import io.ygdrasil.webgpu.GPUCompilationMessage
import io.ygdrasil.webgpu.GPUCompilationMessageType
import io.ygdrasil.wgpu.WGPUCompilationInfo
import io.ygdrasil.wgpu.WGPUCompilationMessage

internal fun map(input: WGPUCompilationInfo): GPUCompilationInfo = object : GPUCompilationInfo {
    override val messages: List<GPUCompilationMessage> = (0 until input.messageCount.toLong())
        // TODO right now, this only mapping the first element, this need to be rework
        .map { map(WGPUCompilationMessage(input.messages?.handler ?: error("message is null"))) }
}

internal fun map(input: WGPUCompilationMessage): GPUCompilationMessage = object : GPUCompilationMessage {
    override val message: String = input.message.data?.toKString(input.message.length) ?: error("message data is null")
    override val type: GPUCompilationMessageType =
        GPUCompilationMessageType.of(input.type) ?: error("unknown type: ${input.type}")
    override val lineNum: ULong = input.lineNum
    override val linePos: ULong = input.linePos
    override val offset: ULong = input.offset
    override val length: ULong = input.length
}