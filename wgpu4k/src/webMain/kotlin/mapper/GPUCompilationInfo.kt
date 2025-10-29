@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUCompilationInfo
import io.ygdrasil.webgpu.GPUCompilationMessage
import io.ygdrasil.webgpu.GPUCompilationMessageType
import io.ygdrasil.webgpu.WGPUCompilationInfo
import io.ygdrasil.webgpu.WGPUCompilationMessage
import io.ygdrasil.webgpu.map
import io.ygdrasil.webgpu.toULong
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.unsafeCast

internal fun map(input: WGPUCompilationInfo): GPUCompilationInfo = object : GPUCompilationInfo {
    override val messages: List<GPUCompilationMessage> = input.messages.map { map(it.unsafeCast<WGPUCompilationMessage>()) }
}

internal fun map(input: WGPUCompilationMessage): GPUCompilationMessage = object : GPUCompilationMessage {
    override val message: String = input.message
    override val type: GPUCompilationMessageType = GPUCompilationMessageType.of(input.type) ?: error("Unknown compilation message type: ${input.type}")
    override val lineNum: ULong = input.lineNum.toULong()
    override val linePos: ULong = input.linePos.toULong()
    override val offset: ULong = input.offset.toULong()
    override val length: ULong = input.length.toULong()
}