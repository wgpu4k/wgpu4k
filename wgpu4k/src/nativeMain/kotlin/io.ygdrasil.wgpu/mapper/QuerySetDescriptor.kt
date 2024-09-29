@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.QuerySetDescriptor
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.WGPUQuerySetDescriptor

internal fun ArenaBase.map(input: QuerySetDescriptor): WGPUQuerySetDescriptor {
    TODO()
}